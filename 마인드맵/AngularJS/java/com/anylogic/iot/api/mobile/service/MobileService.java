
package com.anylogic.iot.api.mobile.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anylogic.iot.api.mobile.mapper.MobileMapper;
import com.anylogic.iot.base.excel.service.ExcelSVC;

/**
 * <PRE>
 *  ClassName : AdminService
 * </PRE>
 * @version : 1.0
 * @date    : 2015. 5. 21. 오전 9:56:36
 * @author  : jkkim
 * @brief   :
 */

@Service
public class MobileService {  

	@Autowired
	private MobileMapper mobileMapper;

	@Autowired
	private ExcelSVC excelSVC;

	
	 public List<Object> getNoticeList(Map<String, Object> parameter){
			return mobileMapper.getNoticeList(parameter);
		}
	 public Map<String, Object> getLogin(Map<String, Object> parameter) {
			return mobileMapper.getLogin(parameter);
		}
	 public Map<String, Object> getPassword(Map<String, Object> parameter) {
			return mobileMapper.getPassword(parameter);
		}
	 public int updatePw(Map<String, Object> parameter) {
			return mobileMapper.updatePw(parameter);
		}
	 public Map<String, Object> getNotice(Map<String, Object> parameter) {
			return mobileMapper.getNotice(parameter);
		}
	 public int setRegUser(Map<String, Object> parameter) {
			return mobileMapper.setRegUser(parameter);
		}
	 public Map<String, Object> getUserInfo(Map<String, Object> parameter) {
			return mobileMapper.getUserInfo(parameter);
		}
	 public int setUserInfo(Map<String, Object> parameter) {
			return mobileMapper.setUserInfo(parameter);
		}	 
	 public List<Object> getReservationList(Map<String, Object> parameter){
			return mobileMapper.getReservationList(parameter);
		}
	 public Map<String, Object> getReservation(Map<String, Object> parameter) {
			return mobileMapper.getReservation(parameter);
		}
	 public int setReservationCancel(Map<String, Object> parameter) {
			return mobileMapper.setReservationCancel(parameter);
		}	
	 public int setReservationMemberCancel(Map<String, Object> parameter) {
			return mobileMapper.setReservationMemberCancel(parameter);
		}	
	 public List<Object> getMemberList(Map<String, Object> parameter){
			return mobileMapper.getMemberList(parameter); 
		}
	 public int setReservationService(Map<String, Object> parameter) {
			return mobileMapper.setReservationService(parameter);
		}	
	 public List<Object> getQuestionList(Map<String, Object> parameter){
			return mobileMapper.getQuestionList(parameter); 
		}
	 public Map<String, Object> getQuestion(Map<String, Object> parameter) {
			return mobileMapper.getQuestion(parameter);
		}
	 public int setQuestionDelete(Map<String, Object> parameter) {
			return mobileMapper.setQuestionDelete(parameter);
		}	
	 public int setQuestionReplyDelete(Map<String, Object> parameter) {
			return mobileMapper.setQuestionReplyDelete(parameter);
		}	
	 public int setQuestionRegistration(Map<String, Object> parameter) {
			return mobileMapper.setQuestionRegistration(parameter);
		}	
	 public List<Object> getProgramList(Map<String, Object> parameter){
			return mobileMapper.getProgramList(parameter); 
		}
	 public Map<String, Object> getProgram(Map<String, Object> parameter) {
			return mobileMapper.getProgram(parameter);
		}
	 public int setProgramRegistration(Map<String, Object> parameter) {
			return mobileMapper.setProgramRegistration(parameter);
		}	
	 public List<Map<String, Object>> getRoomList(Map<String, Object> parameter){
			return mobileMapper.getRoomList(parameter);  
		}
	 public List<Map<String, Object>> getResDate(Map<String, Object> parameter){
			return mobileMapper.getResDate(parameter);  
		}
	 public int setReservationRoom(Map<String, Object> parameter) {
			return mobileMapper.setReservationRoom(parameter);
		}	
	 public int setReservationRoomMember(Map<String, Object> parameter) {
			return mobileMapper.setReservationRoomMember(parameter);
		}	
} 
