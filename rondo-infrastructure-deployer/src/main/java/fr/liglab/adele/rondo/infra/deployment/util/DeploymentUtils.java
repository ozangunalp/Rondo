package fr.liglab.adele.rondo.infra.deployment.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * User: ozan
 * Date: 5/8/13
 * Time: 3:11 PM
 */
public class DeploymentUtils {

    public static String checksum(File file, String algo) throws NoSuchAlgorithmException, IOException {
        MessageDigest sha = MessageDigest.getInstance(algo);
        FileInputStream fis = new FileInputStream(file);

        byte[] data = new byte[1024];
        int read = 0;
        while ((read = fis.read(data)) != -1) {
            sha.update(data, 0, read);
        }
        ;
        byte[] hashBytes = sha.digest();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < hashBytes.length; i++) {
            sb.append(Integer.toString((hashBytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        String fileHash = sb.toString();
        return fileHash;
    }
}
