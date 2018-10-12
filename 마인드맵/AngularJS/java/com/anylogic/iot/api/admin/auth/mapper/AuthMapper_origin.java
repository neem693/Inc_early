
package com.anylogic.iot.api.admin.auth.mapper;

import java.util.List;
import java.util.Map;

/**
 * <PRE>
 *  ClassName : AdminMapper
 * </PRE>
 * @version : 1.0
 * @date    : 2015. 5. 21. 오전 9:37:56
 * @author  : jkkim
 * @brief   :
 */

public interface AuthMapper_origin {
	

	public List<Object> getMemberList(Map<String, Object> parameter);
	public List<Object> getMainViewInfo(Map<String, Object> parameter);
	public List<Object> findId(Map<String, Object> parameter);
	public List<Object> userLogin(Map<String, Object> parameter);
	public int findPw(Map<String, Object> parameter);
	public List<Object> getUserInfo(Map<String, Object> parameter);

}



