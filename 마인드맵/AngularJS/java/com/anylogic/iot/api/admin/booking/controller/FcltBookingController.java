package com.anylogic.iot.api.admin.booking.controller;

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
import com.anylogic.iot.api.admin.booking.service.FcltBookingService;
import com.anylogic.iot.base.common.ResultListForCalendarVO;
import com.anylogic.iot.base.mvc.message.Messages;

      
@RestController
@RequestMapping("/" + Version.V1+"/admin/fcltBooking")
public class FcltBookingController {

	private static final Logger logger = LoggerFactory.getLogger(FcltBookingController.class);

	@Autowired
	private FcltBookingService fcltBookingService;
	
	@Autowired
	private CnfrBookingService cnfrBookingService;

	@RequestMapping(value = "/getFcBookingList", method = RequestMethod.GET)
	public ResultListForCalendarVO getFcBookingList(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListForCalendarVO resultListVO = new ResultListForCalendarVO();
		logger.debug("parameter===" + parameter);

		List<Object> listBranch = cnfrBookingService.getBranchList(parameter);
		resultListVO.setBranchDataRows(listBranch);

		if("".equals(parameter.get("branch_id")) ) {
			Map<?, ?> mapBranch = (Map<?, ?>) listBranch.get(0);
			parameter.put("branch_id", mapBranch.get("branch_id"));
		} else {
			parameter.put("branch_id", parameter.get("branch_id"));
		}
		
		resultListVO.setMeetingRoomDataRows(fcltBookingService.getFcList(parameter));
    	resultListVO.setBookingDataRows(fcltBookingService.getFcBookingList(parameter)); 
    	
    	messages.addMessage("OK", "");
		return resultListVO; 
	}
	
	@RequestMapping(value = "/getFcBookingRegPage", method = RequestMethod.GET)
	public ResultListForCalendarVO getFcBookingRegPage(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListForCalendarVO resultListVO = new ResultListForCalendarVO();
		
		resultListVO.setMeetingRoomDataRows(fcltBookingService.getFcList(parameter));
    	
    	messages.addMessage("OK", "");
		return resultListVO; 
	}

	@RequestMapping(value = "/getFcReserveDetail", method = RequestMethod.GET)
	public Map<?, ?> getFcReserveDetail(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		Map<?, ?> resultMap = fcltBookingService.getFcReserveDetail(parameter);
    	messages.addMessage("OK", "");
		return resultMap; 
	}
	
	@RequestMapping(value = "/cancelFcReserve", method = RequestMethod.POST)
	public boolean cancelFcReserve(@RequestBody Map<String, Object> parameter, Messages messages) {

		try {
			Map<String, Object> paramApi = new HashMap<String, Object>();
			paramApi.put("booking_reference_id", parameter.get("bookingFacilityId"));
			Map<?, ?> bookingHistoryMap = cnfrBookingService.getBookingHistory(paramApi);

			fcltBookingService.cancelFcReserve(parameter);
			
			/*paramApi = new HashMap<String, Object>();
			paramApi.put("memberId", parameter.get("memberId"));
			paramApi.put("resv_time", bookingHistoryMap.get("booking_hour"));*/
			
			paramApi = new HashMap<String, Object>();
			paramApi.put("memberId", parameter.get("booking_member_id"));
			if("D".equals(bookingHistoryMap.get("time_use_type"))) {
				paramApi.put("add_default_time", bookingHistoryMap.get("booking_hour"));
				paramApi.put("add_payment_time","0");
				
			} else {
				paramApi.put("add_payment_time", bookingHistoryMap.get("booking_hour"));
				paramApi.put("add_default_time","0");
			}
			
			
			cnfrBookingService.updateTimeUpDown(paramApi);

			//예약 히스토리 저장
			paramApi = new HashMap<String, Object>();
			paramApi.put("time_use_type", bookingHistoryMap.get("time_use_type"));
			paramApi.put("booking_reference_id",  parameter.get("bookingFacilityId"));
			paramApi.put("booking_type", "F");
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
	
	@RequestMapping(value = "/checkFcReserveTime", method = RequestMethod.GET)
	public boolean checkFcReserveTime(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		logger.debug("parameter==" + parameter);
		
		List<Object> myFcRoomTimeDataRows = fcltBookingService.checkMyFcReserveTime(parameter);
		if(myFcRoomTimeDataRows.size() > 0) {
			messages.addMessage("NotOk", "이미 예약한 정보가 존재 합니다. 확인 바랍니다.");
			return false; 
		}
		
		List<Object> fcRoomTimeDataRows = fcltBookingService.checkFcReserveTime(parameter);
		
		//이미 예약된 건이 있다면 정원 초과 인지 체크 함
		if(fcRoomTimeDataRows.size() > 0) {
			@SuppressWarnings("rawtypes")
			Map tempMap = (Map) fcRoomTimeDataRows.get(0);		
			int acceptorNumber = (int) tempMap.get("acceptor_number");
			
			logger.debug(" >>> acceptorNumber=" + acceptorNumber + " :: " + fcRoomTimeDataRows.size());
			if(fcRoomTimeDataRows.size() >= acceptorNumber) {
				messages.addMessage("NotOk", "선택하신 날짜,시간은 정원 초과 입니다.다시 시도 바랍니다.");
				return false;
			}
		}
		messages.addMessage("OK", "");		
		return true;
	}
	
	@RequestMapping(value = "/registFcReserve", method = RequestMethod.POST)
	public int registFcReserve(@RequestBody Map<String, Object> parameter, Messages messages) {

		String maxBookingSeq = fcltBookingService.getMaxBookingFcRoomSeq(parameter);
		parameter.put("booking_facility_id", maxBookingSeq);

		int res = fcltBookingService.registFcReserve(parameter);

		Map<String, Object> paramApi = null;
		paramApi = new HashMap<>();
		paramApi.put("memberId", parameter.get("memberId"));
		paramApi.put("bookingStartTime", parameter.get("bookingStartTime"));
		paramApi.put("bookingEndTime", parameter.get("bookingEndTime"));
		
		Map<?, ?> tempRemainTime = fcltBookingService.getRemainTime(paramApi);
		
		Double resv_time =  (Double) tempRemainTime.get("resv_time");

		//예약에 대한 시간  차감
		paramApi = new HashMap<String, Object>();
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
		paramApi = new HashMap<>();
		paramApi.put("time_use_type", "O");
		paramApi.put("booking_reference_id", maxBookingSeq);
		paramApi.put("booking_type", "F");
		paramApi.put("memberId", parameter.get("memberId"));
		paramApi.put("booking_hour", resv_time);

		res = cnfrBookingService.insertBookingHistory(paramApi);
		
		if (res == 1) {
			messages.addMessage("0", "OK");
		} else {
			messages.addMessage("1", "Not OK");
		}
		return res;		
	}
}





