
package com.anylogic.iot.base.mail.vo;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.anylogic.iot.base.mail.MailConstants;
import com.anylogic.iot.base.mail.MailConstants.MAIL_TYPE;
import com.anylogic.iot.base.util.PropUtil;
 

public class SendMailVO {
	
	private Date   sendDate; //전송일시
	
	// 송신자 메일
	private String from = com.anylogic.iot.base.util.PropUtil.getInstance().getPropValue(com.anylogic.iot.base.mail.MailConstants.DEFAULT_MAIL_TEMPLATE_PROPERTICE, 
			com.anylogic.iot.base.mail.MailConstants.MAIL_SENDER_ID) ;  
	private List<String> to;  //수신자 
	private String subject; //제목
	private com.anylogic.iot.base.mail.MailConstants.MAIL_TYPE mailType; // 전송 Content 내용
	private Object content; // 전송 내용 객체
	private File[] atachfile; // 첨부파일
	private List<String> cc;  //참조 

	@SuppressWarnings("unused")
	private SendMailVO(){
		super();
	}
	
	public SendMailVO(com.anylogic.iot.base.mail.MailConstants.MAIL_TYPE mailType){
		super();
		this.mailType = mailType;
	} 
	
	public com.anylogic.iot.base.mail.MailConstants.MAIL_TYPE getMailType() {
		return mailType;
	}
 
	public File[] getAtachfile() {
		return atachfile;
	}

	public void setAtachfile(File[] atachfile) {
		this.atachfile = atachfile;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	} 
	
	 
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void addTo(String to){
		if(this.to == null) this.to = new ArrayList<String>();
		this.to.add(to);
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public List<String> getCc() {
		return cc;
	}

	public void setCc(List<String> cc) {
		this.cc = cc;
	} 

	
}
