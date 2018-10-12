package com.anylogic.iot.base.exception;

/**
 * <PRE>
 *  ClassName : KTBCCException 
 * </PRE>
 * @version : 1.0
 * @date    : 2015. 6. 16. 오후 4:02:29
 * @author  : jun
 * @brief   : 
 */

public class KTBCCException extends RuntimeException {
	private static final long serialVersionUID = 3211991520472905865L;

	private final static String DEFCODE = "0";
	private final static String DEFMSG  = "처리중 오류가 발생하였습니다.";

	private String code;
	private String message;

	public KTBCCException(String sMessage){
		this.code = DEFCODE;
		this.message = sMessage;
	}

	public KTBCCException(){
		this.code = DEFCODE;
		this.message = DEFMSG;
	}

	public KTBCCException(String sCode, String sMessage){
		this.code = sCode;
		this.message = sMessage;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static String getDefcode() {
		return DEFCODE;
	}

	public static String getDefmsg() {
		return DEFMSG;
	}

}
