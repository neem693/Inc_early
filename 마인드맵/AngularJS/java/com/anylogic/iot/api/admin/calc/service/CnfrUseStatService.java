
package com.anylogic.iot.api.admin.calc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anylogic.iot.api.admin.calc.mapper.CalcMapper;
import com.anylogic.iot.api.admin.calc.vo.MeetinRoomUseStatusVO;

/**
 * ClassName : CnfrUseStatService
 * 
 * @version : 1.0
 * @date    : 2017. 12. 28.
 * @author  : kpbaek 
 * @brief   :
 */

@Service
public class CnfrUseStatService {

	@Autowired
	private CalcMapper calcMapper;

	 public List<Object> getMeetingRoomListForCalc(Map<String, Object> parameter){
			return calcMapper.getMeetingRoomListForCalc(parameter);
	 }
	
	 public List<Object> getMeetinRoomUseStatusList(Map<String, Object> parameter){
			return calcMapper.getMeetinRoomUseStatusList(parameter);
	 }
	 
	 public List<MeetinRoomUseStatusVO> getMeetinRoomUseStatusExcel(Map<String, Object> parameter){
			return calcMapper.getMeetinRoomUseStatusExcel(parameter);
	 }

	 public List<Object> getMeetinRoomUseStatusCalendar(Map<String, Object> parameter){
			return calcMapper.getMeetinRoomUseStatusCalendar(parameter);
	 }
}
