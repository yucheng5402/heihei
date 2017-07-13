package com.zhidi.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 鏁版嵁鍔犲瘑宸ュ叿绫诲�?�锛屼富瑕佹彁渚涗簡MD5鍜孲HA1鍔犲瘑宸ュ叿鏂规�?
 *
 * @author Lu Jianliang
 *
 */
public class EncryptUtil {
	public static final String KEY_SHA = "SHA";

	public static final String KEY_MD5 = "MD5";

	public static final String KEY_SHA1 = "SHA-1";

	private static final String KEY_SHA256 = "SHA-256";
	
	private static final String KEY_SHA512 = "SHA-512";

	/**
	 * BASE64瑙ｇ�?
	 *
	 * @param key
	 *            -闇�瑕佽В鐮佺殑�?�嗙爜�?�楃涓�?
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

	/**
	 * BASE64缂栫�?
	 *
	 * @param key
	 *            -闇�瑕佺紪鐮佺殑瀛楄妭鏁扮粍
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}

	/**
	 * MD5鍔犲�?
	 *
	 * @param data
	 *            - 闇�瑕佸姞�?�嗙殑�?�楄妭鏁扮粍
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptMD5(byte[] data) throws Exception {

		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		md5.update(data);

		return md5.digest();

	}

	/**
	 * SHA鍔犲�?
	 *
	 * @param data
	 *            -闇�瑕佸姞�?�嗙殑�?�楄妭鏁扮粍
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptSHA(String shaType,byte[] data) throws Exception {

		MessageDigest sha = MessageDigest.getInstance(shaType);
		sha.update(data);

		return sha.digest();

	}

	/**
	 * SHA1鍔犲�?
	 *
	 * @param data
	 *            -闇�瑕佸姞�?�嗙殑�?�楄妭鏁扮粍
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptSHA1(byte[] data) throws Exception {

		return encryptSHA(KEY_SHA1, data);

	}
	
	/**
	 * SHA256鍔犲�?
	 *
	 * @param data
	 *            -闇�瑕佸姞�?�嗙殑�?�楄妭鏁扮粍
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptSHA256(byte[] data) throws Exception {

		return encryptSHA(KEY_SHA256, data);

	}
	
	/**
	 * SHA512鍔犲�?
	 *
	 * @param data
	 *            -闇�瑕佸姞�?�嗙殑�?�楄妭鏁扮粍
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptSHA512(byte[] data) throws Exception {

		return encryptSHA(KEY_SHA512, data);

	}

	/**
	 * MD5鍔犲�?
	 * 
	 * @param content
	 *            -寰呭姞�?�嗘槑鏂囧瓧绗︿�?
	 * @param charset
	 *            -寰呭姞�?�嗘槑鏂囧瓧绗︿覆閲囩敤鐨勫瓧绗︾紪鐮侀�?
	 * @return -杩斿洖MD5鍔犲瘑鍚庣殑瀛楃涓�?
	 */
	public static String encryptMD5(String content, String charset) {
		try {
			byte[] b = encryptMD5(content.getBytes(charset));
			StringBuffer buffer = new StringBuffer();

			for (int i = 0; i < b.length; i++) {
				String shaHex = Integer.toHexString(b[i] & 0xFF);
				if (shaHex.length() < 2) {
					buffer.append(0);
				}
				buffer.append(shaHex);
			}
			return buffer.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}

	/**
	 * MD5鍔犲瘑绠楁硶锛岃鏂规硶閲囩敤UTF-8缂栫爜闆嗗皢鏄庢枃杩涜杞崲锛屽鏋滄槑鏂囧瓧绗︿覆涓哄叾浠栧瓧绗︾紪鐮侊�?<br>
	 * 璇烽噰鐢�?<code>encryptMD5(String content,String charset)</code>鏂规�?
	 * 
	 * @param content
	 *            -寰呭姞�?�嗘槑鏂囧瓧绗︿�?
	 * @return -杩斿洖鍔犲瘑鍚庡瘑鏂囧瓧绗︿�?
	 */
	public static String encryptMD5(String content) {
		return encryptMD5(content, "UTF-8");
	}

	/**
	 * SHA鍔犲瘑绠楁硶
	 * 
	 * @param content -寰呭姞�?�嗘槑鏂�?
	 * @param charset -鏄庢枃鎵�閲囩敤瀛楃缂栫爜闆�
	 * @return
	 */
	public static String encryptSHA(String shaType,String content, String charset) {
		try {
			byte[] b = encryptSHA(shaType,content.getBytes(charset));
			StringBuffer buffer = new StringBuffer();
			// 瀛楄妭鏁扮粍杞崲涓�? 鍗佸叚杩涘埗 鏁�
			for (int i = 0; i < b.length; i++) {
				String shaHex = Integer.toHexString(b[i] & 0xFF);
				if (shaHex.length() < 2) {
					buffer.append(0);
				}
				buffer.append(shaHex);
			}
			return buffer.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * SHA鍔犲瘑绠楁硶
	 * @param content -寰呭姞�?�嗘槑鏂囧瓧绗︿�?
	 * @return
	 */
	public static String encryptSHA(String shaType,String content){
		return encryptSHA(shaType,content,"UTF-8");
	}
	
	/**
	 * SHA1鍔犲瘑绠楁硶
	 * @param content -寰呭姞�?�嗘槑鏂囧瓧绗︿�?
	 * @param charset -鏄庢枃鎵�閲囩敤瀛楃缂栫爜闆�
	 * @return
	 */
	public static String encryptSHA1(String content,String charset){
		
		return encryptSHA(KEY_SHA1, content,charset);
	}
	
	
	/**
	 * SHA1鍔犲瘑绠楁硶
	 * @param content -寰呭姞�?�嗘槑鏂囧瓧绗︿�?
	 * @return
	 */
	public static String encryptSHA1(String content){
		return encryptSHA1(content,"UTF-8");
	}
	
	/**
	 * SHA256鍔犲瘑绠楁硶
	 * @param content -寰呭姞�?�嗘槑鏂囧瓧绗︿�?
	 * @param charset -鏄庢枃鎵�閲囩敤瀛楃缂栫爜闆�
	 * @return
	 */
	public static String encryptSHA256(String content,String charset){
		
		return encryptSHA(KEY_SHA256, content,charset);
	}
	
	
	/**
	 * SHA256鍔犲瘑绠楁硶
	 * @param content -寰呭姞�?�嗘槑鏂囧瓧绗︿�?
	 * @return
	 */
	public static String encryptSHA256(String content){
		return encryptSHA256(content,"UTF-8");
	}
	
	/**
	 * SHA512鍔犲瘑绠楁硶
	 * @param content -寰呭姞�?�嗘槑鏂囧瓧绗︿�?
	 * @param charset -鏄庢枃鎵�閲囩敤瀛楃缂栫爜闆�
	 * @return
	 */
	public static String encryptSHA512(String content,String charset){
		
		return encryptSHA(KEY_SHA512, content,charset);
	}
	
	
	/**
	 * SHA512鍔犲瘑绠楁硶
	 * @param content -寰呭姞�?�嗘槑鏂囧瓧绗︿�?
	 * @return
	 */
	public static String encryptSHA512(String content){
		return encryptSHA512(content,"UTF-8");
	}
	
	public static void main(String[] args) {
		String s1 = encryptSHA512("123456");
		System.out.println(s1);
		String s2 = encryptMD5("123456");
		System.out.println(s2);
		String s3 = encryptSHA1("123456");
		System.out.println(s3);
	}

}