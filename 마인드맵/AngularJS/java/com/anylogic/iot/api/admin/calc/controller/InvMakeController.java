
package com.anylogic.iot.api.admin.calc.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anylogic.iot.Version;
import com.anylogic.iot.api.admin.calc.service.InvMakeService;
import com.anylogic.iot.base.common.ResultListVO;
import com.anylogic.iot.base.mail.MailSender;
import com.anylogic.iot.base.mail.vo.MailContentsVO;
import com.anylogic.iot.base.mail.vo.SendMailVO;
import com.anylogic.iot.base.mvc.message.Messages;


      
@RestController
@RequestMapping("/" + Version.V1+"/admin/calc")
public class InvMakeController {

	private static final Logger logger = LoggerFactory.getLogger(InvMakeController.class);

	@Autowired
	private InvMakeService invMakeService;
	

	@Autowired
	private MailSender mailSender;
	
	@RequestMapping(value = "/showInfoTaxInvoice", method = RequestMethod.POST)
	public ResultListVO showInfoTaxInvoice(@RequestBody Map<String, Object> parameter, Messages messages) {
	
		logger.debug("parameter==" + parameter);
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(invMakeService.showInfoTaxInvoice(parameter));
    	 
    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	@RequestMapping(value = "/getCalculTenantList", method = RequestMethod.POST)
	public ResultListVO getCalculTenantList(@RequestBody Map<String, Object> parameter, Messages messages) {
	
		logger.debug("parameter==" + parameter);
		
		/*
		@SuppressWarnings("unchecked")
		List<String> officeTypeList = (List<String>) parameter.get("office_type_list");
		parameter.put("officeTypeList", officeTypeList);
		*/
		
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(invMakeService.getCalculTenantList(parameter));
    	 
    	messages.addMessage("OK", "");
		return resultListVO;
	}
	
	@RequestMapping(value = "/getCalculTenantViewPage", method = RequestMethod.GET)
	public Map<?, ?> getCalculTenantViewPage(@RequestParam Map<String, Object> parameter, Messages messages) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
    	messages.addMessage("OK", "");
		return resultMap;
	}

	@RequestMapping(value = "/getCalculTenantOriginView", method = RequestMethod.GET)
	public Map<?, ?> getCalculTenantOriginView(@RequestParam Map<String, Object> parameter, Messages messages) {
		Map<?, ?> resultMap = invMakeService.getCalculTenantOriginView(parameter); 
    	messages.addMessage("OK", "");
		return resultMap;
	}

	@RequestMapping(value = "/getInvoinceIdForOrigin", method = RequestMethod.GET)
	public String getInvoinceIdForOrigin(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		logger.debug("getInvoinceIdForOrigin >> parameter==" + parameter);
    	messages.addMessage("OK", "");
		return invMakeService.getInvoinceIdForOrigin(parameter);
	}

	@RequestMapping(value = "/getCalculTenantView", method = RequestMethod.GET)
	public Map<?, ?> getCalculTenantView(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		logger.debug("getCalculTenantView => parameter==" + parameter);
		
		Map<?, ?> resultMap = invMakeService.getCalculTenantView(parameter); 
    	 
    	messages.addMessage("OK", "");
		return resultMap;
	}
	
	@RequestMapping(value = "/getInvoiceWritePage", method = RequestMethod.GET)
	public Map<?, ?> getInvoiceWritePage(@RequestParam Map<String, Object> parameter, Messages messages) {
			
		logger.debug("getInvoiceWritePage >> parameter====" + parameter);
		
		Map<?, ?>	resultMap = invMakeService.getCalculTenantView(parameter);	

		logger.debug("getInvoiceWritePage >> resultMap====" + resultMap);
    	
    	messages.addMessage("OK", "");
		return resultMap;
	}		

	@RequestMapping(value = "/invoiceWrite", method = RequestMethod.POST)
	public int invoiceWrite(@RequestBody Map<String, Object> parameter, Messages messages) {
		
		int res = invMakeService.invoiceWrite(parameter);
		logger.debug("[by kgh] invoiceWrite res==" + res);

		res = invMakeService.updateInvoiceVat(parameter);
		logger.debug("[by kgh] updateInvoiceVat res==" + res);

		res = invMakeService.updateInvoiceTotalAmount(parameter);
		logger.debug("[by kgh] updateInvoiceTotalAmount res==" + res);
    	 
		if (res == 1) {
			messages.addMessage("OK", "");
		} else {
			messages.addMessage("NotOK", "인보이스 작성에 실패 했습니다.");
		}
		return res;	
	}
	
	
	

	/**
	 * 정산작성 메일 전송
	 * 
	 * @param parameter
	 * @param messages
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
	@ResponseBody
	public String sendEmail(@RequestBody Map<String, Object> parameter, Messages messages) throws Exception {

		try  {
			Map<String, Object> sendInvoiceInfo = (Map<String, Object>) parameter.get("invoiceInfo");
		
			List<String> receiver = new ArrayList<String>();
	/*		receiver.add(sendInvoiceInfo.get("mgr_email").toString());
	*/
			
			/*receiver.add("hianpro@daum.net");*/
			
			String Email = sendInvoiceInfo.get("mgr_email").toString();
			receiver.add(Email );
			

			
			SendMailVO vo = null;
			MailContentsVO contents = new MailContentsVO();
	
			vo = new SendMailVO(com.anylogic.iot.base.mail.MailConstants.MAIL_TYPE.INVOICE_WRITE);
			String mailTitle = "";
			Date today = new Date();
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM");
			;
			mailTitle = "COWORKERS "+date.format(today)+" Invoice : "+sendInvoiceInfo.get("company_name").toString();
			vo.setSubject(mailTitle);
	
			/*String value = new String(parameter.get("content").toString().getBytes("UTF-8"));*/
			//contents.setContents(parameter.get("content").toString());
			/*contents.setContents(value);*/
			/*contents.setTitle(parameter.get("title").toString());*/
	
			vo.setSendDate(new Date());
	/*		vo.setContent(contents);*/
			vo.setContent(sendInvoiceInfo);
	
			vo.setTo(receiver);
			
			List<String> ccReceiver = new ArrayList<String>();
			String sendAddr2 = "operations@coworkerskorea.com";
			ccReceiver.add(sendAddr2);
			vo.setCc(ccReceiver);
			
			mailSender.sendMailForHtml(vo);
			//memberService.insertSendingHistoryEmail(parameter);
		} catch (Exception e) {
			return "0";
		}
		return "1";
	}
	

	

	/**
	 * 견적서 정산작성 메일 전송
	 * 
	 * @param parameter
	 * @param messages
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/sendEmailRelative", method = RequestMethod.POST)
	@ResponseBody
	public String sendEmailRelative(@RequestBody Map<String, Object> parameter, Messages messages) throws Exception {

		try  {
			Map<String, Object> sendInvoiceInfo = (Map<String, Object>) parameter.get("invoiceInfo");
		
			List<String> receiver = new ArrayList<String>();

			String Email = sendInvoiceInfo.get("mgr_email").toString();
			receiver.add(Email );
			

			SendMailVO vo = null;
			MailContentsVO contents = new MailContentsVO();
	
			vo = new SendMailVO(com.anylogic.iot.base.mail.MailConstants.MAIL_TYPE.INVOICE_RELATIVE_WRITE);
			vo.setSubject("Coworkers에서 안내 드립니다.");
	
			vo.setSendDate(new Date());
			vo.setContent(sendInvoiceInfo);
	
			vo.setTo(receiver);
	
			mailSender.sendMailForHtml(vo);
		} catch (Exception e) {
			return "0";
		}
		return "1";
	}

	
	@RequestMapping(value = "/getInvoicePreView", method = RequestMethod.GET)
	public ResultListVO getInvoicePreView(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		ResultListVO resultListVO = new ResultListVO();
    	//resultListVO.setRows(invMakeService.getInvoicePreView(parameter)); 
    	 
    	messages.addMessage("OK", "");
		return resultListVO;
	}	
	
	@RequestMapping(value = "/getInvoiceSetVatDefault", method = RequestMethod.GET)
	public  Map<?, ?> getInvoiceSetVatDefault(@RequestParam Map<String, Object> parameter, Messages messages) {
		
		Map<?, ?> resultMap = invMakeService.getInvoiceSetVatDefault(parameter); 
   	 
    	messages.addMessage("OK", "");
		return resultMap;
	}
	
	@RequestMapping(value = "/saveInvoiceSetVatDefault", method = RequestMethod.POST)
	public int saveInvoiceSetVatDefault(@RequestBody Map<String, Object> parameter, Messages messages) {
		
		int res = invMakeService.saveInvoiceSetVatDefault(parameter);
		logger.debug("[by kgh] invoiceWrite res==" + res);
    	 
		if (res == 1) {
			messages.addMessage("OK", "");
		} else {
			messages.addMessage("NotOK", "VAT 예외항목 Default 설정 저장 실패 했습니다.");
		}
		return res;	
	}
}





