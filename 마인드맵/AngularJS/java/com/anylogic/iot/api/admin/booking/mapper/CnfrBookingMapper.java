
package com.anylogic.iot.api.admin.booking.mapper;

import java.util.List;
import java.util.Map;

/**
 * ClassName : CnfrBookingMapper
 * 
 * @version : 1.0
 * @date    : 2017. 12. 28.
 * @author  : kpbaek 
 * @brief   :
 */

public interface CnfrBookingMapper {
	
	public int resetIncidental(Map<String, Object> parameter);

	public List<Object> getIncidental(Map<String, Object> parameter);

	public int insertIncidental(Map<String, Object> parameter);

	public List<Object> getComcodeSecond(Map<String, Object> parameter);

	public List<Object> getComcodeFirst(Map<String, Object> parameter);

	List<Map<String, Object>> getBookingMemberList(Map<String, Object> uploadInfo);
	
	public Map<String, Object> getConfInfo(Map<String, Object> parameter);

	public List<Object> getBranchList(Map<String, Object> parameter);
	
	public List<Object> getConferenceRoomFloorList(Map<String, Object> parameter);
	
	public List<Object> getMeetingRoomList(Map<String, Object> parameter);
	
	public List<Object> getCnfrBookingList(Map<String, Object> parameter);
	
	public List<Object> getMeetingRoomComboList(Map<String, Object> parameter);	
	
	public Map<?, ?> getMeetingRoomReserveDetailPopup(Map<String, Object> parameter);
	
	public Map<?, ?> getMeetingRoomReserveDetail(Map<String, Object> parameter);
	
	public List<Object> getInnerMeetingMember(Map<String, Object> parameter);

	public List<Object> getOuterMeetingMember(Map<String, Object> parameter);

	public Map<?, ?> cancelMeetingRoomReserve(Map<String, Object> parameter);

	public List<Object> getMeetingMemberList(Map<String, Object> parameter);	
	
	public List<Object> getMeetingMemberListForEdit(Map<String, Object> parameter);
	
	public List<Object> checkMeetingRoomReserveTime(Map<String, Object> parameter);
	
	public String getMaxBookingConferenceIdSeq(Map<String, Object> parameter);
	
	public int insertMeetingRoomReserve(Map<String, Object> parameter);
	
	public int insertBookingHistory(Map<String, Object> parameter);
	
	public Map<?, ?> deleteMeetingRoomMember(Map<String, Object> parameter);

	public int insertMeetingMember(Map<String, Object> parameter);
	
	public int updateMeetingRoomReserve(Map<String, Object> parameter);
	
	public  Map<?, ?> getRemainTime(Map<String, Object> parameter);

	public  Map<?, ?> getBookingHistory(Map<String, Object> parameter);
	 
	public int updateTimeUpDown(Map<String, Object> parameter);	
	public int updateTimeUpDown2(Map<String, Object> parameter);
	public int updateTimeUpDown3(Map<String, Object> parameter);
}



