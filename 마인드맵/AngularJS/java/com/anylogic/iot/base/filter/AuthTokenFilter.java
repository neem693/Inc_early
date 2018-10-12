package com.anylogic.iot.base.filter;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.anylogic.iot.base.common.ResultListVO;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AuthTokenFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException { // 모든 요청(Request)에 대해서 이 부분이 실행된다.
		HttpServletRequest request = (HttpServletRequest) req;

		// System.out.println("RequestURL : "+request.getRequestURL());
		Pattern p = Pattern.compile("^[seian_admin/v1/]*$");
		// System.out.println("RequestURI : "+ request.getRequestURI());

		if (request.getRequestURI().matches("^+/seian_admin+/v1+/admin+/[a-zA-Z0-9]*+/[a-zA-Z0-9]*$")) {
			System.out.println("RequestURI : " + request.getRequestURI());
			switch (request.getRequestURI()) {
			case "/seian_admin/v1/admin/auth/userLogin":
			case "/seian_admin/v1/admin/auth/userLogout":
			case "/seian_admin/v1/admin/auth/findId":
			case "/seian_admin/v1/admin/auth/findPw":
			case "/seian_admin/v1/admin/onm/selectUserExcel":
				chain.doFilter(req, res);
				break;
			default:
				/*String Token = request.getSession().getAttribute("authToken").toString();
				String authToken = request.getHeader("Authorization");

				byte[] decodedTokenBytes = Base64.decodeBase64(Token.getBytes());
				byte[] decodedauthTokenBytes = Base64.decodeBase64(authToken.getBytes());

				String TokenStr = new String(decodedTokenBytes);
				String authTokenStr = new String(decodedauthTokenBytes);

				String[] array = authTokenStr.split("#");
				String userid = array[0];//파싱한 ID
				String reqTime = array[1];//파싱한 요청 시간

				// 저장된 토큰과 클라이언트 요청 토큰이 같으면
				if (Token.equals(authToken)) {
					Timestamp timestampNow = new Timestamp(System.currentTimeMillis());
					reqTime = reqTime.trim();
					//reqTime = "2013-04-08 10:10:10";
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date parsedDate = null;
					try {
						parsedDate = dateFormat.parse(reqTime);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Timestamp timestampReq = new java.sql.Timestamp(parsedDate.getTime());

					long diff = (timestampNow.getTime() - timestampReq.getTime());
					long sec = diff / 3600000;

					if (sec >= 3) {// 토큰 유효시간이 3시간 이상일때

					} else {
						chain.doFilter(req, res);

					}

				}*/
				chain.doFilter(req, res);
				break;
			}
			// chain.doFilter(req, res);
		} else {
			chain.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException { // 초기화할 때 실행되는 부분
		String testParam = config.getInitParameter("testParam");

		System.out.println("testParam : " + testParam);
	}

	@Override
	public void destroy() {

	}
}
