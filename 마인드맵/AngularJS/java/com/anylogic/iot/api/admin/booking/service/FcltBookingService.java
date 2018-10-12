
package com.anylogic.iot.api.admin.booking.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anylogic.iot.api.admin.booking.mapper.FcltBookingMapper;

/**
 * ClassName : FcltBookingService
 * 
 * @version : 1.0
 * @date    : 2017. 12. 28.
 * @author  : kpbaek 
 * @brief   :
 */

@Service
public class FcltBookingService {

	@Autowired
	private FcltBookingMapper fcltBookingMapper;

	public List<Object> getFcList(Map<String, Object> parameter){
		return fcltBookingMapper.getFcList(parameter);
	}
	
	 public List<Object> getFcBookingList(Map<String, Object> parameter){
		 return fcltBookingMapper.getFcBookingList(parameter);
	 }

	 public Map<?, ?> getFcReserveDetail(Map<String, Object> parameter){
		 return fcltBookingMapper.getFcReserveDetail(parameter);
	 }
	 
	 public Map<?, ?> cancelFcReserve(Map<String, Object> parameter){
		 return fcltBookingMapper.cancelFcReserve(parameter);
	 }

	 public List<Object> checkMyFcReserveTime(Map<String, Object> parameter){
		 return fcltBookingMapper.checkMyFcReserveTime(parameter);
	 }

	 public List<Object> checkFcReserveTime(Map<String, Object> parameter){
		 return fcltBookingMapper.checkFcReserveTime(parameter);
	 }

	 public int registFcReserve(Map<String, Object> parameter){
		 return fcltBookingMapper.insertFcReserve(parameter);
	 }
	 
	 public Map<?, ?> getRemainTime(Map<String, Object> parameter){
		 return fcltBookingMapper.getRemainTime(parameter);
	 }
	 
	 public String getMaxBookingFcRoomSeq(Map<String, Object> parameter){
		 return fcltBookingMapper.getMaxBookingFcRoomSeq(parameter);
	 }
}
