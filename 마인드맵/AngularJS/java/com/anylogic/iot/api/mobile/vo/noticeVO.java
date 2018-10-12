
package com.anylogic.iot.api.mobile.vo;

import java.util.List;

import com.anylogic.iot.base.common.CommVO;

/**
 * <PRE>
 *  ClassName : AdminVO
 * </PRE>
 * @version : 1.0
 * @date    : 2015. 5. 21. 오전 9:37:22
 * @author  : jkkim
 * @brief   :
 */

public class noticeVO extends CommVO{

	/*NOTICE_ID(PK)	N/A	INTEGER	공지사항ID(PK)
	BOARD_KND	N/A	INTEGER	번호
	WRITER	N/A	VARCHAR(20)	작성자
	TITLE	N/A	VARCHAR(200)	제목
	VIEWS	N/A	INTEGER	조회수
	CONTENTS	N/A	LONG TEXT	내용
	WRITE_DT	N/A	DATE	작성일자
	MODIFY_DT	N/A	DATE	수정일자
	STATE	N/A	CHAR(1)	상태
*/
	// 사용자아이디
	private String notice_id;

	// 역할아이디
	private String board_knd;

	// 관리자 그룹명
	private String writer;

	// 단위서비스코드
	private String title;

	// 사용자명
	private String views;

	// 비밀번호
	private String contents;

	// 이동전화번호
	private String write_dt;

	// 전화번호
	private String modify_dt;

	// 이메일주소
	private String state;

	public String getNotice_id() {
		return notice_id;
	}

	public void setNotice_id(String notice_id) {
		this.notice_id = notice_id;
	}

	public String getBoard_knd() {
		return board_knd;
	}

	public void setBoard_knd(String board_knd) {
		this.board_knd = board_knd;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getViews() {
		return views;
	}

	public void setViews(String views) {
		this.views = views;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getWrite_dt() {
		return write_dt;
	}

	public void setWrite_dt(String write_dt) {
		this.write_dt = write_dt;
	}

	public String getModify_dt() {
		return modify_dt;
	}

	public void setModify_dt(String modify_dt) {
		this.modify_dt = modify_dt;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	
	

}
