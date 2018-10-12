package com.anylogic.iot.api.admin.sms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class smsService {

	private static final Logger logger = LoggerFactory.getLogger(smsService.class);
	
	
	@Async("threadPoolTaskExecutor")
	public void sendSmsMessage(String message, List<String> receiverNumbers) {
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

		//return res;
	}
	
}