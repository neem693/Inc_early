
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
import com.anylogic.iot.api.admin.calc.service.MulMachUseStatService;
import com.anylogic.iot.base.common.ResultListVO;
import com.anylogic.iot.api.admin.calc.vo.MulMachineUseVO;
import com.anylogic.iot.base.excel.service.ExcelSVC;
import com.anylogic.iot.base.mvc.message.Messages;

import net.sf.jxls.exception.ParsePropertyException;

@RestController
@RequestMapping("/" + Version.V1+"/admin/calc")
public class MulMachUseStatController {

	private static final Logger logger = LoggerFactory.getLogger(MulMachUseStatController.class);

	@Autowired
	private MulMachUseStatService mulMachUseStatService;

	@Autowired
	private ExcelSVC excelSVC;
	
	@RequestMapping(value = "/getMulMachineUseStatusPage", method = RequestMethod.GET)
	public ResultListVO getMulMachineUseStatusPage(@RequestParam Map<String, Object> parameter, Messages messages) {
		                
		ResultListVO resultListVO = new ResultListVO();
    	
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
	

	@RequestMapping(value = "/getMulMachineUseStatusList", method = RequestMethod.GET)
	public ResultListVO getMulMachineUseStatusList(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		logger.debug("parameter=" + parameter);
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(mulMachUseStatService.getMulMachineUseStatusList(parameter)); 

    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	/**
	 * <PRE>
	 *  MethodName : listMulMachineUseStatusExcel
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
	@RequestMapping(value="/listMulMachineUseStatusExcel", method=RequestMethod.POST)
	@ResponseBody
/*	public void listMulMachineUseStatusExcel(@RequestParam Map<String, Object> parameter, Messages messages, HttpServletRequest request,
				HttpServletResponse response) throws ParsePropertyException, InvalidFormatException, IOException {*/
	public void listMulMachineUseStatusExcel(HttpServletRequest request, HttpServletResponse response, 
			   @RequestParam Map<String, Object> parameter, Messages messages) throws ParsePropertyException, InvalidFormatException, IOException {
	
		String fileName = "복합기이용목록";
		String fileExt = "xlsx";
		String listName = "list";
		List<MulMachineUseVO>  list = mulMachUseStatService.getMulMachineUseStatusListExcel(parameter);

		int lng = list.size();
		for(int i=0;i<lng;i++){
			int rNum = Integer.parseInt(list.get(i).getRNUM());
			rNum = lng +1 - rNum;
			list.get(i).setRNUM(Integer.toString(rNum));
		}
		
		excelSVC.getExcelDownLoad(fileName, fileExt, listName, list, response);
	}	
}