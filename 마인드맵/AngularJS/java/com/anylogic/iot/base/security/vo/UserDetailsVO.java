package com.anylogic.iot.base.security.vo;

import java.io.Serializable;
import java.util.List;

public class UserDetailsVO implements Serializable {
    /**
	 *
	 */
	private static final long serialVersionUID = 1454167610283345783L;


    private String userId;
    private String passWord;
    private String userName;
    private String roleId;
    private String svcTgtGroupId;
    private String userClasCd;
    private Long svcTgtSeq;
    private Long mbrSeq;
    private List<MenuVo> menuList;
    private String userTypeCd;
    private String langCd;
    private int userRoleClasCd;
    
    
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the passWord
	 */
	public String getPassWord() {
		return passWord;
	}
	/**
	 * @param passWord the passWord to set
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	/**
	 * @return the svcTgtGroupId
	 */
	public String getSvcTgtGroupId() {
		return svcTgtGroupId;
	}
	/**
	 * @param svcTgtGroupId the svcTgtGroupId to set
	 */
	public void setSvcTgtGroupId(String svcTgtGroupId) {
		this.svcTgtGroupId = svcTgtGroupId;
	}
	/**
	 * @return the userClasCd
	 */
	public String getUserClasCd() {
		return userClasCd;
	}
	/**
	 * @param userClasCd the userClasCd to set
	 */
	public void setUserClasCd(String userClasCd) {
		this.userClasCd = userClasCd;
	}
	/**
	 * @return the svcTgtSeq
	 */
	public Long getSvcTgtSeq() {
		return svcTgtSeq;
	}
	/**
	 * @param svcTgtSeq the svcTgtSeq to set
	 */
	public void setSvcTgtSeq(Long svcTgtSeq) {
		this.svcTgtSeq = svcTgtSeq;
	}
	/**
	 * @return the mbrSeq
	 */
	public Long getMbrSeq() {
		return mbrSeq;
	}
	/**
	 * @param mbrSeq the mbrSeq to set
	 */
	public void setMbrSeq(Long mbrSeq) {
		this.mbrSeq = mbrSeq;
	}
	/**
	 * @return the menuList
	 */
	public List<MenuVo> getMenuList() {
		return menuList;
	}
	/**
	 * @param menuList the menuList to set
	 */
	public void setMenuList(List<MenuVo> menuList) {
		this.menuList = menuList;
	}
	/**
	 * @return the userTypeCd
	 */
	public String getUserTypeCd() {
		return userTypeCd;
	}
	/**
	 * @param userTypeCd the userTypeCd to set
	 */
	public void setUserTypeCd(String userTypeCd) {
		this.userTypeCd = userTypeCd;
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
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	public int getUserRoleClasCd() {
		return userRoleClasCd;
	}
	public void setUserRoleClasCd(int userRoleClasCd) {
		this.userRoleClasCd = userRoleClasCd;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserDetailsVO [userId=" + userId + ", passWord=" + passWord
				+ ", userName=" + userName + ", roleId=" + roleId
				+ ", svcTgtGroupId=" + svcTgtGroupId + ", userClasCd="
				+ userClasCd + ", svcTgtSeq=" + svcTgtSeq + ", mbrSeq="
				+ mbrSeq + ", menuList=" + menuList + ", userTypeCd="
				+ userTypeCd + ", langCd=" + langCd + "]";
	}


}
