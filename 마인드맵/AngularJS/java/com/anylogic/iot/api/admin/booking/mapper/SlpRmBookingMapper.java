
package com.anylogic.iot.api.admin.booking.mapper;

import java.util.List;
import java.util.Map;

/**
 * ClassName : SlpRmBookingMapper
 * 
 * @version : 1.0
 * @date    : 2017. 12. 28.
 * @author  : kpbaek 
 * @brief   :
 */

public interface SlpRmBookingMapper {

	public List<Object> getSlpList(Map<String, Object> parameter);
	
	public List<Object> getSlpBookingList(Map<String, Object> parameter);
	
	public Map<?, ?> getSlpReserveDetail(Map<String, Object> parameter);
	
	public Map<?, ?> cancelSlpReserve(Map<String, Object> parameter);
	
	public List<Object> checkMySlpReserveTime(Map<String, Object> parameter);
	
	public List<Object> checkSlpReserveTime(Map<String, Object> parameter);
	
	public int insertSlpReserve(Map<String, Object> parameter);
}



