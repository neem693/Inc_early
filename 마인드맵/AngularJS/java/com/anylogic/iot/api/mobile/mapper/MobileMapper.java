
package com.anylogic.iot.api.mobile.mapper;

import java.util.List;
import java.util.Map;


public interface MobileMapper {
	
	public List<Object> getNoticeList(Map<String, Object> parameter);
	public Map<String, Object> getLogin(Map<String, Object> parameter);
	public Map<String, Object> getPassword(Map<String, Object> parameter);
	public int updatePw(Map<String, Object> parameter);
	public Map<String, Object> getNotice(Map<String, Object> parameter);
	public int setRegUser(Map<String, Object> parameter);
	public Map<String, Object> getUserInfo(Map<String, Object> parameter);
	public int setUserInfo(Map<String, Object> parameter);
	public List<Object> getReservationList(Map<String, Object> parameter);
	public Map<String, Object> getReservation(Map<String, Object> parameter);
	public int setReservationCancel(Map<String, Object> parameter);
	public int setReservationMemberCancel(Map<String, Object> parameter);
	public List<Object> getMemberList(Map<String, Object> parameter);
	public int setReservationService(Map<String, Object> parameter);
	public List<Object> getQuestionList(Map<String, Object> parameter);
	public Map<String, Object> getQuestion(Map<String, Object> parameter);
	public int setQuestionDelete(Map<String, Object> parameter);
	public int setQuestionReplyDelete(Map<String, Object> parameter);
	public int setQuestionRegistration(Map<String, Object> parameter);
	public List<Object> getProgramList(Map<String, Object> parameter);
	public Map<String, Object> getProgram(Map<String, Object> parameter);
	public int setProgramRegistration(Map<String, Object> parameter);
	public List<Map<String, Object>> getRoomList(Map<String, Object> parameter);
	public List<Map<String, Object>> getResDate(Map<String, Object> parameter);
	public int setReservationRoom(Map<String, Object> parameter);
	public int setReservationRoomMember(Map<String, Object> parameter);
}



