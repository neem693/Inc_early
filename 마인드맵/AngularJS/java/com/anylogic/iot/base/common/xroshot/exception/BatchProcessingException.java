package com.anylogic.iot.base.common.xroshot.exception;
/**
* @class TfmBatchProcessingException.java
* @brief 배치 예외처리 클래스
*
* @author TFM 개발팀
* @date 2014. 3. 18.
* @version 1.0.0.1
* @Modification  Information
*   수정일          수정자                수정내용
*  ----------------------------------------------------------------------------
*  2014. 3. 18.       김현수
**/
public class BatchProcessingException extends BatchBaseException
{
	private static final long serialVersionUID = 1L;

	private String description;

	public BatchProcessingException(String message, int errorCode, Throwable cause)
	{
		super(message, errorCode, cause);
	}

	public BatchProcessingException(String message, int errorCode)
	{
		super(message, errorCode);
	}

	public BatchProcessingException(String message, int errorCode, String desctiption) {
		super(message, errorCode);

		this.description = desctiption;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
