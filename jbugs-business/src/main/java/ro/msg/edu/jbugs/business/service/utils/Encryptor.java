package ro.msg.edu.jbugs.business.service.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.msg.edu.jbugs.business.dto.helper.CommentDTOHelper;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class Encryptor {

    private static final String KEY = "Bar12345Bar12345";
    private static Logger logger = LogManager.getLogger(Encryptor.class);

    private Encryptor() {

    }

    public static String encrypt(String toEncrypt) {
        String encryptedString = null;
        try {
            // Create key and cipher
            Key aesKey = new SecretKeySpec(KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(toEncrypt.getBytes());
            encryptedString = new String(encrypted);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return encryptedString;
    }

    public static String decrypt(String encrypted) {
        String originalString = null;
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);

            byte[] original = cipher.doFinal(encrypted.getBytes());
            originalString = new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return originalString;
    }
}

