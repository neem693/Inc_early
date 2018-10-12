package com.anylogic.iot.base.security.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.anylogic.iot.api.auth.service.AuthService2;
import com.anylogic.iot.api.auth.vo.AuthVO;
import com.anylogic.iot.base.message.BaseResponse;
import com.anylogic.iot.base.security.userdetails.CustomUserDetails;
import com.anylogic.iot.base.security.vo.UserDetailsVO;

public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private static final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);

	@Autowired
	private AuthService2 authService;

	private static ObjectMapper mapper = new ObjectMapper();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

		CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
		UserDetailsVO user =(UserDetailsVO)principal.getUserVO();
		BaseResponse result = new BaseResponse();

		AuthVO authVO = new AuthVO();

		logger.debug("LOGIN SUCCESS : {}", user.getUserName());

		if ("application/json".equals(request.getHeader("Content-Type"))) {
			//authVO.setToken(TokenUtil.createToken(user));
			authVO.setUserNm(user.getUserName());
			authVO.setUserId(user.getUserId());
			//사용자 레벨 추가
			authVO.setUserRoleClasCd(user.getUserRoleClasCd());
			user.setLangCd((String) request.getSession().getAttribute("langCd"));
			authVO.setMenuList(authService.getMenuList(user));
			result.setData(authVO);
			result.setResponseOK();
			result.setMessage("LOGIN SUCCESS");
			response.setCharacterEncoding("UTF-8");
			mapper.writeValue(response.getWriter(), result);
		}else{
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}

	@Override
	public void setAlwaysUseDefaultTargetUrl(boolean alwaysUseDefaultTargetUrl) {
		super.setAlwaysUseDefaultTargetUrl(alwaysUseDefaultTargetUrl);
	}

	@Override
	public void setDefaultTargetUrl(String defaultTargetUrl) {
		super.setDefaultTargetUrl(defaultTargetUrl);
	}

}
