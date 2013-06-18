package fr.liglab.adele.rondo.infra.deployment.processor;

import org.apache.felix.ipojo.everest.impl.DefaultRequest;
import org.apache.felix.ipojo.everest.impl.ImmutableResourceMetadata;
import org.apache.felix.ipojo.everest.services.*;

import java.io.File;

/**
 *
 */
public class ResourceState {

    private final ResourceMetadata resourceState;
    private final File bundleFile;
    private final Path path;

    public ResourceState(File initialBundleFile, Resource initialResource) {
        this.bundleFile = initialBundleFile;
        this.path = initialResource==null ? null : initialResource.getCanonicalPath();
        this.resourceState = initialResource==null ? null : new ImmutableResourceMetadata.Builder(initialResource.getMetadata()).build();
    }

    public File getFile() {
        return bundleFile;
    }

    public Path getPath() {
        return path;
    }

    public ResourceMetadata getResourceState() {
        return resourceState;
    }

    public Resource getResourceUsing(EverestService everest){
        try {
            Resource resource = everest.process(new DefaultRequest(Action.READ, this.getPath(), null));
            for (String key : resourceState.keySet()) {
                if(resource.getMetadata().containsKey(key)){
                    if(!resourceState.get(key).equals(resource.getMetadata().get(key))){
                        return null;
                    }
                }
            }
            return resource;
        } catch (IllegalActionOnResourceException e) {
            return null;
        } catch (ResourceNotFoundException e) {
            return null;
        } catch (NullPointerException e){
            return null;
        }
    }

}
