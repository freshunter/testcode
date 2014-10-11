package crypto;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
 
public class AES {
//	  static String IV = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
	  static String IV = "AAAAAAAAAAAAAAAA";
  static String plaintextlong = "test text 123. AES ÖÐÎÄ²âÊÔ"; /*Note null padding*/
  static String plaintext = "test text 123\0\0\0"; /*Note null padding*/
  static String encryptionKey = "0123456789abcdef";
  public static void main(String [] args) {
    try {
      
      System.out.println("==Java==");
      System.out.println("plain:   " + plaintext);
      System.out.println("plain long:   " + plaintextlong );
      String longtmp =  plaintextlong;
      while(longtmp.length() > 16) {
    	  plaintext = longtmp.substring(0, 16);
    	  longtmp = longtmp.substring(16);
          endePrint(plaintext);
      }
      while(longtmp.length() % 8 != 0) {
    		  longtmp += "\0";
      }
      endePrint(longtmp);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }

private static void endePrint(String plaintext) throws Exception {
	byte[] cipher = encrypt(plaintext, encryptionKey);
 
      System.out.print("cipher:  ");
      for (int i=0; i<cipher.length; i++)
        System.out.print(new Integer(cipher[i])+" ");
      System.out.println("");
 
      String decrypted = decrypt(cipher, encryptionKey);
 
      System.out.println("decrypt: " + decrypted);
}
 
  public static byte[] encrypt(String plainText, String encryptionKey) throws Exception {
    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
    SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
    cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
    System.out.println("bit length:" + plainText.getBytes("UTF-8").length);
    return cipher.doFinal(plainText.getBytes("UTF-8"));
  }
 
  public static String decrypt(byte[] cipherText, String encryptionKey) throws Exception{
    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
    SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
    cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
    return new String(cipher.doFinal(cipherText),"UTF-8");
  }
}