package com.anylogic.iot.api.common.vo;

/**
 * <PRE>
 *  ClassName : CommonCodeVO
 * </PRE>
 * @version : 1.0
 * @date    : 2015. 5. 21. 오후 2:29:01
 * @author  : jkkim
 * @brief   :
 */

public class CommonCodeVO {

	// 상세 코드
	private String dtlCd;

	// 상세코드명
	private String dtlCdNm;

	// 언어코드
	private String langCd;

	/**
	 * @return the dtlCd
	 */
	public String getDtlCd() {
		return dtlCd;
	}

	/**
	 * @param dtlCd the dtlCd to set
	 */
	public void setDtlCd(String dtlCd) {
		this.dtlCd = dtlCd;
	}

	/**
	 * @return the dtlCdNm
	 */
	public String getDtlCdNm() {
		return dtlCdNm;
	}

	/**
	 * @param dtlCdNm the dtlCdNm to set
	 */
	public void setDtlCdNm(String dtlCdNm) {
		this.dtlCdNm = dtlCdNm;
	}

	/**
	 * @return the langCd
	 */
	public String getLangCd() {
		return langCd;
	}

	/**
	 * @param langCd the langCd to set
	 */
	public void setLangCd(String langCd) {
		this.langCd = langCd;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CommonCodeVO [dtlCd=" + dtlCd + ", dtlCdNm=" + dtlCdNm
				+ ", langCd=" + langCd + "]";
	}

}
