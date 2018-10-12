package com.anylogic.iot.base.common;

import org.json.simple.JSONObject;

import com.anylogic.iot.api.util.ObjectConverter;
/**
 * <PRE>
 *  ClassName : InterfacJson
 * </PRE>
 * @version : 1.0
 * @date    : 2015. 6. 9. 오후 5:09:21
 * @author  : jun
 * @brief   :
 */

public class InterfacJson {
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public JSONObject convertJson(){
		JSONObject body = null;
		try {

			if(result == null || "".equals(result)) {
				body = null;
			}else {
				body = ObjectConverter.toJSON(this.result);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		return body;
	}



}
