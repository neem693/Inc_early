package com.anylogic.iot.api.admin.tour.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import com.anylogic.iot.api.admin.tenant.vo.CompanyInfoVO;
import com.anylogic.iot.api.admin.tour.service.TourService;
import com.anylogic.iot.api.admin.tour.vo.TourInfoVO;
import com.anylogic.iot.base.common.ResultListVO;
import com.anylogic.iot.base.excel.service.ExcelSVC;
import com.anylogic.iot.base.mail.MailSender;
import com.anylogic.iot.base.mail.vo.MailContentsVO;
import com.anylogic.iot.base.mail.vo.SendMailVO;
import com.anylogic.iot.base.mvc.message.Messages;

import net.sf.jxls.exception.ParsePropertyException;
import org.codehaus.jackson.map.ObjectMapper;
      
@RestController
@RequestMapping("/" + Version.V1+"/admin/tour")
public class TourController {

	private static final Logger logger = LoggerFactory.getLogger(TourController.class);

	/*
	@Autowired
	private MemberService myService;
	*/

	@Autowired
	private TourService myService;

	@Autowired
	private MailSender mailSender;
	@Autowired
	private ExcelSVC excelSVC;
	/**
	 * 목록 조회
	 * @param parameter
	 * @param messages
	 * @return
	 */
	@RequestMapping(value = "/getTourApplicantsList", method = RequestMethod.GET)
	public ResultListVO getTourApplicantsList(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(myService.getTourApplicantsList(parameter)); 
		return resultListVO;
	}

	
	/**
	 * 삭제 
	 * @param parameter
	 * @param messages
	 * @return
	 */
	@RequestMapping(value = "/deleteTourApplicantsList", method = RequestMethod.PUT)
	public void deleteTourApplicantsList(@RequestBody Map<String, Object> parameter, Messages messages) {
		 myService.deleteTourApplicantsList(parameter);
	}

	/**
	 * 추가
	 * @param parameter
	 * @param messages
	 * @return
	 */
	@RequestMapping(value = "/addTourApplicants", method = RequestMethod.POST)
	public String addTourApplicants(@RequestParam Map<String, Object> parameter, Messages messages){
		return myService.addTourApplicants(parameter); 
	}
	
	@RequestMapping(value="/getTourApplicantsListExcel", method=RequestMethod.POST)
	@ResponseBody
	public void getCompanyListExcel(HttpServletRequest request, HttpServletResponse response, 
			   @RequestParam Map<String, Object> parameter, Messages messages) throws ParsePropertyException, InvalidFormatException, IOException {
		String fileName = "투어신청목록";
		
		List<Map<String, Object>> list = myService.getTourApplicantsListExcel(parameter);
		int lng = list.size();
		for(int i=0;i<lng;i++){
			int rNum = Integer.parseInt(list.get(i).get("rnum").toString());
		rNum = lng +1 - rNum;
		list.get(i).put("RNUM", rNum);
		}
		excelSVC.getExcelDownLoad(fileName, "xlsx", "list", list, response);
	}
}
