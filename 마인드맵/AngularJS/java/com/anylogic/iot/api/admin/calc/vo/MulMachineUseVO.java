package com.anylogic.iot.api.admin.calc.vo;


/**
 * 
 * @author kim
 */
public class MulMachineUseVO {
	private String RNUM = "";
	private String company_name = "";
	private String member_nm = "";
	private String paper_type = "";
	private String color = "";
	private String print_count = "";
	private String created_date = "";
	private String amount = "";
	
	
	public String getRNUM() {
		return RNUM;
	}
	public void setRNUM(String rNUM) {
		RNUM = rNUM;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getMember_nm() {
		return member_nm;
	}
	public void setMember_nm(String member_nm) {
		this.member_nm = member_nm;
	}
	public String getPaper_type() {
		return paper_type;
	}
	public void setPaper_type(String paper_type) {
		this.paper_type = paper_type;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPrint_count() {
		return print_count;
	}
	public void setPrint_count(String print_count) {
		this.print_count = print_count;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	 
	
}