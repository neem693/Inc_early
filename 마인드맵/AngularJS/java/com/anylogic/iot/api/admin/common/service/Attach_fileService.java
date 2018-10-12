/*
   ADP Platform version 1.0

   Copyright ⓒ 2018 anylogic corp. All rights reserved.

   This is a proprietary software of anylogic corp, and you may not use this file except in
   compliance with license agreement with anylogic corp. Any redistribution or use of this
   software, with or without modification shall be strictly prohibited without prior written
   approval of anylogic corp, and the copyright notice above does not evidence any actual or
   intended publication of such software.
*/


package com.anylogic.iot.api.admin.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anylogic.iot.Version;
import com.anylogic.iot.api.admin.common.mapper.Attach_fileMapper;
import com.anylogic.iot.base.excel.service.ExcelSVC;

import org.springframework.transaction.annotation.Transactional;

import com.anylogic.iot.api.admin.common.mapper.Attach_fileMapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import java.util.Random;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.File;
@Service
@Transactional
public class Attach_fileService {

	@Autowired
	private Attach_fileMapper attach_fileMapper;

	@Autowired
	private ExcelSVC excelSVC;


    @SuppressWarnings("unchecked")
    public int insertFileData(Map<String, Object> sendInfo, String atcFileId, HttpServletRequest request, HttpServletResponse response)
    {

        String rtnFileId = "";
        int res = 0;

        try
        {
            if(request.getParameter("files") == null)
            {
                rtnFileId = atcFileId;
                return res;
            }

            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;  //다중파일 업로드
            List<MultipartFile> mf = multipartRequest.getFiles("fileField1");

            Random random = new Random();
            String genId = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());  //현재시간
            genId = genId.substring(2, genId.length()) + String.format("%02d", random.nextInt(10));

            if("".equals(atcFileId) || atcFileId == null)
            {
                // 파일 중복명 처리
                // return file ID
                rtnFileId = genId;
            }
            else
            {
                // 기존 파일 아이디에 계속 연결 시킴
                rtnFileId = atcFileId;
            }

            List<Map<String, Object>> filenames = new ArrayList<Map<String, Object>>();
            filenames = (List<Map<String, Object>>)sendInfo.get("tempFileList");

            List<Map<String, Object>> recvFileData = new ArrayList<Map<String, Object>>();
            recvFileData = (List<Map<String, Object>>)sendInfo.get("recvFileList");

            //filenames 에서 recvFileData와 같은 파일을 제거한다. 
            if(recvFileData != null)
            {
                for(int i = 0; i < recvFileData.size(); i++)
                {
                    int target = -1;
                    for(int t = 0; t < filenames.size(); t++)
                    {
                        String tmp = (String)recvFileData.get(i).get("name");
                        if(tmp != null && tmp.equals(filenames.get(t).get("name").toString()))
                        {
                            target = t;
                            break;
                        }
                    }
                    if(target >= 0)
                    {
                        filenames.remove(target);
                    }
                }
            }

            for(int i = 0; i < mf.size(); i++)
            {
                // 본래 파일명
                String originalfileName = (String)filenames.get(i).get("name");

                String saveFileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "_" + originalfileName;

                File path22 = new File(".");
                String saveUrl = path22.getCanonicalPath();

                if(saveUrl.indexOf("saehan") >= 0)
                {   // 윈도우 개발환경 : saehan 프로젝트명 검사
                    saveUrl = saveUrl + "/" + "src" + "/" + "main" + "/" + "webapp" + "/" + "upload" + "/";
                }
                else
                { // 리눅스 운용환경 : seian 프로젝트명 검사
                    saveUrl = "/jboss/domains/upload/";
                }

                // 디렉토리 검사
                File dir = new File(saveUrl);
                if(!dir.isDirectory())
                {
                    dir.mkdirs();
                }

                System.out.println("URL11@@@@ NEW @@@@@1111 : " + saveUrl);

                String saveFullPath = saveUrl + saveFileName; // 저장 될 파일 경로

                mf.get(i).transferTo(new File(saveFullPath)); // 파일 저장

                sendInfo.put("file_name", saveFileName);
                sendInfo.put("file_path", saveFullPath);
                res = attach_fileMapper.insertFileData(sendInfo);
            }

        }
        catch(Exception e)
        {
            throw new com.anylogic.iot.base.exception.KTBCCException(e.getMessage());
        }

        return res;


    }
    public List<Object> getFileData(Map<String, Object> parameter){
    	return attach_fileMapper.getFileData(parameter);
    }


    @SuppressWarnings("unchecked")
    public int updateFileData(Map<String, Object> sendInfo, String atcFileId, HttpServletRequest request, HttpServletResponse response)
    {

        String rtnFileId = "";
        int res = 0;

        try
        {    
            attach_fileMapper.deleteFileData(sendInfo);
            
            if(request.getParameter("files") == null)
            {
                rtnFileId = atcFileId;
                return res;
            }

            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;  //다중파일 업로드
            List<MultipartFile> mf = multipartRequest.getFiles("fileField1");

            Random random = new Random();
            String genId = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());  //현재시간
            genId = genId.substring(2, genId.length()) + String.format("%02d", random.nextInt(10));

            if("".equals(atcFileId) || atcFileId == null)
            {
                // 파일 중복명 처리
                // return file ID
                rtnFileId = genId;
            }
            else
            {
                // 기존 파일 아이디에 계속 연결 시킴
                rtnFileId = atcFileId;
            }

            List<Map<String, Object>> filenames = new ArrayList<Map<String, Object>>();
            filenames = (List<Map<String, Object>>)sendInfo.get("tempFileList");

            List<Map<String, Object>> recvFileData = new ArrayList<Map<String, Object>>();
            recvFileData = (List<Map<String, Object>>)sendInfo.get("recvFileList");

            //filenames 에서 recvFileData와 같은 파일을 제거한다. 
            if(recvFileData != null)
            {
                for(int i = 0; i < recvFileData.size(); i++)
                {
                    int target = -1;
                    for(int t = 0; t < filenames.size(); t++)
                    {
                        String tmp = (String)recvFileData.get(i).get("name");
                        if(tmp != null && tmp.equals(filenames.get(t).get("name").toString()))
                        {
                            target = t;
                            break;
                        }
                    }
                    if(target >= 0)
                    {
                        filenames.remove(target);
                    }
                }
            }

            for(int i = 0; i < mf.size(); i++)
            {
                // 본래 파일명
                String originalfileName = (String)filenames.get(i).get("name");

                String saveFileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "_" + originalfileName;

                File path22 = new File(".");
                String saveUrl = path22.getCanonicalPath();

                if(saveUrl.indexOf("saehan") >= 0)
                {   // 윈도우 개발환경 : saehan 프로젝트명 검사
                    saveUrl = saveUrl + "/" + "src" + "/" + "main" + "/" + "webapp" + "/" + "upload" + "/";
                }
                else
                { // 리눅스 운용환경 : seian 프로젝트명 검사
                    saveUrl = "/jboss/domains/upload/";
                }

                // 디렉토리 검사
                File dir = new File(saveUrl);
                if(!dir.isDirectory())
                {
                    dir.mkdirs();
                }

                System.out.println("URL11@@@@ NEW @@@@@1111 : " + saveUrl);

                String saveFullPath = saveUrl + saveFileName; // 저장 될 파일 경로

                mf.get(i).transferTo(new File(saveFullPath)); // 파일 저장

                sendInfo.put("file_name", saveFileName);
                sendInfo.put("file_path", saveFullPath);
                res = attach_fileMapper.insertFileData(sendInfo);
            }

        }
        catch(Exception e)
        {
            throw new com.anylogic.iot.base.exception.KTBCCException(e.getMessage());
        }

        return res;


    }
    @SuppressWarnings("unchecked")
    public int deleteFileData(Map<String, Object> sendInfo)
    {

        String rtnFileId = "";
        int res = 0;
        res = attach_fileMapper.deleteFileData(sendInfo);
        //실제 파일 삭제 코드 추가
        
        return res;

    }
}
