/*
   ADP Platform version 1.0

   Copyright ⓒ 2018 anylogic corp. All rights reserved.

   This is a proprietary software of anylogic corp, and you may not use this file except in
   compliance with license agreement with anylogic corp. Any redistribution or use of this
   software, with or without modification shall be strictly prohibited without prior written
   approval of anylogic corp, and the copyright notice above does not evidence any actual or
   intended publication of such software.
*/


package com.anylogic.iot.api.admin.onm.mapper;

import java.util.List;
import java.util.Map;


public interface MenuMapper {


    public List<Object> selectMenu(Map<String, Object> parameter);

    public List<Object> selectMenuL(Map<String, Object> parameter);

    public List<Object> selectMenuM(Map<String, Object> parameter);

    public List<Object> selectMenuS(Map<String, Object> parameter);

    public List<Object> selectMenuD(Map<String, Object> parameter);

    public List<Object> viewMenu(Map<String, Object> parameter);

    public List<Object> selectIDL(Map<String, Object> parameter);

    public List<Object> selectIDM(Map<String, Object> parameter);

    public List<Object> selectIDS(Map<String, Object> parameter);

    public List<Object> selectIDD(Map<String, Object> parameter);

    public int insertMenuL(Map<String, Object> parameter);

    public int insertMenuM(Map<String, Object> parameter);

    public int insertMenuS(Map<String, Object> parameter);

    public int insertMenuD(Map<String, Object> parameter);

    public int updateMenuL(Map<String, Object> parameter);

    public int updateMenuM(Map<String, Object> parameter);

    public int updateMenuS(Map<String, Object> parameter);

    public int updateMenuD(Map<String, Object> parameter);

    public int deleteMenuL(Map<String, Object> parameter);

    public int deleteMenuM(Map<String, Object> parameter);

    public int deleteMenuS(Map<String, Object> parameter);

    public int deleteMenuD(Map<String, Object> parameter);

    public List<Object> selectMenuAll(Map<String, Object> parameter);

    public List<Object> selectMyMenu(Map<String, Object> parameter);

}
