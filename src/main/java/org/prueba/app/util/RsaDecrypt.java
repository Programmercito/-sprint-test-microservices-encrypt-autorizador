package org.prueba.app.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Programmercito <devtecpro.org>
 */
public class RsaDecrypt {

    private File getResourceFile(final String fileName) {
        URL url = this.getClass()
                .getClassLoader()
                .getResource(fileName);
        if (url == null) {
            throw new IllegalArgumentException(fileName + " is not found 1");
        }
        File file = new File(url.getFile());
        return file;
    }

    public PublicKey getPublicKey(String filename) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        // reading from resource folder
        byte[] privateKeyBytes = FileUtils.readFileToByteArray(this.getResourceFile(filename));
        String uso = new String(privateKeyBytes);
        uso = uso.replace("-----BEGIN PUBLIC KEY-----", "")
                .replaceAll("\n", "")
                .replace("-----END PUBLIC KEY-----", "");
        byte[] decode = Base64.getDecoder().decode(uso);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decode);
        return keyFactory.generatePublic(keySpec);

    }

    public PrivateKey getPrivateKey(String filename) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        // reading from resource folder
        byte[] privateKeyBytes = FileUtils.readFileToByteArray(this.getResourceFile(filename));
        String uso = new String(privateKeyBytes);
        uso = uso.replace("-----BEGIN PRIVATE KEY-----", "")
                .replaceAll("\n", "")
                .replace("-----END PRIVATE KEY-----", "");
        byte[] decode = Base64.getDecoder().decode(uso);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decode);
        return keyFactory.generatePrivate(keySpec);

    }

    public String decrypt(String content, PrivateKey privateKey) {
        byte[] resul = this.decrypt(content.getBytes(), privateKey);
        String re = new String(resul);
        return re;
    }

    public byte[] decrypt(byte[] content, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] enc(byte[] content, PublicKey publi) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publi);
            return cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
