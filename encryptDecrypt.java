import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import org.apache.commons.codec.binary.Base64;
import java.security.*;

public class encryptDecrypt {

    public static String encrypt(String word) throws Exception {
        //Derive Key
        if (word != null){
            byte[] ivBytes;
            String password = "Hello";

            SecureRandom random = new SecureRandom();
            byte bytes[] = new byte[20];
            random.nextBytes(bytes);
            byte[] saltBytes = bytes;

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, 65556, 256);

            SecretKey secretKey = factory.generateSecret(spec);
            SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");

            //Encrypting
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secret);
            AlgorithmParameters params = cipher.getParameters();
            ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();

            byte[] encryptedTextBytes = cipher.doFinal(word.getBytes("UTF-8"));

            //prepend salt and iv
            byte[] buffer = new byte[saltBytes.length + ivBytes.length + encryptedTextBytes.length];

            System.arraycopy(saltBytes, 0, buffer, 0, saltBytes.length);
            System.arraycopy(ivBytes, 0, buffer, saltBytes.length, ivBytes.length);

            System.arraycopy(encryptedTextBytes, 0, buffer, saltBytes.length + ivBytes.length, encryptedTextBytes.length);

            return new Base64().encodeToString(buffer);
        }
        else {
            return null;
        }
    }

    public static String decrypt(String encryptedText) throws Exception {
        if (encryptedText != null){
            String password = "Hello";
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            //strip salt and iv
            ByteBuffer buffer = ByteBuffer.wrap(new Base64().decode(encryptedText));

            byte[] saltBytes = new byte[20];
            buffer.get(saltBytes, 0, saltBytes.length);
            byte[] ivBytes1 = new byte[cipher.getBlockSize()];
            buffer.get(ivBytes1, 0, ivBytes1.length);
            byte[] encryptedTextBytes = new byte[buffer.capacity() - saltBytes.length - ivBytes1.length];

            buffer.get(encryptedTextBytes);

            //Deriving the key
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, 65556, 256);

            SecretKey secretKey = factory.generateSecret(spec);
            SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");

            cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(ivBytes1));

            byte[] decryptedTextBytes = null;

            try {
                decryptedTextBytes = cipher.doFinal(encryptedTextBytes);
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            }

            return new String(decryptedTextBytes);
        }
        else {
            return null;
        }
    }

}
