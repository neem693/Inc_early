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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anylogic.iot.Version;
import com.anylogic.iot.api.admin.onm.mapper.CodeMapper;
import com.anylogic.iot.base.excel.service.ExcelSVC;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CodeService {

	@Autowired
	private CodeMapper codeMapper;

	@Autowired
	private ExcelSVC excelSVC;


    public List<Object> selectCode(Map<String, Object> parameter){
    	return codeMapper.selectCode(parameter);
    }


    public List<Object> selectCodeSearch(Map<String, Object> parameter){
    	return codeMapper.selectCodeSearch(parameter);
    }


    public int insertCode(Map<String, Object> parameter){
    	return codeMapper.insertCode(parameter);
    }


    public int updateCode(Map<String, Object> parameter){
    	return codeMapper.updateCode(parameter);
    }


    public int deleteCode(Map<String, Object> parameter){
    	return codeMapper.deleteCode(parameter);
    }


}
