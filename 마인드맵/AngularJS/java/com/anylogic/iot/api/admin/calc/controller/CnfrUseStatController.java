
package com.anylogic.iot.api.admin.calc.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anylogic.iot.Version;
import com.anylogic.iot.api.admin.booking.service.CnfrBookingService;
import com.anylogic.iot.api.admin.calc.service.CnfrUseStatService;
import com.anylogic.iot.api.admin.calc.vo.MeetinRoomUseStatusVO;
import com.anylogic.iot.base.common.ResultListVO;
import com.anylogic.iot.base.excel.service.ExcelSVC;
import com.anylogic.iot.base.mvc.message.Messages;

import net.sf.jxls.exception.ParsePropertyException;


      
@RestController
@RequestMapping("/" + Version.V1+"/admin/calc")
public class CnfrUseStatController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CnfrUseStatController.class);

	@Autowired
	private CnfrUseStatService cnfrUseStatService;
	
	@Autowired
	private CnfrBookingService cnfrBookingService;
	
	@Autowired
	private ExcelSVC<List<MeetinRoomUseStatusVO>> excelSVC;

	@RequestMapping(value = "/getMeetingRoomMgmtPage", method = RequestMethod.GET)
	public ResultListVO getMeetingRoomMgmtPage(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
		
    	resultListVO.setRows(cnfrUseStatService.getMeetingRoomListForCalc(parameter)); 
    	 
    	Calendar cal = Calendar.getInstance();
    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	String today = format.format(cal.getTime());
    	cal.add(Calendar.DATE, -7); // 7일(일주일)을 뺀다
    	String beforeOneWeekDay = format.format(cal.getTime());
    	
    	DateFormat format1 = new SimpleDateFormat("yyyy-MM");
    	String monthFirst = format1.format(cal.getTime());
    	monthFirst = monthFirst + "-01";
    	
    	
    	Map<String, Object> baseMap = new HashMap<String, Object>();
    	baseMap.put("today", today);
    	baseMap.put("beforeOneWeekDay", monthFirst);
    	baseMap.put("monthFirst", monthFirst);
    	
    	resultListVO.setBaseData(baseMap);
    	
    	messages.addMessage("OK", "");
		return resultListVO;
	}

	
	@RequestMapping(value = "/getMeetinRoomUseStatusList", method = RequestMethod.GET)
	public ResultListVO getMeetinRoomUseStatusList(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(cnfrUseStatService.getMeetinRoomUseStatusList(parameter)); 

    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	@RequestMapping(value = "/getMeetinRoomUseStatusCalendar", method = RequestMethod.GET)
	public ResultListVO getMeetinRoomUseStatusCalendar(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(cnfrUseStatService.getMeetinRoomUseStatusCalendar(parameter)); 

    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	/**
	 * <PRE>
	 *  MethodName : listMeetinRoomUseStatusExcel
	 * </PRE>
	 * @author : moo
	 * @date   : 2018. 01. 20. 오후 12:56:52
	 * @param  :
	 * @return : void
	 * @brief  : 회의실 예약 현황 엑셀 출력
	 * @param parameter
	 * @param messages
	 * @param request
	 * @param response
	 * @throws ParsePropertyException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	@RequestMapping(value = "/listMeetinRoomUseStatusExcel", method = RequestMethod.POST)
	public void listMeetinRoomUseStatusExcel(HttpServletRequest request, HttpServletResponse response, 
									   @RequestParam Map<String, Object> parameter, Messages messages) throws ParsePropertyException, InvalidFormatException, IOException {
		
		List<MeetinRoomUseStatusVO> list = cnfrUseStatService.getMeetinRoomUseStatusExcel(parameter);
		

		int lng = list.size();
		for(int i=0; i<lng; i++){
			int rNum = Integer.parseInt(list.get(i).getRNUM());
			rNum = lng +1 - rNum;
			list.get(i).setRNUM(Integer.toString(rNum));
		}
		
		excelSVC.getExcelDownLoad("회의실예약현황", "xlsx", "list", list, response);
	}	
}





