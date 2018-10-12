package com.anylogic.iot.base.common.xroshot.exception;
/**
* @class FileProcessingException.java
* @brief 파일처리중 예외처리
*
* @author TFM 개발팀
* @date 2014. 3. 18.
* @version 1.0.0.1
* @Modification  Information 
*   수정일          수정자                수정내용
*  ----------------------------------------------------------------------------
*  2014. 3. 18.       김현수    
**/
public class FileProcessingException extends BatchBaseException {

	private static final long serialVersionUID = -4961807667490147640L;

	public FileProcessingException(String message, int errorCode) {
		super(message, errorCode);
		// TODO Auto-generated constructor stub
	}

}
