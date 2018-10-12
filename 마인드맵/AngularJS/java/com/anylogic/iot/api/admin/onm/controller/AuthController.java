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
import com.anylogic.iot.api.admin.onm.service.AuthService;
import com.anylogic.iot.base.common.ResultListVO;
import com.anylogic.iot.base.mvc.message.Messages;


@RestController
@RequestMapping("/" + Version.V1+"/admin/onm")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;




    @RequestMapping(value = "/myRole", method = RequestMethod.GET)
    public ResultListVO myRole(@RequestParam Map<String, Object> parameter, Messages messages) {
        
        ResultListVO resultListVO = new ResultListVO();
        resultListVO.setRows(authService.myRole(parameter)); 
         
        messages.addMessage("OK", "");
        return resultListVO;
    }


    @RequestMapping(value = "/insertAuth", method = RequestMethod.POST)
    public void insertAuth(@RequestBody Map<String, Object> parameter, Messages messages) {
        int res = 0;
        List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
        sendParam = (List<Map<String, Object>>) parameter.get("list");
        
        for (int i = 0; i < sendParam.size(); i++) {
            res = authService.insertAuth(sendParam.get(i));
            
            if (res == 1) {
                messages.addMessage("OK", "");
            } else {
                messages.addMessage("Not OK", "");
            }
        }
    }



    @RequestMapping(value = "/selectAuth", method = RequestMethod.GET)
    public ResultListVO selectAuth(@RequestParam Map<String, Object> parameter, Messages messages) {
        
        ResultListVO resultListVO = new ResultListVO();
        resultListVO.setRows(authService.selectAuth(parameter)); 
         
        messages.addMessage("OK", "");
        return resultListVO;
    }


    @RequestMapping(value = "/updateAuth", method = RequestMethod.POST)
    public void updateAuth(@RequestBody Map<String, Object> parameter, Messages messages) {
        int res = 0;
        List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
        sendParam = (List<Map<String, Object>>) parameter.get("list");
        
        for (int i = 0; i < sendParam.size(); i++) {
            res = authService.updateAuth(sendParam.get(i));
            if (res == 1) {
                messages.addMessage("OK", "");
            } else {
                messages.addMessage("Not OK", "");
            }
        }
    }


    @RequestMapping(value = "/deleteAuth", method = RequestMethod.POST)
    public void deleteAuth(@RequestBody Map<String, Object> parameter, Messages messages) {
        int res = 0;
        List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
        sendParam = (List<Map<String, Object>>) parameter.get("list");
        
        for (int i = 0; i < sendParam.size(); i++) {
            res = authService.deleteAuth(sendParam.get(i));
            if (res == 1) {
                messages.addMessage("OK", "");
            } else {
                messages.addMessage("Not OK", "");
            }
        }
    }



    @RequestMapping(value = "/viewAuth", method = RequestMethod.GET)
    public ResultListVO viewAuth(@RequestParam Map<String, Object> parameter, Messages messages) {
        
        ResultListVO resultListVO = new ResultListVO();
        resultListVO.setRows(authService.viewAuth(parameter)); 
         
        messages.addMessage("OK", "");
        return resultListVO;
    }


}
