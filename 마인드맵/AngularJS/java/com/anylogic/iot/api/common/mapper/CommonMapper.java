package com.anylogic.iot.api.common.mapper;

import java.util.List;
import java.util.Map;

import com.anylogic.iot.api.common.vo.ResultSvcListVO;

/**
 * <PRE>
 *  ClassName : CommonMapper
 * </PRE>
 * @version : 1.0
 * @date    : 2015. 5. 21. 오후 2:25:02
 * @author  : jkkim
 * @brief   :
 */

public interface CommonMapper {


	/**
	 *
	 * <PRE>
	 *  MethodName : listDtlCd
	 * </PRE>
	 * @author : jkkim
	 * @date   : 2015. 5. 21. 오후 2:26:53
	 * @param  :
	 * @return : List<Object>
	 * @brief  : 상세 코드 조회
	 * @param parameter
	 * @return
	 */
	public List<Object> listDtlCd(Map<String, Object> parameter);

	public List<ResultSvcListVO> listSvc(Map<String, Object> parameter);
	
	

	public void updateDevGPS(Map<String, Object> parameter);
	public void updateSvcTgtGPS(Map<String, Object> parameter);
	
	public List<Map<String, Object>> selectNullAdrFoodIms();
	public void updateFoodImsDevAdr(Map<String, Object> parameter);
	
	public void efctDevBatch();
	public void evetDelBatch();
	
	public void insertHomeCamMemberJoinBatch(Map<String, Object> parameter);
	public void insertHomeCamMemberCancelBatch(Map<String, Object> parameter);
	public void insertHomeCamDevCtrlBatch(Map<String, Object> parameter);
}
