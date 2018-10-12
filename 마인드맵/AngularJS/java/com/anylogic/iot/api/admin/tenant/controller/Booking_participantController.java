/*
   ADP Platform version 1.0

   Copyright ⓒ 2018 anylogic corp. All rights reserved.

   This is a proprietary software of anylogic corp, and you may not use this file except in
   compliance with license agreement with anylogic corp. Any redistribution or use of this
   software, with or without modification shall be strictly prohibited without prior written
   approval of anylogic corp, and the copyright notice above does not evidence any actual or
   intended publication of such software.
*/


package com.anylogic.iot.api.admin.tenant.controller;

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


import com.anylogic.iot.base.excel.service.ExcelSVC;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import net.sf.jxls.exception.ParsePropertyException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import com.anylogic.iot.Version;
import com.anylogic.iot.api.admin.tenant.service.Booking_participantService;
import com.anylogic.iot.base.common.ResultListVO;
import com.anylogic.iot.base.mvc.message.Messages;


@RestController
@RequestMapping("/" + Version.V1+"/admin/tenant")
public class Booking_participantController {

	private static final Logger logger = LoggerFactory.getLogger(Booking_participantController.class);

	@Autowired
	private Booking_participantService booking_participantService;


	@Autowired
  private ExcelSVC excelSVC;



    @RequestMapping(value = "/downGuests", method = RequestMethod.GET)
    public ResultListVO downGuests(@RequestParam Map<String, Object> parameter, Messages messages) {
        
        ResultListVO resultListVO = new ResultListVO();
        resultListVO.setRows(booking_participantService.downGuests(parameter)); 
    	 
        messages.addMessage("OK", "");
        return resultListVO;
    }



    @RequestMapping(value = "/downGuestsExcel", method = RequestMethod.POST)
    @ResponseBody
    public void downGuestsExcel(HttpServletRequest request, HttpServletResponse response,
              	@RequestParam Map<String, Object> parameter, Messages messages) throws
              	ParsePropertyException, InvalidFormatException, IOException {

        String fileName = "출입등록목록";

        List<Map<String, Object>> list = booking_participantService.downGuestsExcel(parameter); 
        int lng = list.size();
        for(int i=0;i<lng;i++){
            int rNum = Integer.parseInt(list.get(i).get("rnum").toString());
      	  rNum = lng +1 - rNum;
      	  list.get(i).put("RNUM", rNum);
        }
        excelSVC.getExcelDownLoad(fileName, "xlsx", "list", list, response);
    }


}
