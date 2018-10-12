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
import com.anylogic.iot.api.admin.onm.mapper.MenuMapper;
import com.anylogic.iot.base.excel.service.ExcelSVC;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Autowired
	private ExcelSVC excelSVC;


    public List<Object> selectMenu(Map<String, Object> parameter){
    	return menuMapper.selectMenu(parameter);
    }


    public List<Object> selectMenuL(Map<String, Object> parameter){
    	return menuMapper.selectMenuL(parameter);
    }


    public List<Object> selectMenuM(Map<String, Object> parameter){
    	return menuMapper.selectMenuM(parameter);
    }


    public List<Object> selectMenuS(Map<String, Object> parameter){
    	return menuMapper.selectMenuS(parameter);
    }


    public List<Object> selectMenuD(Map<String, Object> parameter){
    	return menuMapper.selectMenuD(parameter);
    }


    public List<Object> viewMenu(Map<String, Object> parameter){
    	return menuMapper.viewMenu(parameter);
    }


    public List<Object> selectIDL(Map<String, Object> parameter){
    	return menuMapper.selectIDL(parameter);
    }


    public List<Object> selectIDM(Map<String, Object> parameter){
    	return menuMapper.selectIDM(parameter);
    }


    public List<Object> selectIDS(Map<String, Object> parameter){
    	return menuMapper.selectIDS(parameter);
    }


    public List<Object> selectIDD(Map<String, Object> parameter){
    	return menuMapper.selectIDD(parameter);
    }


    public int insertMenuL(Map<String, Object> parameter){
    	return menuMapper.insertMenuL(parameter);
    }


    public int insertMenuM(Map<String, Object> parameter){
    	return menuMapper.insertMenuM(parameter);
    }


    public int insertMenuS(Map<String, Object> parameter){
    	return menuMapper.insertMenuS(parameter);
    }


    public int insertMenuD(Map<String, Object> parameter){
    	return menuMapper.insertMenuD(parameter);
    }


    public int updateMenuL(Map<String, Object> parameter){
    	return menuMapper.updateMenuL(parameter);
    }


    public int updateMenuM(Map<String, Object> parameter){
    	return menuMapper.updateMenuM(parameter);
    }


    public int updateMenuS(Map<String, Object> parameter){
    	return menuMapper.updateMenuS(parameter);
    }


    public int updateMenuD(Map<String, Object> parameter){
    	return menuMapper.updateMenuD(parameter);
    }


    public int deleteMenuL(Map<String, Object> parameter){
    	return menuMapper.deleteMenuL(parameter);
    }


    public int deleteMenuM(Map<String, Object> parameter){
    	return menuMapper.deleteMenuM(parameter);
    }


    public int deleteMenuS(Map<String, Object> parameter){
    	return menuMapper.deleteMenuS(parameter);
    }


    public int deleteMenuD(Map<String, Object> parameter){
    	return menuMapper.deleteMenuD(parameter);
    }


    public List<Object> selectMenuAll(Map<String, Object> parameter){
    	return menuMapper.selectMenuAll(parameter);
    }


    public List<Object> selectMyMenu(Map<String, Object> parameter){
    	return menuMapper.selectMyMenu(parameter);
    }


}
