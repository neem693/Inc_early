
package com.anylogic.iot.api.admin.booking.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anylogic.iot.api.admin.booking.mapper.SlpRmBookingMapper;

/**
 * ClassName : SlpRmBookingService
 * 
 * @version : 1.0
 * @date    : 2018. 01. 11.
 * @author  : kgh 
 * @brief   :
 */

@Service
public class SlpRmBookingService {

	@Autowired
	private SlpRmBookingMapper slpRmBookingMapper;

	public List<Object> getSlpList(Map<String, Object> parameter){
		return slpRmBookingMapper.getSlpList(parameter);
	}
		
	 public List<Object> getSlpBookingList(Map<String, Object> parameter){
		 return slpRmBookingMapper.getSlpBookingList(parameter);
	 }

	 public Map<?, ?> getSlpReserveDetail(Map<String, Object> parameter){
		 return slpRmBookingMapper.getSlpReserveDetail(parameter);
	 }
	 
	 public Map<?, ?> cancelSlpReserve(Map<String, Object> parameter){
		 return slpRmBookingMapper.cancelSlpReserve(parameter);
	 }
	 
	 public List<Object> checkMySlpReserveTime(Map<String, Object> parameter){
		 return slpRmBookingMapper.checkMySlpReserveTime(parameter);
	 }

	 public List<Object> checkSlpReserveTime(Map<String, Object> parameter){
		 return slpRmBookingMapper.checkSlpReserveTime(parameter);
	 }

	 public int registSlpReserve(Map<String, Object> parameter){
		 return slpRmBookingMapper.insertSlpReserve(parameter);
	 }
}
