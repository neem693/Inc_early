
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anylogic.iot.Version;
import com.anylogic.iot.api.admin.calc.service.FcltUseStatService;
import com.anylogic.iot.api.admin.calc.vo.MeetinRoomUseStatusVO;
import com.anylogic.iot.base.common.ResultListVO;
import com.anylogic.iot.base.excel.service.ExcelSVC;
import com.anylogic.iot.base.mvc.message.Messages;

import net.sf.jxls.exception.ParsePropertyException;


      
@RestController
@RequestMapping("/" + Version.V1+"/admin/calc")
public class FcltUseStatController {

	private static final Logger logger = LoggerFactory.getLogger(FcltUseStatController.class);

	@Autowired
	private FcltUseStatService fcltUseStatService;
	
	@Autowired
	private ExcelSVC excelSVC;
	
	@RequestMapping(value = "/getFacilityUseStatusPage", method = RequestMethod.GET)
	public ResultListVO getFacilityUseStatusPage(@RequestParam Map<String, Object> parameter, Messages messages) {
		                
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(fcltUseStatService.getFacilityList(parameter)); 
    	
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
	

	@RequestMapping(value = "/getFacilityUseStatusList", method = RequestMethod.GET)
	public ResultListVO getFacilityUseStatusList(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		logger.debug("parameter=" + parameter);
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(fcltUseStatService.getFacilityUseStatusList(parameter)); 

    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	
	@RequestMapping(value = "/getFacilityUseStatusCalendar", method = RequestMethod.GET)
	public ResultListVO getFacilityUseStatusCalendar(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		logger.debug("parameter=" + parameter);
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(fcltUseStatService.getFacilityUseStatusCalendar(parameter)); 

    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	/**
	 * <PRE>
	 *  MethodName : listFacilityUseStatusExcel
	 * </PRE>
	 * @author : moo
	 * @date   : 2018. 01. 20. 오후 12:56:52
	 * @param  :
	 * @return : void
	 * @brief  : F/C 예약 현황 엑셀 출력
	 * @param parameter
	 * @param messages
	 * @param request
	 * @param response
	 * @throws ParsePropertyException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/listFacilityUseStatusExcel", method=RequestMethod.POST)
	@ResponseBody
	public void listFacilityUseStatusExcel(@RequestParam Map<String, Object> parameter, Messages messages, HttpServletRequest request,
				HttpServletResponse response) throws ParsePropertyException, InvalidFormatException, IOException {

		List<MeetinRoomUseStatusVO>  list = fcltUseStatService.getFacilityUseStatusExcel(parameter);
		

		int lng = list.size();
		for(int i=0; i<lng; i++){
			int rNum = Integer.parseInt(list.get(i).getRNUM());
			rNum = lng +1 - rNum;
			list.get(i).setRNUM(Integer.toString(rNum));
		}
		
		
		excelSVC.getExcelDownLoad("편의시설예약현황", "xlsx", "list", list, response);
	}	
}





