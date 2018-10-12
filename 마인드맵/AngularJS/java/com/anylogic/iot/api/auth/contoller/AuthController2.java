package com.anylogic.iot.api.auth.contoller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.anylogic.iot.Version;
import com.anylogic.iot.api.auth.service.AuthService2;
import com.anylogic.iot.api.util.HTTPUtil;
import com.anylogic.iot.base.common.InterfacJson;
import com.anylogic.iot.base.common.ResultListVO;
import com.anylogic.iot.base.mvc.message.Messages;

/**
 * <PRE>
 *  ClassName : AuthController
 * </PRE>
 * @version : 1.0
 * @date    : 2015. 6. 3. 오후 1:23:38
 * @author  : jkkim
 * @brief   :
 */

@RestController
@RequestMapping("/" + Version.V1+"/auth")
public class AuthController2 {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthController2.class);

	public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create(); 
	
	@Autowired
	private AuthService2 authService;


	/**
	 *
	 * <PRE>
	 *  MethodName : logout
	 * </PRE>
	 * @author : jkkim
	 * @date   : 2015. 2. 26. 오후 5:34:39
	 * @param  :
	 * @return : String
	 * @brief  : 로그아웃
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/logout")
	public ResultListVO logout(HttpServletRequest request, Messages messages){
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute("currentUser");
            session.invalidate();
        }
        SecurityContextHolder.clearContext();
        ResultListVO resultListVO = new ResultListVO();
   		messages.addMessage("OK", "");
   		return resultListVO;
	}

	
	@RequestMapping(value="/logout", method=RequestMethod.DELETE)
	public InterfacJson logout(@RequestParam Map<String, Object> parameter, Messages messages){
		
		
		String gcmId = (String) parameter.get("gcmId");
		String packet = gson.toJson(gcmId);
		
		System.out.println("logout gcmId" + gcmId);
		
		String ip = com.anylogic.iot.base.util.PropUtil.getInstance().getValues("properties/application.properties", "push.url") + com.anylogic.iot.base.util.PropUtil.getInstance().getValues("properties/application.properties", "push.contextPath");
		System.out.println("logout gcmId ip" + ip);
		String url = ip + com.anylogic.iot.base.util.PropUtil.getInstance().getValues("properties/interface.properties", "api.push.delete.gcmId")+gcmId;
		System.out.println("logout gcmId url" + url);
		
		String response = null;
		try {
			response = HTTPUtil.doAction(url,"DELETE",packet,3000);
			System.out.println("logout : " + response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		String res = httpApi.exchangeMasterApi(HttpMethod.PUT, com.anylogic.iot.base.remote.Constants.API_PUT_MASTER_DEVICE_CTRL, parameter,"master");
		
		InterfacJson resCls = new InterfacJson();
		resCls.setResult(response);
		
		
/*		if(resCls.toString().indexOf("200") != -1) {
			appService.setStatus(parameter);
		}*/
		
		return resCls;
	}
	

	/**
	 *
	 * <PRE>
	 *  MethodName : accessDenied
	 * </PRE>
	 * @author : jkkim
	 * @date   : 2015. 6. 12. 오후 5:05:49
	 * @param  :
	 * @return : ResultListVO
	 * @brief  : 접근 권한 없는 경우
	 * @param request
	 * @param messages
	 * @return
	 */
	@RequestMapping("/accessDenied")
	public ResultListVO accessDenied(HttpServletRequest request, Messages messages){
        ResultListVO resultListVO = new ResultListVO();
   		messages.addMessage("403", "accessDenied");
   		return resultListVO;
	}

	/**
     * 아이디 조회
     * @param parameter (userNm, email)
     * @param messages
     * @return userId
     */
    @RequestMapping(value="/selectUserId", method=POST)
	public String selectUserId(@RequestBody Map<String, Object> parameter, Messages messages) {
    	String id = authService.selectUserId(parameter);
    	messages.addMessage("OK", "");
    	return id;
	}
    
    @RequestMapping(value="/getEventRuletype", method = RequestMethod.GET)
   	public String getEventRuletype(@RequestParam Map<String, Object> parameter, Messages messages) {
       	//String id = authService.selectUserId(parameter);
       	String id = com.anylogic.iot.base.util.PropUtil.getInstance().getValues("properties/interface.properties", "EventRuletype");
       	messages.addMessage("OK", "");
       	return id;
   	}

    /**
     * <PRE>
     *  MethodName : idEmailCheck
     * </PRE>
     * @author : moo
     * @date   : 2015. 7. 22. 오전 11:15:47
     * @param  :
     * @return : String
     * @brief  : 비밀번호찾기(id email 확인)
     * @param parameter
     * @param messages
     * @return
     */
    @RequestMapping(value="/idEmailCheck", method=POST)
	public void idEmailCheck(@RequestBody Map<String, Object> parameter, Messages messages) {
    	int ch = authService.idEmailCheck(parameter);
    	if(ch>0){
    		messages.addMessage("OK", "");
    	}
    	else{
    		messages.addMessage("NG", "");
    	}
	}


    /**
     * <PRE>
     *  MethodName : updatePwd
     * </PRE>
     * @author : moo
     * @date   : 2015. 7. 22. 오후 2:12:57
     * @param  :
     * @return : void
     * @brief  : 비밀번호 초기화
     * @param parameter
     * @param request
     * @param messages
     */
    @RequestMapping(value="/updatePwd", method=PUT)
	public void updatePwd(@RequestBody Map<String, Object> parameter, Messages messages) {
		boolean flag = authService.updatePwd(parameter);
		if (flag)
			messages.addMessage("OK", "");
		else
			messages.addMessage("NG", "");
	}

}








