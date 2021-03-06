package Final;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class NewClass {

    static String IV = "AAAAAAAAAAAAAAAA";
    
    static String plaintext = "test text 123 : Gurpeet";          /*Note null padding*/

    static String encryptionKey = "0123456789abcdef";

    public static void main(String[] args) {

        try {

            System.out.println("Plain:   " + plaintext);

            byte[] cipher = encrypt(plaintext, encryptionKey);

            System.out.print("Cipher:  ");
            for (int i = 0; i < cipher.length; i++) {
                System.out.print(Integer.toString(cipher[i]));
            }

            String decrypted = decrypt(cipher, encryptionKey);

            System.out.println("\nDecrypt: " + decrypted);

        } catch (Exception e) {
           
            e.printStackTrace();
        
        }
    }

    public static byte[] encrypt(String plainText, String encryptionKey) throws Exception {

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
        return cipher.doFinal(plainText.getBytes("UTF-8"));

    }

    public static String decrypt(byte[] cipherText, String encryptionKey) throws Exception {

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
        return new String(cipher.doFinal(cipherText), "UTF-8");

    }
}
