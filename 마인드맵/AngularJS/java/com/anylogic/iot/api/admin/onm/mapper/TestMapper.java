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


public interface TestMapper {


    public int insertTest(Map<String, Object> parameter);
    // 'deleteTest', mapping
    public int deleteTest(Map<String, Object> parameter);
    // 'selectTest' mapping
    public List<Object> selectTest(Map<String, Object> parameter);

    // public int updateMenuL(Map<String, Object> parameter);
    //
    // public int updateMenuM(Map<String, Object> parameter);
    //
    // public int updateMenuS(Map<String, Object> parameter);
    //
    // public int updateMenuD(Map<String, Object> parameter);
    //
    // public int deleteMenuL(Map<String, Object> parameter);
    //
    // public int deleteMenuM(Map<String, Object> parameter);
    //
    // public int deleteMenuS(Map<String, Object> parameter);
    //
    // public int deleteMenuD(Map<String, Object> parameter);

}
