package Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import javax.xml.bind.DatatypeConverter;

public class PasswordUtil {
 public static String hashPasswordPlusSalt(String password, String salt)
 throws NoSuchAlgorithmException {
 MessageDigest md = MessageDigest.getInstance("SHA-256");
 String passwordAndSalt = password + salt;
 
 md.update(passwordAndSalt.getBytes());
 byte[] mdArray = md.digest();
 StringBuilder sb = new StringBuilder(mdArray.length * 2);
 for (byte b:mdArray){
 int v = b & 0xff;
 if (v < 16){
 sb.append('0');
 }
 sb.append(Integer.toHexString(v));
}
 return sb.toString();
 }
 public static String getSalt() {
 Random r = new SecureRandom();
 byte[] saltBytes = new byte[32];
 r.nextBytes(saltBytes);
 
 String encoded = DatatypeConverter.printBase64Binary(saltBytes);
 return encoded;
 }
 public static boolean compareSaltedHashWithUserEnteredPwd(
 String salt, String userEnteredPwd, String expectedHash) 
 throws NoSuchAlgorithmException{
 boolean isMatch = false;
 if (expectedHash.equals(hashPasswordPlusSalt(userEnteredPwd,salt))){
 isMatch = true;
 }
 
 return isMatch;
 }
}