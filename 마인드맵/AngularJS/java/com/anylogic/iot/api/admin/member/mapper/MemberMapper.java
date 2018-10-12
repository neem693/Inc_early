
package com.anylogic.iot.api.admin.member.mapper;

import java.util.List;
import java.util.Map;

import com.anylogic.iot.api.admin.member.vo.MemberInfoVO;
import com.anylogic.iot.api.admin.member.vo.SendMsgLogVO;

public interface MemberMapper {

	public List<Object> selectMemberList(Map<String, Object> parameter);
	public List<Object> selectMemberListAll(Map<String, Object> parameter);

	public List<MemberInfoVO> selectMemberListExcel(Map<String, Object> parameter);

	public void updateMemberList(Map<String, Object> parameter);

	public List<Object> selectMemberDetail(Map<String, Object> parameter);

	public void updateMemberPermit(Map<String, Object> parameter);
	public void updateMemberDetail(Map<String, Object> parameter);

	public List<Object> selectSendingHistoryList(Map<String, Object> parameter);
	public void deleteSendingHistoryList(Map<String, Object> parameter);

	public List<Object> selectSendingHistoryDetail(Map<String, Object> parameter);
	public List<Object> selectSendingHistoryReceiver(Map<String, Object> parameter);
	public int insertMember(Map<String, Object> parameter);
	public List<Object> selectCompanyInfo(Map<String, Object> parameter);
	public List<Object> selectCompanyInfo2(Map<String, Object> parameter);
	public void insertSendingHistorySMS(SendMsgLogVO vo);
	public void insertSendingHistorySmsReceiver(Map<String, Object> parameter);
	public void insertSendingHistoryEmail(SendMsgLogVO vo);
	public void insertSendingHistoryEmailReceiver(Map<String, Object> parameter);

	public int setRegDevice(Map<String, Object> parameter);
}



