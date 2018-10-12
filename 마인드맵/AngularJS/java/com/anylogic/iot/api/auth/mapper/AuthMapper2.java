package com.anylogic.iot.api.auth.mapper;

import java.util.List;
import java.util.Map;

import com.anylogic.iot.api.auth.vo.UserInfoVO;
import com.anylogic.iot.base.security.vo.MenuVo;
import com.anylogic.iot.base.security.vo.UserDetailsVO;

/**
 * <PRE>
 *  ClassName : TestDAO
 * </PRE>
 * @version : 1.0
 * @date    : 2015. 2. 16. 오후 6:29:32
 * @author  : jk.kim
 * @brief   :
 */
public interface AuthMapper2 {


	/**
	 *
	 * <PRE>
	 *  MethodName : login
	 * </PRE>
	 * @author : jkkim
	 * @date   : 2015. 6. 3. 오후 1:38:57
	 * @param  :
	 * @return : UserDetailsVO
	 * @brief  : 로그인
	 * @param userDetailsVO
	 * @return
	 */
	public UserDetailsVO login(UserDetailsVO userDetailsVO);


	/**
	 *
	 * <PRE>
	 *  MethodName : getAuthorities
	 * </PRE>
	 * @author : jkkim
	 * @date   : 2015. 6. 3. 오후 1:39:07
	 * @param  :
	 * @return : List<UserDetailsVO>
	 * @brief  : 사용자 권한 목록 조회
	 * @param userDetailsVO
	 * @return
	 */
	public List<UserDetailsVO> getAuthorities(UserDetailsVO userDetailsVO);


	/**
	 *
	 * <PRE>
	 *  MethodName : getUserInfo
	 * </PRE>
	 * @author : jkkim
	 * @date   : 2015. 6. 3. 오후 1:39:13
	 * @param  :
	 * @return : UserInfoVO
	 * @brief  : 회원정보 조회
	 * @param userInfoVO
	 * @return
	 */
	public UserInfoVO getUserInfo(UserInfoVO userInfoVO);


	/**
	 *
	 * <PRE>
	 *  MethodName : findId
	 * </PRE>
	 * @author : jkkim
	 * @date   : 2015. 6. 3. 오후 1:39:26
	 * @param  :
	 * @return : UserInfoVO
	 * @brief  : 아이디 찾기
	 * @param userInfoVO
	 * @return
	 */
	public UserInfoVO findId(UserInfoVO userInfoVO);


	/**
	 *
	 * <PRE>
	 *  MethodName : findPw
	 * </PRE>
	 * @author : jkkim
	 * @date   : 2015. 6. 3. 오후 1:39:32
	 * @param  :
	 * @return : UserInfoVO
	 * @brief  : 비밀번호 찾기
	 * @param userInfoVO
	 * @return
	 */
	public UserInfoVO findPw(UserInfoVO userInfoVO);


	/**
	 *
	 * <PRE>
	 *  MethodName : updateTempPwd
	 * </PRE>
	 * @author : jkkim
	 * @date   : 2015. 6. 3. 오후 1:39:45
	 * @param  :
	 * @return : void
	 * @brief  : 임시 비밀번호 변경
	 * @param userInfoVO
	 */
	public void updateTempPwd(UserInfoVO userInfoVO);


	/**
	 *
	 * <PRE>
	 *  MethodName : getMenuList
	 * </PRE>
	 * @author : jkkim
	 * @date   : 2015. 6. 3. 오후 1:39:57
	 * @param  :
	 * @return : List<MenuVo>
	 * @brief  : 메뉴목록 조회
	 * @param userDetailsVO
	 * @return
	 */
	public List<MenuVo> getMenuList(UserDetailsVO userDetailsVO);


	/**
	 * <PRE>
	 *  MethodName : selectUserId
	 * </PRE>
	 * @author : moo
	 * @date   : 2015. 6. 16. 오후 4:28:11
	 * @param  :
	 * @return : String
	 * @brief  : 아이디 조회
	 * @param parameter
	 * @return
	 */
	public String selectUserId(Map<String, Object> parameter);


	/**
	 * <PRE>
	 *  MethodName : idEmailCheck
	 * </PRE>
	 * @author : moo
	 * @date   : 2015. 7. 22. 오전 11:17:39
	 * @param  :
	 * @return : int
	 * @brief  : 비밀번호찾기(id email 확인)
	 * @param parameter
	 * @return
	 */
	public int idEmailCheck(Map<String, Object> parameter);


	/**
	 * <PRE>
	 *  MethodName : selectUserInfo
	 * </PRE>
	 * @author : moo
	 * @date   : 2015. 7. 22. 오후 1:26:44
	 * @param  :
	 * @return : UserInfoVO
	 * @brief  : 회원 정보 조회
	 * @param parameter
	 * @return
	 */
	public UserInfoVO selectUserInfo(Map<String, Object> parameter);


	/**
	 * <PRE>
	 *  MethodName : updatePwd
	 * </PRE>
	 * @author : moo
	 * @date   : 2015. 7. 22. 오후 1:36:14
	 * @param  :
	 * @return : void
	 * @brief  : 회원비밀번호 변경
	 * @param parameter
	 */
	public int updatePwd(Map<String, Object> parameter);

}



