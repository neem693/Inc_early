/**
 * <PRE>
 *  Project : BCC.admin-api
 *  Package : com.anylogic.iot.base.common.mail
 * </PRE>
 * @file   : MailSender.java
 * @date   : 2015. 6. 25. 오전 11:05:57
 * @author : jun
 * @brief  :
 *  변경이력    : 
 *        이름     : 일자          : 근거자료   : 변경내용
 *       ------------------------------------
 *        jun  : 2015. 6. 25.       :            : 신규 개발.
 */
package com.anylogic.iot.base.mail;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anylogic.iot.base.mail.SendMail;
import com.anylogic.iot.base.mail.impl.GMailSender;
import com.anylogic.iot.base.mail.vo.SendMailVO; 

/**
 * <PRE>
 *  ClassName : MailSender 
 * </PRE>
 * @version : 1.0
 * @date    : 2015. 6. 25. 오전 11:05:57
 * @author  : jun
 * @brief   : 
 */
@Component
public class MailSender {
	
	private static final Logger logger = LoggerFactory.getLogger(MailSender.class);
	
 	@Autowired
 	private org.apache.velocity.app.VelocityEngine vEngine;
	   
 	private SendMail mailSend;  

	/**
	 * <PRE>
	 *  MethodName : sendMail
	 *               
	 * </PRE>
	 * @author : jun
	 * @date   : 2015. 6. 25. 오후 9:29:26
	 * @param  :
	 * @return : void
	 * @brief  : HTML 형식의 메일 전송
	 * @param mailInfoVo
	 */
	public  <T> void sendMailForHtml(SendMailVO mailInfoVo, Class<T> sender) {
		
		try {
			mailInfoVo = checkMailParam(mailInfoVo);
			
			// HTML Template 처리
			String mailContent = getHtmlContent(mailInfoVo); 
//			mailSend = new com.anylogic.iot.base.common.mail.impl.GMailSender();
			 
			mailSend = (SendMail)sender.newInstance();
			
			// 첨부 파일 미존재 
			if(mailInfoVo.getAtachfile() == null) {
				mailSend.sendMailForHtml(mailInfoVo.getFrom(), mailInfoVo.getTo(),mailInfoVo.getCc(), mailInfoVo.getSubject(), mailContent, mailInfoVo.getSendDate());  
			} else {
				mailSend.sendMailForHtml(mailInfoVo.getFrom(), mailInfoVo.getTo(),mailInfoVo.getCc(), mailInfoVo.getSubject(), mailContent, mailInfoVo.getAtachfile(), mailInfoVo.getSendDate());  
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new com.anylogic.iot.base.exception.KTBCCException(e.getMessage());
		}  
	}
	
	/**
	 * <PRE>
	 *  MethodName : sendMailForHtml
	 * </PRE>
	 * @author : jun
	 * @date   : 2015. 6. 26. 오후 1:00:46
	 * @param  :
	 * @return : void
	 * @brief  :
	 * @param mailInfoVo
	 */
	public void sendMailForHtml(SendMailVO mailInfoVo) {
		
		try {
			mailInfoVo = checkMailParam(mailInfoVo);
			
			// HTML Template 처리
			String mailContent = getHtmlContent(mailInfoVo); 
			mailSend = new com.anylogic.iot.base.mail.impl.GMailSender();
			
			// 첨부 파일 미존재 
			if(mailInfoVo.getAtachfile() == null) {
				mailSend.sendMailForHtml(mailInfoVo.getFrom(), mailInfoVo.getTo(),mailInfoVo.getCc(), mailInfoVo.getSubject(), mailContent, mailInfoVo.getSendDate());  
			} else {
				mailSend.sendMailForHtml(mailInfoVo.getFrom(), mailInfoVo.getTo(), mailInfoVo.getCc(),mailInfoVo.getSubject(), mailContent, mailInfoVo.getAtachfile(), mailInfoVo.getSendDate());  
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new com.anylogic.iot.base.exception.KTBCCException(e.getMessage());
		}  
	}
	
	/**
	 * <PRE>
	 *  MethodName : checkMailParam
	 * </PRE>
	 * @author : jun
	 * @date   : 2015. 6. 25. 오전 11:41:10
	 * @param  :
	 * @return : String
	 * @brief  : 필수값 확인
	 * @param mailInfoVo
	 * @return
	 */
	private SendMailVO checkMailParam(SendMailVO mailInfoVo){
		
		if(mailInfoVo == null) {
			throw new com.anylogic.iot.base.exception.KTBCCException("전송 메일정보가 없습니다.");
		} 
		 
		if(mailInfoVo.getFrom() == null || "".equals(mailInfoVo.getFrom())) {
			throw new com.anylogic.iot.base.exception.KTBCCException("발신자가 누락 되었습니다.");
		}
		
		if(mailInfoVo.getTo() == null || mailInfoVo.getTo().size() == 0 ){
			throw new com.anylogic.iot.base.exception.KTBCCException("수신자가 누락 되었습니다.");
		}
		
		if(mailInfoVo.getSubject() == null || "".equals(mailInfoVo.getSubject())) {
			throw new com.anylogic.iot.base.exception.KTBCCException("제목이 누락 되었습니다.");
		}
		
		if( mailInfoVo.getSendDate() == null) mailInfoVo.setSendDate(new Date()); 
		
		return mailInfoVo;
	}
	
	/**
	 * <PRE>
	 *  MethodName : getHtmlContent
	 * </PRE>
	 * @author : jun
	 * @date   : 2015. 6. 25. 오후 2:38:22
	 * @param  : HTML Template
	 * @return : String
	 * @brief  :
	 * @param mailInfoVo
	 * @return
	 */ 
	private String getHtmlContent(SendMailVO mailInfoVo) {
		String contextStr = "";
		
		Map<String, Object> model = new HashMap<String, Object>(); 
	    model.put(com.anylogic.iot.base.mail.MailConstants.CONTEXT_KEY, mailInfoVo.getContent());
	      
		
		String htmlTemplatePath = com.anylogic.iot.base.util.PropUtil.getInstance().getPropValue(
				com.anylogic.iot.base.mail.MailConstants.DEFAULT_MAIL_TEMPLATE_PROPERTICE, mailInfoVo.getMailType().getSystemPath());
		
		contextStr = org.springframework.ui.velocity.VelocityEngineUtils.mergeTemplateIntoString(vEngine, htmlTemplatePath, "UTF-8", model);
		return contextStr;
	}
	
// UTF-8 깨질수 있어서 안씀 
//	private String getHtmlContent(SendMailVO mailInfoVo) {
//	String contextStr = "";
//	
//	//org.apache.velocity.app.VelocityEngine vEngine = new org.apache.velocity.app.VelocityEngine(); 
//	org.apache.velocity.VelocityContext context = new org.apache.velocity.VelocityContext();
//	context.put(MailConstants.CONTEXT_KEY, mailInfoVo.getContent());
//	
//	System.out.println(mailInfoVo.getContent().toString());
//	
//	String htmlTemplatePath = PropUtil.getInstance().getPropValue(
//			com.anylogic.iot.base.common.mail.MailConstants.DEFAULT_MAIL_TEMPLATE_PROPERTICE, mailInfoVo.getMailType().getSystemPath());
//	
//	org.apache.velocity.Template template = vEngine.getTemplate(htmlTemplatePath);
//	java.io.StringWriter writer = new java.io.StringWriter();
//	template.merge(context, writer);
//	
//	System.out.println( writer.toString());
//	contextStr = writer.toString();
//	return contextStr;
//} 
}
