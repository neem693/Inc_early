package com.anylogic.iot.api.admin.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anylogic.iot.api.admin.member.mapper.MemberMapper;
import com.anylogic.iot.api.admin.member.vo.MemberInfoVO;
import com.anylogic.iot.api.admin.member.vo.SendMsgLogVO;

/**
 * 
 * @author kim
 *
 */
@Service
public class MemberService {

	@Autowired
	private MemberMapper myMapper;

	public List<Object> selectMemberList(Map<String, Object> parameter) {
		return myMapper.selectMemberList(parameter);
	}

	public List<Object> selectMemberListAll(Map<String, Object> parameter) {
		return myMapper.selectMemberListAll(parameter);
	}

	public List<MemberInfoVO> selectMemberListExcel(Map<String, Object> parameter) {
		return myMapper.selectMemberListExcel(parameter);
	}

	public void updateMemberList(Map<String, Object> parameter) {
		myMapper.updateMemberList(parameter);
	}

	public List<Object> selectMemberDetail(Map<String, Object> parameter) {
		return myMapper.selectMemberDetail(parameter);
	}

	public void updateMemberPermit(Map<String, Object> parameter) {
		myMapper.updateMemberPermit(parameter);
	}

	public void updateMemberDetail(Map<String, Object> parameter) {
		myMapper.updateMemberDetail(parameter);
	}

	public void insertSendingHistorySMS(Map<String, Object> parameter) {
		SendMsgLogVO vo = new SendMsgLogVO();
		vo.setContents(parameter.get("content").toString());
		vo.setMember_id(parameter.get("member_id").toString());
		vo.setType(parameter.get("type").toString());
		myMapper.insertSendingHistorySMS(vo);
		
		parameter.put("log_id", vo.getLog_id());
		myMapper.insertSendingHistorySmsReceiver(parameter);
	}

	public void insertSendingHistoryEmail(Map<String, Object> parameter) {
		SendMsgLogVO vo = new SendMsgLogVO();
		vo.setContents(parameter.get("content").toString());
		vo.setMember_id(parameter.get("member_id").toString());
		vo.setType(parameter.get("type").toString());
		vo.setTitle(parameter.get("title").toString());
		myMapper.insertSendingHistoryEmail(vo);
		
		parameter.put("log_id", vo.getLog_id());
		myMapper.insertSendingHistoryEmailReceiver(parameter);
	}

	public List<Object> selectSendingHistoryList(Map<String, Object> parameter) {
		return myMapper.selectSendingHistoryList(parameter);
	}

	public void deleteSendingHistoryList(Map<String, Object> parameter) {
		myMapper.deleteSendingHistoryList(parameter);
	}

	public List<Object> selectSendingHistoryDetail(Map<String, Object> parameter) {
		return myMapper.selectSendingHistoryDetail(parameter);
	}

	public List<Object> selectSendingHistoryReceiver(Map<String, Object> parameter) {
		return myMapper.selectSendingHistoryReceiver(parameter);
	}

	public int insertMember(Map<String, Object> parameter) {
		return myMapper.insertMember(parameter);
	}

	public List<Object> selectCompanyInfo(Map<String, Object> parameter) {
		return myMapper.selectCompanyInfo(parameter);
	}
	
	public List<Object> selectCompanyInfo2(Map<String, Object> parameter) {
		return myMapper.selectCompanyInfo2(parameter);
	}
	public int setRegDevice(Map<String, Object> parameter) {
		return myMapper.setRegDevice(parameter);
	}

}
