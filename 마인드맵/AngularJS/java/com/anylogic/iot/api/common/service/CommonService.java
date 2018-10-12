package com.anylogic.iot.api.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.anylogic.iot.api.common.mapper.CommonMapper;
import com.anylogic.iot.api.common.vo.ResultSvcContextVO;
import com.anylogic.iot.api.common.vo.ResultSvcListVO;
import com.anylogic.iot.base.remote.HttpApi;

/**
 * <PRE>
 *  ClassName : CommonService
 * </PRE>
 * 
 * @version : 1.0
 * @date : 2015. 5. 21. 오후 2:27:41
 * @author : jkkim
 * @brief :
 */
@Service
public class CommonService {

	@Autowired
	private CommonMapper commonMapper;

	@Autowired
	private HttpApi httpApi;

	/**
	 *
	 * <PRE>
	 *  MethodName : listDtlCd
	 * </PRE>
	 * 
	 * @author : jkkim
	 * @date : 2015. 5. 21. 오후 2:26:53
	 * @param :
	 * @return : List<Object>
	 * @brief : 상세 코드 조회
	 * @param parameter
	 * @return
	 */
	public List<Object> listDtlCd(Map<String, Object> parameter) {
		return commonMapper.listDtlCd(parameter);
	}

	public ResultSvcContextVO listSvcContext(Map<String, Object> parameter) {
		ResultSvcContextVO resultSvcContextVO = new ResultSvcContextVO();
		resultSvcContextVO.makeContext();
		List<ResultSvcListVO> svcList = commonMapper.listSvc(parameter);
		for (ResultSvcListVO vo : svcList)
			resultSvcContextVO.addItem(vo);
		return resultSvcContextVO;
	}

	public void efctDevBatch() {
		commonMapper.efctDevBatch();

	}

	public void evetDelBatch() {
		commonMapper.evetDelBatch();

	}

	public void sleepTime(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
		}
	}

	

}
