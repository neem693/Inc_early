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
import com.anylogic.iot.api.admin.onm.mapper.TestMapper;
import com.anylogic.iot.api.admin.onm.mapper.TestMapper2;
import com.anylogic.iot.api.admin.onm.mapper.UserMapper;
import com.anylogic.iot.base.excel.service.ExcelSVC;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestService2 {

	@Autowired
	private TestMapper2 testMapper2;

	
	
	
	public List<Object> selectList(Map<String, Object> parameter){
		 return testMapper2.selectList(parameter);
	 }
	
	 public int insertTest2(Map<String, Object> parameter){
	    	return testMapper2.insertTest2(parameter);
	    }
	 
	 public List<Object> searchTest(Map<String, Object> parameter){
		 return testMapper2.searchTest(parameter);
	 }
	 
	 public int deleteOne(Map<String, Object> parameter) {
		 System.out.println("이것은 딜리트 테스트입니다.");
		 return testMapper2.deleteOne(parameter);
		 
	 }

	public int updateOne(Map<String, Object> parameter) {
		// TODO Auto-generated method stub
		System.out.println("이것은 업데이트 입니다.");
		
		
		return testMapper2.updateOne(parameter);
	}
	 
    
}
