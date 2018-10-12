package com.anylogic.iot.base.remote;


import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
//import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

@Component
@EnableConfigurationProperties(HttpProperties.class)
public class HttpApi {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(HttpApi.class);

	@Autowired
	private  HttpProperties httpProperties;

	@Autowired
	private TokenVerifier tokenVerfier;

	private static String _ACCESSTION_TOKEN = "";

	@Value("${auth.token.useYN}")
	private String authTokenFlag;
	
	@Value("${master.url}")
	private String masterUrl;
	@Value("${master.contextPath}")
	private String masterContextPath;
	
	@Value("${external.url}")
	private String externalUrl;
	@Value("${external.contextPath}")
	private String externalContextPath;
	
	@Value("${core.url}")
	private String coreUrl;
	@Value("${core.contextPath}")
	private String coreContextPath;
	
	@Value("${ec.url}")
	private String ecUrl;
	@Value("${ec.contextPath}")
	private String ecContextPath;
	
	@Value("${homeIoT.url}")
	private String homeIotUrl;
	@Value("${homeIoT.contextPath}")
	
	private String homeContextPath;
	@Value("${portal.url}")
	
	private String portalUrl;
	@Value("${portal.contextPath}")
	private String portalContextPath;
	
	@Value("${whether.url}")
	private String whetherUrl;
	@Value("${whether.contextPath}")
	private String whetherContextPath;
	
	@Value("${push.url}")
	private String pushUrl;
	@Value("${push.contextPath}")
	private String pushContextPath;
	/**
	 * <PRE>
	 *  MethodName : exchangeMasterApi
	 * </PRE>
	 * @author : jun
	 * @date   : 2015. 6. 8. 오후 8:22:43
	 * @param  :
	 * @return : S
	 * @brief  :
	 * @param method
	 * @param interfaceId
	 * @param reqVO
	 * @param resType
	 * @return
	 */
	public String exchangeMasterApi(HttpMethod method ,String interfaceId, Map<String, Object> reqVO, String apiType) {
		
		ResponseEntity<byte[]> res = null;		
		String resultData = "";

 		String interfaceUrl = getExpandedUrlToMap(interfaceId, reqVO, apiType);
		if(interfaceUrl == null || "".equals(interfaceUrl)) {
			throw new RuntimeException("Interface Tager Url Is null.. check please");
		}
		//if(interfaceId == "api.external.contract.get"){
			System.out.println("interfaceUrl : " + interfaceUrl);
		//}
		
		if("Y".equals(authTokenFlag)){

			// ACCESS TOKEN 없을시 요청  처리
			if("".equals(_ACCESSTION_TOKEN)){
				requestAuthServerKey();
			}  else {
				// Token 시간  만료 또는 오류시 재발급 처리
				if( !tokenVerfier.getTokenVerify(_ACCESSTION_TOKEN) ){
					logger.info("_ACCESSTION_TOKEN GET ");
					requestAuthServerKey();
				}
			}
			
			if(!"".equals(_ACCESSTION_TOKEN)) {
				
				RestTemplate restTemplate = getRestTemplate(MediaType.APPLICATION_JSON);
				
				HttpHeaders headers = getAuthorizationHeaders(com.anylogic.iot.base.remote.Constants._PREFIX_JWT_AFTER);
				headers.setContentType(MediaType.APPLICATION_JSON);
				
				HttpEntity<Map<String, Object>> entitiry = null;
				
				if (!HttpMethod.DELETE.equals(method)) {
					entitiry = new HttpEntity<Map<String, Object>>(reqVO, headers);
				} else {
					entitiry = new HttpEntity<Map<String, Object>>(null, headers);
				}
				res = restTemplate.exchange(interfaceUrl, method, entitiry, byte[].class);
				
				if( res == null || res.getStatusCode() != HttpStatus.OK || res.getBody() == null){
					throw new RuntimeException("Master Api Call Exception is null");
				}
				
			}else {
				throw new RuntimeException("Auth Exception is null");				
			}
			
		}else{
			
			RestTemplate restTemplate = getRestTemplate(MediaType.APPLICATION_JSON);
			
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_JSON);
			if(apiType == "portal"){
				header.set("Authorization", "Bearer eyJhbGciOiJSUzI1NiJ9.eyJzdmNfdGd0X3NlcSI6IjEwMDAwMDI3MTYiLCJ1c2VyX25hbWUiOiJydGJsaWZlOTciLCJwdWJfdGltZSI6MTQ4MTc2MjA4NzQ3NywibWJyX2lkIjoicnRibGlmZTk3IiwibWJyX3NlcSI6IjEwMDAwMDI2MzEiLCJtYnJfY2xhcyI6IjAwMDMiLCJhdXRob3JpdGllcyI6WyJST0xFX09QRU5BUEkiLCJST0xFX1VTRVIiXSwicGxhdGZvcm0iOiIzTVAiLCJ0aGVtZV9jZCI6IlBUTCIsImNsaWVudF9pZCI6Ik1qWmlaV014T0dOaU5qZzBOR1UyWldKbFl6WXhZbVZoTlRabE9XSXhaREV4TkRNeU1qQTJPVGc0T1RVMCIsImF1ZCI6WyJJT1QtQVBJIl0sInVuaXRfc3ZjX2NkIjoiMDAxIiwic2NvcGUiOlsidHJ1c3QiXSwiZHN0cl9jZCI6IjAwMSIsImNvbXBhbnkiOiJLdCIsIm1icl9ubSI6IuyViOyyreynhCIsImV4cCI6MTQ4MjM2MjA4NywianRpIjoiMTE0ZTA3MGQtNzA5MC00YTUzLWJiMzEtZDkwNjIzNjE4ODliIn0.hrYtL8R9jgibJQMY0qP7xW3tv8E2ylOfAHeFYu621iAIvhQxhXZuzeL0-mdlPotyHf_tRtPVpwTlH74iMx4P8QhXORvoXm1R1CFzj7oaxF43rE-nitfBbOI9TGCojaiFViaWr4NWgrbVCNAahAcU4G-nG5gBK4xtXVY2e1A_yFEKUjTQCAFyc0Kc53_PLFsGEyMs41a7Btn0GA7G3JtBqVmw0hA09bR3H00qtNigb0KGtkt_E38YN5E-KLhC6j3VWrxKD1dbFs-LBhcdNetgoC04h7U5_TUzhQ8tdu-mvnMd4Ds72_e4N5gl2RlOmeZ9KbdX_qx4zkpZFfIVrjas5Q");
				
			}
			
			HttpEntity<Map<String, Object>> entitiry = null;
			
			if (!HttpMethod.DELETE.equals(method)) {
				entitiry = new HttpEntity<Map<String, Object>>(reqVO, header);
			} else {
				entitiry = new HttpEntity<Map<String, Object>>(null, header);
				/*entitiry = new HttpEntity<Map<String, Object>>(reqVO, header);*/
			}
			res = restTemplate.exchange(interfaceUrl, method, entitiry, byte[].class);
			
			if( res == null || res.getStatusCode() != HttpStatus.OK || res.getBody() == null){
				throw new RuntimeException("Master Api Call Exception is null");
			}
			
		}
		
		resultData = new String(res.getBody());
		
		return resultData;
	}

	/**
	 * <PRE>
	 *  MethodName : exchangeMasterApi
	 * </PRE>
	 * @author : jun
	 * @date   : 2015. 6. 8. 오후 8:22:43
	 * @param  :
	 * @return : S
	 * @brief  :
	 * @param method
	 * @param interfaceId
	 * @param reqVO
	 * @param resType
	 * @return
	 */
	public String exchangeMasterApiImage(HttpMethod method ,String interfaceId, MultiValueMap<String, Object> reqVO, String apiType) {
 		String interfaceUrl = getExpandedUrlToMapImage(interfaceId, reqVO, apiType);
		if(interfaceUrl == null || "".equals(interfaceUrl)) {
			throw new RuntimeException("Interface Tager Url Is null.. check please");
		}
		ResponseEntity<String> res = null;
		
		RestTemplate restTemplate = getRestTemplate(MediaType.APPLICATION_JSON);
		HttpHeaders headers = getAuthorizationHeaders(com.anylogic.iot.base.remote.Constants._PREFIX_JWT_AFTER);

		HttpEntity<MultiValueMap<String, Object>> entitiry = new HttpEntity<MultiValueMap<String, Object>>(reqVO, headers);

		res = restTemplate.exchange(interfaceUrl, method, entitiry, String.class);

		if( res != null && res.getStatusCode() == HttpStatus.OK && res.getBody() != null){
		} else {
			throw new RuntimeException("Master Api Call Exception is null");
		}
		
		return res.getBody();
	}

	/**
	 * <PRE>
	 *  MethodName : exchangeMasterApi
	 *               개발 테브스 필요
	 * </PRE>
	 * @author : jun
	 * @date   : 2015. 6. 10. 오후 4:05:34
	 * @param  :
	 * @return : S
	 * @brief  :
	 * @param method
	 * @param interfaceId
	 * @param reqVO
	 * @param resType
	 * @return
	 */
	public <Q,S> S exchangeMasterApi(HttpMethod method ,String interfaceId, Q reqVO,  Class<S> resType, String apiType) {

		@SuppressWarnings("unchecked")
		Map<Object, Object> param = new BeanMap(reqVO);

 		String interfaceUrl = getExpandedUrl(interfaceId, param, apiType);

		if(interfaceUrl == null || "".equals(interfaceUrl)) {
			throw new RuntimeException("Interface Tager Url Is null.. check please");
		}
		ResponseEntity<S> res = null;

		// ACCESS TOKEN 없을시 요청  처리
		if("".equals(_ACCESSTION_TOKEN)){
			requestAuthServerKey();
		}  else {
			// Token 시간  만료 또는 오류시 재발급 처리
			if( !tokenVerfier.getTokenVerify(_ACCESSTION_TOKEN) ){
				logger.info("_ACCESSTION_TOKEN GET ");
				requestAuthServerKey();
			}
		}

		if(!"".equals(_ACCESSTION_TOKEN)) {

			RestTemplate restTemplate = getRestTemplate(MediaType.APPLICATION_JSON);
			HttpHeaders headers = getAuthorizationHeaders(com.anylogic.iot.base.remote.Constants._PREFIX_JWT_AFTER);

			HttpEntity<Q> entitiry = new HttpEntity<Q>(reqVO, headers);

			res = restTemplate.exchange(interfaceUrl, method, entitiry, resType);

			if( res != null && res.getStatusCode() == HttpStatus.OK && res.getBody() != null){
			} else {
				throw new RuntimeException("Master Api Call Exception is null");
			}

		}else {
			throw new RuntimeException("Auth Exception is null");
		}

		return res.getBody();
	}

	/**
	 * <PRE>
	 *  MethodName : requestAuthServerKey
	 *               ACCESS TOKEN 요청 처리
	 * </PRE>
	 * @author : jun
	 * @date   : 2015. 6. 5. 오후 2:53:09
	 * @param  :
	 * @return : String
	 * @brief  :
	 * @return
	 */
	private void requestAuthServerKey(){
		RestTemplate restTemplate = getRestTemplate(MediaType.APPLICATION_JSON);

		org.springframework.util.MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		List<String> authParam = new ArrayList<String>();
		authParam.add(com.anylogic.iot.base.remote.Constants.AUTH_GRANT_TYPE_MSG);
		body.put(com.anylogic.iot.base.remote.Constants.AUTH_GRANT_TYPE, authParam);

		HttpHeaders headers = getAuthorizationHeaders(com.anylogic.iot.base.remote.Constants._PREFIX_JWT_BEFORE);

        HttpEntity<org.springframework.util.MultiValueMap<String, String>> request = new HttpEntity<org.springframework.util.MultiValueMap<String, String>>(body, headers);
//        https://

        URL url = null;
		try {
			url = new URL("https", httpProperties.getAuthUrl(), "");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		String authUrl =  url.toString().replace("[", "").replace("]", "");

		sslCert();

		Map resVO = restTemplate.postForObject(authUrl, request, Map.class);

		if(resVO == null) {
			_ACCESSTION_TOKEN = "";

		}else {

			if( resVO.get(com.anylogic.iot.base.remote.Constants.AUTH_ACCESSTOKEN) == null )
				_ACCESSTION_TOKEN = "";
			else
				_ACCESSTION_TOKEN = (String)resVO.get(com.anylogic.iot.base.remote.Constants.AUTH_ACCESSTOKEN);
		}
		if("".equals(_ACCESSTION_TOKEN)){
			throw new RuntimeException("Auth Server Call Exception Access token is null");
		}
	}

	public static void sslCert() {
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
               public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                      return null;
               }

               public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
               }

               public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
               }
        } };

        try {
               SSLContext sc = SSLContext.getInstance("SSL");
               sc.init(null, trustAllCerts, new java.security.SecureRandom());
               HttpsURLConnection
                            .setDefaultSSLSocketFactory(sc.getSocketFactory());
               HttpsURLConnection
                            .setDefaultHostnameVerifier(new HostnameVerifier() {
                                   public boolean verify(String paramString, SSLSession paramSSLSession) {
                                         return true;
                                   }
                            });
        } catch (Exception e) {
               e.printStackTrace();
        }
     }

	/**
	 * <PRE>
	 *  MethodName : getExpandedUrlToMap
	 * </PRE>
	 * @author : jun
	 * @date   : 2015. 6. 9. 오후 3:15:59
	 * @param  :
	 * @return : String
	 * @brief  :
	 * @param interfaceId
	 * @param param
	 * @return
	 */
	private  String getExpandedUrlToMap(String interfaceId, Map<String, Object> param, String apiType) {
		String url = getUrl(interfaceId, apiType);

		URI expanded = new UriTemplate(url).expand(param);

		try {
			url = URLDecoder.decode(expanded.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return url;
	}

	/**
	 * <PRE>
	 *  MethodName : getExpandedUrlToMapImage
	 * </PRE>
	 * @author : jun
	 * @date   : 2015. 6. 9. 오후 3:15:59
	 * @param  :
	 * @return : String
	 * @brief  :
	 * @param interfaceId
	 * @param param
	 * @return
	 */
	private  String getExpandedUrlToMapImage(String interfaceId, MultiValueMap<String, Object> param, String apiType) {
		String url = getUrl(interfaceId, apiType);

		URI expanded = new UriTemplate(url).expand(param);

		try {
			url = URLDecoder.decode(expanded.toString(), "UTF-8").replace("[", "").replace("]", "");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return url;
	}

	/**
	 * <PRE>
	 *  MethodName : getAuthorizationHeaderStr
	 *               인증을 위한 HEAD Authorization STR
	 * </PRE>
	 * @author : jun
	 * @date   : 2015. 6. 5. 오후 2:22:09
	 * @param  :
	 * @return : String
	 * @brief  :
	 * @param type
	 * @return
	 */
	private HttpHeaders getAuthorizationHeaders(String type) {

		String authorizationStr = "";

		//Access Token 신규 요청 시
		if(com.anylogic.iot.base.remote.Constants._PREFIX_JWT_BEFORE.equals(type)) {
			//인증키 생성 처리
			authorizationStr = com.anylogic.iot.base.remote.Constants._PREFIX_JWT_BEFORE +  new String(Base64.encodeBase64((httpProperties.getClientId() + ":" +
					httpProperties.getClientSecurity()).getBytes()));
		}else if(com.anylogic.iot.base.remote.Constants._PREFIX_JWT_AFTER.equals(type)) {
			authorizationStr = com.anylogic.iot.base.remote.Constants._PREFIX_JWT_AFTER + _ACCESSTION_TOKEN;
		}else {
			throw new RuntimeException("not define header type");
		}
		HttpHeaders header = new HttpHeaders();
		header.add(com.anylogic.iot.base.remote.Constants.AUTH_AUTHORIZATION, authorizationStr);
		return header;
	}
	/**
	 * <PRE>
	 *  MethodName : get
	 *               GET Method 처리를 위한 Function
	 * </PRE>
	 * @author : jun
	 * @date   : 2015. 6. 4. 오후 6:47:16
	 * @param  :
	 * @return : S
	 * @brief  :
	 * @param interfaceId
	 * @param reqVO
	 * @param resType
	 * @return
	 */
	public <Q, S> S get(String interfaceId, Q reqVO, Class<S> resType, String apiType) {

		@SuppressWarnings("unchecked")
		Map<Object, Object> param = new BeanMap(reqVO);

		String url = getExpandedUrl(interfaceId, param, apiType);
		RestTemplate restTemplate = getRestTemplate(MediaType.APPLICATION_JSON);

		S resVO = restTemplate.getForObject(url, resType);

		return resVO;
	}

	/**
	 * <PRE>
	 *  MethodName : post
	 *             : POST METHOD 처리를 위한 Function
	 * </PRE>
	 * @author : jun
	 * @date   : 2015. 6. 4. 오후 6:47:39
	 * @param  :
	 * @return : S
	 * @brief  :
	 * @param interfaceId
	 * @param reqVO
	 * @param resType
	 * @return
	 */
	public <Q, S> S post(String interfaceId, Q reqVO, Class<S> resType, String apiType) {

		@SuppressWarnings("unchecked")
		Map<Object, Object> param = new BeanMap(reqVO);
		String url = getExpandedUrl(interfaceId, param, apiType);

		RestTemplate restTemplate = getRestTemplate(MediaType.APPLICATION_JSON);

		S resVO = restTemplate.postForObject(url, reqVO, resType);

		return resVO;
	}

	/**
	 * <PRE>
	 *  MethodName : put
	 *             : PUT MeTHOD 처리를 위한 Function
	 * </PRE>
	 * @author : jun
	 * @date   : 2015. 6. 4. 오후 6:48:02
	 * @param  :
	 * @return : S
	 * @brief  :
	 * @param interfaceId
	 * @param reqVO
	 * @param resType
	 * @return
	 */
	public <Q, S> S put(String interfaceId, Q reqVO, Class<S> resType, String apiType) {

		@SuppressWarnings("unchecked")
		Map<Object, Object> param = new BeanMap(reqVO);

		String url = getExpandedUrl(interfaceId, param, apiType);

		RestTemplate restTemplate = getRestTemplate(MediaType.APPLICATION_JSON);

		HttpEntity<Q> entity = new HttpEntity<Q>(reqVO);

		return restTemplate.exchange(url, HttpMethod.PUT, entity, resType).getBody();
	}

	/** delete 메소드 */
	/**
	 * <PRE>
	 *  MethodName : delete
	 *             : DELETE 처리를 위한 Function
	 * </PRE>
	 * @author : jun
	 * @date   : 2015. 6. 4. 오후 6:48:30
	 * @param  :
	 * @return : S
	 * @brief  :
	 * @param interfaceId
	 * @param reqVO
	 * @param resType
	 * @return
	 */
//	public static <Q, S> S delete(String interfaceId, Q reqVO, Class<S> resType) {
//
//		@SuppressWarnings("unchecked")
//		Map<Object, Object> param = new BeanMap(reqVO);
//
//		String url = getExpandedUrl(interfaceId, param);
//		RestTemplate restTemplate = getRestTemplate(MediaType.APPLICATION_JSON);
//
//		// DELETE 오류 처리
//		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory() {
//	        @Override
//	        protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
//	            if (HttpMethod.DELETE == httpMethod) {
//	                return new HttpEntityEnclosingDeleteRequest(uri);
//	            }
//	            return super.createHttpUriRequest(httpMethod, uri);
//	        }
//	    });
//
//		HttpEntity<Q> entity = new HttpEntity<Q>(reqVO);
//		return restTemplate.exchange(url, HttpMethod.DELETE, entity, resType).getBody();
//	}
	public <Q, S> S delete(String interfaceId, Q reqVO, Class<S> resType, String apiType) {

		Map<Object, Object> param = new BeanMap(reqVO);

		String url = getExpandedUrl(interfaceId, param, apiType);

		RestTemplate restTemplate = getRestTemplate(MediaType.APPLICATION_JSON);

		HttpEntity<Q> entity = new HttpEntity<Q>(reqVO);

		return restTemplate.exchange(url, HttpMethod.DELETE, entity, resType).getBody();
	}

	/**
	 * <PRE>
	 *  MethodName : getExpandedUrl
	 * </PRE>
	 * @author : jun
	 * @date   : 2015. 6. 5. 오후 3:01:08
	 * @param  :
	 * @return : String
	 * @brief  :
	 * @param interfaceId
	 * @param param
	 * @return
	 */
	private  String getExpandedUrl(String interfaceId, Map<Object, Object> param, String apiType) {
		String url = getUrl(interfaceId, apiType);

		URI expanded = new UriTemplate(url).expand(param);

		try {
			url = URLDecoder.decode(expanded.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return url;
	}


	/**
	 * <PRE>
	 *  MethodName : getUrl
	 * </PRE>
	 * @author : jun
	 * @date   : 2015. 6. 5. 오후 3:01:13
	 * @param  :
	 * @return : String
	 * @brief  :
	 * @param interfaceId
	 * @return
	 */
	public String getUrl(String interfaceId, String apiType) {

		String reqUrl = "";
		//String protocol = "";
		
		if(apiType.equals("master")){
			reqUrl = masterUrl + masterContextPath;
			//protocol = masterProtocol;
		}else if(apiType.equals("core")){
			reqUrl = coreUrl + coreContextPath;
			//protocol = coreProtocol;
		}else if(apiType.equals("ec")){
			reqUrl = ecUrl + ecContextPath;
			//protocol = ecProtocol;
		}else if(apiType.equals("home")){
			reqUrl = homeIotUrl+ homeContextPath;
			//protocol = ecProtocol;
		}else if(apiType.equals("portal")){
			reqUrl = portalUrl+ portalContextPath;
		}
		else if(apiType.equals("whether")){
			reqUrl = whetherUrl+ whetherContextPath;
		}
		else if(apiType.equals("external")){
			reqUrl = externalUrl+ externalContextPath;
		}else if(apiType.equals("push")){
			reqUrl = pushUrl+ pushContextPath;
		}
		
		String interfacePath = com.anylogic.iot.base.util.PropUtil.getInstance().getValues("properties/interface.properties", interfaceId);
		String addr = reqUrl +  interfacePath;
		
		URL url = null;
		try {
			//url = new URL(protocol, addr, "");
			url = new URL(addr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return url.toString().replace("[", "").replace("]", "");
	}

 	private RestTemplate getRestTemplate(MediaType mediaType) {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(mediaType);

//		// DELETE METHOD 인 경우
//		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory() {
//	        @Override
//	        protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
//
//	    		if (HttpMethod.DELETE == httpMethod) {
//	                return new HttpEntityEnclosingDeleteRequest(uri);
//	            }
//	            return super.createHttpUriRequest(httpMethod, uri);
//	        }
//	    });

		return restTemplate;
	}

	protected <T> HttpEntity<T> getRequestEntity(T body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<T>(body, headers);
	}

	/**
	 * <PRE>
	 *  ClassName : HttpEntityEnclosingDeleteRequest HttpApi
	 * </PRE>
	 * @version : 1.0
	 * @date    : 2015. 3. 16. 오후 8:30:42
	 * @author  : jun
	 * @brief   :
	 */
	public class HttpEntityEnclosingDeleteRequest extends HttpEntityEnclosingRequestBase {

	    public HttpEntityEnclosingDeleteRequest(final URI uri) {
	        super();
	        setURI(uri);
	    }

	    @Override
	    public String getMethod() {
	        return "DELETE";
	    }
	}
}
