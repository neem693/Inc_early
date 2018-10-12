
package com.anylogic.iot.api.admin.booking.mapper;

import java.util.List;
import java.util.Map;

/**
 * ClassName : FcltBookingMapper
 * 
 * @version : 1.0
 * @date    : 2017. 12. 28.
 * @author  : kpbaek 
 * @brief   :
 */

public interface FcltBookingMapper {

	public List<Object> getFcList(Map<String, Object> parameter);
	
	public List<Object> getFcBookingList(Map<String, Object> parameter);
	
	public Map<?, ?> getFcReserveDetail(Map<String, Object> parameter);

	public  Map<?, ?> cancelFcReserve(Map<String, Object> parameter);
	
	public  Map<?, ?> getRemainTime(Map<String, Object> parameter);
	
	public List<Object> checkMyFcReserveTime(Map<String, Object> parameter);
	
	public List<Object> checkFcReserveTime(Map<String, Object> parameter);
	
	public int insertFcReserve(Map<String, Object> parameter);

	public String getMaxBookingFcRoomSeq(Map<String, Object> parameter);
}



