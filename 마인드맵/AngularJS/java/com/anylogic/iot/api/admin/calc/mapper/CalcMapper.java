
package com.anylogic.iot.api.admin.calc.mapper;

import java.util.List;
import java.util.Map;

import com.anylogic.iot.api.admin.calc.vo.DailyCalMonthVO;
import com.anylogic.iot.api.admin.calc.vo.DailyCalYearVO;
import com.anylogic.iot.api.admin.calc.vo.EstimateInfoVO;
import com.anylogic.iot.api.admin.calc.vo.MeetinRoomUseStatusVO;
import com.anylogic.iot.api.admin.calc.vo.MonthlySumVO;
import com.anylogic.iot.api.admin.calc.vo.MulMachineUseVO;
import com.anylogic.iot.api.admin.calc.vo.OfficeUseStatusVO;
import com.anylogic.iot.api.admin.calc.vo.PGUseVO;
import com.anylogic.iot.api.admin.calc.vo.VendingUseVO;

/**
 * ClassName : CalcMapper
 * 
 * @version : 1.0
 * @date : 2017. 12. 28.
 * @author : kpbaek
 * @brief :
 */

public interface CalcMapper {

	public List<Object> getDailyCalYearList(Map<String, Object> parameter);

	public int saveDailyCalData(Map<String, Object> parameter);

	public int updateDailyCalData(Map<String, Object> parameter);

	public List<Object> getRoomTaxbillAuto(Map<String, Object> parameter);

	public List<Object> getMeetingTaxbillAuto(Map<String, Object> parameter);

	public List<Object> getDailyCalDayList(Map<String, Object> parameter);

	public List<Object> getDailyCalMonthList(Map<String, Object> parameter);

	public List<Object> getEstimateBookingInfo(Map<String, Object> parameter);

	public List<Object> getIncidental(Map<String, Object> parameter);

	public int setIncidentel(Map<String, Object> parameter);

	public int updateEstimate(Map<String, Object> parameter);

	public int resetIncidentalList(Map<String, Object> parameter);

	public int setEstimate(Map<String, Object> parameter);

	public List<Object> getBookingInfo(Map<String, Object> parameter);

	public String getConferenceId(Map<String, Object> parameter);

	public List<Object> getConferenceInfo(Map<String, Object> parameter);

	
	public int deletePayData(Map<String, Object> parameter);
	
	public Map<String, Object> getPgData(Map<String, Object> parameter);

	public List<Object> getPayList(Map<String, Object> parameter);
	
	public List<DailyCalYearVO> getDailyCalYearListExcel(Map<String, Object> parameter);
	public List<DailyCalMonthVO> getDailyCalDayListExcel(Map<String, Object> parameter);
	public List<DailyCalMonthVO> getDailyCalMonthListExcel(Map<String, Object> parameter);
	
	public List<PGUseVO> getPgListExcel(Map<String, Object> parameter);

	public List<Object> getPgList(Map<String, Object> parameter);

	public List<Object> getOfficeRentInfo2(Map<String, Object> parameter);

	public List<Object> getOfficeRentInfo(Map<String, Object> parameter);

	public List<Object> setBranch(Map<String, Object> parameter);

	public List<EstimateInfoVO> getEstimateListExcel_simple(Map<String, Object> parameter);

	public List<Object> getEstimateList_simple(Map<String, Object> parameter);

	public List<EstimateInfoVO> getEstimateListExcel(Map<String, Object> parameter);

	public List<Object> getEstimateList(Map<String, Object> parameter);

	public List<Object> showInfoTaxInvoice(Map<String, Object> parameter);

	public List<Object> getCalculTenantList(Map<String, Object> parameter);

	public Map<?, ?> getCalculTenantOriginView(Map<String, Object> parameter);

	public String getInvoinceIdForOrigin(Map<String, Object> parameter);

	public Map<?, ?> getCalculTenantView(Map<String, Object> parameter);

	public int invoiceWrite(Map<String, Object> parameter);

	public int updateInvoiceVat(Map<String, Object> parameter);

	public int updateInvoiceTotalAmount(Map<String, Object> parameter);

	public List<Object> userLogin(Map<String, Object> parameter);

	public Map<?, ?> getInvoiceSetVatDefault(Map<String, Object> parameter);

	public int insertInvoicevattemplate(Map<String, Object> parameter);

	public int updateInvoicevattemplate(Map<String, Object> parameter);

	public List<Object> getInvoiceList(Map<String, Object> parameter);

	public List<Object> getInvoiceListRental(Map<String, Object> parameter);

	public List<Object> getInvoiceLastList(Map<String, Object> parameter);

	public List<Object> getInvoiceLastListRental(Map<String, Object> parameter);

	public Map<?, ?> getInvoiceView(Map<String, Object> parameter);

	public Map<?, ?> getInvoiceIdSeq(Map<String, Object> parameter);

	public int insertInvoiceIssuStatus(Map<String, Object> parameter);

	public int insertInvoiceRelativeIssuStatus(Map<String, Object> parameter);

	public int saveTaxInvoice(Map<String, Object> parameter);

	public int saveDepositInvoice(Map<String, Object> parameter);

	public Map<?, ?> deleteInvoiceStatus(Map<String, Object> parameter);

	public List<Object> getMeetingRoomListForCalc(Map<String, Object> parameter);

	public List<Object> getMeetinRoomUseStatusList(Map<String, Object> parameter);

	public List<MeetinRoomUseStatusVO> getMeetinRoomUseStatusExcel(Map<String, Object> parameter);

	public List<Object> getMeetinRoomUseStatusCalendar(Map<String, Object> parameter);

	public List<Object> getFacilityList(Map<String, Object> parameter);

	public List<Object> getFacilityUseStatusList(Map<String, Object> parameter);

	public List<MeetinRoomUseStatusVO> getFacilityUseStatusExcel(Map<String, Object> parameter);

	public List<Object> getFacilityUseStatusCalendar(Map<String, Object> parameter);

	public List<Object> getMulMachineUseStatusList(Map<String, Object> parameter);

	public List<MulMachineUseVO> getMulMachineUseStatusListExcel(Map<String, Object> parameter);

	public List<Object> getVndMachUseStatusList(Map<String, Object> parameter);

	public List<VendingUseVO> getVndMachUseStatusListExcel(Map<String, Object> parameter);

	public List<Object> getReportMonthlyStatList(Map<String, Object> parameter);

	public List<MonthlySumVO> getReportMonthlyStatListExcel(Map<String, Object> parameter);

	public List<Object> getReportOfficeStatList(Map<String, Object> parameter);

	public List<OfficeUseStatusVO> getReportOfficeStatExcel(Map<String, Object> parameter);
}
