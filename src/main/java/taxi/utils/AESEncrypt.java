package taxi.utils;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.*;


/*
 * For encryption we must use a secret key along with an algorithm. 
 * In the following code we use an algorithm called AES 128 and the bytes of the word 
 * "TheBestSecretKey" as the secret key (the best secret key we found in this world). 
 * AES algorithm can use a key of 128 bits (16 bytes * 8); so we selected that key.
 * 
 * We use "generateKey()" method to generate a secret key for AES algorithm with a given key. 
 * Below is the code how you can use the above encryption algorithm.
 */

public class AESEncrypt {
    
    private static final String ALGO = "AES";
    private static final byte[] keyValue = 
        new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };


public static String encrypt(String Data) throws Exception {
        try {
        	Key key = generateKey();
        	Cipher c = Cipher.getInstance(ALGO);
        	c.init(Cipher.ENCRYPT_MODE, key);
        	byte[] encVal = c.doFinal(Data.getBytes());
        	String encryptedValue = new BASE64Encoder().encode(encVal);
        	return encryptedValue;
        }
        catch (Exception e){
        	System.out.println(e.getStackTrace());
        	return Data;
        }
    }

    public static String decrypt(String encryptedData) throws Exception {
    	try {
	        Key key = generateKey();
	        Cipher c = Cipher.getInstance(ALGO);
	        c.init(Cipher.DECRYPT_MODE, key);
	        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
	        byte[] decValue = c.doFinal(decordedValue);
	        String decryptedValue = new String(decValue);
	        return decryptedValue;
    	}
        catch (Exception e){
        	System.out.println(e.getStackTrace());
        	return encryptedData;
        }
    }
    
    
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
}

}
