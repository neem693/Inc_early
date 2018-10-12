package com.anylogic.iot.base.message;

import java.io.Serializable;


public class BaseResponse implements Serializable {

	private static final long serialVersionUID = 5468480346176063191L;

	private String responseCode;

	private String message;

    private Object data;

    public BaseResponse() {
        message = "";
        this.setResponseNG();
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void putData(Object object) {
        this.data = object;
    }

    public void setResponseOK() {
        this.responseCode = "OK";
    }

    public void setResponseNG() {
        this.responseCode = "NG";
    }

}
