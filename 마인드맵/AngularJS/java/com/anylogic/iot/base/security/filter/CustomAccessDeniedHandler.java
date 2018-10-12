package com.anylogic.iot.base.security.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.anylogic.iot.base.message.BaseResponse;


public class CustomAccessDeniedHandler implements AccessDeniedHandler{

	private ObjectMapper mapper = new ObjectMapper();

	private String errorPage;

	public CustomAccessDeniedHandler(){ 
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.access.AccessDeniedHandler#handle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.access.AccessDeniedException)
	 */
	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
			ServletException {

		if ("application/json".equals(request.getHeader("Content-Type"))) {
			BaseResponse result = new BaseResponse();
			result.setResponseNG();
			result.setResponseCode("403");
			result.setMessage("ACCESS DENIED");
			mapper.writeValue(response.getWriter(), result);
		}
	}

	/**
	 * @return the errorPage
	 */
	public String getErrorPage() {
		return errorPage;
	}

	/**
	 * @param errorPage the errorPage to set
	 */
	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

}
