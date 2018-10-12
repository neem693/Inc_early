
package com.anylogic.iot.api.admin.calc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anylogic.iot.api.admin.calc.mapper.CalcMapper;
import com.anylogic.iot.api.admin.calc.vo.MulMachineUseVO;

/**
 * ClassName : MulMachUseStatService
 * 
 * @version : 1.0
 * @date    : 2017. 12. 28.
 * @author  : kpbaek 
 * @brief   :
 */

@Service
public class MulMachUseStatService {

	@Autowired
	private CalcMapper calcMapper;

	public List<Object> getMulMachineUseStatusList(Map<String, Object> parameter){
		return calcMapper.getMulMachineUseStatusList(parameter);
	}

	public List<MulMachineUseVO> getMulMachineUseStatusListExcel(Map<String, Object> parameter){
			return calcMapper.getMulMachineUseStatusListExcel(parameter);
	}
}
