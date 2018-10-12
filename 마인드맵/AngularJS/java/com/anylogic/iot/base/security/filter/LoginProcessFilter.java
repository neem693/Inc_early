package com.anylogic.iot.base.security.filter;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.anylogic.iot.base.security.vo.LoginRequest;

public class LoginProcessFilter extends UsernamePasswordAuthenticationFilter {
	private static final Logger logger = LoggerFactory
			.getLogger(LoginProcessFilter.class);

	  private String jsonUsername = "";
	  private String jsonPassword = "";

	  @Override
	  protected String obtainPassword(HttpServletRequest request)  {
		  String password = null;


		  if ("application/json".equals(request.getHeader("Content-Type"))) {
			  password = this.jsonPassword;
		  }else{
			  password = super.obtainPassword(request);
		  }

		  logger.debug("obtainPassword : {}" , password);
	//	  this.password = password;
	      return password;
	  }

	  @Override
	  protected String obtainUsername (HttpServletRequest request){
		  String username = null;

		  if ("application/json".equals(request.getHeader("Content-Type"))) {
			  username = this.jsonUsername;
		  }else{
			  username = super.obtainUsername(request);
		  }

		  logger.info("obtainUsername : {}" , username);
		  return username;
	  }


	  @Override
	  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

		  String langCd = null;		  
			
		  if ("application/json".equals(request.getHeader("Content-Type"))) {
	            try {
	                /*
	                 * HttpServletRequest can be read only once
	                 */
	                StringBuffer sb = new StringBuffer();
	                String line = null;

	                BufferedReader reader = request.getReader();
	                while ((line = reader.readLine()) != null){
	                    sb.append(line);
	                }

	                //json transformation
	                ObjectMapper mapper = new ObjectMapper();
	                LoginRequest loginRequest = mapper.readValue(sb.toString(), LoginRequest.class);

	                this.jsonUsername = loginRequest.getJ_username();
	                this.jsonPassword = loginRequest.getJ_password();

	                langCd = loginRequest.getLangCd();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }else{
	        	langCd = request.getParameter("langCd");
	        }

		    request.getSession().setAttribute("langCd", langCd);
			return super.attemptAuthentication(request, response);
	}


}
