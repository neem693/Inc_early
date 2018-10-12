package com.anylogic.iot.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;

import JKTFCrypto.JKTFSymmetricKey;

/**
 * <PRE>
 *  ClassName : Crypto
 *  Crypto.isEnabled() 인 경우에만 암호화 수행
 *  com.propertiesj8 crypto.useYn=1 이면 암호화 모듈을 적용한다.
 *  암호화 모듈 적용시 오류가 발생한 경우 null을 반환한다.
 * </PRE>
 * @version : 1.0
 * @date    : 2013. 8. 23. 오후 12:21:08
 * @author  : dev13
 * @brief   :
 *
 */
@Component
public class Crypto {

	private static Logger logger = LoggerFactory.getLogger(Crypto.class);

	private static JKTFSymmetricKey crypt = null;

	@Value("${crypto.useYn}")
	private static String useYn;
	
	private static String anyKey = "anylogic";


	private Crypto(){

	}

	public static int SYMM_ALGO_SEED = 1;
	public static int SYMM_ALGO_AES = 4;

	public static int HASH_ALGO_SHA1 = 256;
	public static int HASH_ALGO_SHA256 = 512;

	/**
	 * 암호화 적용여부 반환, 적용모드인 경우 true를 반환한다.
	 * <PRE>
	 *  MethodName : isEnabled
	 * </PRE>
	 * @author : dev13
	 * @date   : 2013. 8. 26. 오전 11:27:01
	 * @param  :
	 * @return : boolean
	 * @brief  :
	 * @return
	 */
	public static boolean isEnabled(){
		logger.debug("crypto.useYn1 : {}", useYn);

		if(useYn == null ||
				useYn.equals("0")){
			return false;
		}else{
			return true;
		}

	}


	/**
	 * SEED 암호화
	 * <PRE>
	 *  MethodName : encryptSeed
	 * </PRE>
	 * @author : dev13
	 * @date   : 2013. 8. 26. 오전 11:27:30
	 * @param  :
	 * @return : String
	 * @brief  :
	 * @param msg
	 * @return
	 */
	public static String encryptSeed(String msg){
		if(isEnabled()){
			//StringBuffer sb = new StringBuffer();
			if(crypt == null){
				crypt = new JKTFSymmetricKey();
				crypt.InitSecretKey();
				if(crypt.getErrorCode()<0){
			//		sb.append("ERROR [").append(crypt.getErrorCode()).append("]");
					logger.debug("[ERROR] JKTFSymmetricKey::InitSecretKey<br>에러코드 : {}", crypt.getErrorCode());
					return null;
				}
			}
			crypt.setCipherAlgorithm(SYMM_ALGO_SEED);
			String enc_msg = crypt.EncryptData(msg.getBytes());

			if (enc_msg == null || enc_msg.length() == 0) {
			//	sb.append("ERROR [").append(crypt.getErrorCode()).append("]");
				logger.debug("JKTFSymmetricKey::EncryptData 에러코드 : {}" + crypt.getErrorCode() );
				return null;
		    }else{
		    	return enc_msg;
			}
		}
		return msg;
	}

	/**
	 * AES 알고리즘으로 암호화.
	 * <PRE>
	 *  MethodName : encryptAes
	 * </PRE>
	 * @author : dev13
	 * @date   : 2013. 8. 26. 오전 11:27:50
	 * @param  :
	 * @return : String
	 * @brief  :
	 * @param msg
	 * @return
	 */
	public static String encryptAes(String msg){
		if(isEnabled()){
			//StringBuffer sb = new StringBuffer();
			if(crypt == null){
				crypt = new JKTFSymmetricKey();
				crypt.InitSecretKey();
				if(crypt.getErrorCode()<0){
			//		sb.append("ERROR [").append(crypt.getErrorCode()).append("]");
					logger.debug("[ERROR] JKTFSymmetricKey::InitSecretKey<br>에러코드 : {}", crypt.getErrorCode());
					return null;
				}
			}
			crypt.setCipherAlgorithm(SYMM_ALGO_AES);
			String enc_msg = crypt.EncryptData(msg.getBytes());

			if (enc_msg == null || enc_msg.length() == 0) {
			//	sb.append("ERROR [").append(crypt.getErrorCode()).append("]");
				logger.debug("JKTFSymmetricKey::EncryptData 에러코드 : {}" + crypt.getErrorCode() );
				return null;
		    }else{
		    	return enc_msg;
			}
		}
		return msg;
	}

	/**
	 * Seed 알고리즘으로 복호화 수행
	 * <PRE>
	 *  MethodName : decryptSeed
	 * </PRE>
	 * @author : dev13
	 * @date   : 2013. 8. 26. 오전 11:28:20
	 * @param  :
	 * @return : String
	 * @brief  :
	 * @param enc_msg
	 * @return
	 */
	public static String decryptSeed(String enc_msg){
		if(isEnabled()){
		//	StringBuffer sb = new StringBuffer();
			if(crypt == null){
				crypt = new JKTFSymmetricKey();
				crypt.InitSecretKey();
				if(crypt.getErrorCode()<0){
		//			sb.append("ERROR [").append(crypt.getErrorCode()).append("]");
					logger.debug("[ERROR] JKTFSymmetricKey::InitSecretKey<br>에러코드 : {}", crypt.getErrorCode());
					return null;
				}
			}
			crypt.setCipherAlgorithm(SYMM_ALGO_SEED);
			byte[] dec_data = crypt.DecryptData(enc_msg);

			if (dec_data == null) {
		//		sb.append("ERROR [").append(crypt.getErrorCode()).append("]");
				logger.debug("JKTFSymmetricKey::DecryptData 에러코드 : {}" + crypt.getErrorCode() );
				return null;
			}else{
			   	return new String(dec_data);
			}
		}
		return enc_msg;
	}

	/**
	 * AES 알고리즘으로 복호화
	 * <PRE>
	 *  MethodName : decryptAes
	 * </PRE>
	 * @author : dev13
	 * @date   : 2013. 8. 26. 오전 11:28:38
	 * @param  :
	 * @return : String
	 * @brief  :
	 * @param enc_msg
	 * @return
	 */
	public static String decryptAes(String enc_msg){
		if(isEnabled()){
		//	StringBuffer sb = new StringBuffer();
			if(crypt == null){
				crypt = new JKTFSymmetricKey();
				crypt.InitSecretKey();
				if(crypt.getErrorCode()<0){
		//			sb.append("ERROR [").append(crypt.getErrorCode()).append("]");
					logger.debug("[ERROR] JKTFSymmetricKey::InitSecretKey<br>에러코드 : {}", crypt.getErrorCode());
					return null;
				}
			}
			crypt.setCipherAlgorithm(SYMM_ALGO_AES);
			byte[] dec_data = crypt.DecryptData(enc_msg);

			 if (dec_data == null) {
		//		sb.append("ERROR [").append(crypt.getErrorCode()).append("]");
				 logger.debug("JKTFSymmetricKey::DecryptData 에러코드 : {}" + crypt.getErrorCode() );
				return null;
		    }else{
		    	return new String(dec_data);
		    }

		}
		return enc_msg;
	}

	/**
	 * SHA1 알고르즘으로 HASHING
	 * <PRE>
	 *  MethodName : toHash_sha1
	 * </PRE>
	 * @author : dev13
	 * @date   : 2013. 8. 26. 오전 11:28:55
	 * @param  :
	 * @return : String
	 * @brief  :
	 * @param msg
	 * @return
	 */
	public static String toHash_sha1(String msg){
		if(isEnabled()){
		//	StringBuffer sb = new StringBuffer();
			if(crypt == null){
				crypt = new JKTFSymmetricKey();
				crypt.InitSecretKey();
				if(crypt.getErrorCode()<0){
		//			sb.append("ERROR [").append(crypt.getErrorCode()).append("]");
					logger.debug("[ERROR] JKTFSymmetricKey::InitSecretKey<br>에러코드 : {}", crypt.getErrorCode());
					return null;
				}
			}
			crypt.setHashAlgorithm(HASH_ALGO_SHA1);
			String enc_msg = crypt.Hash(msg.getBytes());

			if (enc_msg == null || enc_msg.length() == 0) {
		//		sb.append("ERROR [").append(crypt.getErrorCode()).append("]");
				logger.debug("JKTFSymmetricKey::Hash 에러코드 : {}" + crypt.getErrorCode() );
				return null;
		    }else{
		    	return enc_msg;
			}
		}
		return msg;
	}

	/**
	 * SHA256 알고리즘으로 HASHING
	 * <PRE>
	 *  MethodName : toHash_sha256
	 * </PRE>
	 * @author : dev13
	 * @date   : 2013. 8. 26. 오전 11:29:21
	 * @param  :
	 * @return : String
	 * @brief  :
	 * @param msg
	 * @return
	 */
	public static String toHash_sha256(String msg){
		if(isEnabled()){
		//	StringBuffer sb = new StringBuffer();
			if(crypt == null){
				crypt = new JKTFSymmetricKey();
				crypt.InitSecretKey();
				if(crypt.getErrorCode()<0){
		//			sb.append("ERROR [").append(crypt.getErrorCode()).append("]");
					logger.debug("[ERROR] JKTFSymmetricKey::InitSecretKey<br>에러코드 : {}", crypt.getErrorCode());
					return null;
				}
			}
			crypt.setHashAlgorithm(HASH_ALGO_SHA256);
			String enc_msg = crypt.Hash(msg.getBytes());

			if (enc_msg == null || enc_msg.length() == 0) {
		//		sb.append("ERROR [").append(crypt.getErrorCode()).append("]");
				logger.debug("JKTFSymmetricKey::Hash 에러코드 : {}" + crypt.getErrorCode() );
				return null;
		    }else{
		    	return enc_msg;
			}
		}
		return msg;
	}

	/**
	 * SALT키와 함께 SHA1알고리즘으로 HASHING
	 * <PRE>
	 *  MethodName : toHashEx_sha1
	 * </PRE>
	 * @author : dev13
	 * @date   : 2013. 8. 26. 오전 11:29:57
	 * @param  :
	 * @return : String
	 * @brief  :
	 * @param msg
	 * @return
	 */
	public static String toHashEx_sha1(String msg){
		if(isEnabled()){
	//		StringBuffer sb = new StringBuffer();
			if(crypt == null){
				crypt = new JKTFSymmetricKey();
				crypt.InitSecretKey();
				if(crypt.getErrorCode()<0){
		//			sb.append("ERROR [").append(crypt.getErrorCode()).append("]");
					logger.debug("[ERROR] JKTFSymmetricKey::InitSecretKey<br>에러코드 : {}", crypt.getErrorCode());
					return null;
				}
			}
			crypt.setHashAlgorithm(HASH_ALGO_SHA1);
			String enc_msg = crypt.HashEx(msg.getBytes());

			if (enc_msg == null || enc_msg.length() == 0) {
		//		sb.append("ERROR [").append(crypt.getErrorCode()).append("]");
				logger.debug("JKTFSymmetricKey::HashEx 에러코드 : {}" + crypt.getErrorCode() );
				return null;
		    }else{
		    	return enc_msg;
			}
		}
		return msg;
	}

	/**
	 * SALT키와 함께 SHA256알고리즘으로 HASHING
	 * <PRE>
	 *  MethodName : toHashEx_sha256
	 * </PRE>
	 * @author : dev13
	 * @date   : 2013. 8. 26. 오전 11:30:27
	 * @param  :
	 * @return : String
	 * @brief  :
	 * @param msg
	 * @return
	 */
	public static String toHashEx_sha256(String msg){
		if(isEnabled()){
		//	StringBuffer sb = new StringBuffer();
			if(crypt == null){
				crypt = new JKTFSymmetricKey();
				crypt.InitSecretKey();
				if(crypt.getErrorCode()<0){
			//		sb.append("ERROR [").append(crypt.getErrorCode()).append("]");
					logger.debug("[ERROR] JKTFSymmetricKey::InitSecretKey<br>에러코드 : {}", crypt.getErrorCode());
					return null;
				}
			}
			crypt.setHashAlgorithm(HASH_ALGO_SHA256);
			String enc_msg = crypt.HashEx(msg.getBytes());

			if (enc_msg == null || enc_msg.length() == 0) {
			//	sb.append("ERROR [").append(crypt.getErrorCode()).append("]");
				logger.debug("JKTFSymmetricKey::HashEx 에러코드 : {}" + crypt.getErrorCode() );
				return null;
		    }else{
		    	return enc_msg;
			}
		}
		return msg;
	}

	/**
	 * 메시지와 해싱된 데이타 비교(SHA1알고리즘), 일치하는 경우 TRUE
	 * <PRE>
	 *  MethodName : compareHash_sha1
	 * </PRE>
	 * @author : dev13
	 * @date   : 2013. 8. 26. 오전 11:30:55
	 * @param  :
	 * @return : boolean
	 * @brief  :
	 * @param msg
	 * @param hashed
	 * @return
	 */
	public static boolean compareHash_sha1(String msg, String hashed){
		if(isEnabled()){
			if(crypt == null){
				crypt = new JKTFSymmetricKey();
				crypt.InitSecretKey();
				if(crypt.getErrorCode()<0){
					logger.debug("[ERROR] JKTFSymmetricKey::InitSecretKey<br>에러코드 : {}", crypt.getErrorCode());
					return false;
				}
			}
			crypt.setHashAlgorithm(HASH_ALGO_SHA1);
			String enc_msg = crypt.Hash(msg.getBytes());

			if (enc_msg == null || enc_msg.length() == 0) {
				logger.debug("JKTFSymmetricKey::Hash 에러코드 : {}", crypt.getErrorCode() );
				return false;
		    }else{
		    	if(enc_msg.equals(hashed)){
		    		return true;
		    	}else{
		    		logger.debug("[ERROR] JKTFSymmetricKey::Hash Value Mismatch {}", msg, hashed, enc_msg);
		    		return false;
		    	}
			}
		}else{
			return true;
		}
	}

	/**
	 * 메시지와 해싱된 데이타 비교(SHA256알고리즘), 일치하는 경우 TRUE
	 * <PRE>
	 *  MethodName : compareHash_sha256
	 * </PRE>
	 * @author : dev13
	 * @date   : 2013. 8. 26. 오전 11:32:09
	 * @param  :
	 * @return : boolean
	 * @brief  :
	 * @param msg
	 * @param hashed
	 * @return
	 */
	public static boolean compareHash_sha256(String msg, String hashed){
		if(isEnabled()){
			if(crypt == null){
				crypt = new JKTFSymmetricKey();
				crypt.InitSecretKey();
				if(crypt.getErrorCode()<0){
					logger.debug("[ERROR] JKTFSymmetricKey::InitSecretKey<br>에러코드 : {}", crypt.getErrorCode());
					return false;
				}
			}
			crypt.setHashAlgorithm(HASH_ALGO_SHA256);
			String enc_msg = crypt.Hash(msg.getBytes());

			if (enc_msg == null || enc_msg.length() == 0) {
				logger.debug("JKTFSymmetricKey::Hash 에러코드 : {}" + crypt.getErrorCode() );
				return false;
		    }else{
		    	if(enc_msg.equals(hashed)){
		    		return true;
		    	}else{
		    		logger.debug("[ERROR] JKTFSymmetricKey::Hash Value Mismatch {}", msg, hashed, enc_msg);
		    		return false;
		    	}
			}
		}else {
			return true;
		}
	}

	/**
	 * 메시지와 해싱된 데이타 비교(SALT+SHA1알고리즘), 일치하는 경우 TRUE
	 * <PRE>
	 *  MethodName : comapreHashEx_sha1
	 * </PRE>
	 * @author : dev13
	 * @date   : 2013. 8. 26. 오전 11:32:24
	 * @param  :
	 * @return : boolean
	 * @brief  :
	 * @param msg
	 * @param hashed
	 * @return
	 */
	public static boolean comapreHashEx_sha1(String msg, String hashed){
		if(isEnabled()){
			if(crypt == null){
				crypt = new JKTFSymmetricKey();
				crypt.InitSecretKey();
				if(crypt.getErrorCode()<0){
					logger.debug("[ERROR] JKTFSymmetricKey::InitSecretKey<br>에러코드 : {}", crypt.getErrorCode());
					return false;
				}
			}
			crypt.setHashAlgorithm(HASH_ALGO_SHA1);
			String enc_msg = crypt.HashEx(msg.getBytes());

			if (enc_msg == null || enc_msg.length() == 0) {
				logger.debug("JKTFSymmetricKey::HashEx 에러코드 : {}" + crypt.getErrorCode() );
				return false;
		    }else{
		    	if(enc_msg.equals(hashed)){
		    		return true;
		    	}else{
		    		logger.debug("[ERROR] JKTFSymmetricKey::HashEx Value Mismatch {}", msg, hashed, enc_msg);
		    		return false;
		    	}
			}
		}else{
			return true;
		}
	}

	/**
	 * 메시지와 해싱된 데이타 비교(SALT+SHA256알고리즘), 일치하는 경우 TRUE
	 * <PRE>
	 *  MethodName : comapreHashEx_sha256
	 * </PRE>
	 * @author : dev13
	 * @date   : 2013. 8. 26. 오전 11:32:47
	 * @param  :
	 * @return : boolean
	 * @brief  :
	 * @param msg
	 * @param hashed
	 * @return
	 */
	public static boolean comapreHashEx_sha256(String msg, String hashed){
		if(isEnabled()){
			if(crypt == null){
				crypt = new JKTFSymmetricKey();
				crypt.InitSecretKey();
				if(crypt.getErrorCode()<0){
					logger.debug("[ERROR] JKTFSymmetricKey::InitSecretKey<br>에러코드 : {}", crypt.getErrorCode());
					return false;
				}
			}
			crypt.setHashAlgorithm(HASH_ALGO_SHA256);
			String enc_msg = crypt.HashEx(msg.getBytes());

			if (enc_msg == null || enc_msg.length() == 0) {
				logger.debug("JKTFSymmetricKey::HashEx 에러코드 : {}" + crypt.getErrorCode() );
				return false;
		    }else{
		    	if(enc_msg.equals(hashed)){
		    		return true;
		    	}else{
		    		logger.debug("[ERROR] JKTFSymmetricKey::HashEx Value Mismatch {}", msg, hashed, enc_msg);
		    		return false;
		    	}
			}
		}else{
			return true;
		}
	}
	
	
	
	
	private static byte[] getUserKey () {
		byte ret[] = new byte[16];
		byte key[] = anyKey.getBytes();
		
		for (int i = 0; i < 16; i++) {
			if (key.length <= i)
				ret[i] = (byte)0x00;
			else 
				ret[i] = key[i];
		}
		
		return ret;
	}
	
	
	/**
	 * KISA SEED encryption
	 */
	public static String AseedEnc(String plain) {
		int pdwRoundKey[] = new int[32] ;
		byte pbUserKey[] = getUserKey();
		Aseedx.SeedRoundKey(pdwRoundKey, pbUserKey);

		byte[] pbDataAll = plain.getBytes();
		byte[] pbChiperAll = new byte[16];
		byte[] tmpChiper = new byte[16];
		
		
		for(int i = 0; i < pbDataAll.length; i+=16) {
			byte pbData[] = new byte[16];
			byte pbChiper[] = new byte[16];
			
			for (int j = 0; j < 16; j++) {
				if (i + j >= pbDataAll.length) 
					pbData[j] = (byte)0x00;
				else 
					pbData[j] = pbDataAll[i+j];
			}

			Aseedx.SeedEncrypt(pbData, pdwRoundKey, pbChiper);

			if (i == 0) {
				tmpChiper = new byte[pbChiper.length];
				for (int k = 0; k < tmpChiper.length; k++ ) {
					tmpChiper[k] = pbChiper[k];
				}
			}
			else {
				tmpChiper = new byte[pbChiperAll.length + pbChiper.length];
				int idx = 0;
				for (int k = 0; k < pbChiperAll.length; k++ ) {
					tmpChiper[idx] = pbChiperAll[k];
					idx++;
				}
				for (int k = 0; k < pbChiper.length; k++ ) {
					tmpChiper[idx] = pbChiper[k];
					idx++;
				}
			}
			
			pbChiperAll = tmpChiper;
		}
		
		return new String (Base64.encode(pbChiperAll));
	}

	/**
	 * KISA SEED decryption
	 */
	public static String AseedDec(String enc) {
		int pdwRoundKey[] = new int[32] ;
		byte pbUserKey[] = getUserKey();

		Aseedx.SeedRoundKey(pdwRoundKey, pbUserKey);

		byte[] pbDataAll = new byte[16];
		byte[] pbChiperAll = Base64.decode(enc.getBytes());
		byte[] tmpData = new byte[16];
		
		if (pbChiperAll.length % 16 > 0) {
			return ""; // error
		}
		
		for (int i = 0; i < pbChiperAll.length; i +=16) {
			byte pbData[] = new byte[16];
			byte pbChiper[] = new byte[16];

			for (int j = 0; j < 16; j++) {
				pbChiper[j] = pbChiperAll[i+j];
			}
			Aseedx.SeedDecrypt(pbChiper, pdwRoundKey, pbData);

			if (i == 0) {
				tmpData = new byte[pbData.length];
				for (int k = 0; k < tmpData.length; k++ ) {
					tmpData[k] = pbData[k];
				}
			}
			else {
				tmpData = new byte[pbDataAll.length + pbData.length];
				int idx = 0;
				for (int k = 0; k < pbDataAll.length; k++ ) {
					tmpData[idx] = pbDataAll[k];
					idx++;
				}
				for (int k = 0; k < pbData.length; k++ ) {
					tmpData[idx] = pbData[k];
					idx++;
				}
			}
			pbDataAll = tmpData;
		}
		
		String ret = new String(pbDataAll);
		return ret.trim();
	}

}
