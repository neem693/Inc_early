
package com.anylogic.iot.api.admin.booking.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anylogic.iot.Version;
import com.anylogic.iot.api.admin.booking.service.CnfrBookingService;
import com.anylogic.iot.api.admin.sms.controller.smsService;
import com.anylogic.iot.base.common.ResultListForCalendarVO;
import com.anylogic.iot.base.common.ResultListVO;
import com.anylogic.iot.base.mvc.message.Messages;
      
@RestController
@RequestMapping("/" + Version.V1+"/admin/cnfrBooking")
public class CnfrBookingController {

	private static final Logger logger = LoggerFactory.getLogger(CnfrBookingController.class);

	@Autowired
	private CnfrBookingService cnfrBookingService;
	
	/*@Autowired
	private BoardService myService;
	*/
	@Autowired
	private smsService smsservice;
	
	@RequestMapping(value = "/getIncidental", method = RequestMethod.GET)
	public ResultListVO getIncidental(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(cnfrBookingService.getIncidental(parameter)); 
    	 
    	messages.addMessage("OK", "");
		return resultListVO;
	}	
	
	@RequestMapping(value = "/getComcodeSecond", method = RequestMethod.GET)
	public ResultListVO getComcodeSecond(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(cnfrBookingService.getComcodeSecond(parameter)); 
    	 
    	messages.addMessage("OK", "");
		return resultListVO;
	}	
	
	
	@RequestMapping(value = "/getComcodeFirst", method = RequestMethod.GET)
	public ResultListVO getComcodeFirst(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(cnfrBookingService.getComcodeFirst(parameter)); 
    	 
    	messages.addMessage("OK", "");
		return resultListVO;
	}	
	
	@RequestMapping(value = "/getCnfrBookingPage", method = RequestMethod.GET)
	public ResultListForCalendarVO getCnfrBookingPage(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListForCalendarVO resultListVO = new ResultListForCalendarVO();

		resultListVO.setBranchDataRows(cnfrBookingService.getBranchList(parameter));
		resultListVO.setFloorDataRows(cnfrBookingService.getConferenceRoomFloorList(parameter));
    	
    	messages.addMessage("OK", "");
		return resultListVO; 
	}
	
	@RequestMapping(value = "/getCnfrBookingList", method = RequestMethod.GET)
	public ResultListForCalendarVO getCnfrBookingList(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListForCalendarVO resultListVO = new ResultListForCalendarVO();
		
		resultListVO.setMeetingRoomDataRows(cnfrBookingService.getMeetingRoomList(parameter));
    	resultListVO.setBookingDataRows(cnfrBookingService.getCnfrBookingList(parameter)); 
    	
    	messages.addMessage("OK", "");
		return resultListVO; 
	}
	
	@RequestMapping(value = "/getCnfrBookingRegPage", method = RequestMethod.GET)
	public ResultListForCalendarVO getCnfrBookingRegPage(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListForCalendarVO resultListVO = new ResultListForCalendarVO();

		//Map<String, Object> tempMap = new HashMap<String, Object>();
		resultListVO.setMeetingRoomDataRows(cnfrBookingService.getMeetingRoomComboList(parameter));
		resultListVO.setMeetingMemberDataRows(cnfrBookingService.getMeetingMemberList(parameter));
    	
    	messages.addMessage("OK", "");
		return resultListVO; 
	}

	@RequestMapping(value = "/getMeetingRoomReserveDetailPopup", method = RequestMethod.GET)
	public Map<?, ?> getMeetingRoomReserveDetailPopup(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		Map<?, ?> resultMap = cnfrBookingService.getMeetingRoomReserveDetailPopup(parameter);
    	messages.addMessage("OK", "");
		return resultMap; 
	}
	
	@RequestMapping(value = "/getMeetingRoomReserveEditPage", method = RequestMethod.GET)
	public ResultListForCalendarVO getMeetingRoomReserveEditPage(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListForCalendarVO resultListVO = new ResultListForCalendarVO();

		resultListVO.setMeetingRoomDataRows(cnfrBookingService.getMeetingRoomComboList(parameter));
		resultListVO.setMeetingMemberDataRows(cnfrBookingService.getMeetingMemberListForEdit(parameter));
    	
    	messages.addMessage("OK", "");
		return resultListVO; 
	}
	
	@RequestMapping(value = "/getMeetingRoomReserveDetail", method = RequestMethod.GET)
	public Map<?, ?> getMeetingRoomReserveDetail(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		Map<?, ?> resultMap = cnfrBookingService.getMeetingRoomReserveDetail(parameter);
    	messages.addMessage("OK", "");
		return resultMap; 
	}

	@RequestMapping(value = "/getMeetingMemberList", method = RequestMethod.GET)
	public ResultListForCalendarVO getMeetingMemberList(@RequestParam Map<String, Object> parameter, Messages messages) {

		ResultListForCalendarVO resultListVO = new ResultListForCalendarVO();
		resultListVO.setInnerMeetingMemberDataRows(cnfrBookingService.getInnerMeetingMember(parameter));
    	resultListVO.setOutterMeetingMemberDataRows(cnfrBookingService.getOuterMeetingMember(parameter)); 
    	messages.addMessage("OK", "");
		return resultListVO; 
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateMeetingRoomReserve", method = RequestMethod.POST)
	public int updateMeetingRoomReserve(@RequestBody Map<String, Object> parameter, Messages messages) {
		
		Map<String, Object> paramApi = new HashMap<String, Object>();
		paramApi.put("booking_reference_id", parameter.get("bookingConferenceId"));
		Map<?, ?> bookingHistoryMap = cnfrBookingService.getBookingHistory(paramApi);

		String isSalesClient = parameter.get("isSalesClient").toString();
		if(isSalesClient == "true"){
			cnfrBookingService.resetIncidental(paramApi);
			
			List<Map<String, Object>> incidentalList = (List<Map<String, Object>>) parameter.get("incidentalList");
			for(int i=0;i<incidentalList.size();i++){
				//incidental 등록
				incidentalList.get(i).put("bookingConferenceId", parameter.get("bookingConferenceId"));
				cnfrBookingService.insertIncidental(incidentalList.get(i));
			}
		}
		
		int res = cnfrBookingService.updateMeetingRoomReserve(parameter);
		
		cnfrBookingService.deleteMeetingRoomMember(parameter);
		
		
		Map<String, Object> conInfo = new HashMap<>();
		conInfo = cnfrBookingService.getConfInfo(parameter);
		String confName = (String) conInfo.get("room_name");
		String floor = (String) conInfo.get("floor");
		
		List<Map<String, Object>> meetingMemberList =  (List<Map<String, Object>>) parameter.get("meetingRoomMember");
		
		for(int i=0;i<meetingMemberList.size();i++){
			
			paramApi = new HashMap<>();
			
			paramApi.put("bookingConferenceId", meetingMemberList.get(i).get("booking_conference_id"));
			paramApi.put("memberId", meetingMemberList.get(i).get("member_id"));
			paramApi.put("participantName", meetingMemberList.get(i).get("participant_name"));
			paramApi.put("companyName", meetingMemberList.get(i).get("company_name"));
			paramApi.put("phoneNumber", meetingMemberList.get(i).get("phone_number"));

			logger.debug("[by kgh] registMeetingRoomMember >> paramApi==" + paramApi.toString());
			
			//여기서 push 보냄
			
			
			
			
			//여기서 push 보냄
			List<Object> receiverInfos = null;
			Map<String, Object> uploadInfo = new HashMap<String, Object>();
			if(meetingMemberList.get(i).get("member_id") != ""){
				uploadInfo.put("writer_id", meetingMemberList.get(i).get("member_id"));
				//receiverInfos = myService.getPushReceiverInfo(uploadInfo);
			}
			if(receiverInfos != null){
				if(receiverInfos.size() >0){
			@SuppressWarnings("unchecked")
			Map<String, Object> receiverInfo = (Map<String, Object>)receiverInfos.get(0);

			try {
				
				receiverInfo.put("title", "예약 등록");
				String title = (String) parameter.get("meetingStartTime");
				if(title.isEmpty() || title == null || title == ""){
					title = "Coworkers 회의예약";
				}else{
					title = "Coworkers 회의예약(" + title + ")";
				}
				String sendStr = title+" " + confName+ " 회의가 예약되었습니다.";
				
				receiverInfo.put("message", sendStr);
				receiverInfo.put("type", "I"); // 개인
				
				String url 	= "http://222.110.254.130/mudSub/api/push/" + receiverInfo.get("device_os").toString() 
							+ "?deviceToken=" + URLEncoder.encode(receiverInfo.get("device_token").toString(), "UTF-8")
							+ "&title=" + URLEncoder.encode(receiverInfo.get("title").toString(), "UTF-8")
							+ "&message=" + URLEncoder.encode(receiverInfo.get("message").toString(), "UTF-8")
							+ "&code=0";
			
				URL obj;
				obj = new URL(url);
				HttpURLConnection conn;
				conn = (HttpURLConnection) obj.openConnection();

				conn.setRequestProperty("Content-Type", "application/json");
				conn.setDoOutput(true);

				conn.setRequestMethod("GET");

				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));

				String inputLine;
				StringBuffer responsePush = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					responsePush.append(inputLine);
				}
				in.close();

				//System.out.println(response.toString()); //결과, json결과를 parser하여 처리
			} catch (Exception e) {
				e.printStackTrace();
				//return atcFileId;
			}
			//myService.insertPushLog(receiverInfo);//이력 저장
				}
			}
			
			//여기서 sms 보내자
			if(!meetingMemberList.get(i).containsKey("member_id")){
				int sendProfNum = setRandom();
				//sendProfNum = 112233;
				//int[] sendArr = {254784,256941,950147,719584,602258,126890,425987,632014,894120,225109};
				//intValue = (int)(Math.random() * (999999 - 1))+1;
				//int idx = (int)(Math.random() * (10 ));
				//sendProfNum =sendArr[idx];
				String title = (String) parameter.get("meetingStartTime");
				if(title.isEmpty() || title == null || title == ""){
					title = "Coworkers 회의예약";
				}else{
					title = "Coworkers 회의예약(" + title + ")";
				}
				String sendStr = title+" " + confName+ " "+floor+ "층 KIOSK > 게스트 보안 출입 코드 > " + String.valueOf(sendProfNum) +" 입력";
				paramApi.put("sendProfNum",  String.valueOf(sendProfNum));

				List<String> supplierNames1 = new ArrayList<String>();
				
				String recvNum = (String) meetingMemberList.get(i).get("phone_number");
				supplierNames1.add(recvNum);
			
				String messageStr = (String) String.valueOf(sendProfNum);
				//boolean resSMS = sendSmsMessage(sendStr, supplierNames1);
				smsservice.sendSmsMessage(sendStr, supplierNames1);
			}
			
			
			res = cnfrBookingService.registMeetingRoomMember(paramApi);
		}
		
		//이전 예약건에 대한 시간 원복
		paramApi = new HashMap<String, Object>();
		paramApi.put("memberId", parameter.get("memberId"));
		paramApi.put("resv_time", bookingHistoryMap.get("booking_hour"));
		paramApi.put("selectedOfficeRentId" , parameter.get("selectedOfficeRentId"));
		cnfrBookingService.updateTimeUpDown2(paramApi);
		
		//예약 히스토리 저장
		paramApi = new HashMap<String, Object>();
		paramApi.put("time_use_type", "O");
		paramApi.put("booking_reference_id",  parameter.get("bookingConferenceId"));
		paramApi.put("booking_type", "C");
		paramApi.put("memberId", parameter.get("memberId"));
		
		int booking_hour =  (Integer) bookingHistoryMap.get("booking_hour");
		paramApi.put("booking_hour", -(booking_hour));
		
		cnfrBookingService.insertBookingHistory(paramApi);
		
		paramApi = new HashMap<>();
		paramApi.put("memberId", parameter.get("memberId"));
		paramApi.put("meetingStartTime", parameter.get("meetingStartTime"));
		paramApi.put("meetingEndTime", parameter.get("meetingEndTime"));
		paramApi.put("conferenceId" , parameter.get("conferenceId"));
		paramApi.put("selectedOfficeRentId" , parameter.get("selectedOfficeRentId"));
		
		 Map<?, ?> tempRemainTime = cnfrBookingService.getRemainTime(paramApi);
		 Double resv_time =  (Double) tempRemainTime.get("resv_time");
		 
		//수정 건 예약에 대한 시간  차감
		//paramApi = new HashMap<String, Object>();
		paramApi.put("memberId", parameter.get("memberId"));
		paramApi.put("resv_time", -(resv_time));
		
		 if( Integer.parseInt(tempRemainTime.get("payment_time").toString()) >= resv_time) {				
				paramApi.put("add_payment_time", -(resv_time));
				paramApi.put("add_default_time","0");
				
			} else {
				paramApi.put("add_default_time", -(resv_time));
				paramApi.put("add_payment_time","0");
			}
		 
		 
		 
		cnfrBookingService.updateTimeUpDown(paramApi);	

		//예약 히스토리 저장
		paramApi = new HashMap<String, Object>();
		paramApi.put("time_use_type", "O");
		paramApi.put("booking_reference_id",  parameter.get("bookingConferenceId"));
		paramApi.put("booking_type", "C");
		paramApi.put("memberId", parameter.get("memberId"));
		paramApi.put("booking_hour", resv_time);
		
		cnfrBookingService.insertBookingHistory(paramApi);
		
		if (res == 1) {
			messages.addMessage("0", "OK");
		} else {
			messages.addMessage("1", "Not OK");
		}
		return res;		
	}
		
	@RequestMapping(value = "/cancelMeetingRoomReserve", method = RequestMethod.POST)
	public boolean cancelFcReserve(@RequestBody Map<String, Object> parameter, Messages messages) {

		try {
			Map<String, Object> paramApi = new HashMap<String, Object>();
			paramApi.put("booking_reference_id", parameter.get("booking_conference_id"));
			Map<?, ?> bookingHistoryMap = cnfrBookingService.getBookingHistory(paramApi);

			cnfrBookingService.resetIncidental(paramApi);
			
			cnfrBookingService.cancelMeetingRoomReserve(parameter);
			
			Map<String, Object> paramApi2 = new HashMap<String, Object>();
			paramApi2.put("reservationNo", parameter.get("booking_conference_id"));
			List<Map<String, Object>> memList = cnfrBookingService.getBookingMemberList(parameter);
			
			

			for(int i=0;i<memList.size();i++){
				List<Object> receiverInfos = null;
				Map<String, Object> uploadInfo = new HashMap<String, Object>();
				String memberId = memList.get(i).get("member_id").toString();
				String meeting_start_time = "";
				String meeting_title = "";
				if(memberId != null){
					uploadInfo.put("writer_id", memberId);
					//receiverInfos = myService.getPushReceiverInfo(uploadInfo);
					meeting_start_time = memList.get(i).get("meeting_start_time").toString();
					meeting_title = memList.get(i).get("meeting_title").toString();
				}
				
				if(receiverInfos != null){
					if(receiverInfos.size() >0){
						
						@SuppressWarnings("unchecked")
						Map<String, Object> receiverInfo = (Map<String, Object>)receiverInfos.get(0);

						try {
							
							receiverInfo.put("title", "예약 취소");
							
							String title = (String) parameter.get("reservationStartTime");
							if(title.isEmpty() || title == null || title == ""){
								title = "Coworkers 회의예약";
							}else{
								title = "Coworkers 회의예약(" + title + ")";
							}
							String sendStr = title+" " + meeting_start_time+" "+meeting_title+ " 회의가 취소되었습니다.";
							
							receiverInfo.put("message", sendStr);
							
							
							receiverInfo.put("type", "I"); // 개인
							
							String url 	= "http://222.110.254.130/mudSub/api/push/" + receiverInfo.get("device_os").toString() 
										+ "?deviceToken=" + URLEncoder.encode(receiverInfo.get("device_token").toString(), "UTF-8")
										+ "&title=" + URLEncoder.encode(receiverInfo.get("title").toString(), "UTF-8")
										+ "&message=" + URLEncoder.encode(receiverInfo.get("message").toString(), "UTF-8")
										+ "&code=0";
						
							URL obj;
							obj = new URL(url);
							HttpURLConnection conn;
							conn = (HttpURLConnection) obj.openConnection();

							conn.setRequestProperty("Content-Type", "application/json");
							conn.setDoOutput(true);

							conn.setRequestMethod("GET");

							BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));

							String inputLine;
							StringBuffer responsePush = new StringBuffer();

							while ((inputLine = in.readLine()) != null) {
								responsePush.append(inputLine);
							}
							in.close();

							//System.out.println(response.toString()); //결과, json결과를 parser하여 처리
						} catch (Exception e) {
							e.printStackTrace();
							//return atcFileId;
						}
						//myService.insertPushLog(receiverInfo);//이력 저장
						
						
					}
				}
			}
			
			
			
			paramApi = new HashMap<String, Object>();
			paramApi.put("memberId", parameter.get("bookingMemberId"));
			//paramApi.put("resv_time", bookingHistoryMap.get("booking_hour"));
			
			if("D".equals(bookingHistoryMap.get("time_use_type")) ||"O".equals(bookingHistoryMap.get("time_use_type")) ) {
				paramApi.put("add_default_time", bookingHistoryMap.get("booking_hour"));
				paramApi.put("add_payment_time","0");
				
			} else {
				paramApi.put("add_payment_time", bookingHistoryMap.get("booking_hour"));
				paramApi.put("add_default_time","0");
			}
			paramApi.put("company_name",  parameter.get("company_name"));
			//cnfrBookingService.updateTimeUpDown(paramApi);
			cnfrBookingService.updateTimeUpDown3(paramApi);

			//예약 히스토리 저장
			paramApi = new HashMap<String, Object>();
			paramApi.put("time_use_type", bookingHistoryMap.get("time_use_type"));
			paramApi.put("booking_reference_id",  parameter.get("booking_conference_id"));
			paramApi.put("booking_type", "C");
			paramApi.put("memberId", parameter.get("memberId"));
			int booking_hour =  (Integer) bookingHistoryMap.get("booking_hour");
			paramApi.put("booking_hour", -(booking_hour));
		
			cnfrBookingService.insertBookingHistory(paramApi);
			
			messages.addMessage("OK", "Sucess!!");
			return true;
			
		} catch(Exception e) {
			messages.addMessage("NotOk", "It's failed fror cancel booking..[" + e.getMessage() + "]");
			return false;	
		}
	}
	
	@RequestMapping(value = "/checkMeetingRoomReserveTime", method = RequestMethod.GET)
	public Map<String,Object> checkMeetingRoomReserveTime(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		Map<String,Object> returnMap = new HashMap<String,Object>();
		
		logger.debug("parameter==" + parameter);

		List<Object> meetingRoomTimeDataRows = cnfrBookingService.checkMeetingRoomReserveTime(parameter);
		
		if(meetingRoomTimeDataRows.size() > 0) {
			returnMap.put("time_out", "Y");
			messages.addMessage("NotOk", "");
		} else {
			returnMap.put("time_out", "N");
			messages.addMessage("OK", "");
		}
		
		return returnMap; 		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/registMeetingRoomReserve", method = RequestMethod.POST)
	public String registMeetingRoomReserve(@RequestBody Map<String, Object> parameter, Messages messages) {

		String maxBookingConferenceIdSeq = cnfrBookingService.getMaxBookingConferenceIdSeq(parameter);
		logger.debug("[by kgh] maxBookingConferenceIdSeq==" + maxBookingConferenceIdSeq);
		
		String isSalesClient = parameter.get("isSalesClient").toString();
		if(isSalesClient == "true"){
			List<Map<String, Object>> incidentalList = (List<Map<String, Object>>) parameter.get("incidentalList");
			for(int i=0;i<incidentalList.size();i++){
				//incidental 등록
				incidentalList.get(i).put("bookingConferenceId", maxBookingConferenceIdSeq);
				cnfrBookingService.insertIncidental(incidentalList.get(i));
			}
		}
		parameter.put("bookingConferenceId", maxBookingConferenceIdSeq);
		int res = cnfrBookingService.registMeetingRoomReserve(parameter);
		
		Map<String, Object> conInfo = new HashMap<>();
		conInfo = cnfrBookingService.getConfInfo(parameter);
		String confName = (String) conInfo.get("room_name");
		String floor = (String) conInfo.get("floor");
		
		

		List<Map<String, Object>> meetingMemberList =  (List<Map<String, Object>>) parameter.get("meetingRoomMember");
		logger.debug("[by kgh] meetingMemberList.size()==" + meetingMemberList.size());
		
		for(int i=0;i<meetingMemberList.size();i++){
			
			Map<String, Object> paramApi = new HashMap<>();
			paramApi.put("bookingConferenceId", maxBookingConferenceIdSeq);
			paramApi.put("memberId", meetingMemberList.get(i).get("member_id"));
			paramApi.put("participantName", meetingMemberList.get(i).get("participant_name"));
			paramApi.put("companyName", meetingMemberList.get(i).get("company_name"));
			paramApi.put("phoneNumber", meetingMemberList.get(i).get("phone_number"));
			
			//여기서 push 보냄
			List<Object> receiverInfos = null;
			Map<String, Object> uploadInfo = new HashMap<String, Object>();
			if(meetingMemberList.get(i).get("member_id") != ""){
				uploadInfo.put("writer_id", meetingMemberList.get(i).get("member_id"));
				//receiverInfos = myService.getPushReceiverInfo(uploadInfo);
			}
			if(receiverInfos != null){
				if(receiverInfos.size() >0){
			@SuppressWarnings("unchecked")
			Map<String, Object> receiverInfo = (Map<String, Object>)receiverInfos.get(0);

			try {
				
				receiverInfo.put("title", "예약 등록");
				String title = (String) parameter.get("meetingStartTime");
				if(title.isEmpty() || title == null || title == ""){
					title = "Coworkers 회의예약";
				}else{
					title = "Coworkers 회의예약(" + title + ")";
				}
				String sendStr = title+" " + confName+ " 회의가 예약되었습니다.";
				
				receiverInfo.put("message", sendStr);
				receiverInfo.put("type", "I"); // 개인
				
				String url 	= "http://222.110.254.130/mudSub/api/push/" + receiverInfo.get("device_os").toString() 
							+ "?deviceToken=" + URLEncoder.encode(receiverInfo.get("device_token").toString(), "UTF-8")
							+ "&title=" + URLEncoder.encode(receiverInfo.get("title").toString(), "UTF-8")
							+ "&message=" + URLEncoder.encode(receiverInfo.get("message").toString(), "UTF-8")
							+ "&code=0";
			
				URL obj;
				obj = new URL(url);
				HttpURLConnection conn;
				conn = (HttpURLConnection) obj.openConnection();

				conn.setRequestProperty("Content-Type", "application/json");
				conn.setDoOutput(true);

				conn.setRequestMethod("GET");

				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));

				String inputLine;
				StringBuffer responsePush = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					responsePush.append(inputLine);
				}
				in.close();

				//System.out.println(response.toString()); //결과, json결과를 parser하여 처리
			} catch (Exception e) {
				e.printStackTrace();
				//return atcFileId;
			}
			//myService.insertPushLog(receiverInfo);//이력 저장
				}
			}
			
			//여기서 sms 보내자
			if(!meetingMemberList.get(i).containsKey("member_id")){
				int sendProfNum = setRandom();
				//sendProfNum = 112233;
				//int[] sendArr = {254784,256941,950147,719584,602258,126890,425987,632014,894120,225109};
				//intValue = (int)(Math.random() * (999999 - 1))+1;
				//int idx = (int)(Math.random() * (10 ));
				//sendProfNum =sendArr[idx];
				String title = (String) parameter.get("meetingStartTime");
				if(title.isEmpty() || title == null || title == ""){
					title = "Coworkers 회의예약";
				}else{
					title = "Coworkers 회의예약(" + title + ")";
				}
				String sendStr = title+" " + confName+ " "+floor+ "층 KIOSK > 게스트 보안 출입 코드 > " + String.valueOf(sendProfNum) +" 입력";
				paramApi.put("sendProfNum",  String.valueOf(sendProfNum));

				List<String> supplierNames1 = new ArrayList<String>();
				
				String recvNum = (String) meetingMemberList.get(i).get("phone_number");
				supplierNames1.add(recvNum);
			
				String messageStr = (String) String.valueOf(sendProfNum);
				//boolean resSMS = sendSmsMessage(sendStr, supplierNames1);
				smsservice.sendSmsMessage(sendStr, supplierNames1);
			}
			
			res = cnfrBookingService.registMeetingRoomMember(paramApi);
		}

		Map<String, Object> paramApi = new HashMap<>();
		paramApi = new HashMap<>();
		paramApi.put("memberId", parameter.get("memberId"));
		paramApi.put("meetingStartTime", parameter.get("meetingStartTime"));
		paramApi.put("meetingEndTime", parameter.get("meetingEndTime"));
		paramApi.put("conferenceId" , parameter.get("conferenceId"));
		paramApi.put("selectedOfficeRentId" , parameter.get("selectedOfficeRentId"));
		
		 Map<?, ?> tempRemainTime = cnfrBookingService.getRemainTime(paramApi);
		 Double resv_time =  (Double) tempRemainTime.get("resv_time");
		 int default_time = (int) tempRemainTime.get("default_time");
			int payment_time = (int) tempRemainTime.get("payment_time");
			int default_offer_time = (int) tempRemainTime.get("default_offer_time");
			int payment_buying_time = (int) tempRemainTime.get("payment_buying_time");
			
		 
		 //paramApi = new HashMap<>();
		 paramApi.put("memberId", parameter.get("memberId"));
		// paramApi.put("resv_time", -(resv_time));

		 if(payment_buying_time >0 && resv_time <= payment_buying_time) {
				paramApi.put("add_payment_time", -(resv_time));
				paramApi.put("add_default_time","0");
			}else {
				paramApi.put("add_default_time", -(resv_time));
				paramApi.put("add_payment_time","0");
			}
			
		
		 
		 cnfrBookingService.updateTimeUpDown(paramApi);

		//예약 히스토리 저장
		paramApi = new HashMap<>();
		paramApi.put("time_use_type", "O");
		paramApi.put("booking_reference_id", maxBookingConferenceIdSeq);
		paramApi.put("booking_type", "C");
		paramApi.put("memberId", parameter.get("memberId"));
		paramApi.put("meetingStartTime", parameter.get("meetingStartTime"));
		paramApi.put("meetingEndTime", parameter.get("meetingEndTime"));
		paramApi.put("booking_hour", resv_time);

		logger.debug("insertBookingHistory booking_reference_id==" + parameter.get("booking_reference_id"));
		logger.debug("insertBookingHistory paramApi.meetingStartTime==" + paramApi.get("meetingStartTime"));
		logger.debug("insertBookingHistory paramApi.meetingEndTime==" + paramApi.get("meetingEndTime"));
		logger.debug("insertBookingHistory paramApi.booking_hour==" + paramApi.get("booking_hour"));

		res = cnfrBookingService.insertBookingHistory(paramApi);
		
		if (res == 1) {
			messages.addMessage("0", "OK");
		} else {
			messages.addMessage("1", "Not OK");
		}
		return maxBookingConferenceIdSeq;		
	}
	
	private boolean sendSmsMessage(String message, List<String> receiverNumbers) {
		boolean res = false;
		try {
			// Xroshot 설정 정보
			String spid = com.anylogic.iot.base.util.PropUtil.getInstance().getPropValue(com.anylogic.iot.base.common.xroshot.constant.XroshotConstant.DEFAULT_PROP_FILE, com.anylogic.iot.base.common.xroshot.constant.XroshotConstant.XROSUOT_SPID);
			String sppwd = com.anylogic.iot.base.util.PropUtil.getInstance().getPropValue(com.anylogic.iot.base.common.xroshot.constant.XroshotConstant.DEFAULT_PROP_FILE, com.anylogic.iot.base.common.xroshot.constant.XroshotConstant.XROSUOT_SPPWD);
			String certfile_path = com.anylogic.iot.base.util.PropUtil.getInstance().getPropValue(com.anylogic.iot.base.common.xroshot.constant.XroshotConstant.DEFAULT_PROP_FILE, com.anylogic.iot.base.common.xroshot.constant.XroshotConstant.XROSUOT_CERTFILE_PATH);

			// Sender 정보
			String senderNumber = com.anylogic.iot.base.util.PropUtil.getInstance().getPropValue(com.anylogic.iot.base.common.xroshot.constant.XroshotConstant.DEFAULT_PROP_FILE, com.anylogic.iot.base.common.xroshot.constant.XroshotConstant.XROSUOT_SENDER);

			com.anylogic.iot.base.common.xroshot.job.XroshotSms smsSend = new com.anylogic.iot.base.common.xroshot.job.XroshotSms(spid, sppwd, certfile_path);

			// SMS 메세지 전송
			res = smsSend.sendSms(message, senderNumber, receiverNumbers);

		} catch (Exception e) {
			logger.info("Send SMS MESSAGE ERROR :" + e.getMessage());
		}

		return res;
	}
	
	private int setRandom(){

		double randomVal = Math.random();

		int intValue = 0;

		while(true){

			intValue = (int)(Math.random() * (499999 - 1))+1;

			if(intValue >= 100000){

				break;

			}

		}

		return intValue;

	}
}