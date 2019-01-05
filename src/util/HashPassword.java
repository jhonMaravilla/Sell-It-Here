package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.xml.bind.DatatypeConverter;

public class HashPassword {
	public static String getSalt() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		return DatatypeConverter.printHexBinary(salt);		
	}
	
	public static String getHashPassword(String password, String salt) {
		String hashedPassword = null; 
		try {
			// Create MessageDigest instance for SHA-256
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			// add salt
			md.update(DatatypeConverter.parseHexBinary(salt));
			// add password and generate hash bytes 
			byte[] digest = md.digest(password.getBytes());
			// convert to Base64 schema
			hashedPassword = DatatypeConverter.printHexBinary(digest);	
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hashedPassword;
	}
	
}
