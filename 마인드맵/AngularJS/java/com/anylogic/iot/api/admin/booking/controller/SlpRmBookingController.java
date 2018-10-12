
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
import com.anylogic.iot.api.admin.booking.service.SlpRmBookingService;
import com.anylogic.iot.base.common.ResultListForCalendarVO;
import com.anylogic.iot.base.mvc.message.Messages;

      
@RestController
@RequestMapping("/" + Version.V1+"/admin/slpRmBooking")
public class SlpRmBookingController {

	private static final Logger logger = LoggerFactory.getLogger(SlpRmBookingController.class);

	@Autowired
	private SlpRmBookingService slpRmBookingService;

	@Autowired
	private FcltBookingService fcltBookingService;
	
	@Autowired
	private CnfrBookingService cnfrBookingService;
	
	@RequestMapping(value = "/getSlpBookingList", method = RequestMethod.GET)
	public ResultListForCalendarVO getSlpBookingList(@RequestParam Map<String, Object> parameter, Messages messages) {
		
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
		
		resultListVO.setMeetingRoomDataRows(slpRmBookingService.getSlpList(parameter));
    	resultListVO.setBookingDataRows(slpRmBookingService.getSlpBookingList(parameter)); 
    	
    	messages.addMessage("OK", "");
		return resultListVO; 
	}	
	
	@RequestMapping(value = "/getSlpBookingRegPage", method = RequestMethod.GET)
	public ResultListForCalendarVO getSlpBookingRegPage(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListForCalendarVO resultListVO = new ResultListForCalendarVO();
		
		resultListVO.setMeetingRoomDataRows(slpRmBookingService.getSlpList(parameter));
    	
    	messages.addMessage("OK", "");
		return resultListVO; 
	}
	
	@RequestMapping(value = "/getSlpReserveDetail", method = RequestMethod.GET)
	public Map<?, ?> getSlpReserveDetail(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		Map<?, ?> resultMap = slpRmBookingService.getSlpReserveDetail(parameter);
    	messages.addMessage("OK", "");
		return resultMap; 
	}
	
	@RequestMapping(value = "/cancelSlpReserve", method = RequestMethod.POST)
	public boolean cancelSlpReserve(@RequestBody Map<String, Object> parameter, Messages messages) {

		try {
			Map<String, Object> paramApi = new HashMap<String, Object>();
			paramApi.put("booking_reference_id", parameter.get("bookingFacilityId"));
			Map<?, ?> bookingHistoryMap = cnfrBookingService.getBookingHistory(paramApi);

			slpRmBookingService.cancelSlpReserve(parameter);
			
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
			paramApi.put("booking_type", "S");
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
	
	
	@RequestMapping(value = "/checkSlpReserveTime", method = RequestMethod.GET)
	public boolean checkSlpReserveTime(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		logger.debug("parameter==" + parameter);
		
		List<Object> mySlpRoomTimeDataRows = slpRmBookingService.checkMySlpReserveTime(parameter);
		if(mySlpRoomTimeDataRows.size() > 0) {
			messages.addMessage("NotOk", "이미 예약한 정보가 존재 합니다. 확인 바랍니다.");
			return false; 
		}
		
		List<Object> fcRoomTimeDataRows = slpRmBookingService.checkSlpReserveTime(parameter);
		
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
	
	@RequestMapping(value = "/registSlpReserve", method = RequestMethod.POST)
	public int registSlpReserve(@RequestBody Map<String, Object> parameter, Messages messages) {

		String maxBookingSeq = fcltBookingService.getMaxBookingFcRoomSeq(parameter);
		parameter.put("booking_facility_id", maxBookingSeq);

		int res = slpRmBookingService.registSlpReserve(parameter);

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
		paramApi.put("selectedOfficeRentId",  parameter.get("selectedOfficeRentId"));
		
		
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
		paramApi.put("booking_type", "S");
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



