
package com.anylogic.iot.api.admin.booking.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anylogic.iot.api.admin.booking.mapper.CnfrBookingMapper;

/**
 * ClassName : CnfrBookingService
 * 
 * @version : 1.0
 * @date    : 2017. 12. 28.
 * @author  : kpbaek 
 * @brief   :
 */

@Service
public class CnfrBookingService {

	@Autowired
	private CnfrBookingMapper cnfrBookingMapper;

	public int resetIncidental(Map<String, Object> parameter) {
		return cnfrBookingMapper.resetIncidental(parameter);
	}

	public List<Object> getIncidental(Map<String, Object> parameter) {
		return cnfrBookingMapper.getIncidental(parameter);
	}
	
	public int insertIncidental(Map<String, Object> parameter) {
		return cnfrBookingMapper.insertIncidental(parameter);
	}

	public List<Object> getComcodeSecond(Map<String, Object> parameter) {
		return cnfrBookingMapper.getComcodeSecond(parameter);
	}

	public List<Object> getComcodeFirst(Map<String, Object> parameter) {
		return cnfrBookingMapper.getComcodeFirst(parameter);
	}

	public List<Map<String, Object>> getBookingMemberList(Map<String, Object> uploadInfo) {
		return cnfrBookingMapper.getBookingMemberList(uploadInfo);
	}
	
	public Map<String, Object> getConfInfo(Map<String, Object> parameter){
		return cnfrBookingMapper.getConfInfo(parameter);
	}
	
	public List<Object> getBranchList(Map<String, Object> parameter){
		return cnfrBookingMapper.getBranchList(parameter);
	}

	public List<Object> getConferenceRoomFloorList(Map<String, Object> parameter){
		return cnfrBookingMapper.getConferenceRoomFloorList(parameter);
	}
	
	public List<Object> getMeetingRoomList(Map<String, Object> parameter){
		return cnfrBookingMapper.getMeetingRoomList(parameter);
	}
		
	 public List<Object> getCnfrBookingList(Map<String, Object> parameter){
		 return cnfrBookingMapper.getCnfrBookingList(parameter);
	 }
	 
	 public List<Object> getMeetingRoomComboList(Map<String, Object> parameter){
		 return cnfrBookingMapper.getMeetingRoomComboList(parameter);
	 }
	 
	 public Map<?, ?> getMeetingRoomReserveDetailPopup(Map<String, Object> parameter){
		 return cnfrBookingMapper.getMeetingRoomReserveDetailPopup(parameter);
	 }
	 
	 public Map<?, ?> getMeetingRoomReserveDetail(Map<String, Object> parameter){
		 return cnfrBookingMapper.getMeetingRoomReserveDetail(parameter);
	 }
	 
	 public List<Object> getInnerMeetingMember(Map<String, Object> parameter){
		 return cnfrBookingMapper.getInnerMeetingMember(parameter);
	 }

	 public List<Object> getOuterMeetingMember(Map<String, Object> parameter){
		 return cnfrBookingMapper.getOuterMeetingMember(parameter);
	 }
	 
	 public  Map<?, ?> cancelMeetingRoomReserve(Map<String, Object> parameter){
		 return cnfrBookingMapper.cancelMeetingRoomReserve(parameter);
	 }	 
	 
	 public List<Object> getMeetingMemberList(Map<String, Object> parameter){
		 return cnfrBookingMapper.getMeetingMemberList(parameter);
	 }
	 
	 public List<Object> getMeetingMemberListForEdit(Map<String, Object> parameter){
		 return cnfrBookingMapper.getMeetingMemberListForEdit(parameter);
	 }
	 
	 public List<Object> checkMeetingRoomReserveTime(Map<String, Object> parameter){
		 return cnfrBookingMapper.checkMeetingRoomReserveTime(parameter);
	 }
	 
	 public String getMaxBookingConferenceIdSeq(Map<String, Object> parameter){
		 return cnfrBookingMapper.getMaxBookingConferenceIdSeq(parameter);
	 }
	 
	 public int registMeetingRoomReserve(Map<String, Object> parameter){
		 return cnfrBookingMapper.insertMeetingRoomReserve(parameter);
	 }
	 
	 public int insertBookingHistory(Map<String, Object> parameter){
		 return cnfrBookingMapper.insertBookingHistory(parameter);
	 }
	 
	 public  Map<?, ?> deleteMeetingRoomMember(Map<String, Object> parameter){
		 return cnfrBookingMapper.deleteMeetingRoomMember(parameter);
	 }	 
	 
	 public int registMeetingRoomMember(Map<String, Object> parameter){
		 return cnfrBookingMapper.insertMeetingMember(parameter);
	 }
	 
	 public int updateMeetingRoomReserve(Map<String, Object> parameter){
		 return cnfrBookingMapper.updateMeetingRoomReserve(parameter);
	 }
	 
	 public Map<?, ?> getRemainTime(Map<String, Object> parameter){
		 return cnfrBookingMapper.getRemainTime(parameter);
	 }

	 public Map<?, ?> getBookingHistory(Map<String, Object> parameter){
		 return cnfrBookingMapper.getBookingHistory(parameter);
	 }
	 
	 public int updateTimeUpDown(Map<String, Object> parameter){
		 return cnfrBookingMapper.updateTimeUpDown(parameter);
	 }
	 public int updateTimeUpDown2(Map<String, Object> parameter){
		 return cnfrBookingMapper.updateTimeUpDown2(parameter);
	 }
	 public int updateTimeUpDown3(Map<String, Object> parameter){
		 return cnfrBookingMapper.updateTimeUpDown3(parameter);
	 }
}
