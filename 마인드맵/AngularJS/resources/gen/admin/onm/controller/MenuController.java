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
import com.anylogic.iot.api.admin.onm.service.MenuService;
import com.anylogic.iot.base.common.ResultListVO;
import com.anylogic.iot.base.mvc.message.Messages;


@RestController
@RequestMapping("/" + Version.V1+"/admin/onm")
public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;




    @RequestMapping(value = "/selectMenu", method = RequestMethod.GET)
    public ResultListVO selectMenu(@RequestParam Map<String, Object> parameter, Messages messages) {
        
        ResultListVO resultListVO = new ResultListVO();
        resultListVO.setRows(menuService.selectMenu(parameter)); 
         
        messages.addMessage("OK", "");
        return resultListVO;
    }



    @RequestMapping(value = "/selectMenuAll", method = RequestMethod.GET)
    public ResultListVO selectMenuAll(@RequestParam Map<String, Object> parameter, Messages messages) {
        
        ResultListVO resultListVO = new ResultListVO();
        resultListVO.setRows(menuService.selectMenuAll(parameter)); 
         
        messages.addMessage("OK", "");
        return resultListVO;
    }



    @RequestMapping(value = "/selectMyMenu", method = RequestMethod.GET)
    public ResultListVO selectMyMenu(@RequestParam Map<String, Object> parameter, Messages messages) {
        
        ResultListVO resultListVO = new ResultListVO();
        resultListVO.setRows(menuService.selectMyMenu(parameter)); 
         
        messages.addMessage("OK", "");
        return resultListVO;
    }


}
