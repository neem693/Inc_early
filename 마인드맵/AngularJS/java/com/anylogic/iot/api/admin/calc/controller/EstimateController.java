
package com.anylogic.iot.api.admin.calc.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
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
import com.anylogic.iot.api.admin.calc.service.EstimateService;
import com.anylogic.iot.api.admin.calc.service.FcltUseStatService;
import com.anylogic.iot.api.admin.calc.vo.EstimateInfoVO;
import com.anylogic.iot.api.admin.calc.vo.MeetinRoomUseStatusVO;
import com.anylogic.iot.api.admin.tenant.vo.CompanyInfoVO;
import com.anylogic.iot.base.common.ResultListVO;
import com.anylogic.iot.base.excel.service.ExcelSVC;
import com.anylogic.iot.base.mail.MailSender;
import com.anylogic.iot.base.mvc.message.Messages;
import com.anylogic.iot.base.util.DateUtil;

import net.sf.jxls.exception.ParsePropertyException;


      
@RestController
@RequestMapping("/" + Version.V1+"/admin/calc")
public class EstimateController {

	private static final Logger logger = LoggerFactory.getLogger(EstimateController.class);

	@Autowired
	private EstimateService estimateService;
	
	@Autowired
	private ExcelSVC excelSVC;
	
	@Autowired
	private MailSender mailSender;
	
	
	
	@RequestMapping(value="/updateEstimate", method=RequestMethod.POST)
	@ResponseBody
	public void updateEstimate(@RequestBody Map<String, Object> parameter, Messages messages) {
		String insertKey ;
		estimateService.updateEstimate(parameter);
		insertKey = parameter.get("estimate_id").toString();
		
		estimateService.resetIncidentalList(parameter);
		List<Map<String, Object>> incidentalList = new ArrayList<Map<String, Object>>();
		
		incidentalList = (List<Map<String, Object>>) parameter.get("incidentalList");
		for(int i=0;i<incidentalList.size();i++){
			incidentalList.get(i).put("insertKey", insertKey);
			incidentalList.get(i).put("member_id", parameter.get("member_id").toString());
			estimateService.setIncidentel(incidentalList.get(i));
		}
		
		
	}
	
	@RequestMapping(value = "/getEstimateBookingInfo", method = RequestMethod.GET)
	public ResultListVO getEstimateBookingInfo(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		logger.debug("parameter=" + parameter);
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(estimateService.getEstimateBookingInfo(parameter)); 

    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	@RequestMapping(value = "/sendEmail_estimate", method = RequestMethod.POST)
	public Map<String, Object> sendEmail_estimate(@RequestBody Map<String, Object> parameter, Messages messages) {
		Map<String, Object> ret = new HashMap<>();

		/*// 사용자 정보 확인
		List<Object> userInfo = authService.getUserInfo(parameter);
		if (userInfo.size() == 0) {
			ret.put("result", "REJECT");
			return ret;
		}*/

		String ramdom = RandomStringUtils.randomAlphanumeric(10);

		com.anylogic.iot.base.mail.vo.SendMailVO vo = new com.anylogic.iot.base.mail.vo.SendMailVO(
				com.anylogic.iot.base.mail.MailConstants.MAIL_TYPE.SEND_ESTIMATE);

		String sendAddr = (String) parameter.get("userEmail");
		int branchId = (int) parameter.get("selectBranchId");
		if(branchId == 1) {
			vo.setFrom("meetings@coworkerskorea.com");
		}else {
			vo.setFrom("meetings@vertexkorea.com");
		}
		vo.setSendDate(new Date());
		vo.setSubject("견적서 전송");
		vo.addTo(sendAddr);

		String estimate = parameter.get("estimate").toString();
		estimate = "<img src='" + estimate + "' />";
		estimate = estimate + "<div style=\"font-size: 9px; margin:0 auto;text-align:left;border:1px solid #ddd;\">"+"<br><span style=\"font:normal 14px;font-family:'NanumBarunGothic', 'Nanum Gothic', 'Nanum Myeongjo', tahoma, dotum, Apple-Gothic, sans-serif;color:#ff0000;text-align:left;\">\n" + 
				"			Remarks\n" + 
				"	</span><br>\n" + 
				"	<span>\n" + 
				"	1.	If you notice us the name of seminar, we will display it on 1st and the relevant floor.\n" + 
				"	</span><br>\n" + 
				"	<span>\n" + 
				"	2.	Water/Pen/Mint is free as much as the number of attendances.\n" + 
				"	</span><br>\n" + 
				"	<span>\n" + 
				"	3.	Food purchased outside the premises is not permitted.\n" + 
				"	</span><br>\n" + 
				"	<span>\n" + 
				"	4.	Payments can be made either with credit card or money transfer at the day.\n" + 
				"	</span><br>\n" + 
				"	<span>\n" + 
				"	5.	Cancellation should be made before 3 days.<br>\n" + 
				"	 We are announcing that 100% of rental charge will be issued as penalty in case of cancellation before 2 days.\n" + 
				"	</span><br>\n" + 
				"	<span>\n" + 
				"	6.	If you need parking tickets, make an inquiry at the reception desk.<br>\n" + 
				"	*KRW 20,000/hour, KRW 10,000/day \n" + 
				"	</span>\n" + "</div>";
		vo.setContent(new com.anylogic.iot.base.mail.vo.FindPwVO(estimate,DateUtil.getTransDate() ));
		mailSender.sendMailForHtml(vo);

		ret.put("result", "SUCCESS");
    	return ret;
	}
	
	
	@RequestMapping(value = "/getIncidental", method = RequestMethod.GET)
	public ResultListVO getIncidental(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		logger.debug("parameter=" + parameter);
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(estimateService.getIncidental(parameter)); 

    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	@RequestMapping(value = "/getOfficeRentInfo2", method = RequestMethod.GET)
	public ResultListVO getOfficeRentInfo2(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		logger.debug("parameter=" + parameter);
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(estimateService.getOfficeRentInfo2(parameter)); 

    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	@RequestMapping(value="/setEstimate", method=RequestMethod.POST)
	@ResponseBody
	public void setEstimate(@RequestBody Map<String, Object> parameter, Messages messages) {
		String insertKey ;
		estimateService.setEstimate(parameter);
		insertKey = parameter.get("insertKey").toString();
		List<Map<String, Object>> incidentalList = new ArrayList<Map<String, Object>>();
		
		incidentalList = (List<Map<String, Object>>) parameter.get("incidentalList");
		for(int i=0;i<incidentalList.size();i++){
			incidentalList.get(i).put("insertKey", insertKey);
			incidentalList.get(i).put("member_id", parameter.get("member_id").toString());
			estimateService.setIncidentel(incidentalList.get(i));
		}
		
		
	}
	
	@RequestMapping(value = "/getBookingInfo", method = RequestMethod.GET)
	public ResultListVO getBookingInfo(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		logger.debug("parameter=" + parameter);
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(estimateService.getBookingInfo(parameter)); 

    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	@RequestMapping(value = "/getConferenceInfo", method = RequestMethod.GET)
	public ResultListVO getConferenceInfo(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		logger.debug("parameter=" + parameter);
		
		ResultListVO resultListVO = new ResultListVO();
		//String conferenceId = estimateService.getConferenceId(parameter);
		//parameter.put("conferenceId", conferenceId);
    	resultListVO.setRows(estimateService.getConferenceInfo(parameter)); 

    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	
	@RequestMapping(value = "/getOfficeRentInfo", method = RequestMethod.GET)
	public ResultListVO getOfficeRentInfo(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		logger.debug("parameter=" + parameter);
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(estimateService.getOfficeRentInfo(parameter)); 

    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	@RequestMapping(value = "/setBranch", method = RequestMethod.GET)
	public ResultListVO setBranch(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		logger.debug("parameter=" + parameter);
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(estimateService.setBranch(parameter)); 

    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	
	@RequestMapping(value = "/getEstimateList_simple", method = RequestMethod.GET)
	public ResultListVO getEstimateList_simple(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		logger.debug("parameter=" + parameter);
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(estimateService.getEstimateList_simple(parameter)); 

    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	
	@RequestMapping(value = "/getEstimateList", method = RequestMethod.GET)
	public ResultListVO getEstimateList(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		logger.debug("parameter=" + parameter);
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(estimateService.getEstimateList(parameter)); 

    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getEstimateListExcel_simple", method=RequestMethod.POST)
	@ResponseBody
	public void getEstimateListExcel_simple(@RequestParam Map<String, Object> parameter, Messages messages, HttpServletRequest request,
				HttpServletResponse response) throws ParsePropertyException, InvalidFormatException, IOException {

		String fileName = "견적서목록(단순견적)";

		//total_count + 1 - x.RNUM  Integer.parseInt(from);
		List<EstimateInfoVO> list = estimateService.getEstimateListExcel_simple(parameter);
		int lng = list.size();
		for(int i=0;i<lng;i++){
		int rNum = Integer.parseInt(list.get(i).getRNUM());
		rNum = lng +1 - rNum;
		list.get(i).setRNUM(Integer.toString(rNum));
		}
		excelSVC.getExcelDownLoad("견적서목록(단순견적)", "xlsx", "list", list, response);
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
	@RequestMapping(value="/getEstimateListExcel", method=RequestMethod.POST)
	@ResponseBody
	public void getEstimateListExcel(@RequestParam Map<String, Object> parameter, Messages messages, HttpServletRequest request,
				HttpServletResponse response) throws ParsePropertyException, InvalidFormatException, IOException {

		String fileName = "견적서목록";

		//total_count + 1 - x.RNUM  Integer.parseInt(from);
		List<EstimateInfoVO> list = estimateService.getEstimateListExcel(parameter);
		int lng = list.size();
		for(int i=0;i<lng;i++){
		int rNum = Integer.parseInt(list.get(i).getRNUM());
		rNum = lng +1 - rNum;
		list.get(i).setRNUM(Integer.toString(rNum));
		}
		excelSVC.getExcelDownLoad("견적서목록", "xlsx", "list", list, response);
	}	
}





