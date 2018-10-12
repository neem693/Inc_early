/*
   ADP Platform version 1.0

   Copyright ⓒ 2018 anylogic corp. All rights reserved.

   This is a proprietary software of anylogic corp, and you may not use this file except in
   compliance with license agreement with anylogic corp. Any redistribution or use of this
   software, with or without modification shall be strictly prohibited without prior written
   approval of anylogic corp, and the copyright notice above does not evidence any actual or
   intended publication of such software.
*/


package com.anylogic.iot.api.admin.tenant.mapper;

import java.util.List;
import java.util.Map;


public interface Booking_participantMapper {


    public List<Object> downGuests(Map<String, Object> parameter);

    public List<Map<String, Object>> downGuestsExcel(Map<String, Object> parameter);

}
