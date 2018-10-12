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

    public List<Object> selectMenuAll(Map<String, Object> parameter);

    public List<Object> selectMyMenu(Map<String, Object> parameter);

}
