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
import com.anylogic.iot.api.admin.onm.mapper.UserMapper;
import com.anylogic.iot.base.excel.service.ExcelSVC;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestService {

	@Autowired
	private TestMapper testMapper;

	@Autowired
	private ExcelSVC excelSVC;


	/*public List<Object> selectList(Map<String, Object> parameter){
    	return testMapper.selectList(parameter);
    }*/
	
	/*public List<Object> selectList(Map<String, Object> parameter){
    	return userMapper.selectList(parameter);
    }
	
    public List<Object> login(Map<String, Object> parameter){
    	return userMapper.login(parameter);
    }


    public int insertUser(Map<String, Object> parameter){
    	return userMapper.insertUser(parameter);
    }


    public List<Object> dupID(Map<String, Object> parameter){
    	return userMapper.dupID(parameter);
    }


    public int logout(Map<String, Object> parameter){
    	return userMapper.logout(parameter);
    }


    public List<Object> selectUser(Map<String, Object> parameter){
    	return userMapper.selectUser(parameter);
    }


    public List<Map<String, Object>> selectUserExcel(Map<String, Object> parameter){
    	return userMapper.selectUserExcel(parameter);
    }


    public int updateUser(Map<String, Object> parameter){
    	return userMapper.updateUser(parameter);
    }


    public int deleteUser(Map<String, Object> parameter){
    	return userMapper.deleteUser(parameter);
    }*/


}
