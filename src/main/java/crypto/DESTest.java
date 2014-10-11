package crypto;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class DESTest {

	// public static void main(String[] args) throws Exception {
	// String key = "80f28a1ef4aa9df6ee2ee3210316b98f383eb344";
	// System.out.println(key.length());
	// // key = "10806220100.............";
	// // System.out.println(key.length());
	//
	// // Init the key
	// DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
	// SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	// Key secretKey = keyFactory.generateSecret(desKeySpec);
	//
	// Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
	// cipher.init(Cipher.DECRYPT_MODE, secretKey);
	//
	// byte[] buf = new byte[1024];
	// InputStream input = new FileInputStream(new File("enc.txt"));
	// FileOutputStream output = new FileOutputStream(new File("dec.txt"));
	//
	// int count = input.read(buf);
	//
	// // Read and decrypt file content
	// while (count >= 0) {
	// output.write(cipher.update(buf, 0, count));
	// count = input.read(buf);
	// }
	// output.write(cipher.doFinal());
	// output.flush();
	//
	// }

	public static void main(String[] argv) {
		try {
			KeyGenerator keygen = KeyGenerator.getInstance("DES");
			SecretKey desKey = keygen.generateKey();
			Cipher desCipher;
			desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			desCipher.init(Cipher.ENCRYPT_MODE, desKey);
			byte[] text = "No body can see me".getBytes();
			System.out.println("Test [Byte Format]: " + text);
			System.out.println("Text: " + new String(text));
			byte[] textEncrypted = desCipher.doFinal(text);
			System.out.println("Encrypted Text: " + textEncrypted);
			desCipher.init(Cipher.DECRYPT_MODE, desKey);
			byte[] textDecrypted = desCipher.doFinal(textEncrypted);
			System.out.println("Encrypted Text: " + textDecrypted);
		} catch (Exception e) {
		}
	}

}
