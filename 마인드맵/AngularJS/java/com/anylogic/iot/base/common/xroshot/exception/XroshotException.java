package com.anylogic.iot.base.common.xroshot.exception;
/**
* @class XroshotException.java
* @brief 크로샷 예외처리 클래스 
*
* @author TFM 개발팀
* @date 2014. 3. 18.
* @version 1.0.0.1
* @Modification  Information 
*   수정일          수정자                수정내용
*  ----------------------------------------------------------------------------
*  2014. 3. 18.       김현수    
**/
public class XroshotException extends BatchProcessingException {
	private static final long serialVersionUID = 1L;
	private String xroshotResult;
	
	public XroshotException(String message, int errorCode, String xroshotResult) {
		super(message, errorCode);
		
		this.setXroshotResult(xroshotResult);
	}

	public String getXroshotResult() {
		return xroshotResult;
	}

	public void setXroshotResult(String xroshotResult) {
		this.xroshotResult = xroshotResult;
	}
}
