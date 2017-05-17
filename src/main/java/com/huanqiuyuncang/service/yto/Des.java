package com.huanqiuyuncang.service.yto;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Key;
import java.security.SecureRandom;

public class Des {
	private static final Logger LOGGER = Logger.getLogger(Des.class);
	private Key key;
	private String ENCRYPT_ALGORITHM = "DES";
	
	public Des(){
		getKey(getDefaultKey());
	}
		
	public Des(String keyStr){
		getKey(keyStr);
	}
	
	private String getDefaultKey(){
		InetAddress netAddress = null;
		try{
			netAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e){
			e.printStackTrace();
		}
		return netAddress.getHostName();		
	}
	
	private void getKey(String strKey){
		try{
			KeyGenerator generator = KeyGenerator.getInstance(ENCRYPT_ALGORITHM);
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(strKey.getBytes());
			generator.init(secureRandom);
			this.key = generator.generateKey();
			generator = null;
		} catch (Exception e){
			LOGGER.warn("getKey error:", e);
		}
	}

	//将内容进行base64位转码
	public String getEncString(String strMing){
		byte[] byteMi = null;
		byte[] byteMing = null;
		String strMi = "";
		Base64 base64en = new Base64();
		try{
			byteMing = strMing.getBytes("UTF-8");
			byteMi = this.getEncCode(byteMing);
			strMi = base64en.encodeAsString(byteMi);
		} catch(Exception e){
			LOGGER.warn("getEncString error:", e);
		} finally {
			base64en = null;
			byteMing = null;
			byteMi = null;
		}
		return strMi;
	}
	
	public String getDesString(String strMi){
		Base64 base64De = new Base64();
		byte[] byteMing = null;
		byte[] byteMi = null;
		String strMing = "";
		try {
			byteMi = base64De.decode(strMi);
			byteMing = this.getDesCode(byteMi);
			strMing = new String(byteMing, "UTF-8");
		} catch (Exception e){
			LOGGER.warn("getDesString error:", e);
		} finally {
			base64De = null;
			byteMing = null;
			byteMi = null;
		}
		return strMing;
	}
	
	private byte[] getEncCode(byte[] byteS){
		byte[] byteFina = null;
		Cipher cipher;
		try {
			cipher = Cipher.getInstance(ENCRYPT_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byteFina = cipher.doFinal(byteS);
		} catch (Exception e){
			LOGGER.warn("getEncCode error:", e);
		} finally {
			cipher = null;
		}
		return byteFina;
	}
	
	private byte[] getDesCode(byte[] byteD){
		Cipher cipher;
		byte[] byteFina = null;
		try {
			cipher = Cipher.getInstance(ENCRYPT_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byteFina = cipher.doFinal(byteD);
		} catch (Exception e) {
			LOGGER.warn("getDesCode eror:" , e);
		} finally {
			cipher = null;
		}
		return byteFina;
	}
}
