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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.anylogic.iot.Version;
import com.anylogic.iot.api.admin.common.service.Attach_fileService;
import com.anylogic.iot.api.admin.onm.service.BoardService;
import com.anylogic.iot.base.common.ResultListVO;
import com.anylogic.iot.base.mvc.message.Messages;


@RestController
@RequestMapping("/" + Version.V1+"/admin/onm")
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    private BoardService boardService;

    @Autowired
    private Attach_fileService attach_fileService;

    @ResponseBody
    @RequestMapping(value = "/excelUploadAjax", method = RequestMethod.POST)
    public ModelAndView excelUploadAjax(MultipartHttpServletRequest request)  throws Exception{
        MultipartFile excelFile =request.getFile("excelFile");
        System.out.println("엑셀 파일 업로드 컨트롤러");
        if(excelFile==null || excelFile.isEmpty()){
            throw new RuntimeException("엑셀파일을 선택 해 주세요.");
        }
        
        File destFile = new File("D:\\"+excelFile.getOriginalFilename());
        try{
            excelFile.transferTo(destFile);
        }catch(IllegalStateException | IOException e){
            throw new RuntimeException(e.getMessage(),e);
        }
        
        boardService.excelUpload(destFile);
        
       
        destFile.delete();
        ModelAndView view = new ModelAndView();
        view.setViewName("");
        return view;
    }




    @RequestMapping(value = "/insertBoard", method = RequestMethod.POST)
    public String insertBoard(HttpServletRequest request, HttpServletResponse response, Messages messages) {

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

            boardService.insertBoard(uploadInfo);
        }

        attach_fileService.insertFileData(uploadInfo, atcFileId, request, response);
        messages.addMessage("OK", "");

        return atcFileId;
    }

    @RequestMapping(value = "/updateBoard", method = RequestMethod.POST)
    public String updateBoard(HttpServletRequest request, HttpServletResponse response, Messages messages) {

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
            uploadInfo.put("table_name", "sdp.tb_board");
            //file update
            List<Map<String, Object>> recvFileData = new ArrayList<Map<String, Object>>();
			List<Object> curFileData = new ArrayList<Object>();
			recvFileData = (List<Map<String, Object>>) uploadInfo.get("recvFileList");
			uploadInfo.put("reference_id", uploadInfo.get("board_id").toString());
			uploadInfo.put("insertKey", uploadInfo.get("board_id"));
			curFileData = boardService.getBoardFile(uploadInfo);
			
			boardService.resetBoardFile(uploadInfo);
			
           
        
            for(int i=0;i<recvFileData.size();i++){
				Map<String, Object> temp = recvFileData.get(i);
				try{
					
					//String confirmStr = (String)temp.get("attach_file_id");
					if(temp.containsKey("attach_file_id")){
						recvFileData.get(i).put("reference_id", uploadInfo.get("board_id").toString());
						boardService.reinsertFile(recvFileData.get(i));
					}
				}catch (Exception e) {
					messages.addMessage("not OK", "");

					return atcFileId;
				}
				
			}
            
            boardService.updateBoard(uploadInfo);

            attach_fileService.updateFileData(uploadInfo, atcFileId, request, response);
            messages.addMessage("OK", "");
        }

        return atcFileId;
    }

    @RequestMapping(value = "/addViewCount", method = RequestMethod.POST)
    public void addViewCount(@RequestBody Map<String, Object> parameter, Messages messages) {
        int res = 0;
        List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
        sendParam = (List<Map<String, Object>>) parameter.get("list");
        
        for (int i = 0; i < sendParam.size(); i++) {
            res = boardService.addViewCount(sendParam.get(i));
            if (res == 1) {
                messages.addMessage("OK", "");
            } else {
                messages.addMessage("Not OK", "");
            }
        }
    }



    @RequestMapping(value = "/selectBoard", method = RequestMethod.GET)
    public ResultListVO selectBoard(@RequestParam Map<String, Object> parameter, Messages messages) {
        
        ResultListVO resultListVO = new ResultListVO();
        resultListVO.setRows(boardService.selectBoard(parameter)); 
        					
        messages.addMessage("OK", "");
        return resultListVO;
    }


    @RequestMapping(value = "/deleteBoard", method = RequestMethod.POST)
    public int deleteBoard(@RequestBody Map<String, Object> parameter, Messages messages) {
    	logger.debug("deleteBoard" + parameter.toString());
		messages.addMessage("Not OK","");
		
        int res = 0;
        List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
        //String json = request.getParameter("data").toString();
        sendParam = (List<Map<String, Object>>) parameter.get("list");

        for (int i = 0; i < sendParam.size(); i++) {

            //res = userService.deleteUser(sendParam.get(i));
        	 res = boardService.deleteBoard(sendParam.get(i));
            if (res == 1) {
                //sendParam.get(i).put("reference_id", sendParam.get(i).get("user_id").toString());
            	sendParam.get(i).put("reference_id", sendParam.get(i).get("board_id").toString());
                sendParam.get(i).put("table_name", "sdp.tb_board");
                attach_fileService.deleteFileData(sendParam.get(i));
                
                messages.addMessage("OK", "");
            } else {
                messages.addMessage("Not OK", "");
            }
        }
        return res;
    }

}
