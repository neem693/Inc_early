/*
   ADP Platform version 1.0

   Copyright ⓒ 2018 anylogic corp. All rights reserved.

   This is a proprietary software of anylogic corp, and you may not use this file except in
   compliance with license agreement with anylogic corp. Any redistribution or use of this
   software, with or without modification shall be strictly prohibited without prior written
   approval of anylogic corp, and the copyright notice above does not evidence any actual or
   intended publication of such software.
*/


package com.anylogic.iot.api.admin.systemConfig.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anylogic.iot.Version;
import com.anylogic.iot.api.admin.systemConfig.service.OfficeService;
import com.anylogic.iot.base.common.ResultListVO;
import com.anylogic.iot.base.mvc.message.Messages;


@RestController
@RequestMapping("/" + Version.V1+"/admin/systemConfig")
public class OfficeController {

	private static final Logger logger = LoggerFactory.getLogger(OfficeController.class);

	@Autowired
	private OfficeService officeService;


    @RequestMapping(value = "/getOffice", method = RequestMethod.GET)
    public ResultListVO getOfficeList(@RequestParam Map<String, Object> parameter, Messages messages) {
    	
    	ResultListVO resultListVO = new ResultListVO();
      	resultListVO.setRows(officeService.getOffice(parameter)); 
      	 
      	messages.addMessage("OK", "");
    	return resultListVO;
    }


    @RequestMapping(value = "/selectOF", method = RequestMethod.GET)
    public ResultListVO selectOFList(@RequestParam Map<String, Object> parameter, Messages messages) {
    	
    	ResultListVO resultListVO = new ResultListVO();
      	resultListVO.setRows(officeService.selectOF(parameter)); 
      	 
      	messages.addMessage("OK", "");
    	return resultListVO;
    }


    @RequestMapping(value = "/insertOF", method = RequestMethod.POST)
    public void insertOF(@RequestBody Map<String, Object> parameter, Messages messages) {
    	int res = 0;
    	List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
    	sendParam = (List<Map<String, Object>>) parameter.get("list");
    	
    	for (int i = 0; i < sendParam.size(); i++) {
    		res = officeService.insertOF(sendParam.get(i));
    		
    		if (res == 1) {
    			messages.addMessage("OK", "");
    		} else {
    			messages.addMessage("Not OK", "");
    		}
    	}
    }


    @RequestMapping(value = "/updateOF", method = RequestMethod.POST)
    public void updateOF(@RequestBody Map<String, Object> parameter, Messages messages) {
    	int res = 0;
    	List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
    	sendParam = (List<Map<String, Object>>) parameter.get("list");
    	
    	for (int i = 0; i < sendParam.size(); i++) {
    		res = officeService.updateOF(sendParam.get(i));
    		if (res == 1) {
    			messages.addMessage("OK", "");
    		} else {
    			messages.addMessage("Not OK", "");
    		}
    	}
    }


    @RequestMapping(value = "/deleteOF", method = RequestMethod.POST)
    public void deleteOF(@RequestBody Map<String, Object> parameter, Messages messages) {
    	int res = 0;
    	List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
    	sendParam = (List<Map<String, Object>>) parameter.get("list");
    	
    	for (int i = 0; i < sendParam.size(); i++) {
    		res = officeService.deleteOF(sendParam.get(i));
    		if (res == 1) {
    			messages.addMessage("OK", "");
    		} else {
    			messages.addMessage("Not OK", "");
    		}
    	}
    }


}
