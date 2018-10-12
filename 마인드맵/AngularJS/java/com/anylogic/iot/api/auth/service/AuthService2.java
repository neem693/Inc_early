package com.anylogic.iot.api.auth.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.anylogic.iot.api.auth.mapper.AuthMapper2;
import com.anylogic.iot.api.auth.vo.UserInfoVO;
import com.anylogic.iot.base.security.vo.MenuVo;
import com.anylogic.iot.base.security.vo.UserDetailsVO;


/**
 * <PRE>
 *  ClassName : TestService
 * </PRE>
 * @version : 1.0
 * @date    : 2015. 2. 16. 오후 5:50:22
 * @author  : jk.kim
 * @brief   :
 */

@Service
public class AuthService2 {

	@Autowired
	private AuthMapper2 authMapper;

	//@Resource(name="SendMailService")
   // private SendMailService sendMailService;


	/**
	 *
	 * <PRE>
	 *  MethodName : login
	 * </PRE>
	 * @author : jkkim
	 * @date   : 2015. 6. 3. 오후 1:40:57
	 * @param  :
	 * @return : UserDetailsVO
	 * @brief  : 로그인
	 * @param userDetailsVO
	 * @return
	 */
	public UserDetailsVO login(UserDetailsVO userDetailsVO){
		return authMapper.login(userDetailsVO);
	}


	/**
	 *
	 * <PRE>
	 *  MethodName : getAuthorities
	 * </PRE>
	 * @author : jkkim
	 * @date   : 2015. 6. 3. 오후 1:41:08
	 * @param  :
	 * @return : List<UserDetailsVO>
	 * @brief  : 사용자 권한 목록 조회
	 * @param userDetailsVO
	 * @return
	 */
	public List<UserDetailsVO> getAuthorities(UserDetailsVO userDetailsVO){
		return authMapper.getAuthorities(userDetailsVO);
	}


	/**
	 *
	 * <PRE>
	 *  MethodName : findId
	 * </PRE>
	 * @author : jkkim
	 * @date   : 2015. 6. 3. 오후 1:41:46
	 * @param  :
	 * @return : UserInfoVO
	 * @brief  : 아이디 찾기
	 * @param userInfoVO
	 * @return
	 */
	public UserInfoVO findId(UserInfoVO userInfoVO){
		return authMapper.findId(userInfoVO);
	}


	/**
	 *
	 * <PRE>
	 *  MethodName : findPw
	 * </PRE>
	 * @author : jkkim
	 * @date   : 2015. 6. 3. 오후 1:41:57
	 * @param  :
	 * @return : UserInfoVO
	 * @brief  : 비밀번호 찾기
	 * @param userInfoVO
	 * @return
	 */
	public UserInfoVO findPw(UserInfoVO userInfoVO){
		UserInfoVO resultUserInfoVO = authMapper.findPw(userInfoVO);
		if(resultUserInfoVO == null){
			return null;
		}
		// 임시 비밀번호
		userInfoVO.setPwd(this.createTempPwd(8));

		// 회원 비밀번호 수정
		authMapper.updateTempPwd(userInfoVO);

		return userInfoVO;
	}

	/**
	 * <PRE>
	 *  MethodName : updatePwd
	 * </PRE>
	 * @author : moo
	 * @date   : 2015. 7. 22. 오후 3:19:45
	 * @param  :
	 * @return : boolean
	 * @brief  : 비밀번호 초기화
	 * @param parameter
	 * @return
	 */
	/*public boolean updatePwd(Map<String, Object> parameter) {

		parameter.put("pwd", "new1234!");

		int ch = authMapper.updatePwd(parameter);
		if (ch > 0) {
			return true;
		}
		return false;
	}*/

	@Transactional(value= "transactionManager", propagation = Propagation.REQUIRED)
	public boolean updatePwd(Map<String, Object> parameter) {
		String tmpPwd = createTempPwd(9);

		UserInfoVO vo =  authMapper.selectUserInfo(parameter);
		parameter.put("pwd", tmpPwd);

		int ch = authMapper.updatePwd(parameter);
		if (ch > 0) {
			try{
				//sendMailService.sendEmail(vo, tmpPwd);
				return true;
			} catch(Exception e){
				throw e;
			}
		} else
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

		return false;
	}




	/**
	 *
	 * <PRE>
	 *  MethodName : createTempPwd
	 * </PRE>
	 * @author : jkkim
	 * @date   : 2015. 3. 6. 오후 2:46:38
	 * @param  :
	 * @return : String
	 * @brief  : 임시 비밀번호 생성
	 * @param pwdLenth
	 * @return
	 */
	public String createTempPwd(int pwdLenth){
		StringBuffer tempPwd = new StringBuffer("");
		//String tempPwd = "";

		for(int i = 0; i < pwdLenth; i++){
			char lowerStr = (char)(Math.random() * 26 + 97);
			if(i%2 == 0){
				tempPwd.append((int)(Math.random() * 10));
				//tempPwd += (int)(Math.random() * 10);
			}else{
				tempPwd.append(lowerStr);
				//tempPwd += lowerStr;
			}
		}
		return tempPwd.toString();
		//return tempPwd;
	}


	/**
	 *
	 * <PRE>
	 *  MethodName : getMenuList
	 * </PRE>
	 * @author : jkkim
	 * @date   : 2015. 6. 3. 오후 1:42:39
	 * @param  :
	 * @return : List<MenuVo>
	 * @brief  : 메뉴 조회
	 * @param userDetailsVO
	 * @return
	 */
	public List<MenuVo> getMenuList(UserDetailsVO userDetailsVO){
		return authMapper.getMenuList(userDetailsVO);
	}

	/**
	 * <PRE>
	 *  MethodName : selectUserId
	 * </PRE>
	 * @author : moo
	 * @date   : 2015. 6. 16. 오후 4:27:16
	 * @param  :
	 * @return : String
	 * @brief  : 아이디 조회
	 * @param parameter
	 * @return
	 */
	public String selectUserId(Map<String, Object> parameter) {
		return authMapper.selectUserId(parameter);
	}


	/**
	 * <PRE>
	 *  MethodName : idEmailCheck
	 * </PRE>
	 * @author : moo
	 * @date   : 2015. 7. 22. 오전 11:17:16
	 * @param  :
	 * @return : int
	 * @brief  : 비밀번호찾기(id email 확인)
	 * @param parameter
	 * @return
	 */
	public int idEmailCheck(Map<String, Object> parameter) {
		return authMapper.idEmailCheck(parameter);
	}


}
