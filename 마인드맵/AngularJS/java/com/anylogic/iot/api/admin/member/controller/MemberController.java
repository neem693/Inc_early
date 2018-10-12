package com.anylogic.iot.api.admin.member.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.anylogic.iot.Version;
import com.anylogic.iot.api.admin.member.service.MemberService;
import com.anylogic.iot.api.admin.member.vo.MemberInfoVO;
import com.anylogic.iot.base.common.ResultListVO;
import com.anylogic.iot.base.excel.service.ExcelSVC;
import com.anylogic.iot.base.mail.MailSender;
import com.anylogic.iot.base.mail.vo.MailContentsVO;
import com.anylogic.iot.base.mail.vo.SendMailVO;
import com.anylogic.iot.base.mvc.message.Messages;
import com.anylogic.iot.base.util.Crypto;

import net.sf.jxls.exception.ParsePropertyException;
      
@RestController
@RequestMapping("/" + Version.V1+"/admin/member")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService myService;

	/*@Autowired
	private BoardService boardService;*/

	@Autowired
	private MailSender mailSender;

	@Autowired
	private ExcelSVC excelSVC;

	/**
	 * 사용자 목록 조회
	 * @param parameter
	 * @param messages
	 * @return
	 */

	//downloadImageFile
	@RequestMapping("/downloadImageFile")
	public void attachImage(String uploadPath,  String orgFilename, String mimeTypeParam, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) throws IOException {
		
		
		if(logger.isDebugEnabled()) {
			logger.debug("download...");
			logger.debug("파일 정보 : {} - {} - {}", uploadPath,  orgFilename);
		}
		
		OutputStream outputStream = response.getOutputStream();
		if(StringUtils.isEmpty(uploadPath) || StringUtils.isEmpty(orgFilename)  || StringUtils.isEmpty(mimeTypeParam)) {
			String errorMessage = "다운로드 파일 정보가 존재하지 않습니다.";
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            if(logger.isDebugEnabled()) logger.debug(errorMessage);
            
            return;
		}
		
		String base64Image = "";
		File file = new File(uploadPath);
		try (FileInputStream imageInFile = new FileInputStream(file)) {
			// Reading a Image file from file system
			byte imageData[] = new byte[(int) file.length()];
			imageInFile.read(imageData);
			base64Image = Base64.encodeBase64String(imageData);
		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image " + ioe);
		}
		
		
		 response.setContentType(mimeTypeParam);
	    response.getOutputStream().write(base64Image.getBytes() );
	}
	
	@RequestMapping(value = "/getMemberList", method = RequestMethod.GET)
	public ResultListVO getMemberList(@RequestParam Map<String, Object> parameter, Messages messages,HttpServletRequest request,HttpServletResponse response) {
	//public String getMemberList(@RequestParam Map<String, Object> parameter, Messages messages,HttpServletRequest request,HttpServletResponse response) {		
		logger.debug("getMemberList");
		logger.debug(request.getHeader("Authorization"));
		String Token = request.getSession().getAttribute("authToken").toString();
		String authToken = request.getHeader("Authorization");

		System.out.println("test log");
		
		ResultListVO resultListVO = new ResultListVO();
		
		if(!Token.equals(authToken)) {
			return resultListVO;	
		}
		
    	resultListVO.setRows(myService.selectMemberList(parameter));
    	logger.debug(response.toString());
		return resultListVO;
	}

	/**
	 * 사용자 정보 상세 조회
	 * @param parameter
	 * @param messages
	 * @return
	 */
	@RequestMapping(value = "/checkDupId", method = RequestMethod.GET)
	public String checkDupId(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		/*
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(myService.selectMemberDetail(parameter)); 
    	
    	return myService.checkDupId(parameter);
    	*/
		return "N";
	}

	/**
	 * 사용자 목록 조회 전체
	 * @param parameter
	 * @param messages
	 * @return
	 */
	@RequestMapping(value = "/getMemberListAll", method = RequestMethod.GET)
	public ResultListVO getMemberListAll(@RequestParam Map<String, Object> parameter, Messages messages,HttpServletRequest request,HttpServletResponse response) {

		String Token = request.getSession().getAttribute("authToken").toString();
		String authToken = request.getHeader("Authorization");
		
		ResultListVO resultListVO = new ResultListVO();
		
		if(!Token.equals(authToken)) {
			return resultListVO;	
		}		
		
    	resultListVO.setRows(myService.selectMemberListAll(parameter)); 
		return resultListVO;
	}
	
	/**
	 * 사용자 삭제 
	 * @param parameter
	 * @param messages
	 * @return
	 */
	@RequestMapping(value = "/updateMemberList", method = RequestMethod.PUT)
	public void updateMemberList(@RequestBody Map<String, Object> parameter, Messages messages) {
		int res = 0;
		List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
		sendParam = (List<Map<String, Object>>) parameter.get("list");
		
		for (Map<String, Object> target : sendParam) {
			 myService.updateMemberList(target);
		}

		//messages.addMessage("OK", "");
	}

	/**
	 * 사용자 목록 조회 for excel
	 * @param parameter
	 * @param messages
	 * @return
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 * @throws ParsePropertyException 
	 */
	@RequestMapping(value = "/getMemberListExcel", method = RequestMethod.POST)
	public void getMemberListExcel(HttpServletRequest request, HttpServletResponse response, 
								   @RequestParam Map<String, Object> parameter, Messages messages) throws ParsePropertyException, InvalidFormatException, IOException {
		
    	//resultListVO.setRows(myService.selectMemberList(parameter)); 
		List<MemberInfoVO> list = myService.selectMemberListExcel(parameter);
		int lng = list.size();
		for (MemberInfoVO t : list) {
			if (!t.getPermit_yn().isEmpty()) {
				String value = t.getPermit_yn();

				if (value.equals("Y")) t.setPermit_yn("승인완료");
				if (value.equals("W")) t.setPermit_yn("입주원승인대기");
				if (value.equals("R")) t.setPermit_yn("승인취소");
			}
			
			int rNum = Integer.parseInt(t.getRNUM());
			rNum = lng +1 - rNum;
			t.setRNUM(Integer.toString(rNum));
		}

		excelSVC.getExcelDownLoad("사용자목록", "xlsx", "list", list, response);
	}

	/**
	 * 승인
	 * @param parameter
	 * @param messages
	 * @return
	 */
	@RequestMapping(value = "/updateMemberPermit", method = RequestMethod.PUT)
	public void updateMemberPermit(@RequestBody Map<String, Object> parameter, Messages messages) {
		String permit = (String) parameter.get("permit_yn");
		
		// make qrcode : 
		if ("Y".equals(permit)) {

			String qr = parameter.get("id").toString() + "|" + parameter.get("member_id").toString() + "|" + parameter.get("office_rent_id").toString();
			String cryption_qr = Crypto.AseedEnc(qr);
			String decript = Crypto.AseedDec(cryption_qr); 

			parameter.put("qr_code", cryption_qr);
		} else {
			parameter.put("qr_code", "");
		}
		
		 myService.updateMemberPermit(parameter);
		 
		if (!"Y".equals(permit)) return;

		// 메일 보냄
		List<String> receiver = new ArrayList<String>();
		receiver.add(parameter.get("email").toString());
		
		SendMailVO vo = null;
		MailContentsVO contents = new MailContentsVO();
		
		vo = new SendMailVO(com.anylogic.iot.base.mail.MailConstants.MAIL_TYPE.MEMBER_JOIN_ACCEPT);
		vo.setSubject("Coworkers 승인신청이 처리되었습니다.");
		contents.setId(parameter.get("id").toString());
		contents.setNm(parameter.get("nm").toString());


		vo.setSendDate(new Date());
		vo.setContent(contents);

		vo.setTo(receiver);
	
		mailSender.sendMailForHtml(vo);


		// member_id는 로그를 저장하기 전에, email_sender로 변경해줘야 함.
		List<Map<String, Object>> sendList = new ArrayList<Map<String, Object>> (); 
		Map<String, Object> reciever_info = new HashMap();
		reciever_info.put("member_id", parameter.get("member_id").toString());
		sendList.add(reciever_info);
		parameter.put("sendList", sendList);
		
		
		parameter.put("member_id", parameter.get("email_sender").toString());
		parameter.put("title", contents.getId() + "(" + contents.getNm() + ")" + "님 승인신청" );
		parameter.put("contents", contents.getId() + "(" + contents.getNm() + ")" + "님 승인신청" );
		parameter.put("type", "I" );
		myService.insertSendingHistoryEmail(parameter);
	}

	/**
	 * 사용자 상세 정보 조회
	 * @param parameter
	 * @param messages
	 * @return
	 */
	@RequestMapping(value = "/memberDetail", method = RequestMethod.GET)
	public ResultListVO getMemberDetail(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(myService.selectMemberDetail(parameter)); 
		return resultListVO;
	}
	
	/**
	 * sms, email 전송내역 조회
	 */
	@RequestMapping(value = "/sendingHistroyList", method = RequestMethod.GET)
	public ResultListVO sendingHistroyList(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(myService.selectSendingHistoryList(parameter)); 
		return resultListVO;
	}

	/**
	 * 전송내역 삭제 
	 * @param parameter
	 * @param messages
	 * @return
	 */
	@RequestMapping(value = "/deleteSendingHistoryList", method = RequestMethod.PUT)
	public void deleteSendingHistoryList(@RequestBody Map<String, Object> parameter, Messages messages) {
		List<Map<String, Object>> sendParam = new ArrayList<Map<String, Object>>();
		sendParam = (List<Map<String, Object>>) parameter.get("list");
		
		for (Map<String, Object> target : sendParam) {
			 myService.deleteSendingHistoryList(target);
		}
	}

	/**
	 * 전송내역 상세 조회
	 */
	@RequestMapping(value = "/sendingHistroyDetail", method = RequestMethod.GET)
	public ResultListVO sendingHistroyDetail(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(myService.selectSendingHistoryDetail(parameter)); 
		return resultListVO;
	}

	/**
	 * 전송내역 수신자  조회
	 */
	@RequestMapping(value = "/sendingHistroyReceiver", method = RequestMethod.GET)
	public ResultListVO sendingHistroyReceiver(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(myService.selectSendingHistoryReceiver(parameter)); 
		return resultListVO;
	}

	/**
	 * 사용자 추가
	 * @param parameter
	 * @param messages
	 * @return
	 */
	/*
	@RequestMapping(value = "/memberRegist", method = RequestMethod.PUT)
	public void memberRegist(@RequestBody Map<String, Object> parameter, Messages messages) {
		parameter.put("language_type", "K");
		int res = 0;
		res = myService.insertMember(parameter);
		if (res == 1) {
			messages.addMessage("OK", "");
		} else {
			messages.addMessage("Not OK", "");
		}
	}
	*/
	@RequestMapping(value = "/memberRegist", method = RequestMethod.POST)
	public String memberRegist(HttpServletRequest request, HttpServletResponse response, Messages messages){

		String atcFileId = "";
		String json = request.getParameter("data").toString();

		List<Map<String, Object>> atcFileSeqList = new ArrayList<Map<String, Object>>();
		Map<String,Object> parameter = null;

		try {
			parameter = new ObjectMapper().readValue(json, HashMap.class);
		} catch (Exception e) {
			throw new com.anylogic.iot.base.exception.KTBCCException();
		}

		if(parameter.get("atcFileId") != null){
			atcFileId = parameter.get("atcFileId").toString();
		}

		//int insertKey ;
		Map<String, Object> uploadInfo = new HashMap<String, Object>(); 
		if(parameter.get("uploadInfo") != null){
			uploadInfo =   (Map<String, Object>) parameter.get("uploadInfo");
			
			myService.insertMember(uploadInfo);
			String insertKey  = uploadInfo.get("insertKey").toString();
			parameter.put("insertKey", insertKey);
			myService.setRegDevice(parameter);
		}

		//boardService.insertFileData(uploadInfo, atcFileId, request, response);
		messages.addMessage("OK", "");

		return atcFileId;
	}

	/**
	 * 회사 정보 호출 
	 */
	@RequestMapping(value = "/companyInfo", method = RequestMethod.GET)
	public ResultListVO companyInfo(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(myService.selectCompanyInfo(parameter)); 
		return resultListVO;
	}
	
	/**
	 * 회사 정보 호출 
	 */
	@RequestMapping(value = "/companyInfo2", method = RequestMethod.GET)
	public ResultListVO companyInfo2(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(myService.selectCompanyInfo2(parameter)); 
		return resultListVO;
	}

	/** 
	 * 사용자 정보 수정 (회원관리 - 회원상세)
	 */
	@RequestMapping(value = "/updateMemberDetail", method = RequestMethod.PUT)
	public void updateMemberDetail(@RequestBody Map<String, Object> parameter, Messages messages) {
		String permit = (String) parameter.get("permit_yn");
		
		if ("Y".equals(permit)) {

			String qr = parameter.get("id").toString() + "|" + parameter.get("member_id").toString() + "|" + parameter.get("office_rent_id").toString();
			String cryption_qr = Crypto.AseedEnc(qr);
			String decript = Crypto.AseedDec(cryption_qr); 

			parameter.put("qr_code", cryption_qr);
		} else {
			parameter.put("qr_code", "");
		}
		
		 myService.updateMemberDetail(parameter);
	}
}
