
package com.anylogic.iot.api.admin.tenant.mapper;

import java.util.List;
import java.util.Map;

import com.anylogic.iot.api.admin.tenant.vo.CompanyInfoVO;
import com.anylogic.iot.api.admin.tenant.vo.guestListVO;

public interface TenantMapper { 
	
	public int deleteContractInfo(Map<String, Object> parameter);
	
	public int insertContractInfo(Map<String, Object> parameter);
	public int updateContractInfo(Map<String, Object> parameter);
	
	public int addTime(Map<String, Object> parameter);

	public List<Object> getContract(Map<String, Object> parameter);
	
	public List<Object> getGuestList(Map<String, Object> parameter);
	public List<guestListVO> getGuestListExcel(Map<String, Object> parameter);
	
	public List<Object> getOfficeRentUseInfo(Map<String, Object> parameter);
	public int resetOffice(Map<String, Object> parameter);
	public int deleteMember(Map<String, Object> parameter);
	public int resetOfficeStatus(Map<String, Object> parameter);
	public int resetOfficeRentUse(Map<String, Object> parameter);
	public int resetMngInfo(Map<String, Object> parameter);
	public int updateSetMngInfo(Map<String, Object> parameter);
	public int updateOfficeStatus(Map<String, Object> parameter);
	public int deleteCompany(Map<String, Object> parameter);
	public int reinsertCompanyFile(Map<String, Object> parameter);
	public int resetCompantFile(Map<String, Object> parameter);
	public List<Object> getCompanyFile(Map<String, Object> parameter);
	public List<Object> getCompanyList(Map<String, Object> parameter);
	public List<Object> getCompanyMember(Map<String, Object> parameter);
	public List<Object> getOfficeInfo(Map<String, Object> parameter);
	public List<CompanyInfoVO> getCompanyListExcel(Map<String, Object> parameter);
	public int updateCompanyInfo(Map<String, Object> parameter);
	public void insertFileData(Map<String, Object> parameter);	
	public List<Object> dupCheckId(Map<String, Object> parameter);
	public int insertMngInfo(Map<String, Object> parameter);
	public int insertCompanyInfo(Map<String, Object> parameter);
	public int insertOfficeRentUse(Map<String, Object> parameter);
	public int updateMngInfo(Map<String, Object> parameter);
	
}



