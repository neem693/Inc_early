package com.anylogic.iot.base.common.xroshot.exception;
/**
* @class TfmTransactionException.java
* @brief 트랜잭션 예외처리 클래스 
*
* @author TFM 개발팀
* @date 2014. 3. 18.
* @version 1.0.0.1
* @Modification  Information 
*   수정일          수정자                수정내용
*  ----------------------------------------------------------------------------
*  2014. 3. 18.       김현수    
**/
public class TransactionException extends BatchBaseException {
	private static final long serialVersionUID = 1L;

	public TransactionException(String message, int errorCode) {
		super(message, errorCode);
	}
}
