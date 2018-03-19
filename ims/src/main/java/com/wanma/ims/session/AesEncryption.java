package com.wanma.ims.session;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class AesEncryption {
    private String secretKey;

    public AesEncryption(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String serialize(String data) {
        try {
            byte[] bytes = data.getBytes("UTF-8");

            Key aesKey = new SecretKeySpec(secretKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(bytes);

            return new String(Base64.encodeBase64(encrypted));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String deserialize(String string) {
        try {
            byte[] encrypted = Base64.decodeBase64(string);

            Key aesKey = new SecretKeySpec(secretKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            byte[] bytes = cipher.doFinal(encrypted);

            return new String(bytes, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
