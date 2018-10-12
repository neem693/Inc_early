package com.anylogic.iot.api.admin.tour.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anylogic.iot.api.admin.member.vo.SendMsgLogVO;
import com.anylogic.iot.api.admin.tour.mapper.TourMapper;
import com.anylogic.iot.api.admin.tour.vo.TourInfoVO;
import com.anylogic.iot.base.mail.MailSender;
import com.anylogic.iot.base.mail.vo.MailContentsVO;
import com.anylogic.iot.base.mail.vo.SendMailVO;

/**
 * 
 * @author kim
 *
 */
@Service
public class TourService {

	@Autowired
	private MailSender mailSender;

	@Autowired
	private TourMapper myMapper;

	public List<Map<String, Object>> getTourApplicantsListExcel(Map<String, Object> parameter) {
		return myMapper.getTourApplicantsListExcel(parameter);
	}
	
	public List<Object> getTourApplicantsList(Map<String, Object> parameter) {
		return myMapper.getTourApplicantsList(parameter);
	}

	public void deleteTourApplicantsList(Map<String, Object> target) {
		myMapper.deleteTourApplicantsList(target);
	}

	public String addTourApplicants(Map<String, Object> parameter) {
		// 이메일 보냄
		String ret = "ERROR";
		try {
			//List<Object> receivers = getManagersEmailList();
			List<String> receiver = new ArrayList<String>();
			receiver.add("sales@coworkerskorea.com");

			// 받는 사람 이메일 주소 세팅
			//for (Object tgt : receivers) {
			//	Map<String, Object> mgm = (Map<String, Object>)tgt;
			//	receiver.add(mgm.get("email").toString());
			//}

			SendMailVO vo = null;
			MailContentsVO contents = new MailContentsVO();

			vo = new SendMailVO(com.anylogic.iot.base.mail.MailConstants.MAIL_TYPE.MEMBER_INFORMATION);
			vo.setSubject("[투어신청] Coworkers 투어신청이 등록되었습니다.");

			String value = "";
			value += "이름 : " + parameter.get("name").toString() + "<br/>";
			value += "연락처 : " + parameter.get("mobile").toString() + "<br/>";
			value += "이메일 : " + parameter.get("email").toString() + "<br/>";
			value += "투어 희망 시간 : " + parameter.get("tour_date").toString() + "<br/>";
			value += "투어 희망 인원 : " + parameter.get("tour_attendee").toString() + "<br/>";
			value += "입주 희망 규모 : " + parameter.get("room_size").toString() + "<br/>";
			contents.setContents(value);
			contents.setTitle("투어신청 정보");

			vo.setSendDate(new Date());
			vo.setContent(contents);

			vo.setTo(receiver);

			mailSender.sendMailForHtml(vo);
			//memberService.insertSendingHistoryEmail(parameter);

			int t = myMapper.addTourApplicants(parameter); 
			if (t > 0) {
				ret = "SUCCESS";
			} else {
				ret = "REJECT";
			}
		} catch (Exception e) {
			ret = "ERROR";
		}

		return ret;
	}

	public List<Object> getManagersEmailList() {
		return myMapper.getManagersEmailList();
	}


}
