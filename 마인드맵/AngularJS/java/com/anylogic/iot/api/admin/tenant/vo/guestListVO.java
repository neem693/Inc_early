package com.anylogic.iot.api.admin.tenant.vo;



public class guestListVO {
	private String RNUM = "";	
	private String participant_name = "";
	private String company_name = "";
	private String phone_number = "";
	private String created_date = "";
	private String invite_date = "";
	public String getRNUM() {
		return RNUM;
	}
	public void setRNUM(String rNUM) {
		RNUM = rNUM;
	}
	public String getParticipant_name() {
		return participant_name;
	}
	public void setParticipant_name(String participant_name) {
		this.participant_name = participant_name;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getInvite_date() {
		return invite_date;
	}
	public void setInvite_date(String invite_date) {
		this.invite_date = invite_date;
	}
	
	
}