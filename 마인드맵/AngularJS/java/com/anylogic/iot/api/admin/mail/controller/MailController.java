
package com.anylogic.iot.api.admin.mail.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.anylogic.iot.Version;
import com.anylogic.iot.api.admin.member.service.MemberService;
import com.anylogic.iot.base.mail.MailSender;
import com.anylogic.iot.base.mail.vo.MailContentsVO;
import com.anylogic.iot.base.mail.vo.SendMailVO;
import com.anylogic.iot.base.mvc.message.Messages;
import com.anylogic.iot.base.util.DateUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.codec.binary.Base64;
@RestController
@RequestMapping("/" + Version.V1 + "/admin/mail")
public class MailController {

	private static final Logger logger = LoggerFactory.getLogger(MailController.class);

	@Autowired
	private MailSender mailSender;

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/sendEamil", method = RequestMethod.GET)
	public Map<String, Object> sendEamil(@RequestParam Map<String, Object> parameter, Messages messages) {
		String ramdom = RandomStringUtils.randomAlphanumeric(10);

		com.anylogic.iot.base.mail.vo.SendMailVO vo = new com.anylogic.iot.base.mail.vo.SendMailVO(
				com.anylogic.iot.base.mail.MailConstants.MAIL_TYPE.CUSTOMER_PORTAL_FIND_PWD);

		String sendAddr = (String) parameter.get("sendId");

		vo.setSendDate(new Date());
		vo.setSubject("인증키 전송");
		vo.addTo(sendAddr);

		vo.setContent(new com.anylogic.iot.base.mail.vo.FindPwVO(ramdom, DateUtil.getTransDate()));
		mailSender.sendMailForHtml(vo);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("tempKey", ramdom);
		// resultListVO.setRows(appService.confirmHint(parameter));

		return resultMap;
	}

	/**
	 * 일반메일용 (회원관리에서 사용함)
	 * 
	 * @param parameter
	 * @param messages
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
	@ResponseBody
	public void sendEmail(@RequestBody Map<String, Object> parameter, Messages messages) throws UnsupportedEncodingException {

		List<Map<String, Object>> sendList = (List<Map<String, Object>>) parameter.get("sendList");
		List<String> receiver = new ArrayList<String>();
		for (int i = 0; i < sendList.size(); i++) {
			// 받는 사람 이메일 주소 세팅
			String sendAddr = sendList.get(i).get("email").toString();
			receiver.add(sendAddr);
		}

		SendMailVO vo = null;
		MailContentsVO contents = new MailContentsVO();

		vo = new SendMailVO(com.anylogic.iot.base.mail.MailConstants.MAIL_TYPE.MEMBER_INFORMATION);
		
		vo.setSubject("Coworkers에서 안내 드립니다.");

		String value = new String(parameter.get("content").toString().getBytes("UTF-8"));
		//contents.setContents(parameter.get("content").toString());
		contents.setContents(value);
		contents.setTitle(parameter.get("title").toString());

		vo.setSubject(parameter.get("title").toString());
		vo.setSendDate(new Date());
		vo.setContent(contents);

		vo.setTo(receiver);

		mailSender.sendMailForHtml(vo);
		memberService.insertSendingHistoryEmail(parameter);
	}

	@RequestMapping(value = "/sendEmail2", method = RequestMethod.POST)
	public String sendEmail2(HttpServletRequest request, HttpServletResponse response, Messages messages)
			throws JsonParseException, JsonMappingException, IOException {

		String sendFile = "";
		String atcFileId = "";
		// String memId = "";
		String json = request.getParameter("data").toString();

		List<Map<String, Object>> atcFileSeqList = new ArrayList<Map<String, Object>>();
		Map<String, Object> parameter = null;

		try {
			parameter = new ObjectMapper().readValue(json, HashMap.class);
		} catch (Exception e) {
			throw new com.anylogic.iot.base.exception.KTBCCException();
		}

		if (parameter.get("atcFileId") != null) {
			atcFileId = parameter.get("atcFileId").toString();
		}

		String json1 = request.getParameter("files").toString();
		List<HashMap<String, Object>> fileList = new ObjectMapper().readValue(json1, List.class);
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> mf = multipartRequest.getFiles("fileField1");

		if (parameter.get("emailInfo") != null) {
			Map<String, Object> emailInfo = new HashMap<String, Object>();
			
			emailInfo = (Map<String, Object>) parameter.get("emailInfo");

			List<String> sendList = (List<String>) emailInfo.get("sendList");
			List<String> receiver = new ArrayList<String>();
			
			for (int i = 0; i < sendList.size(); i++) {				
				String sendAddr = sendList.get(i).toString();
				receiver.add(sendAddr);
			}

			SendMailVO vo = null;
			MailContentsVO contents = new MailContentsVO();

			vo = new SendMailVO(com.anylogic.iot.base.mail.MailConstants.MAIL_TYPE.MEMBER_INFORMATION);
			
			String value = new String(emailInfo.get("content").toString().getBytes("UTF-8"));
			contents.setContents(value);
			
			contents.setTitle(emailInfo.get("title").toString());

			vo.setSendDate(new Date());
			vo.setContent(contents);
			vo.setSubject(emailInfo.get("title").toString());
			vo.setTo(receiver);
			
			if(mf.size() >0){
				int fileLength = mf.size();
				if(emailInfo.containsKey("pdfFileData")) {
					fileLength = fileLength +1;
				}
				File[] attfiles = new File[fileLength];
				String fileName = (String) emailInfo.get("fileName");
				sendFile = fileName;
				for (int j = 0; j < mf.size(); j++) {
					File tempFile = new File(fileName); 
					OutputStream os = new FileOutputStream(tempFile);
					os.write(mf.get(j).getBytes());
					
					attfiles[j] = tempFile;
					
				}
				if(emailInfo.containsKey("pdfFileData")) {
					
					 Document document = new Document();
					 String b64Image = emailInfo.get("pdfFileData").toString();
					 if (b64Image.startsWith("data:image/")) {
						 b64Image = b64Image.substring(b64Image.indexOf(",") + 1);
					 }
						 
					 String output = "e:\\test.pdf";
					 try {
				            PdfWriter.getInstance(document, new FileOutputStream(output));
				            document.open();
				            byte[] decoded = Base64.decodeBase64(b64Image.getBytes());
				            Image base_image = Image.getInstance(decoded); 
				            float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
				            float documentHeight = document.getPageSize().getHeight() - document.topMargin() - document.bottomMargin();
				            base_image.scaleToFit(documentWidth, documentHeight);
				            
				            document.add(base_image);
				            document.close();
				            File tempFile = new File("e:\\test.pdf"); 
							
							attfiles[attfiles.length-1] = tempFile;
				        }
				        catch (Exception e) {
				            e.printStackTrace();
				        }
					
				}
				vo.setAtachfile(attfiles);
			}
		
			mailSender.sendMailForHtml(vo);			
			
			if (!sendFile.isEmpty()) {
				File target = new File(sendFile);
				if (target.exists()) 
					target.delete();
			}
		}
		return atcFileId;
	}

}
