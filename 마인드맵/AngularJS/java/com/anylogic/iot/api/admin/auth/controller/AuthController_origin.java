
package com.anylogic.iot.api.admin.auth.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
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
import com.anylogic.iot.api.admin.auth.service.AuthService_origin;
import com.anylogic.iot.api.admin.sms.controller.smsService;
import com.anylogic.iot.base.common.ResultListVO;
import com.anylogic.iot.base.mail.MailSender;
import com.anylogic.iot.base.mvc.message.Messages;
import com.anylogic.iot.base.util.DateUtil;

      
@RestController
@RequestMapping("/" + Version.V1+"/admin/auth")
public class AuthController_origin {

	private static final Logger logger = LoggerFactory.getLogger(AuthController_origin.class);

	@Autowired
	private AuthService_origin authService;
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private smsService smsservice;
	
	@RequestMapping(value="/sendSMS", method=RequestMethod.POST)
	@ResponseBody
	public void sendSMS(@RequestBody Map<String, Object> parameter, Messages messages) {
		
		List<Map<String, Object>> sendList = (List<Map<String, Object>>) parameter.get("sendList");
		List<String> supplierNames1 = new ArrayList<String>();
		
		for(int i=0;i<sendList.size();i++){
			String setData = sendList.get(i).get("number").toString();
			setData  = setData.replace("-", "");
			supplierNames1.add(setData);
		}
		String messageStr = (String) parameter.get("content");

		smsservice.sendSmsMessage(messageStr, supplierNames1);
		
	}

	@RequestMapping(value = "/getMemberList", method = RequestMethod.GET)
	public ResultListVO getMemberList(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(authService.getMemberList(parameter)); 
    	 
    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	@RequestMapping(value = "/getMainViewInfo", method = RequestMethod.GET)
	public ResultListVO getMainViewInfo(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(authService.getMainViewInfo(parameter)); 
    	 
    	messages.addMessage("OK", "");
		return resultListVO;
	}

	@RequestMapping(value = "/userLogout", method = RequestMethod.GET)
	public ResultListVO userLogin(Messages messages,HttpServletRequest request,HttpServletResponse response) {
		ResultListVO resultListVO = new ResultListVO();

    	request.getSession().removeAttribute("loginAuth");
    	request.getSession().removeAttribute("authToken");
    	
    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	@RequestMapping(value = "/userLogin", method = RequestMethod.GET)
	public ResultListVO userLogin(@RequestParam Map<String, Object> parameter, Messages messages,HttpServletRequest request,HttpServletResponse response) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(authService.userLogin(parameter)); 
    	
    	if(resultListVO.getRowNum()>0) { 
    	request.getSession().setAttribute("loginAuth","true");
    	
    	Map<String, Object> resData = (Map<String, Object>) resultListVO.getRows().get(0);
    	
    	String user_id = resData.get("id").toString();
    	
    	String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    	String authToken = user_id + "#" + timeStamp;
    	
    	byte[] encoded = Base64.encodeBase64(authToken.getBytes());
    	  
    	request.getSession().setAttribute("authToken",new String(encoded));

    	resultListVO.setAuthToken(new String(encoded));
    	}
    	
    	messages.addMessage("OK", "");
    	
		return resultListVO;
	}
	
	@RequestMapping(value = "/findId", method = RequestMethod.GET)
	public ResultListVO findId(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(authService.findId(parameter)); 
    	 
    	messages.addMessage("OK", "");
		return resultListVO;
	}

	@RequestMapping(value = "/findPw", method = RequestMethod.GET)
	public Map<String, Object> findPw(@RequestParam Map<String, Object> parameter, Messages messages) {
		Map<String, Object> ret = new HashMap<>();

		// 사용자 정보 확인
		List<Object> userInfo = authService.getUserInfo(parameter);
		if (userInfo.size() == 0) {
			ret.put("result", "REJECT");
			return ret;
		}

		String ramdom = RandomStringUtils.randomAlphanumeric(10);

		com.anylogic.iot.base.mail.vo.SendMailVO vo = new com.anylogic.iot.base.mail.vo.SendMailVO(
				com.anylogic.iot.base.mail.MailConstants.MAIL_TYPE.CUSTOMER_PORTAL_FIND_PWD);

		String sendAddr = (String) parameter.get("userEmail");

		vo.setSendDate(new Date());
		vo.setSubject("임시 비밀번호 전송");
		vo.addTo(sendAddr);


		vo.setContent(new com.anylogic.iot.base.mail.vo.FindPwVO(ramdom,DateUtil.getTransDate() ));
		mailSender.sendMailForHtml(vo);

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("tempKey", ramdom);
		resultMap.put("userId", parameter.get("userId"));
		//resultListVO.setRows(appService.confirmHint(parameter));
		authService.findPw(resultMap);

		ret.put("result", "SUCCESS");
    	return ret;
	}
	
}





