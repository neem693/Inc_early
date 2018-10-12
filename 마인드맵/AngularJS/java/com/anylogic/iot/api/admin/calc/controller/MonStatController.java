
package com.anylogic.iot.api.admin.calc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anylogic.iot.Version;
import com.anylogic.iot.api.admin.booking.service.CnfrBookingService;
import com.anylogic.iot.api.admin.calc.service.MonStatService;
import com.anylogic.iot.api.admin.calc.vo.DailyCalMonthVO;
import com.anylogic.iot.api.admin.calc.vo.DailyCalYearVO;
import com.anylogic.iot.api.admin.calc.vo.MonthlySumVO;
import com.anylogic.iot.api.admin.calc.vo.OfficeUseStatusVO;
import com.anylogic.iot.api.admin.calc.vo.PGUseVO;
import com.anylogic.iot.base.common.ResultListVO;
import com.anylogic.iot.base.excel.service.ExcelSVC;
import com.anylogic.iot.base.mvc.message.Messages;

import net.sf.jxls.exception.ParsePropertyException;


      
@RestController
@RequestMapping("/" + Version.V1+"/admin/calc")
public class MonStatController { 

	private static final Logger logger = LoggerFactory.getLogger(MonStatController.class);

	@Autowired
	private MonStatService monStatService;

	@Autowired
	private CnfrBookingService cnfrBookingService;
	
	@Autowired
	private ExcelSVC excelSVC;

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getDailyCalYearListExcel", method=RequestMethod.POST)
	@ResponseBody
	public void getDailyCalYearListExcel(HttpServletRequest request, HttpServletResponse response, 
			   @RequestParam Map<String, Object> parameter, Messages messages) throws ParsePropertyException, InvalidFormatException, IOException {
		
		String fileName = "월간 집계-일마감";
		String fileExt = "xlsx";
		String listName = "list";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("date", parameter.get("date").toString());
		List<DailyCalYearVO>  list = monStatService.getDailyCalYearListExcel(params);
		
		Map<String, Object> excelParam = new HashMap<String, Object>();
		//excelParam.put("partialsum_deposit", parameter.get("partialsum_deposit").toString());
		excelParam.put("partialsum_room", parameter.get("partialsum_room").toString());
		excelParam.put("partialsum_meetings", parameter.get("partialsum_meetings").toString());
		excelParam.put("partialsum_incidental", parameter.get("partialsum_incidental").toString());
		excelParam.put("partialsum", parameter.get("partialsum").toString());
		/*excelParam.put("totalsum_sales", parameter.get("totalsum_sales").toString());
		excelParam.put("totalsum_total", parameter.get("totalsum_total").toString());*/
		
		excelSVC.getCalExcelDownLoad(fileName, fileExt, listName, list, excelParam,response);
	}
	
	@RequestMapping(value = "/getDailyCalYearList", method = RequestMethod.GET)
	public ResultListVO getDailyCalYearList(@RequestParam Map<String, Object> parameter, Messages messages) {
	
		logger.debug("parameter=" + parameter);
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(monStatService.getDailyCalYearList(parameter)); 

    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	@RequestMapping(value="/updateDailyCalData", method=RequestMethod.POST)
	@ResponseBody
	public int updateDailyCalData(@RequestBody Map<String, Object> parameter, Messages messages) {
		int res;
		res = monStatService.updateDailyCalData(parameter);
		messages.addMessage("OK", "");
		return res;
	}
	
	@RequestMapping(value = "/getRoomTaxbillAuto", method = RequestMethod.GET)
	public ResultListVO getRoomTaxbillAuto(@RequestParam Map<String, Object> parameter, Messages messages) {
	
		logger.debug("parameter=" + parameter);
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(monStatService.getRoomTaxbillAuto(parameter)); 

    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	@RequestMapping(value = "/getMeetingTaxbillAuto", method = RequestMethod.GET)
	public ResultListVO getMeetingTaxbillAuto(@RequestParam Map<String, Object> parameter, Messages messages) {
	
		logger.debug("parameter=" + parameter);
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(monStatService.getMeetingTaxbillAuto(parameter)); 

    	messages.addMessage("OK", "");
		return resultListVO;
	}
	@RequestMapping(value="/saveDailyCalData", method=RequestMethod.POST)
	@ResponseBody
	public int saveDailyCalData(@RequestBody Map<String, Object> parameter, Messages messages) {
		int res;
		res = monStatService.saveDailyCalData(parameter);
		messages.addMessage("OK", "");
		return res;
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getDailyCalDayListExcel", method=RequestMethod.POST)
	@ResponseBody
	public void getDailyCalDayListExcel(HttpServletRequest request, HttpServletResponse response, 
			   @RequestParam Map<String, Object> parameter, Messages messages) throws ParsePropertyException, InvalidFormatException, IOException {
		
		String fileName = "일마감 일목록";
		String fileExt = "xlsx";
		String listName = "list";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("date", parameter.get("date").toString());
		List<DailyCalMonthVO>  list = monStatService.getDailyCalDayListExcel(params);
		
		Map<String, Object> excelParam = new HashMap<String, Object>();
		excelParam.put("partialsum_deposit", parameter.get("partialsum_deposit").toString());
		excelParam.put("partialsum_room", parameter.get("partialsum_room").toString());
		excelParam.put("partialsum_meetings", parameter.get("partialsum_meetings").toString());
		excelParam.put("partialsum_incidental", parameter.get("partialsum_incidental").toString());
		excelParam.put("partialsum", parameter.get("partialsum").toString());
		/*excelParam.put("totalsum_sales", parameter.get("totalsum_sales").toString());
		excelParam.put("totalsum_total", parameter.get("totalsum_total").toString());*/
		
		excelSVC.getCalExcelDownLoad(fileName, fileExt, listName, list, excelParam,response);
	}
	
	@RequestMapping(value = "/getDailyCalDayList", method = RequestMethod.GET)
	public ResultListVO getDailyCalDayList(@RequestParam Map<String, Object> parameter, Messages messages) {
	
		logger.debug("parameter=" + parameter);
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(monStatService.getDailyCalDayList(parameter)); 

    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	@RequestMapping(value = "/getDailyCalMonthList", method = RequestMethod.GET)
	public ResultListVO getDailyCalMonthList(@RequestParam Map<String, Object> parameter, Messages messages) {
	
		logger.debug("parameter=" + parameter);
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(monStatService.getDailyCalMonthList(parameter)); 

    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getDailyCalMonthListExcel", method=RequestMethod.POST)
	@ResponseBody
	public void getDailyCalMonthListExcel(HttpServletRequest request, HttpServletResponse response, 
			   @RequestParam Map<String, Object> parameter, Messages messages) throws ParsePropertyException, InvalidFormatException, IOException {
		
		String fileName = "일마감 월목록";
		String fileExt = "xlsx";
		String listName = "list";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("date", parameter.get("date").toString());
		List<DailyCalMonthVO>  list = monStatService.getDailyCalMonthListExcel(params);
		
		Map<String, Object> excelParam = new HashMap<String, Object>();
		excelParam.put("partialsum_deposit", parameter.get("partialsum_deposit").toString());
		excelParam.put("partialsum_room", parameter.get("partialsum_room").toString());
		excelParam.put("partialsum_meetings", parameter.get("partialsum_meetings").toString());
		excelParam.put("partialsum_incidental", parameter.get("partialsum_incidental").toString());
		excelParam.put("partialsum", parameter.get("partialsum").toString());
		excelParam.put("totalsum_sales", parameter.get("totalsum_sales").toString());
		excelParam.put("totalsum_total", parameter.get("totalsum_total").toString());
		
		excelSVC.getCalExcelDownLoad(fileName, fileExt, listName, list, excelParam,response);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getPgListExcel", method=RequestMethod.POST)
	@ResponseBody
	public void getPgListExcel(HttpServletRequest request, HttpServletResponse response, 
			   @RequestParam Map<String, Object> parameter, Messages messages) throws ParsePropertyException, InvalidFormatException, IOException {
	
		String fileName = "PG이용목록";
		String fileExt = "xlsx";
		String listName = "list";
		List<PGUseVO>  list = monStatService.getPgListExcel(parameter);

		PGUseVO addData = new PGUseVO();
		//int lng = list.size();
		int totalAmount = 0;
		
		for(int i=0;i<list.size();i++){
			totalAmount = totalAmount + Integer.parseInt(list.get(i).getApproval_amount());
		}
		addData.setApproval_amount(Integer.toString(totalAmount));
		addData.setAproval_date("합계");
		list.add(addData);
		excelSVC.getExcelDownLoad(fileName, fileExt, listName, list, response);
	}	
	
	@RequestMapping(value = "/deletePayData", method = RequestMethod.POST)
	public int deletePayData(@RequestBody Map<String, Object> parameter, Messages messages) {
	
		logger.debug("parameter==" + parameter);
		
		//ResultListVO resultListVO = new ResultListVO();
    	int res = monStatService.deletePayData(parameter);
    	 
    	messages.addMessage("OK", "");
		return res;
	}
	
	@RequestMapping(value = "/getPayList", method = RequestMethod.GET)
	public ResultListVO getPayList(@RequestParam Map<String, Object> parameter, Messages messages) {
	
		logger.debug("parameter=" + parameter);
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(monStatService.getPayList(parameter)); 

    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	@RequestMapping(value = "/getPgList", method = RequestMethod.GET)
	public ResultListVO getPgList(@RequestParam Map<String, Object> parameter, Messages messages) {
	
		logger.debug("parameter=" + parameter);
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(monStatService.getPgList(parameter)); 

    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	@RequestMapping(value = "/getReportAllMonthlyStatList", method = RequestMethod.GET)
	public ResultListVO getReportAllMonthlyStatList(@RequestParam Map<String, Object> parameter, Messages messages) {
	
		logger.debug("parameter=" + parameter);
		
		ResultListVO resultListVO = new ResultListVO();
		List<Object> resList = monStatService.getReportMonthlyStatList(parameter);
		for(int i=0;i<resList.size();i++){
			Map<String, Object> params = new HashMap<String, Object>();
			Map<String, Object> res = new HashMap<String, Object>();
			params = (Map<String, Object>) resList.get(i);
			res = monStatService.getPgData(params);
			if(res != null){
				params.put("pgData", res.get("approval_amount"));
				double approval_amount = Double.parseDouble(res.get("approval_amount").toString());
				double total1 = (double) params.get("total1");
				int totalRes;
				totalRes = Math.round((int)total1) + Math.round((int)approval_amount);
				double total2 = (double) params.get("total2");
				long ratio = Math.round( ((int)total1 - (int)total2) * 100.0 / total2 ) /100; 
				params.put("ratio", ratio);
				String total = String.valueOf(total1);
				params.put("total",totalRes );
				resList.set(i, params);
			}
			
		}
		
    	resultListVO.setRows(resList); 

    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	@RequestMapping(value = "/getReportOfficeStatPage", method = RequestMethod.GET)
	public ResultListVO getReportOfficeStatPage(@RequestParam Map<String, Object> parameter, Messages messages) {
		ResultListVO resultListVO = new ResultListVO();
		resultListVO.setRows(cnfrBookingService.getBranchList(parameter));
    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	@RequestMapping(value = "/getReportOfficeStatList", method = RequestMethod.GET)
	public ResultListVO getReportOfficeStatList(@RequestParam Map<String, Object> parameter, Messages messages) {
		logger.debug("parameter=" + parameter);
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(monStatService.getReportOfficeStatList(parameter)); 
    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	/**
	 * <PRE>
	 *  MethodName : getReportMonthlyStatListExcel
	 * </PRE>
	 * @author : moo
	 * @date   : 2018. 01. 20. 오후 12:56:52
	 * @param  :
	 * @return : void
	 * @brief  : 엑셀 출력
	 * @param parameter
	 * @param messages
	 * @param request
	 * @param response
	 * @throws ParsePropertyException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getReportMonthlyStatListExcel", method=RequestMethod.POST)
	@ResponseBody
	public void getReportMonthlyStatListExcel(HttpServletRequest request, HttpServletResponse response, 
			   @RequestParam Map<String, Object> parameter, Messages messages) throws ParsePropertyException, InvalidFormatException, IOException {
		
		String fileName = "월간집계리포트";
		String fileExt = "xlsx";
		String listName = "list";
		List<MonthlySumVO>  list = monStatService.getReportMonthlyStatListExcel(parameter);

		for(int i=0;i<list.size();i++){
			Map<String, Object> params = new HashMap<String, Object>();
			Map<String, Object> res = new HashMap<String, Object>();
			params.put("issue_month", list.get(i).getIssue_month());
			res = monStatService.getPgData(params);
			list.get(i).setPgdata("0");
			if(res != null){
				String pgdata = res.get("approval_amount").toString();
				list.get(i).setPgdata(pgdata);
				double approval_amount = Double.parseDouble(res.get("approval_amount").toString());
				double total1 = Double.parseDouble(list.get(i).getTotal1());
				int totalRes;
				totalRes = Math.round((int)total1) + Math.round((int)approval_amount);
				double total2 = Double.parseDouble(list.get(i).getTotal2());
				long ratio = Math.round( ((int)total1 - (int)total2) * 100.0 / total2 ) /100; 
				list.get(i).setRatio(Long.toString(ratio));
				String total = String.valueOf(total1);
				list.get(i).setTotal(Integer.toString(totalRes));
			}
		}
		
/*		int lng = list.size();
		for(int i=0; i<lng; i++){
			int rNum = Integer.parseInt(list.get(i).getRNUM());
			rNum = lng +1 - rNum;
			list.get(i).setRNUM(Integer.toString(rNum));
		}*/
		
		excelSVC.getExcelDownLoad(fileName, fileExt, listName, list, response);
	}



	/**
	 * <PRE>
	 *  MethodName : listReportOfficeStatExcel
	 * </PRE>
	 * @author : moo
	 * @date   : 2018. 01. 20. 오후 12:56:52
	 * @param  :
	 * @return : void
	 * @brief  : 엑셀 출력
	 * @param parameter
	 * @param messages
	 * @param request
	 * @param response
	 * @throws ParsePropertyException
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/listReportOfficeStatExcel", method=RequestMethod.POST)
	@ResponseBody
	public void listReportOfficeStatExcel(HttpServletRequest request, HttpServletResponse response, 
			   @RequestParam Map<String, Object> parameter, Messages messages) throws ParsePropertyException, InvalidFormatException, IOException {
		
		String fileName = "오피스점유정보통계";
		String fileExt = "xlsx";
		String listName = "list";
		List<OfficeUseStatusVO>  list = monStatService.getReportOfficeStatExcel(parameter);
	
	/*		int lng = list.size();
		for(int i=0; i<lng; i++){
			int rNum = Integer.parseInt(list.get(i).getRNUM());
			rNum = lng +1 - rNum;
			list.get(i).setRNUM(Integer.toString(rNum));
		}*/
		
		excelSVC.getExcelDownLoad(fileName, fileExt, listName, list, response);
	}
}	
