package fr.liglab.adele.rondo.infra.deployment.processor.impl;

import fr.liglab.adele.rondo.infra.deployment.DeploymentException;
import fr.liglab.adele.rondo.infra.deployment.processor.DefaultDeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.processor.DefaultResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.processor.ResourceProcessor;
import fr.liglab.adele.rondo.infra.deployment.processor.ResourceState;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentParticipant;
import fr.liglab.adele.rondo.infra.deployment.transaction.DeploymentTransaction;
import fr.liglab.adele.rondo.infra.deployment.util.DeploymentUtils;
import fr.liglab.adele.rondo.infra.model.File;
import fr.liglab.adele.rondo.infra.model.ResourceDeclaration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.felix.ipojo.annotations.*;
import org.apache.felix.ipojo.everest.services.EverestService;
import org.osgi.framework.BundleContext;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 5/5/13
 * Time: 9:59 PM
 */
@Component
@Instantiate
@Provides(specifications = {ResourceProcessor.class})
public class FileProcessor extends DefaultResourceProcessor {

    @Requires(optional = false)
    public EverestService m_everest;

    @ServiceProperty(name = "resource.type", value = "fr.liglab.adele.rondo.infra.model.File")
    public final String m_resourceType = "fr.liglab.adele.rondo.infra.model.File";

    BundleContext m_context;

    public FileProcessor(BundleContext context) {
        super();
        this.m_context = context;
    }

    @Override
    public DeploymentParticipant process(ResourceDeclaration resource, DeploymentTransaction transaction) throws DeploymentException {
        return new FileParticipant(resource, transaction);
    }

    private class FileParticipant extends DefaultDeploymentParticipant {

        File m_fileDef;

        private java.io.File cacheDirectory = null;

        private java.io.File sourceFile = null;

        private java.io.File pathFile = null;

        ResourceState m_initialFile;

        public FileParticipant(ResourceDeclaration resource, DeploymentTransaction transaction) throws DeploymentException {
            super(resource,transaction);
            try {
                this.m_fileDef = (File) resource;
            } catch (Exception e) {
                throw new DeploymentException("Received resource " + resource.name() + " is not of type: " + m_resourceType);
            }
        }

        @Override
        public void prepare() throws DeploymentException {

            URL sourceUrl=null;
            if(m_fileDef.path()==null){ // fail fast
                throw new DeploymentException("A target path is mandatory");
            }
            pathFile = new java.io.File(m_fileDef.path());
            if(m_fileDef.source()!=null){
                try {
                    sourceUrl = new URL(m_fileDef.source());
                } catch (MalformedURLException e) {
                    throw new DeploymentException("Error handling url : "+m_fileDef.source()+" : "+e.getMessage());
                }
            }

            // prepare cache directory
            java.io.File directory = (java.io.File) this.get("working.dir");
            cacheDirectory = new java.io.File(directory, "file-" + m_fileDef.name());
            cacheDirectory.mkdirs();

            // if there is a file take it into the cache
            if(pathFile.isFile() && pathFile.exists()){
                String name = pathFile.getName();
                java.io.File cachedFile = new java.io.File(cacheDirectory,name);
                try {
                    FileUtils.copyFile(pathFile,cachedFile);
                } catch (IOException e) {
                    throw new DeploymentException("Error on taking cache of the existing file: "+pathFile.getName());
                }
                m_initialFile = new ResourceState(cachedFile,null);
            } else if(sourceUrl==null){
                throw new DeploymentException("Target file does not exist and source url not found");
            }

            // check the source file
            if(sourceUrl!=null){
                String fileName = calculateFileName(sourceUrl);
                sourceFile = new java.io.File(cacheDirectory, fileName);
                try {
                    IOUtils.copy(sourceUrl.openStream(), new FileOutputStream(sourceFile));
                    //verify checksum if it is set in properties
                    //TODO find a way to take checksum algo as parameter. SHA1, SHA-256,..
                    if (m_fileDef.properties().containsKey("checksum")) {
                        Object checksum = m_fileDef.properties().get("checksum");
                        try {
                            String fileChecksum = DeploymentUtils.checksum(sourceFile, "SHA1");
                            if (!fileChecksum.equals(checksum)) {
                                throw new DeploymentException("Checksum failed : found: " + fileChecksum + ", expected: " + checksum);
                            }
                        } catch (NoSuchAlgorithmException e) {
                            // log
                            System.out.println("Failed to find the checksum algorithm " + "SHA1");
                        }
                    }
                } catch (IOException e) {
                    throw new DeploymentException("Cannot get resource file from " + m_fileDef.source()+" : "+e.getMessage());
                }
            }

        }

        @Override
        public void commit() throws DeploymentException {

            if(!((pathFile!=null && pathFile.exists()))){
                try {
                    FileUtils.copyFile(sourceFile,pathFile);
                } catch (IOException e) {
                    throw new DeploymentException("Error on ");
                }
            }
            try{
                boolean b = true;
                if(m_fileDef.readable()!=null){
                    b = pathFile.setReadable(m_fileDef.readable());
                }
                if(m_fileDef.executable()!=null){
                    b = (b && pathFile.setExecutable(m_fileDef.executable()));
                }
                if(m_fileDef.writable()!=null){
                    b = (b && pathFile.setWritable(m_fileDef.writable()));
                }
                if(!b){
                    throw new DeploymentException("Cannot apply rights changes on file "+pathFile.getName());
                }

                // TODO SET OWNER SET
                //
            } catch (SecurityException e){
                throw new DeploymentException("Operation did not allowed "+ e.getMessage());
            }
        }

        @Override
        public void cleanup() {
            try {
                FileUtils.deleteDirectory(cacheDirectory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void rollback() {
            try {
                if(m_initialFile==null){
                    FileUtils.forceDelete(pathFile);
                } else {
                    FileUtils.copyFile(m_initialFile.getFile(),pathFile);
                }
            } catch (IOException e) {
                System.out.println("failed to roll back "+m_fileDef.id());
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        private String calculateFileName(URL url) {
            String urlString = url.toString();
            // lets look if url finishes with a filename
            String fileName = urlString.substring(urlString.lastIndexOf('/') + 1, urlString.length());
            return fileName;
        }

    }
}
