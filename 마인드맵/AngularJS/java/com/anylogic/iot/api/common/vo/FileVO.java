package com.anylogic.iot.api.common.vo;

import com.anylogic.iot.base.common.CommVO;

/**
 * <PRE>
 *  ClassName : FileVO
 * </PRE>
 * @version : 1.0
 * @date    : 2015. 3. 06. 오후 3:08:00
 * @author  : moo
 * @brief   :
 */

public class FileVO extends CommVO {

	public FileVO(){
		super();
	}

	public FileVO(String atcFileId){
		super();
		this.atcFileId = atcFileId;
	}

	public FileVO(String atcFileId,int atcFileSeq){
		super();
		this.atcFileId = atcFileId;
		this.atcFileSeq = atcFileSeq;
	}

	//첨부파일유형코드
	private String fileTypeCd;

	//생성일시
	private String cretDt;
	//삭제여부
	private String delYn;
	//파일아이디
	private String atcFileId;
	//파일명
	private String fileNm;
	//파일일련번호
	private int atcFileSeq;
	//파일크기
	private long fileSize;
	//파일구분값
	private String fileDivVal;
	//업로드경로내용
	private String upldPathSbst;
	// 첨부파일경로내용
	private String atcFilePathSbst;

	//다운로드횟수
	private String downlTmscnt;
	//다운로드만료일자
	private String downlExpDate;
	//다운로드한도횟수
	private String downlLmtTmscnt;

	//비고1
	private String expnsnStrVal1;
	//비고2
	private String expnsnStrVal2;
	//비고3
	private String expnsnStrVal3;
	
	private int deviceNo;
	
	private String memId;
	

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public int getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(int deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getAtcFilePathSbst() {
		return atcFilePathSbst;
	}

	public void setAtcFilePathSbst(String atcFilePathSbst) {
		this.atcFilePathSbst = atcFilePathSbst;
	}

	public String getFileTypeCd() {
		return fileTypeCd;
	}

	public void setFileTypeCd(String fileTypeCd) {
		this.fileTypeCd = fileTypeCd;
	}

	public String getCretDt() {
		return cretDt;
	}

	public void setCretDt(String cretDt) {
		this.cretDt = cretDt;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getAtcFileId() {
		return atcFileId;
	}

	public void setAtcFileId(String atcFileId) {
		this.atcFileId = atcFileId;
	}

	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	public int getAtcFileSeq() {
		return atcFileSeq;
	}

	public void setAtcFileSeq(int atcFileSeq) {
		this.atcFileSeq = atcFileSeq;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileDivVal() {
		return fileDivVal;
	}

	public void setFileDivVal(String fileDivVal) {
		this.fileDivVal = fileDivVal;
	}

	public String getUpldPathSbst() {
		return upldPathSbst;
	}

	public void setUpldPathSbst(String upldPathSbst) {
		this.upldPathSbst = upldPathSbst;
	}

	public String getDownlTmscnt() {
		return downlTmscnt;
	}

	public void setDownlTmscnt(String downlTmscnt) {
		this.downlTmscnt = downlTmscnt;
	}

	public String getDownlExpDate() {
		return downlExpDate;
	}

	public void setDownlExpDate(String downlExpDate) {
		this.downlExpDate = downlExpDate;
	}

	public String getDownlLmtTmscnt() {
		return downlLmtTmscnt;
	}

	public void setDownlLmtTmscnt(String downlLmtTmscnt) {
		this.downlLmtTmscnt = downlLmtTmscnt;
	}

	/**
	 * @return the expnsnStrVal1
	 */
	public String getExpnsnStrVal1() {
		return expnsnStrVal1;
	}

	/**
	 * @param expnsnStrVal1 the expnsnStrVal1 to set
	 */
	public void setExpnsnStrVal1(String expnsnStrVal1) {
		this.expnsnStrVal1 = expnsnStrVal1;
	}

	/**
	 * @return the expnsnStrVal2
	 */
	public String getExpnsnStrVal2() {
		return expnsnStrVal2;
	}

	/**
	 * @param expnsnStrVal2 the expnsnStrVal2 to set
	 */
	public void setExpnsnStrVal2(String expnsnStrVal2) {
		this.expnsnStrVal2 = expnsnStrVal2;
	}

	/**
	 * @return the expnsnStrVal3
	 */
	public String getExpnsnStrVal3() {
		return expnsnStrVal3;
	}

	/**
	 * @param expnsnStrVal3 the expnsnStrVal3 to set
	 */
	public void setExpnsnStrVal3(String expnsnStrVal3) {
		this.expnsnStrVal3 = expnsnStrVal3;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileVO [fileTypeCd=" + fileTypeCd + ", cretDt=" + cretDt
				+ ", delYn=" + delYn + ", atcFileId=" + atcFileId + ", fileNm="
				+ fileNm + ", atcFileSeq=" + atcFileSeq + ", fileSize="
				+ fileSize + ", fileDivVal=" + fileDivVal + ", upldPathSbst="
				+ upldPathSbst + ", atcFilePathSbst=" + atcFilePathSbst
				+ ", downlTmscnt=" + downlTmscnt + ", downlExpDate="
				+ downlExpDate + ", downlLmtTmscnt=" + downlLmtTmscnt
				+ ", expnsnStrVal1=" + expnsnStrVal1 + ", expnsnStrVal2="
				+ expnsnStrVal2 + ", expnsnStrVal3=" + expnsnStrVal3 + "]";
	}

}
