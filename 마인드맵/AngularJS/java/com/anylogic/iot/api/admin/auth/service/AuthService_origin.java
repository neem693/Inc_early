
package com.anylogic.iot.api.admin.auth.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anylogic.iot.api.admin.auth.mapper.AuthMapper_origin;
import com.anylogic.iot.base.excel.service.ExcelSVC;

/**
 * <PRE>
 *  ClassName : AdminService
 * </PRE>
 * @version : 1.0
 * @date    : 2015. 5. 21. 오전 9:56:36
 * @author  : jkkim
 * @brief   :
 */

@Service
public class AuthService_origin { 

	@Autowired
	private AuthMapper_origin authMapper;

	@Autowired
	private ExcelSVC excelSVC;

	
	public List<Object> getMemberList(Map<String, Object> parameter){
		return authMapper.getMemberList(parameter);
	}
	
	public List<Object> getMainViewInfo(Map<String, Object> parameter){
		return authMapper.getMainViewInfo(parameter);
	}
	
	 public List<Object> userLogin(Map<String, Object> parameter){
			return authMapper.userLogin(parameter);
		}
	 public List<Object> findId(Map<String, Object> parameter){
			return authMapper.findId(parameter);
		}
	 public int findPw(Map<String, Object> parameter){
			return authMapper.findPw(parameter);
		}

	public List<Object> getUserInfo(Map<String, Object> parameter) {
		return authMapper.getUserInfo(parameter);
	}


}
