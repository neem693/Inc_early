
package com.anylogic.iot.api.admin.calc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anylogic.iot.api.admin.calc.mapper.CalcMapper;
import com.anylogic.iot.api.admin.calc.vo.VendingUseVO;

/**
 * ClassName : VndMachUseStatService
 * 
 * @version : 1.0
 * @date    : 2017. 12. 28.
 * @author  : kpbaek 
 * @brief   :
 */

@Service
public class VndMachUseStatService {

	@Autowired
	private CalcMapper calcMapper;

	public List<Object> getVndMachUseStatusList(Map<String, Object> parameter){
		 return calcMapper.getVndMachUseStatusList(parameter);
	 }
	
	public List<VendingUseVO> getVndMachUseStatusListExcel(Map<String, Object> parameter){
		 return calcMapper.getVndMachUseStatusListExcel(parameter);
	 }
}
