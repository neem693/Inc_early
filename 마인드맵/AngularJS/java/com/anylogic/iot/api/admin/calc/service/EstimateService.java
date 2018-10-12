
package com.anylogic.iot.api.admin.calc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anylogic.iot.api.admin.calc.mapper.CalcMapper;
import com.anylogic.iot.api.admin.calc.vo.EstimateInfoVO;
import com.anylogic.iot.api.admin.calc.vo.MeetinRoomUseStatusVO;

/**
 * ClassName : FcltUseStatService
 * 
 * @version : 1.0
 * @date : 2017. 12. 28.
 * @author : kpbaek
 * @brief :
 */

@Service
public class EstimateService {

	@Autowired
	private CalcMapper calcMapper;

public List<Object> getEstimateBookingInfo(Map<String, Object> parameter) {
	return calcMapper.getEstimateBookingInfo(parameter);
}

public List<Object> getIncidental(Map<String, Object> parameter) {
	return calcMapper.getIncidental(parameter);
}

	public List<Object> getOfficeRentInfo2(Map<String, Object> parameter) {
		return calcMapper.getOfficeRentInfo2(parameter);
	}

	public int setIncidentel(Map<String, Object> parameter) {
		return calcMapper.setIncidentel(parameter);
	}
	
	public int resetIncidentalList(Map<String, Object> parameter) {
		return calcMapper.resetIncidentalList(parameter);
	}
	public int updateEstimate(Map<String, Object> parameter) {
		return calcMapper.updateEstimate(parameter);
	}
	public int setEstimate(Map<String, Object> parameter) {
		return calcMapper.setEstimate(parameter);
	}

	public List<Object> getBookingInfo(Map<String, Object> parameter) {
		return calcMapper.getBookingInfo(parameter);
	}

	public String getConferenceId(Map<String, Object> parameter) {
		return calcMapper.getConferenceId(parameter);
	}

	public List<Object> getConferenceInfo(Map<String, Object> parameter) {
		return calcMapper.getConferenceInfo(parameter);
	}

	public List<Object> getOfficeRentInfo(Map<String, Object> parameter) {
		return calcMapper.getOfficeRentInfo(parameter);
	}

	public List<Object> setBranch(Map<String, Object> parameter) {
		return calcMapper.setBranch(parameter);
	}

	public List<Object> getEstimateList_simple(Map<String, Object> parameter) {
		return calcMapper.getEstimateList_simple(parameter);
	}

	public List<Object> getEstimateList(Map<String, Object> parameter) {
		return calcMapper.getEstimateList(parameter);
	}

	public List<EstimateInfoVO> getEstimateListExcel_simple(Map<String, Object> parameter) {
		return calcMapper.getEstimateListExcel_simple(parameter);
	}

	public List<EstimateInfoVO> getEstimateListExcel(Map<String, Object> parameter) {
		return calcMapper.getEstimateListExcel(parameter);
	}

}
