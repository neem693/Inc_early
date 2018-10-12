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
import com.anylogic.iot.api.admin.onm.service.CodeService;
import com.anylogic.iot.base.common.ResultListVO;
import com.anylogic.iot.base.mvc.message.Messages;


@RestController
@RequestMapping("/" + Version.V1+"/admin/onm")
public class CodeController {

    private static final Logger logger = LoggerFactory.getLogger(CodeController.class);

    @Autowired
    private CodeService codeService;




    @RequestMapping(value = "/selectCode", method = RequestMethod.GET)
    public ResultListVO selectCode(@RequestParam Map<String, Object> parameter, Messages messages) {
        
        ResultListVO resultListVO = new ResultListVO();
        resultListVO.setRows(codeService.selectCode(parameter)); 
         
        messages.addMessage("OK", "");
        return resultListVO;
    }



    @RequestMapping(value = "/selectCodeSearch", method = RequestMethod.GET)
    public ResultListVO selectCodeSearch(@RequestParam Map<String, Object> parameter, Messages messages) {
        
        ResultListVO resultListVO = new ResultListVO();
        resultListVO.setRows(codeService.selectCodeSearch(parameter)); 
         
        messages.addMessage("OK", "");
        return resultListVO;
    }


    @RequestMapping(value = "/insertCode", method = RequestMethod.POST)
    public void insertCode(@RequestBody Map<String, Object> parameter, Messages messages) {
        int res = 0;
        List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
        sendParam = (List<Map<String, Object>>) parameter.get("list");
        
        for (int i = 0; i < sendParam.size(); i++) {
            res = codeService.insertCode(sendParam.get(i));
            
            if (res == 1) {
                messages.addMessage("OK", "");
            } else {
                messages.addMessage("Not OK", "");
            }
        }
    }


    @RequestMapping(value = "/updateCode", method = RequestMethod.POST)
    public void updateCode(@RequestBody Map<String, Object> parameter, Messages messages) {
        int res = 0;
        List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
        sendParam = (List<Map<String, Object>>) parameter.get("list");
        
        for (int i = 0; i < sendParam.size(); i++) {
            res = codeService.updateCode(sendParam.get(i));
            if (res == 1) {
                messages.addMessage("OK", "");
            } else {
                messages.addMessage("Not OK", "");
            }
        }
    }


    @RequestMapping(value = "/deleteCode", method = RequestMethod.POST)
    public void deleteCode(@RequestBody Map<String, Object> parameter, Messages messages) {
        int res = 0;
        List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
        sendParam = (List<Map<String, Object>>) parameter.get("list");
        
        for (int i = 0; i < sendParam.size(); i++) {
            res = codeService.deleteCode(sendParam.get(i));
            if (res == 1) {
                messages.addMessage("OK", "");
            } else {
                messages.addMessage("Not OK", "");
            }
        }
    }


}
