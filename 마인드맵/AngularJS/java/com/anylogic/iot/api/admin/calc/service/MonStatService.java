
package com.anylogic.iot.api.admin.calc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anylogic.iot.api.admin.calc.mapper.CalcMapper;
import com.anylogic.iot.api.admin.calc.vo.DailyCalMonthVO;
import com.anylogic.iot.api.admin.calc.vo.DailyCalYearVO;
import com.anylogic.iot.api.admin.calc.vo.MonthlySumVO;
import com.anylogic.iot.api.admin.calc.vo.OfficeUseStatusVO;
import com.anylogic.iot.api.admin.calc.vo.PGUseVO;

/**
 * ClassName : MonStatService
 * 
 * @version : 1.0
 * @date : 2017. 12. 28.
 * @author : kpbaek
 * @brief :
 */

@Service
public class MonStatService {

	@Autowired
	private CalcMapper calcMapper;

	public List<Object> getDailyCalYearList(Map<String, Object> parameter) {
		return calcMapper.getDailyCalYearList(parameter);
	}
	
	public int updateDailyCalData(Map<String, Object> parameter) {
		return calcMapper.updateDailyCalData(parameter);
	}

	public List<Object> getRoomTaxbillAuto(Map<String, Object> parameter) {
		return calcMapper.getRoomTaxbillAuto(parameter);
	}

	public List<Object> getMeetingTaxbillAuto(Map<String, Object> parameter) {
		return calcMapper.getMeetingTaxbillAuto(parameter);
	}

	public int saveDailyCalData(Map<String, Object> parameter) {
		return calcMapper.saveDailyCalData(parameter);
	}

	public List<Object> getDailyCalDayList(Map<String, Object> parameter) {
		return calcMapper.getDailyCalDayList(parameter);
	}

	public List<Object> getDailyCalMonthList(Map<String, Object> parameter) {
		return calcMapper.getDailyCalMonthList(parameter);
	}

	public Map<String, Object> getPgData(Map<String, Object> parameter) {
		return calcMapper.getPgData(parameter);
	}

	public int deletePayData(Map<String, Object> parameter) {
		return calcMapper.deletePayData(parameter);
	}
	
	public List<Object> getPayList(Map<String, Object> parameter) {
		return calcMapper.getPayList(parameter);
	}
	
	
	public List<DailyCalYearVO> getDailyCalYearListExcel(Map<String, Object> parameter) {
		return calcMapper.getDailyCalYearListExcel(parameter);
	}
	
	public List<DailyCalMonthVO> getDailyCalDayListExcel(Map<String, Object> parameter) {
		return calcMapper.getDailyCalDayListExcel(parameter);
	}
	
	public List<DailyCalMonthVO> getDailyCalMonthListExcel(Map<String, Object> parameter) {
		return calcMapper.getDailyCalMonthListExcel(parameter);
	}

	public List<PGUseVO> getPgListExcel(Map<String, Object> parameter) {
		return calcMapper.getPgListExcel(parameter);
	}

	public List<Object> getPgList(Map<String, Object> parameter) {
		return calcMapper.getPgList(parameter);
	}

	public List<Object> getReportMonthlyStatList(Map<String, Object> parameter) {
		return calcMapper.getReportMonthlyStatList(parameter);
	}

	public List<Object> getReportOfficeStatList(Map<String, Object> parameter) {
		return calcMapper.getReportOfficeStatList(parameter);
	}

	public List<MonthlySumVO> getReportMonthlyStatListExcel(Map<String, Object> parameter) {
		return calcMapper.getReportMonthlyStatListExcel(parameter);
	}

	public List<OfficeUseStatusVO> getReportOfficeStatExcel(Map<String, Object> parameter) {
		return calcMapper.getReportOfficeStatExcel(parameter);
	}

}
