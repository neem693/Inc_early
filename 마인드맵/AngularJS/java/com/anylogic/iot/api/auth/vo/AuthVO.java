
package com.anylogic.iot.api.auth.vo;

import java.util.List;

import com.anylogic.iot.base.security.vo.MenuVo;

/**
 * <PRE>
 *  ClassName : TokenVO
 * </PRE>
 * @version : 1.0
 * @date    : 2015. 3. 4. 오후 1:18:37
 * @author  : jkkim
 * @brief   :
 */

public class AuthVO {

	// 로그인시 생성된 토근정보
	private String token;

	// 사용자 아이디
	private String userId;

	// 사용자 이름
	private String userNm;
	
	// 사용자 레벨
	private int userRoleClasCd;	

	// 메뉴 목록
	private List<MenuVo> menuList;

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

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
	 * @return the userNm
	 */
	public String getUserNm() {
		return userNm;
	}

	/**
	 * @param userNm the userNm to set
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
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

	public int getUserRoleClasCd() {
		return userRoleClasCd;
	}

	public void setUserRoleClasCd(int userRoleClasCd) {
		this.userRoleClasCd = userRoleClasCd;
	}

	
	
	
}


