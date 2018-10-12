package com.anylogic.iot.base.mail.vo;

import java.io.Serializable;

/**
 * 
 * @author kim
 *
 */
public class MailContentsVO implements Serializable{

	private static final long serialVersionUID = 1726119075008601658L;
	
	private String title = "";
	private String contents = "";
	private String nm = "";
	private String id = "";

	public MailContentsVO(){
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
