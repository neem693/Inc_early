
package com.anylogic.iot.api.admin.calc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anylogic.iot.api.admin.calc.mapper.CalcMapper;
import com.anylogic.iot.api.admin.calc.vo.MeetinRoomUseStatusVO;

/**
 * ClassName : FcltUseStatService
 * 
 * @version : 1.0
 * @date    : 2017. 12. 28.
 * @author  : kpbaek 
 * @brief   :
 */

@Service
public class FcltUseStatService {

	@Autowired
	private CalcMapper calcMapper;
	
	 public List<Object> getFacilityList(Map<String, Object> parameter){
			return calcMapper.getFacilityList(parameter);
	 }

	 public List<Object> getFacilityUseStatusList(Map<String, Object> parameter){
			return calcMapper.getFacilityUseStatusList(parameter);
	 }
	 
	 public List<MeetinRoomUseStatusVO> getFacilityUseStatusExcel(Map<String, Object> parameter){
			return calcMapper.getFacilityUseStatusExcel(parameter);
	 }

	 public List<Object> getFacilityUseStatusCalendar(Map<String, Object> parameter){
			return calcMapper.getFacilityUseStatusCalendar(parameter);
	 }	 
}
