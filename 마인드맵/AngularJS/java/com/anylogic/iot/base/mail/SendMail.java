
package com.anylogic.iot.base.mail;

import java.io.File;
import java.util.Date;
import java.util.List;  

/**
 * <PRE>
 *  ClassName : SendMail 
 * </PRE>
 * @version : 1.0
 * @date    : 2015. 6. 25. 오전 10:45:28
 * @author  : jun
 * @brief   : 
 */

public interface SendMail {
	// 메일 전송 기능
	public void sendMailForHtml(String from, List<String> to, List<String> cc,String subject, String content, File[] attachFile, Date sendDate); 
	// 첨부 파일 미존재
	public void sendMailForHtml(String from, List<String> to,List<String> cc, String subject, String content,  Date sendDate); 
	//  메일 전송 기능
	public void sendMailForText(String from, List<String> to, String subject, String content,  File[] attachFile, Date sendDate); 
	// 첨부 파일 미존재
	public void sendMailForText(String from, List<String> to, String subject, String content,  Date sendDate);
}
