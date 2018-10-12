package com.anylogic.iot.base.security.vo;

import java.util.List;

public class Authority {

	private String parent;
	private String id;
	private String name;
	private String level;
	private String url;
	private String visible;
	private List<Authority> child;

	private String menuId;
	private String upMenuId;
	private String menuNm;
	private String menuTypeCd;
	private String scrnAdr;
	private String depthVal;
	private String path;
	private String titleDepth;
	private String isSubExist;
	private String subScrnAdr;
	private String menuImgdivVal;

	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}

	public List<Authority> getChild() {
		return child;
	}
	public void setChild(List<Authority> child) {
		this.child = child;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getVisible() {
		return visible;
	}
	public void setVisible(String visible) {
		this.visible = visible;
	}
	/**
	 * @return the menuId
	 */
	public String getMenuId() {
		return menuId;
	}
	/**
	 * @param menuId the menuId to set
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	/**
	 * @return the upMenuId
	 */
	public String getUpMenuId() {
		return upMenuId;
	}
	/**
	 * @param upMenuId the upMenuId to set
	 */
	public void setUpMenuId(String upMenuId) {
		this.upMenuId = upMenuId;
	}
	/**
	 * @return the menuNm
	 */
	public String getMenuNm() {
		return menuNm;
	}
	/**
	 * @param menuNm the menuNm to set
	 */
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	/**
	 * @return the menuTypeCd
	 */
	public String getMenuTypeCd() {
		return menuTypeCd;
	}
	/**
	 * @param menuTypeCd the menuTypeCd to set
	 */
	public void setMenuTypeCd(String menuTypeCd) {
		this.menuTypeCd = menuTypeCd;
	}
	/**
	 * @return the scrnAdr
	 */
	public String getScrnAdr() {
		return scrnAdr;
	}
	/**
	 * @param scrnAdr the scrnAdr to set
	 */
	public void setScrnAdr(String scrnAdr) {
		this.scrnAdr = scrnAdr;
	}
	/**
	 * @return the depthVal
	 */
	public String getDepthVal() {
		return depthVal;
	}
	/**
	 * @param depthVal the depthVal to set
	 */
	public void setDepthVal(String depthVal) {
		this.depthVal = depthVal;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the titleDepth
	 */
	public String getTitleDepth() {
		return titleDepth;
	}
	/**
	 * @param titleDepth the titleDepth to set
	 */
	public void setTitleDepth(String titleDepth) {
		this.titleDepth = titleDepth;
	}
	/**
	 * @return the isSubExist
	 */
	public String getIsSubExist() {
		return isSubExist;
	}
	/**
	 * @param isSubExist the isSubExist to set
	 */
	public void setIsSubExist(String isSubExist) {
		this.isSubExist = isSubExist;
	}
	/**
	 * @return the subScrnAdr
	 */
	public String getSubScrnAdr() {
		return subScrnAdr;
	}
	/**
	 * @param subScrnAdr the subScrnAdr to set
	 */
	public void setSubScrnAdr(String subScrnAdr) {
		this.subScrnAdr = subScrnAdr;
	}
	/**
	 * @return the menuImgdivVal
	 */
	public String getMenuImgdivVal() {
		return menuImgdivVal;
	}
	/**
	 * @param menuImgdivVal the menuImgdivVal to set
	 */
	public void setMenuImgdivVal(String menuImgdivVal) {
		this.menuImgdivVal = menuImgdivVal;
	}

}
