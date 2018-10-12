/*
   ADP Platform version 1.0

   Copyright ⓒ 2018 anylogic corp. All rights reserved.

   This is a proprietary software of anylogic corp, and you may not use this file except in
   compliance with license agreement with anylogic corp. Any redistribution or use of this
   software, with or without modification shall be strictly prohibited without prior written
   approval of anylogic corp, and the copyright notice above does not evidence any actual or
   intended publication of such software.
*/


package com.anylogic.iot.api.admin.onm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anylogic.iot.Version;
import com.anylogic.iot.api.admin.sms.controller.smsService;
import com.anylogic.iot.base.mvc.message.Messages;


@RestController
@RequestMapping("/" + Version.V1+"/admin/onm")
public class SmsSendController {

    private static final Logger logger = LoggerFactory.getLogger(SmsSendController.class);

    @Autowired
	private smsService smsservice;
    
    @RequestMapping(value="/sendSMS", method=RequestMethod.POST)
	@ResponseBody
	public void sendSMS(@RequestBody Map<String, Object> parameter, Messages messages) {
		
		List<Map<String, Object>> sendList = (List<Map<String, Object>>) parameter.get("sendList");
		List<String> supplierNames1 = new ArrayList<String>();
		
		for(int i=0;i<sendList.size();i++){
			String setData = sendList.get(i).get("number").toString();
			setData  = setData.replace("-", "");
			supplierNames1.add(setData);
		}
		String messageStr = (String) parameter.get("content");

		smsservice.sendSmsMessage(messageStr, supplierNames1);
		
	}

}
