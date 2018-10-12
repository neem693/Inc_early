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
import com.anylogic.iot.api.admin.onm.mapper.AuthMapper;
import com.anylogic.iot.base.excel.service.ExcelSVC;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService {

	@Autowired
	private AuthMapper authMapper;

	@Autowired
	private ExcelSVC excelSVC;


    public List<Object> myRole(Map<String, Object> parameter){
    	return authMapper.myRole(parameter);
    }


    public int insertAuth(Map<String, Object> parameter){
    	return authMapper.insertAuth(parameter);
    }


    public List<Object> selectAuth(Map<String, Object> parameter){
    	return authMapper.selectAuth(parameter);
    }


    public int updateAuth(Map<String, Object> parameter){
    	return authMapper.updateAuth(parameter);
    }


    public int deleteAuth(Map<String, Object> parameter){
    	return authMapper.deleteAuth(parameter);
    }


    public List<Object> viewAuth(Map<String, Object> parameter){
    	return authMapper.viewAuth(parameter);
    }


}
