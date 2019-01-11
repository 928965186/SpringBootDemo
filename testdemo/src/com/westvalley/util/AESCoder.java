package com.westvalley.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESCoder {
	private static final String KEY_ALGORITHM = "AES";
	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

	public static byte[] initSecretKey(String paramString) {
		KeyGenerator localKeyGenerator = null;
		try {
			localKeyGenerator = KeyGenerator.getInstance("AES");

			SecureRandom localSecureRandom = SecureRandom.getInstance("SHA1PRNG");
			localSecureRandom.setSeed(paramString.getBytes());
			localKeyGenerator.init(128, localSecureRandom);
		} catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
			localNoSuchAlgorithmException.printStackTrace();
			return new byte[0];
		}
		SecretKey localSecretKey = localKeyGenerator.generateKey();
		return localSecretKey.getEncoded();
	}

	private static Key toKey(byte[] paramArrayOfByte) {
		return new SecretKeySpec(paramArrayOfByte, "AES");
	}

	public static InputStream encrypt(InputStream paramInputStream, String paramString) throws Exception {
		byte[] arrayOfByte = initSecretKey(paramString);

		Key localKey = toKey(arrayOfByte);

		Cipher localCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		localCipher.init(1, localKey);

		CipherInputStream localCipherInputStream = new CipherInputStream(paramInputStream, localCipher);

		return localCipherInputStream;
	}

	public static OutputStream encrypt(OutputStream paramOutputStream, String paramString) throws Exception {
		byte[] arrayOfByte = initSecretKey(paramString);

		Key localKey = toKey(arrayOfByte);

		Cipher localCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		localCipher.init(1, localKey);

		CipherOutputStream localCipherOutputStream = new CipherOutputStream(paramOutputStream, localCipher);

		return localCipherOutputStream;
	}

	public static InputStream decrypt(InputStream paramInputStream, String paramString) throws Exception {
		byte[] arrayOfByte = initSecretKey(paramString);

		Key localKey = toKey(arrayOfByte);

		Cipher localCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		localCipher.init(Cipher.DECRYPT_MODE, localKey);

		CipherInputStream localCipherInputStream = new CipherInputStream(paramInputStream, localCipher);

		return localCipherInputStream;
	}

	public static OutputStream decrypt(OutputStream paramOutputStream, String paramString) throws Exception {
		byte[] arrayOfByte = initSecretKey(paramString);

		Key localKey = toKey(arrayOfByte);

		Cipher localCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		localCipher.init(Cipher.DECRYPT_MODE, localKey);

		CipherOutputStream localCipherOutputStream = new CipherOutputStream(paramOutputStream, localCipher);

		return localCipherOutputStream;
	}

	public static String decrypt(String paramString1, String paramString2) throws Exception {
		try {
			if (paramString2 == null) {
				System.out.print("Key为空null");
				return null;
			}
			byte[] arrayOfByte1 = initSecretKey(paramString2);
			SecretKeySpec localSecretKeySpec = new SecretKeySpec(arrayOfByte1, "AES");
			Cipher localCipher = Cipher.getInstance("AES");
			localCipher.init(2, localSecretKeySpec);
			byte[] arrayOfByte2 = hex2byte(paramString1);
			try {
				byte[] arrayOfByte3 = localCipher.doFinal(arrayOfByte2);
				return new String(arrayOfByte3);
			} catch (Exception localException2) {
				localException2.printStackTrace();
				return null;
			}
		} catch (Exception localException1) {
			localException1.printStackTrace();
		}
		return paramString2;
	}

	public static String encrypt(String paramString1, String paramString2) throws Exception {
		if (paramString2 == null) {
			System.out.print("Key为空null");
			return null;
		}
		byte[] arrayOfByte1 = initSecretKey(paramString2);
		SecretKeySpec localSecretKeySpec = new SecretKeySpec(arrayOfByte1, "AES");
		Cipher localCipher = Cipher.getInstance("AES");
		localCipher.init(1, localSecretKeySpec);
		byte[] arrayOfByte2 = localCipher.doFinal(paramString1.getBytes());
		return byte2hex(arrayOfByte2).toLowerCase();
	}

	public static byte[] hex2byte(String paramString) {
		if (paramString == null) {
			return null;
		}
		int i = paramString.length();
		if (i % 2 == 1) {
			return null;
		}
		byte[] arrayOfByte = new byte[i / 2];
		for (int j = 0; j != i / 2; j++) {
			arrayOfByte[j] = ((byte) Integer.parseInt(paramString.substring(j * 2, j * 2 + 2), 16));
		}
		return arrayOfByte;
	}

	public static String byte2hex(byte[] paramArrayOfByte) {
		String str1 = "";
		String str2 = "";
		for (int i = 0; i < paramArrayOfByte.length; i++) {
			str2 = Integer.toHexString(paramArrayOfByte[i] & 0xFF);
			if (str2.length() == 1) {
				str1 = str1 + "0" + str2;
			} else {
				str1 = str1 + str2;
			}
		}
		return str1.toUpperCase();
	}

	public static String randomKey() {
		String str1 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int i = (int) (Math.random() * 16.0D + 16.0D);
		int j = 0;
		String str2 = "";
		while (j < i) {
			int k = (int) (Math.random() * 61.0D);
			if (k <= 61) {
				str2 = str2 + str1.substring(k, k + 1);
				j++;
			}
		}
		return str2;
	}

	public static void main(String[] paramArrayOfString) throws Exception {
		String str = encrypt("ecologyxindaoa@weaver.com.cn", "WEAVERECOLOGYDBENCODER");
		System.out.println(str);
		System.out.println(decrypt(str, "WEAVERECOLOGYDBENCODER"));
	}
}
