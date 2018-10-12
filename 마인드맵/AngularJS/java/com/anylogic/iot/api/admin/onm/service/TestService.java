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
import com.anylogic.iot.api.admin.onm.mapper.UserMapper;
import com.anylogic.iot.base.excel.service.ExcelSVC;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestService {

	@Autowired
	private TestMapper testMapper;

	public int insertTest(Map<String, Object> parameter) {
		return testMapper.insertTest(parameter);
	}

	public int deleteTest(Map<String, Object> parameter) {
		return testMapper.deleteTest(parameter);
	}

	// public int selectTest(Map<String, Object> parameter){
	public List<Object> selectTest(Map<String, Object> parameter) {
		return testMapper.selectTest(parameter);
	}
}
