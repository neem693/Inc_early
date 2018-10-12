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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anylogic.iot.Version;
import com.anylogic.iot.api.admin.common.service.Attach_fileService;
import com.anylogic.iot.api.admin.onm.service.TestService;
import com.anylogic.iot.api.admin.onm.service.TestService2;
import com.anylogic.iot.api.admin.onm.service.UserService;
import com.anylogic.iot.base.common.ResultListVO;
import com.anylogic.iot.base.excel.service.ExcelSVC;
import com.anylogic.iot.base.mvc.message.Messages;

import net.sf.jxls.exception.ParsePropertyException;

@RestController
@RequestMapping("/" + Version.V1 + "/admin/onm")
public class TestController2 {

	private static final Logger logger = LoggerFactory.getLogger(TestController2.class);

	/*
	 * @Autowired private UserService userService;
	 * 
	 * @Autowired private Attach_fileService attach_fileService;
	 * 
	 * 
	 * @Autowired private ExcelSVC excelSVC;
	 */
	@Autowired
	private TestService2 testService2;

	@RequestMapping(value = "/insertTest2", method = RequestMethod.POST)
	public void insertTest(@RequestBody Map<String, Object> parameter, Messages messages) {

		int res = 0;

		res = testService2.insertTest2(parameter);
		if (res == 1) {
			messages.addMessage("OK", "");
		} else {
			messages.addMessage("Not OK", "");
		}

	}

	@RequestMapping(value = "/searchTest", method = RequestMethod.GET)
	public ResultListVO searchTest(@RequestParam Map<String, Object> parameter, Messages messages) {

		ResultListVO resultListVO = new ResultListVO();
		resultListVO.setRows(testService2.searchTest(parameter));

		messages.addMessage("OK", "");
		return resultListVO;

	}

	@RequestMapping(value = "/selectList", method = RequestMethod.GET)
	public ResultListVO selectList(@RequestParam Map<String, Object> parameter, Messages messages) {

		ResultListVO resultListVO = new ResultListVO();
		resultListVO.setRows(testService2.selectList(parameter));

		messages.addMessage("OK", "");
		return resultListVO;

	}

	/*
	 * @RequestMapping(value = "/selectBoard", method = RequestMethod.GET) public
	 * ResultListVO selectBoard(@RequestParam Map<String, Object> parameter,
	 * Messages messages) {
	 * 
	 * ResultListVO resultListVO = new ResultListVO();
	 * resultListVO.setRows(boardService.selectBoard(parameter));
	 * 
	 * messages.addMessage("OK", ""); return resultListVO; }
	 */

	@RequestMapping(value = "/deleteOne", method = RequestMethod.GET)
	public void deleteOne(@RequestParam Map<String, Object> parameter, Messages messages) {
		System.out.println(parameter);
		int res = testService2.deleteOne(parameter);
		System.out.println(res);
		if (res == 1) {
			messages.addMessage("OK", "");
		}
		else
			messages.addMessage("Not OK", "");
	}
	
	
	@RequestMapping(value = "/updateOne", method = RequestMethod.POST)
	public void updateOne(@RequestBody Map<String,Object> parameter, Messages messages) {
		
		System.out.println(parameter);
		int res = testService2.updateOne(parameter);
		System.out.println(res);
		if(res >0)
			messages.addMessage("OK", "");
		else
			messages.addMessage("Not OK", "");
	}
	
	
	
	
	
	

}
