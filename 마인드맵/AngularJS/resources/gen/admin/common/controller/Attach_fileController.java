/*
   ADP Platform version 1.0

   Copyright ⓒ 2018 anylogic corp. All rights reserved.

   This is a proprietary software of anylogic corp, and you may not use this file except in
   compliance with license agreement with anylogic corp. Any redistribution or use of this
   software, with or without modification shall be strictly prohibited without prior written
   approval of anylogic corp, and the copyright notice above does not evidence any actual or
   intended publication of such software.
*/


package com.anylogic.iot.api.admin.common.controller;

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

import org.apache.commons.codec.binary.Base64;
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

import com.anylogic.iot.Version;
import com.anylogic.iot.api.admin.common.service.Attach_fileService;
import com.anylogic.iot.base.common.ResultListVO;
import com.anylogic.iot.base.mvc.message.Messages;


@RestController
@RequestMapping("/" + Version.V1+"/admin/common")
public class Attach_fileController {

    private static final Logger logger = LoggerFactory.getLogger(Attach_fileController.class);

    @Autowired
    private Attach_fileService attach_fileService;



    @RequestMapping(value = "/downloadImageFile")
    public void attachImage(String uploadPath,  String orgFilename, String mimeTypeParam, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws IOException {
    
        if(logger.isDebugEnabled()) {
            logger.debug("download...");
            logger.debug("파일 정보 : {} - {} - {}", uploadPath,  orgFilename);
        }
        
        OutputStream outputStream = response.getOutputStream();
        if(StringUtils.isEmpty(uploadPath) || StringUtils.isEmpty(orgFilename)  || StringUtils.isEmpty(mimeTypeParam)) {
            String errorMessage = "다운로드 파일 정보가 존재하지 않습니다.";
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            if(logger.isDebugEnabled()) logger.debug(errorMessage);
            return;
        }
        
        String base64Image = "";
        File file = new File(uploadPath);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            // Reading a Image file from file system
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);
            base64Image = Base64.encodeBase64String(imageData);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
        
        response.setContentType(mimeTypeParam);
        response.getOutputStream().write(base64Image.getBytes() );
    }
    
    
}
    @RequestMapping(value = "/getFileData", method = RequestMethod.GET)
    public ResultListVO getFileData(@RequestParam Map<String, Object> parameter, Messages messages) {{
        
        ResultListVO resultListVO = new ResultListVO();
        resultListVO.setRows(attach_fileService.getFileData(parameter)); 
     
        messages.addMessage("OK", "");
        return resultListVO;
    }
    
    
    @RequestMapping(value = "/fileDownload")
    public void download(String uploadPath,  String orgFilename, String mimeType, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws IOException {
        if(logger.isDebugEnabled()) {
            logger.debug("download...");
            logger.debug("파일 정보 : {} - {} - {}", uploadPath,  orgFilename);
        }
        
        OutputStream outputStream = response.getOutputStream();
        if(StringUtils.isEmpty(uploadPath) || StringUtils.isEmpty(orgFilename)  || StringUtils.isEmpty(mimeType)) {
            String errorMessage = "다운로드 파일 정보가 존재하지 않습니다.";
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            if(logger.isDebugEnabled()) logger.debug(errorMessage);
            
            return;
        }
        
        if(logger.isDebugEnabled()) logger.debug(uploadPath);
        File file = new File(uploadPath);
        
        if(!file.exists()) {
            String errorMessage = "다운로드 파일이 존재하지 않습니다.";
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            
            if(logger.isDebugEnabled()) logger.debug(errorMessage);
            return;
        }
        
        if(logger.isDebugEnabled()) logger.debug(mimeType);
        
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", "inline; filename=\"" + URLEncoder.encode(orgFilename, "UTF-8") + "\"");
        response.setContentLength((int) file.length());
        
        int comma = orgFilename.indexOf(".");
        String fileExt = orgFilename.substring(comma, orgFilename.length());
    
        response.getOutputStream().write(readFile2(uploadPath));
    }
    
    
    public static byte[] readFile2(String fileName){
        FileInputStream fis=null;
        byte[] data = null;
        try {
            fis = new FileInputStream(fileName);
            data = new byte[fis.available()];
            fis.read(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(fis!=null) fis.close();
            }catch(IOException e){ ; }
        }
        return data;
    }
    
    
    
