/*
   ADP Platform version 1.0

   Copyright ⓒ 2018 anylogic corp. All rights reserved.

   This is a proprietary software of anylogic corp, and you may not use this file except in
   compliance with license agreement with anylogic corp. Any redistribution or use of this
   software, with or without modification shall be strictly prohibited without prior written
   approval of anylogic corp, and the copyright notice above does not evidence any actual or
   intended publication of such software.
*/


package com.anylogic.iot.api.admin.onm.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anylogic.iot.api.admin.onm.mapper.BoardMapper;
import com.anylogic.iot.api.util.excel.ExcelRead;
import com.anylogic.iot.api.util.excel.ExcelReadOption;
import com.anylogic.iot.base.excel.service.ExcelSVC;

@Service
@Transactional
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;

	@Autowired
	private ExcelSVC excelSVC;

	
	public void excelUpload(File destFile) throws Exception{
        ExcelReadOption excelReadOption = new ExcelReadOption();
        excelReadOption.setFilePath(destFile.getAbsolutePath());
        excelReadOption.setOutputColumns("A","B","C","D","E","F");
        excelReadOption.setStartRow(2);
        
        
        List<Map<String, String>>excelContent = ExcelRead.read(excelReadOption);
        
        for(Map<String, String> article: excelContent){
            System.out.println(article.get("A"));
            System.out.println(article.get("B"));
            System.out.println(article.get("C"));
            System.out.println(article.get("D"));
            System.out.println(article.get("E"));
            System.out.println(article.get("F"));
            
        }
	}


    public int insertBoard(Map<String, Object> parameter){
    	return boardMapper.insertBoard(parameter);
    }
    
    public int reinsertFile(Map<String, Object> parameter) {
    	return boardMapper.reinsertFile(parameter);
    }
    
    public int resetBoardFile(Map<String, Object> parameter) {
		return boardMapper.resetBoardFile(parameter);
	}
    
    public List<Object> getBoardFile(Map<String, Object> parameter) {
		return boardMapper.getBoardFile(parameter);
	}
    public int updateBoard(Map<String, Object> parameter){
    	return boardMapper.updateBoard(parameter);
    }


    public int addViewCount(Map<String, Object> parameter){
    	return boardMapper.addViewCount(parameter);
    }


    public List<Object> selectBoard(Map<String, Object> parameter){
    	return boardMapper.selectBoard(parameter);
    }


    public int deleteBoard(Map<String, Object> parameter){
    	return boardMapper.deleteBoard(parameter);
    }


}
