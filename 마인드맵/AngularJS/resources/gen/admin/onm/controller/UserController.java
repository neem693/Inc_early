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

import org.codehaus.jackson.map.ObjectMapper;
import com.anylogic.iot.api.admin.common.service.Attach_fileService;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.OutputStream;
import org.apache.commons.lang3.StringUtils;
import java.nio.charset.Charset;
import java.io.File;
import java.net.URLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.anylogic.iot.base.excel.service.ExcelSVC;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import net.sf.jxls.exception.ParsePropertyException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anylogic.iot.Version;
import com.anylogic.iot.api.admin.onm.service.UserService;
import com.anylogic.iot.base.common.ResultListVO;
import com.anylogic.iot.base.mvc.message.Messages;


@RestController
@RequestMapping("/" + Version.V1+"/admin/onm")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private Attach_fileService attach_fileService;


    @Autowired
    private ExcelSVC excelSVC;


   /* @RequestMapping(value = "/selectList", method = RequestMethod.GET)
    public ResultListVO login(@RequestParam Map<String, Object> parameter, Messages messages) {
        
        ResultListVO resultListVO = new ResultListVO();
        resultListVO.setRows(userService.selectList(parameter)); 
         
        messages.addMessage("OK", "");
        return resultListVO;
    }*/
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResultListVO login(@RequestParam Map<String, Object> parameter, Messages messages) {
        
        ResultListVO resultListVO = new ResultListVO();
        resultListVO.setRows(userService.login(parameter)); 
         
        messages.addMessage("OK", "");
        return resultListVO;
    }


    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    public String insertUser(HttpServletRequest request, HttpServletResponse response, Messages messages) {

        String atcFileId = "";
        String json = request.getParameter("data").toString();

        List<Map<String, Object>> atcFileSeqList = new ArrayList<Map<String, Object>>();
        Map<String,Object> parameter = null;

        try {
            parameter = new ObjectMapper().readValue(json, HashMap.class);
        } catch (Exception e) {
            throw new com.anylogic.iot.base.exception.KTBCCException();
        }


        int insertKey ;
        Map<String, Object> uploadInfo = new HashMap<String, Object>(); 
        if(parameter.get("uploadInfo") != null){
            uploadInfo =   (Map<String, Object>) parameter.get("uploadInfo");

            userService.insertUser(uploadInfo);
        }

        attach_fileService.insertFileData(uploadInfo, atcFileId, request, response);
        messages.addMessage("OK", "");

        return atcFileId;
    }


    @RequestMapping(value = "/dupID", method = RequestMethod.GET)
    public ResultListVO dupID(@RequestParam Map<String, Object> parameter, Messages messages) {
        
        ResultListVO resultListVO = new ResultListVO();
        resultListVO.setRows(userService.dupID(parameter)); 
         
        messages.addMessage("OK", "");
        return resultListVO;
    }


    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logout(@RequestBody Map<String, Object> parameter, Messages messages) {
        int res = 0;
        List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
        sendParam = (List<Map<String, Object>>) parameter.get("list");
        
        for (int i = 0; i < sendParam.size(); i++) {
            res = userService.logout(sendParam.get(i));
            if (res == 1) {
                messages.addMessage("OK", "");
            } else {
                messages.addMessage("Not OK", "");
            }
        }
    }



    @RequestMapping(value = "/selectUser", method = RequestMethod.GET)
    public ResultListVO selectUser(@RequestParam Map<String, Object> parameter, Messages messages) {
        
        ResultListVO resultListVO = new ResultListVO();
        resultListVO.setRows(userService.selectUser(parameter)); 
         
        messages.addMessage("OK", "");
        return resultListVO;
    }



    @RequestMapping(value = "/selectUserExcel", method = RequestMethod.POST)
    @ResponseBody
    public void selectUserExcel(HttpServletRequest request, HttpServletResponse response,
                  @RequestParam Map<String, Object> parameter, Messages messages) throws
                  ParsePropertyException, InvalidFormatException, IOException {

        String fileName = "사용자리스트";

        List<Map<String, Object>> list = userService.selectUserExcel(parameter); 
        int lng = list.size();
        for(int i=0;i<lng;i++){
            int rNum = Integer.parseInt(list.get(i).get("rnum").toString());
            rNum = lng +1 - rNum;
            list.get(i).put("RNUM", rNum);
        }
        excelSVC.getExcelDownLoad(fileName, "xlsx", "list", list, response);
    }


    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUser(HttpServletRequest request, HttpServletResponse response, Messages messages) {

        String atcFileId = "";
        //String memId = "";
        String json = request.getParameter("data").toString();

        List<Map<String, Object>> atcFileSeqList = new ArrayList<Map<String, Object>>();
        Map<String,Object> parameter = null;

        try {
            parameter = new ObjectMapper().readValue(json, HashMap.class);
        } catch (Exception e) {
            throw new com.anylogic.iot.base.exception.KTBCCException();
        }

        if(parameter.get("atcFileId") != null){
            atcFileId = parameter.get("atcFileId").toString();
        }

        
        Map<String, Object> uploadInfo = new HashMap<String, Object>(); 
        if(parameter.get("uploadInfo") != null){
            uploadInfo =   (Map<String, Object>) parameter.get("uploadInfo");
        
            uploadInfo.put("table_name", "tb_user");
        
            userService.updateUser(uploadInfo);

            attach_fileService.updateFileData(uploadInfo, atcFileId, request, response);
            messages.addMessage("OK", "");
        }

        return atcFileId;
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public String deleteUser(@RequestBody Map<String, Object> parameter, Messages messages) {

        int res = 0;
        List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
        String json = request.getParameter("data").toString();
        sendParam = (List<Map<String, Object>>) parameter.get("list");

        for (int i = 0; i < sendParam.size(); i++) {

            res = userService.deleteUser(sendParam.get(i));
            if (res == 1) {
                messages.addMessage("OK", "");
            } else {
                messages.addMessage("Not OK", "");
            }
        }
    }

}
