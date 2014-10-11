package crypto;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class CMSAES {
	static final String ALGO = "AES";
	static String charset = "UTF-8";

	public static void main(String[] args) throws UnsupportedEncodingException {
		byte[] cipher = encrypt("kkk跳进长江eee".getBytes(charset), "kkk");
		 
	      System.out.print("cipher:  ");
	      for (int i=0; i<cipher.length; i++)
	        System.out.print(new Integer(cipher[i])+" ");
	      System.out.println("");
	 
	      byte[] decrypted = decrypt(cipher, "kkk");
	 
	      System.out.println("decrypt: " + new String(decrypted,charset));
	}
	
	public static byte[] encrypt(byte[] byteContent, String keyString) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance(ALGO);
			kgen.init(128, new SecureRandom(keyString.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, ALGO);
			Cipher cipher = Cipher.getInstance(ALGO);// 创建密码器
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] decrypt(byte[] content, String keyString) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance(ALGO);
			kgen.init(128, new SecureRandom(keyString.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, ALGO);
			Cipher cipher = Cipher.getInstance(ALGO);// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
