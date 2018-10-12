package com.anylogic.iot.base.security.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.anylogic.iot.base.message.BaseResponse;


public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	String defaultFailureUrl;

	private ObjectMapper mapper = new ObjectMapper();

	/*public LoginFailureHandler(String defaultFailureUrl) {
		super(defaultFailureUrl);
		this.defaultFailureUrl = defaultFailureUrl;
	}*/

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException auth) throws IOException, ServletException {

		if ("application/json".equals(request.getHeader("Content-Type"))) {
			BaseResponse result = new BaseResponse();
			result.setResponseNG();
			result.setMessage("LOGIN FAIL");
			mapper.writeValue(response.getWriter(), result);
		}else{
			response.sendRedirect(request.getContextPath() + this.defaultFailureUrl);
		}
	}

	@Override
	public void setUseForward(boolean forwardToDestination) {
		super.setUseForward(forwardToDestination);
	}


}
