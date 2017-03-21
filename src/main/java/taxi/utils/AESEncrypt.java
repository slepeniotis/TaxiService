package taxi.utils;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.*;


/**The AESEncrypt class implements the encryption and decryption with AES 128 algorithm.
 * <p>
 * For encryption a secret key must be used along with the algorithm. 
 * In this implementation of the algorithm, the bytes of the word "TheBestSecretKey" are used 
 * as the secret key (the best secret key found in this world).
 * <p> 
 * AES algorithm can use a key of 128 bits (16 bytes * 8).
 * <p>
 * @author  Team 4
 * @since   Academic Year 2016-2017 
 */

public class AESEncrypt {

	private static final String ALGO = "AES";
	private static final byte[] keyValue = 
			new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };


	/**<h2>encrypt method</h2>
	 * This method implements the encryption of a string using AES 128 algorithm.<p>
	 * @param Data type String
	 * @return String
	 */	
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

	/**<h2>decrypt method</h2>
	 * This method implements the decryption of a string which was before encrypted using the encrypt method.<p>
	 * @param encryptedData type String
	 * @return String
	 */	
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

	/**<h2>generateKey method</h2>
	 * This method is used to generate a secret key for AES algorithm with a given key. <p>
	 * @return Key
	 */	
	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, ALGO);
		return key;
	}

}
