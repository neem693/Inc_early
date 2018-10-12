
package com.anylogic.iot.base.mail;

/**
 * <PRE>
 *  ClassName : MailConstants
 * </PRE>
 * @version : 1.0
 * @date    : 2015. 6. 25. 오전 11:58:30
 * @author  : jun
 * @brief   :
 */

public class MailConstants {

	public final static String CONTEXT_KEY = "data";
	public static final String DEFAULT_MAIL_TEMPLATE_PROPERTICE = "properties/mail.properties";

	public final static String MAIL_AUTH_ID = "mail.auth.id";
	public final static String MAIL_AUTH_PW = "mail.auth.pw";
	public final static String MAIL_AUTH_PASSWD = "mail.auth.passwd";
	public final static String MAIL_AUTH_HOST = "mail.auth.host";
	public final static String MAIL_AUTH_PORT = "mail.auth.port";
	public final static String MAIL_SENDER_ID = "mail.sender.id";

	
	// 사용자 승인신청 승인 메일
	private final static String CD_MEMBER_JOIN_ACCEPT = "A01";
	private final static String PATH_MEMBER_JOIN_ACCEPT = "mail.member.join.accept";

	// 일반 메일
	private final static String CD_MEMBER_INFORMATION = "A02";
	private final static String PATH_MEMBER_INFORMATION = "mail.member.information";
	
	
	// 정산 메일
	private final static String CD_INVOICE_WRITE = "A03";
	private final static String PATH_INVOICE_WRITE = "mail.dm_invoice_make";
	
	// 견적서 정산 메일
	private final static String CD_INVOICE_RELATIVE_WRITE = "A04";
	private final static String PATH_INVOICE_RELATIVE_WRITE = "mail.dm_invoice_relative_make";
	
	
	
	//  메일 유형
	private final static String CD_TEST_MAIL = "999";
	private final static String PATH_TEST_MAIL = "mail.templates.test";

	private final static String CD_CUSTOMER_PORTAL_FIND_PWD = "001";
	private final static String PATH_CUSTOMER_PORTAL_FIND_PWD = "mail.templates.customer.pwd";
	
	private final static String CD_CUSTOMER_PORTAL_FIND_ENG_PWD = "005";
	private final static String PATH_CUSTOMER_PORTAL_FIND_ENG_PWD = "mail.templates.customer.eng.pwd";
	
	
	private final static String CD_SEND_ESTIMATE = "006";
	private final static String PATH_SEND_ESTIMATE = "mail.templates.estimate.send";
	

	private final static String CD_ADMIN_PORTAL_FIND_PWD = "002";
	private final static String PATH_ADMIN_PORTAL_FIND_PWD = "mail.templates.admin.pwd";

	private final static String CD_CUSTOMER_QNA = "003";
	private final static String PATH_CUSTOMER_QNA = "mail.templates.admin.pwd";

	private final static String CD_ADMIN_QNA = "004";
	private final static String PATH_ADMIN_QNA = "mail.templates.admin.pwd";

	// 회원승인
	private final static String CD_CONFIRM_ALLIANCE = "C01";
	private final static String PATH_CONFIRM_ALLIANCE = "mail.alliance.confirm";
	
	private final static String CD_CONFIRM_ALLIANCE_ENG = "C07";
	private final static String PATH_CONFIRM_ALLIANCE_ENG = "mail.alliance.eng.confirm";

	private final static String CD_CONFIRM_REPRESENT = "C02";
	private final static String PATH_CONFIRM_REPRESENT = "mail.represent.confirm";

	private final static String CD_CONFIRM_BELONG = "C03";
	private final static String PATH_CONFIRM_BELONG = "mail.belong.confirm";

	private final static String CD_CONFIRM_VERIFY = "C04";
	private final static String PATH_CONFIRM_VERIFY = "mail.verify.confirm";

	private final static String CD_UPT_REJECT_ALLIANCE = "C05";
	private final static String PATH_UPT_REJECT_ALLIANCE = "mail.upt.reject.alliance";

	private final static String CD_JOIN_REJECT_ALLIANCE = "C06";
	private final static String PATH_JOIN_REJECT_ALLIANCE = "mail.join.reject.alliance";


	// Q&A
	private final static String CD_REQUEST_QNA = "Q01";
	private final static String PATH_REQUEST_QNA = "mail.qna.request";

	private final static String CD_ANSWER_QNA = "Q02";
	private final static String PATH_ANSWER_QNA = "mail.qna.answer";

	// 상담 신청
	private final static String CD_REQUEST_CNSLG = "N01";
	private final static String PATH_REQUEST_CNSLG = "mail.cnslg.request";

	private final static String CD_ANSWER_CNSLG = "N02";
	private final static String PATH_ANSWER_CNSLG = "mail.cnslg.answer";


	// 사업제안
	private final static String CD_BIZ_PRP = "B01";
	private final static String PATH_BIZ_PRP = "mail.templates.business.receipt";

	private final static String CD_BIZ_PRP_REVIEW = "B02";
	private final static String PATH_BIZ_PRP_REVIEW = "mail.templates.business.review";

	// 검증
	private final static String CD_RESULT_VERIFY = "V01";
	private final static String PATH_RESULT_VERIFY = "mail.verify.request.result";

	
	//모듈신청
	private final static String CD_MODULE_REQ = "M01";
	private final static String PATH_MODULE_REQ  = "mail.module.answer";
	
	public static enum MAIL_TYPE{
		TEST_MAIL(CD_TEST_MAIL){
			public String getSystemPath() {
				return PATH_TEST_MAIL;
			}
		},
		MODULE_REQ(CD_MODULE_REQ){
			public String getSystemPath() {
				return PATH_MODULE_REQ;
			}
		},
		CUSTOMER_PORTAL_FIND_PWD(CD_CUSTOMER_PORTAL_FIND_PWD){
			public String getSystemPath() {
				return PATH_CUSTOMER_PORTAL_FIND_PWD;
			}
		},
		
		SEND_ESTIMATE(CD_SEND_ESTIMATE){
			public String getSystemPath() {
				return PATH_SEND_ESTIMATE;
			}
		},
		
		CUSTOMER_PORTAL_FIND_ENG_PWD(CD_CUSTOMER_PORTAL_FIND_ENG_PWD){
			public String getSystemPath() {
				return PATH_CUSTOMER_PORTAL_FIND_ENG_PWD;
			}
		},
		ADMIN_PORTAL_FIND_PWD(CD_ADMIN_PORTAL_FIND_PWD){
			public String getSystemPath() {
				return PATH_ADMIN_PORTAL_FIND_PWD;
			}
		},
		CUSTOMER_JOIN(CD_ADMIN_PORTAL_FIND_PWD){
			public String getSystemPath() {
				return PATH_ADMIN_PORTAL_FIND_PWD;
			}
		},
		CUSTOMER_QNA(CD_CUSTOMER_QNA){
			public String getSystemPath() {
				return PATH_CUSTOMER_QNA;
			}
		},
		ADMIN_QNA(CD_ADMIN_QNA){
			public String getSystemPath() {
				return PATH_ADMIN_QNA;
			}
		},
		CONFIRM_BELONG(CD_CONFIRM_BELONG){
			public String getSystemPath() {
				return PATH_CONFIRM_BELONG;
			}
		},
		CONFIRM_ALLIANCE(CD_CONFIRM_ALLIANCE){
			public String getSystemPath() {
				return PATH_CONFIRM_ALLIANCE;
			}
		},
		CONFIRM_ALLIANCE_ENG(CD_CONFIRM_ALLIANCE_ENG){
			public String getSystemPath() {
				return PATH_CONFIRM_ALLIANCE_ENG;
			}
		},
		CONFIRM_REPRESENT(CD_CONFIRM_REPRESENT){
			public String getSystemPath() {
				return PATH_CONFIRM_REPRESENT;
			}
		},
		CONFIRM_VERIFY(CD_CONFIRM_VERIFY){
			public String getSystemPath() {
				return PATH_CONFIRM_VERIFY;
			}
		},
		REQUEST_QNA(CD_REQUEST_QNA){
			public String getSystemPath() {
				return PATH_REQUEST_QNA;
			}
		},
		ANSWER_QNA(CD_ANSWER_QNA){
			public String getSystemPath() {
				return PATH_ANSWER_QNA;
			}
		},
		REQUEST_CNSLG(CD_REQUEST_CNSLG){
			public String getSystemPath() {
				return PATH_REQUEST_CNSLG;
			}
		},
		ANSWER_CNSLG(CD_ANSWER_CNSLG){
			public String getSystemPath() {
				return PATH_ANSWER_CNSLG;
			}
		},
		BUSINESS_PROPOSAL(CD_BIZ_PRP){
			public String getSystemPath() {
				return PATH_BIZ_PRP;
			}
		},
		BUSINESS_PROPOSAL_REVIEW(CD_BIZ_PRP_REVIEW){
			public String getSystemPath() {
				return PATH_BIZ_PRP_REVIEW;
			}
		},
		ALLIANCE_UPDATE_REJECT(CD_UPT_REJECT_ALLIANCE){
			public String getSystemPath() {
				return PATH_UPT_REJECT_ALLIANCE;
			}
		},
		ALLIANCE_JOIN_REJECT(CD_JOIN_REJECT_ALLIANCE){
			public String getSystemPath() {
				return PATH_JOIN_REJECT_ALLIANCE;
			}
		},
		RESULT_VERIFY(CD_RESULT_VERIFY){
			public String getSystemPath() {
				return PATH_RESULT_VERIFY;
			}
		},
		MEMBER_JOIN_ACCEPT(CD_MEMBER_JOIN_ACCEPT) {
			public String getSystemPath() {
				return PATH_MEMBER_JOIN_ACCEPT;
			}
		},
		MEMBER_INFORMATION(CD_MEMBER_INFORMATION) {
			public String getSystemPath() {
				return PATH_MEMBER_INFORMATION;
			}
		},
		INVOICE_WRITE(CD_INVOICE_WRITE) {
			public String getSystemPath() {
				return PATH_INVOICE_WRITE;
			}
		},
		INVOICE_RELATIVE_WRITE(CD_INVOICE_RELATIVE_WRITE) {
			public String getSystemPath() {
				return PATH_INVOICE_RELATIVE_WRITE;
			}
		};


		private String typeCd;
		private String systemPath;

		MAIL_TYPE(String typeCd){
			this.typeCd = typeCd;
		};

		public String getTypeCd() {
			return typeCd;
		}
		public String getSystemPath() {
			return systemPath;
		}
	}
}
