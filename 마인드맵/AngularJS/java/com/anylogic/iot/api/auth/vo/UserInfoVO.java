package com.anylogic.iot.api.auth.vo;


/**
 * <PRE>
 *  ClassName : TestVO
 * </PRE>
 * @version : 1.0
 * @date    : 2015. 2. 23. 오전 11:04:22
 * @author  : jkkim
 * @brief   :
 *
 */

/*@Setter
@Getter
@ToString*/
public class UserInfoVO {

	// 사용자 아이디
	private String userId;

	// 역할 아이디
	private String roleId;

	// 사용자명
	private String userNm;

	// 비밀번호
	private String pwd;

	// 이동전화번호
	private String mphonNo;

	// 이메일주소
	private String emailAdr;

	// 사용자 등급 코드
	private String userClasCd;

	// 서비스 대상 일련번호
	private Long   svcTgtSeq;

	// 이메일 수신 여부
	private String emailRcvYn;

	// 문자메세지 수신여부
	private String chrMsgRcvYn;

	// 사용약관 동의 여부
	private String useStpltAgreeYn;

	// 비고
	private String rmark;

	// 삭제여부
	private String delYn;

	// 생성자 아이디
	private String cretrId;

	// 생성일시
	private String cretDt;

	// 수정자 아이디
	private String amdrId;

	// 수정일시
	private String amdDt;

	//회원일련번호
	private Long mbrSeq;

	//사용자 토큰
	private String userTokn;

	//질문
	private String qstnTypeCd;

	//답변
	private String qstnAnsSbst;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @return the userNm
	 */
	public String getUserNm() {
		return userNm;
	}

	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * @return the mphonNo
	 */
	public String getMphonNo() {
		return mphonNo;
	}

	/**
	 * @return the emailAdr
	 */
	public String getEmailAdr() {
		return emailAdr;
	}

	/**
	 * @return the userClasCd
	 */
	public String getUserClasCd() {
		return userClasCd;
	}

	/**
	 * @return the svcTgtSeq
	 */
	public Long getSvcTgtSeq() {
		return svcTgtSeq;
	}

	/**
	 * @return the emailRcvYn
	 */
	public String getEmailRcvYn() {
		return emailRcvYn;
	}

	/**
	 * @return the chrMsgRcvYn
	 */
	public String getChrMsgRcvYn() {
		return chrMsgRcvYn;
	}

	/**
	 * @return the useStpltAgreeYn
	 */
	public String getUseStpltAgreeYn() {
		return useStpltAgreeYn;
	}

	/**
	 * @return the rmark
	 */
	public String getRmark() {
		return rmark;
	}

	/**
	 * @return the delYn
	 */
	public String getDelYn() {
		return delYn;
	}

	/**
	 * @return the cretrId
	 */
	public String getCretrId() {
		return cretrId;
	}

	/**
	 * @return the cretDt
	 */
	public String getCretDt() {
		return cretDt;
	}

	/**
	 * @return the amdrId
	 */
	public String getAmdrId() {
		return amdrId;
	}

	/**
	 * @return the amdDt
	 */
	public String getAmdDt() {
		return amdDt;
	}

	/**
	 * @return the mbrSeq
	 */
	public Long getMbrSeq() {
		return mbrSeq;
	}

	/**
	 * @return the userTokn
	 */
	public String getUserTokn() {
		return userTokn;
	}

	/**
	 * @return the qstnTypeCd
	 */
	public String getQstnTypeCd() {
		return qstnTypeCd;
	}

	/**
	 * @return the qstnAnsSbst
	 */
	public String getQstnAnsSbst() {
		return qstnAnsSbst;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @param userNm the userNm to set
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * @param mphonNo the mphonNo to set
	 */
	public void setMphonNo(String mphonNo) {
		this.mphonNo = mphonNo;
	}

	/**
	 * @param emailAdr the emailAdr to set
	 */
	public void setEmailAdr(String emailAdr) {
		this.emailAdr = emailAdr;
	}

	/**
	 * @param userClasCd the userClasCd to set
	 */
	public void setUserClasCd(String userClasCd) {
		this.userClasCd = userClasCd;
	}

	/**
	 * @param svcTgtSeq the svcTgtSeq to set
	 */
	public void setSvcTgtSeq(Long svcTgtSeq) {
		this.svcTgtSeq = svcTgtSeq;
	}

	/**
	 * @param emailRcvYn the emailRcvYn to set
	 */
	public void setEmailRcvYn(String emailRcvYn) {
		this.emailRcvYn = emailRcvYn;
	}

	/**
	 * @param chrMsgRcvYn the chrMsgRcvYn to set
	 */
	public void setChrMsgRcvYn(String chrMsgRcvYn) {
		this.chrMsgRcvYn = chrMsgRcvYn;
	}

	/**
	 * @param useStpltAgreeYn the useStpltAgreeYn to set
	 */
	public void setUseStpltAgreeYn(String useStpltAgreeYn) {
		this.useStpltAgreeYn = useStpltAgreeYn;
	}

	/**
	 * @param rmark the rmark to set
	 */
	public void setRmark(String rmark) {
		this.rmark = rmark;
	}

	/**
	 * @param delYn the delYn to set
	 */
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	/**
	 * @param cretrId the cretrId to set
	 */
	public void setCretrId(String cretrId) {
		this.cretrId = cretrId;
	}

	/**
	 * @param cretDt the cretDt to set
	 */
	public void setCretDt(String cretDt) {
		this.cretDt = cretDt;
	}

	/**
	 * @param amdrId the amdrId to set
	 */
	public void setAmdrId(String amdrId) {
		this.amdrId = amdrId;
	}

	/**
	 * @param amdDt the amdDt to set
	 */
	public void setAmdDt(String amdDt) {
		this.amdDt = amdDt;
	}

	/**
	 * @param mbrSeq the mbrSeq to set
	 */
	public void setMbrSeq(Long mbrSeq) {
		this.mbrSeq = mbrSeq;
	}

	/**
	 * @param userTokn the userTokn to set
	 */
	public void setUserTokn(String userTokn) {
		this.userTokn = userTokn;
	}

	/**
	 * @param qstnTypeCd the qstnTypeCd to set
	 */
	public void setQstnTypeCd(String qstnTypeCd) {
		this.qstnTypeCd = qstnTypeCd;
	}

	/**
	 * @param qstnAnsSbst the qstnAnsSbst to set
	 */
	public void setQstnAnsSbst(String qstnAnsSbst) {
		this.qstnAnsSbst = qstnAnsSbst;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserInfoVO [userId=" + userId + ", roleId=" + roleId
				+ ", userNm=" + userNm + ", pwd=" + pwd + ", mphonNo="
				+ mphonNo + ", emailAdr=" + emailAdr + ", userClasCd="
				+ userClasCd + ", svcTgtSeq=" + svcTgtSeq + ", emailRcvYn="
				+ emailRcvYn + ", chrMsgRcvYn=" + chrMsgRcvYn
				+ ", useStpltAgreeYn=" + useStpltAgreeYn + ", rmark=" + rmark
				+ ", delYn=" + delYn + ", cretrId=" + cretrId + ", cretDt="
				+ cretDt + ", amdrId=" + amdrId + ", amdDt=" + amdDt
				+ ", mbrSeq=" + mbrSeq + ", userTokn=" + userTokn
				+ ", qstnTypeCd=" + qstnTypeCd + ", qstnAnsSbst=" + qstnAnsSbst
				+ "]";
	}

}


