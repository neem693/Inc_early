package com.anylogic.iot.api.common.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anylogic.iot.Version;
import com.anylogic.iot.api.common.service.CommonService;
import com.anylogic.iot.api.common.vo.ResultSvcContextVO;
import com.anylogic.iot.api.util.StringParsingUtil;
import com.anylogic.iot.base.common.InterfacJson;
import com.anylogic.iot.base.common.ResultListVO;
import com.anylogic.iot.base.mvc.message.Messages;
import com.anylogic.iot.base.remote.HttpApi;

/**
 * <PRE>
 *  ClassName : CommonController
 * </PRE>
 * @version : 1.0
 * @date    : 2015. 5. 21. 오후 2:24:46
 * @author  : jkkim
 * @brief   :
 */

@RestController
@RequestMapping("/" + Version.V1+"/common")
public class CommonController {

	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	private CommonService commonService;

	@Autowired
	private HttpApi httpApi;
	
	@Value("${master.url}")
	String masterUrl;

	/**
	 *
	 * <PRE>
	 *  MethodName : listDtlCd
	 * </PRE>
	 * @author : jkkim
	 * @date   : 2015. 5. 21. 오후 2:36:04
	 * @param  :
	 * @return : ResultListVO
	 * @brief  : 상세 코드 조회
	 * @param parameter
	 * @param messages
	 * @return
	 */
	@RequestMapping(value = "/listDtlCd", method = RequestMethod.GET)
	public ResultListVO listDtlCd(@RequestParam Map<String, Object> parameter, Messages messages) {
		logger.debug("CommonController.listDtlCd");
		ResultListVO resultListVO = new ResultListVO();
    	resultListVO.setRows(commonService.listDtlCd(parameter));
    	messages.addMessage("OK", "");
		return resultListVO;
	}

	// 상세 코드 조회 use Master API
	@RequestMapping(value = "/listDtlCd_M", method = RequestMethod.GET)
	public InterfacJson listDtlCd_M(@RequestParam Map<String, Object> parameter, Messages messages) {
		String res = httpApi.exchangeMasterApi(HttpMethod.GET, com.anylogic.iot.base.remote.Constants.API_GET_CODES, parameter, "master");
		InterfacJson resCls = new InterfacJson();
		resCls.setResult(res);
		return resCls;
	}
	
	// 상세 코드 조회 use Master API
	@RequestMapping(value = "/listDtlCd_Event", method = RequestMethod.GET)
	public InterfacJson listDtlCd_Event(@RequestParam Map<String, Object> parameter, Messages messages) {
		String res = httpApi.exchangeMasterApi(HttpMethod.GET, com.anylogic.iot.base.remote.Constants.API_GET_CODES2, parameter, "master");
		InterfacJson resCls = new InterfacJson();
		resCls.setResult(res);
		return resCls;
	}
		

	@RequestMapping(value = "/listSvcContext", method = RequestMethod.GET)
	public InterfacJson listSvcContext(@RequestParam Map<String, Object> parameter, Messages messages) {
		ResultSvcContextVO resultSvcContextVO = commonService.listSvcContext(parameter);
		String res = StringParsingUtil.voToJsonStr(resultSvcContextVO);
		InterfacJson resCls = new InterfacJson();
		resCls.setResult(res);
		return resCls;
	}
	
	// MasterAPI url 조회
	@RequestMapping(value = "/getMasterApiUrl", method = RequestMethod.GET)
	public String getMasterApiUrl(@RequestParam Map<String, Object> parameter, Messages messages) {
		messages.addMessage("OK", "");
		return masterUrl;
	}

}
