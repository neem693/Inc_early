
package com.anylogic.iot.api.admin.sms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.anylogic.iot.Version;
import com.anylogic.iot.base.mvc.message.Messages;


      
@RestController
@RequestMapping("/" + Version.V1+"/admin/sms")
public class SmsController {

	private static final Logger logger = LoggerFactory.getLogger(SmsController.class);

	@RequestMapping(value="/sendSmsData", method= RequestMethod.POST)
    public boolean sendSmsData(@RequestBody List<Map<String, Object>> parameter, Messages messages){
		
		// SMS 메세지 전송
		
		List<String> supplierNames1 = new ArrayList<String>();
		
		for(int i=0;i<parameter.size();i++){
			String recvNum = (String) parameter.get(i).get("recvNum");
			supplierNames1.add(recvNum);
		}
		
		String messageStr = (String) parameter.get(0).get("sendMsg");
		boolean resSMS = sendSmsMessage(messageStr, supplierNames1);
		
		return resSMS;
	}
	
	@RequestMapping(value="/sendSmsData2", method= RequestMethod.POST)
    public boolean sendSmsData2(@RequestBody Map<String, Object> parameter, Messages messages){
		
		// SMS 메세지 전송
		
		List<String> supplierNames1 = new ArrayList<String>();
		List<Map<String, Object>> resvParam = (List<Map<String, Object>>) parameter.get("recvData");
		
		for(int i=0;i<resvParam.size();i++){
			String recvNum = (String) resvParam.get(i).get("recvNum");
			supplierNames1.add(recvNum);
		}
		
		String messageStr = (String) resvParam.get(0).get("sendMsg");
		boolean resSMS = sendSmsMessage(messageStr, supplierNames1);
		
		return resSMS;
	}
	
	private boolean sendSmsMessage(String message, List<String> receiverNumbers) {
		boolean res = false;
		try {
			// Xroshot 설정 정보
			String spid = com.anylogic.iot.base.util.PropUtil.getInstance().getPropValue(com.anylogic.iot.base.common.xroshot.constant.XroshotConstant.DEFAULT_PROP_FILE, com.anylogic.iot.base.common.xroshot.constant.XroshotConstant.XROSUOT_SPID);
			String sppwd = com.anylogic.iot.base.util.PropUtil.getInstance().getPropValue(com.anylogic.iot.base.common.xroshot.constant.XroshotConstant.DEFAULT_PROP_FILE, com.anylogic.iot.base.common.xroshot.constant.XroshotConstant.XROSUOT_SPPWD);
			String certfile_path = com.anylogic.iot.base.util.PropUtil.getInstance().getPropValue(com.anylogic.iot.base.common.xroshot.constant.XroshotConstant.DEFAULT_PROP_FILE, com.anylogic.iot.base.common.xroshot.constant.XroshotConstant.XROSUOT_CERTFILE_PATH);

			// Sender 정보
			String senderNumber = com.anylogic.iot.base.util.PropUtil.getInstance().getPropValue(com.anylogic.iot.base.common.xroshot.constant.XroshotConstant.DEFAULT_PROP_FILE, com.anylogic.iot.base.common.xroshot.constant.XroshotConstant.XROSUOT_SENDER);

			com.anylogic.iot.base.common.xroshot.job.XroshotSms smsSend = new com.anylogic.iot.base.common.xroshot.job.XroshotSms(spid, sppwd, certfile_path);

			// SMS 메세지 전송
			res = smsSend.sendSms(message, senderNumber, receiverNumbers);

		} catch (Exception e) {
			logger.info("Send SMS MESSAGE ERROR :" + e.getMessage());
		}

		return res;
	}

}





