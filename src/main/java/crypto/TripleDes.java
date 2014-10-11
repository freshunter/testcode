package crypto;

import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;

public class TripleDes {

    private static final String Algorithm = "DESede/CBC/PKCS5Padding";

    // keybyte len 24

    // src
    public static byte[] encryptMode(String iv, String key, String src) {

	try {
	    byte[] keybyte = key.getBytes();
	    byte[] rand = new byte[8];
	    rand = iv.getBytes();
	    IvParameterSpec ivp = new IvParameterSpec(rand);
	    DESedeKeySpec dks = new DESedeKeySpec(keybyte);
	    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
	    SecretKey securekey = keyFactory.generateSecret(dks);

	    Cipher c1 = Cipher.getInstance(Algorithm);

	    c1.init(Cipher.ENCRYPT_MODE, securekey, ivp);

	    return c1.doFinal(src.getBytes());

	} catch (java.security.NoSuchAlgorithmException e1) {

	    // TODO: handle exception

	    e1.printStackTrace();

	} catch (javax.crypto.NoSuchPaddingException e2) {

	    e2.printStackTrace();

	} catch (java.lang.Exception e3) {

	    e3.printStackTrace();

	}

	return null;

    }

    // keybyte lenth 24

    // src

    public static byte[] decryptMode(String iv, String key, byte[] src) {

	try {
	    byte[] srcbytes = src;
	    byte[] keybyte = key.getBytes();
	    byte[] rand = new byte[8];
	    rand = iv.getBytes();
	    IvParameterSpec ivp = new IvParameterSpec(rand);
	    DESedeKeySpec dks = new DESedeKeySpec(keybyte);
	    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
	    SecretKey securekey = keyFactory.generateSecret(dks);

	    Cipher c1 = Cipher.getInstance(Algorithm);

	    c1.init(Cipher.DECRYPT_MODE, securekey, ivp);

	    return c1.doFinal(srcbytes);

	} catch (java.security.NoSuchAlgorithmException e1) {

	    // TODO: handle exception

	    e1.printStackTrace();

	} catch (javax.crypto.NoSuchPaddingException e2) {

	    e2.printStackTrace();

	} catch (java.lang.Exception e3) {

	    e3.printStackTrace();

	}

	return null;

    }

    public static void main(String[] args) throws IOException {

//	String szSrc = "TXDATE=1412724186,ISSUER=CALIX-LOCAL,OPERATOR=CALIX-LOCAL:FTR=O3PP,COUNT=1,END=1413933786";
//	String szSrc = "TXDATE=1419999999,ISSUER=CALIX-kkkkk,OPERATOR=CALIX:FTR=O3PP-jjj,COUNT=5,END=1420000000";
	String szSrc = "href=\"http://commons.apache.org/codec/\">http://commons.apache.org/codec/</a>";

	System.out.println("Plain string:\n" + szSrc);

	String iv = "CalixInc";
	String key = "10806220100.............";
	
	System.out.println("Algorithm:" + Algorithm +"  Key:" +key + "  iv:" + iv);

	byte[] encoded = encryptMode(iv, key, szSrc);

	System.out.println("Encrypted hex string:\n" + encodeHex(encoded));

//	 BASE64Encoder base64en = new BASE64Encoder();
//	 String strB64en = base64en.encode(encoded);
//	 System.out.println("Base 64 encoded:" + strB64en);
//	
//	 BASE64Decoder base64de = new BASE64Decoder();
//	 byte[] byte64de = base64de.decodeBuffer(strB64en);
//	 System.out.println("Base 64 decoded:" + encodeHex(byte64de));

	byte[] byteB64en = Base64.encodeBase64(encoded);
	System.out.println("Base 64 encoded hex string:\n" + encodeHex(byteB64en));
	System.out.println("Base 64 encoded string:\n" + new String(byteB64en));

	byte[] byte64de = Base64.decodeBase64(byteB64en);
	System.out.println("Base 64 decoded hex string:\n" + encodeHex(byte64de));
//	System.out.println("Base 64 decoded hex string:" + encodeHex(Base64.decodeBase64("YIPW0xAcdoJCiCgB6EM5+buqXzvwtVsAhN3Sw3TbkXrCLHuCcg3wvSAwFfxRdFRtegHOB7rfMrTahPT43ZxAluo7RET9In0WMPNGWkr1qQ2dm/r4EHXyekMHAdMYQPzN"
//                .getBytes())));

	 byte[] srcBytes = decryptMode(iv, key, byte64de);
//	byte[] srcBytes = decryptMode(
//	        iv,
//	        key,
//	        Base64.decodeBase64("YIPW0xAcdoJCiCgB6EM5+buqXzvwtVsAhN3Sw3TbkXrCLHuCcg3wvSAwFfxRdFRtegHOB7rfMrTahPT43ZxAluo7RET9In0WMPNGWkr1qQ2dm/r4EHXyekMHAdMYQPzN"
//	                .getBytes()));

	System.out.println("Decrypted Text:\n" + (new String(srcBytes)));

    }

   

    public static String byte2Hex(byte[] b) {

	String hs = "";

	String stmp = "";

	for (int n = 0; n < b.length; n++) {

	    stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));

	    if (stmp.length() == 1) {

		hs = hs + "0" + stmp;

	    } else {

		hs = hs + stmp;

	    }

	    if (n < b.length - 1)
		hs = hs + ":";

	}

	return hs.toUpperCase();

    }

    public static final String encodeHex(byte bytes[]) {
	StringBuffer buf = new StringBuffer(bytes.length * 2);
	for (int i = 0; i < bytes.length; i++) {
	    if ((bytes[i] & 0xff) < 16)
		buf.append("0");
	    buf.append(Long.toString(bytes[i] & 0xff, 16));
	}
	return buf.toString();
    }

    public static final byte[] decodeHex(String hex) {
	char chars[] = hex.toCharArray();
	byte bytes[] = new byte[chars.length / 2];
	int byteCount = 0;
	for (int i = 0; i < chars.length; i += 2) {
	    int newByte = 0;
	    newByte |= hexCharToByte(chars[i]);
	    newByte <<= 4;
	    newByte |= hexCharToByte(chars[i + 1]);
	    bytes[byteCount] = (byte) newByte;
	    byteCount++;
	}
	return bytes;
    }

    private static final byte hexCharToByte(char ch) {
	switch (ch) {
	case 48: // '0'
	    return 0;

	case 49: // '1'
	    return 1;

	case 50: // '2'
	    return 2;

	case 51: // '3'
	    return 3;

	case 52: // '4'
	    return 4;

	case 53: // '5'
	    return 5;

	case 54: // '6'
	    return 6;

	case 55: // '7'
	    return 7;

	case 56: // '8'
	    return 8;

	case 57: // '9'
	    return 9;

	case 97: // 'a'
	    return 10;

	case 98: // 'b'
	    return 11;

	case 99: // 'c'
	    return 12;

	case 100: // 'd'
	    return 13;

	case 101: // 'e'
	    return 14;

	case 102: // 'f'
	    return 15;

	case 58: // ':'
	case 59: // ';'
	case 60: // '<'
	case 61: // '='
	case 62: // '>'
	case 63: // '?'
	case 64: // '@'
	case 65: // 'A'
	case 66: // 'B'
	case 67: // 'C'
	case 68: // 'D'
	case 69: // 'E'
	case 70: // 'F'
	case 71: // 'G'
	case 72: // 'H'
	case 73: // 'I'
	case 74: // 'J'
	case 75: // 'K'
	case 76: // 'L'
	case 77: // 'M'
	case 78: // 'N'
	case 79: // 'O'
	case 80: // 'P'
	case 81: // 'Q'
	case 82: // 'R'
	case 83: // 'S'
	case 84: // 'T'
	case 85: // 'U'
	case 86: // 'V'
	case 87: // 'W'
	case 88: // 'X'
	case 89: // 'Y'
	case 90: // 'Z'
	case 91: // '['
	case 92: // '\\'
	case 93: // ']'
	case 94: // '^'
	case 95: // '_'
	case 96: // '`'
	default:
	    return 0;
	}
    }

}