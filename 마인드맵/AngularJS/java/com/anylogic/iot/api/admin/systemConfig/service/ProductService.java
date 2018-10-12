/*
   ADP Platform version 1.0

   Copyright ⓒ 2018 anylogic corp. All rights reserved.

   This is a proprietary software of anylogic corp, and you may not use this file except in
   compliance with license agreement with anylogic corp. Any redistribution or use of this
   software, with or without modification shall be strictly prohibited without prior written
   approval of anylogic corp, and the copyright notice above does not evidence any actual or
   intended publication of such software.
*/


package com.anylogic.iot.api.admin.systemConfig.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anylogic.iot.Version;
import com.anylogic.iot.api.admin.systemConfig.mapper.ProductMapper;
import com.anylogic.iot.base.excel.service.ExcelSVC;


@Service
public class ProductService {

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private ExcelSVC excelSVC;


    public int insertPD(Map<String, Object> parameter){
    	return productMapper.insertPD(parameter);
    }


    public int updatePD(Map<String, Object> parameter){
    	return productMapper.updatePD(parameter);
    }


    public List<Object> selectPD(Map<String, Object> parameter){
    	return productMapper.selectPD(parameter);
    }


    public int deletePD(Map<String, Object> parameter){
    	return productMapper.deletePD(parameter);
    }


}
