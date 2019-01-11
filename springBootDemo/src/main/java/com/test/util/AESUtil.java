package com.test.util;
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
/**
 * AES解码工具类
 * @author xieh
 *
 */
public class AESUtil {

	/**解密
	 * @param content  待解密内容
	 * @param password 解密密钥
	 * @return
	 */
	public static byte[] decrypt(byte[] content, String password) {
	        try {
	                 KeyGenerator kgen = KeyGenerator.getInstance("AES");
	                 kgen.init(128, new SecureRandom(password.getBytes()));
	                 SecretKey secretKey = kgen.generateKey();
	                 byte[] enCodeFormat = secretKey.getEncoded();
	                 SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");           
	                 Cipher cipher = Cipher.getInstance("AES");// 创建密码器
	                cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
	                byte[] result = cipher.doFinal(content);
	                return result; //明文
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
	
	
	/**将16进制转换为二进制
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
	        if (hexStr.length() < 1)
	                return null;
	        byte[] result = new byte[hexStr.length()/2];
	        for (int i = 0;i< hexStr.length()/2; i++) {
	                int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
	                int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
	                result[i] = (byte) (high * 16 + low);
	        }
	        return result;
	}

}
