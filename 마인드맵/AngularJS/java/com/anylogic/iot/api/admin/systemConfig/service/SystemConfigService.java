
package com.anylogic.iot.api.admin.systemConfig.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anylogic.iot.api.admin.systemConfig.mapper.SystemConfigMapper;
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
public class SystemConfigService {

	@Autowired
	private SystemConfigMapper systemConfigMapper;

	@Autowired
	private ExcelSVC excelSVC;

	public int insertDevice(Map<String, Object> parameter){
		return systemConfigMapper.insertDevice(parameter);
	}
	
	 public List<Object> getDefaultList(Map<String, Object> parameter){
			return systemConfigMapper.getDefaultList(parameter);
		}
	
	public int deleteAccount(Map<String, Object> parameter){
		return systemConfigMapper.deleteAccount(parameter);
	}
	public int deleteEquipment(Map<String, Object> parameter){
		return systemConfigMapper.deleteEquipment(parameter);
	}
	public int deleteFacility(Map<String, Object> parameter){
		return systemConfigMapper.deleteFacility(parameter);
	}
	public int deleteGoods(Map<String, Object> parameter){
		return systemConfigMapper.deleteGoods(parameter);
	}
	public int deleteMeetingRoom(Map<String, Object> parameter){
		return systemConfigMapper.deleteMeetingRoom(parameter);
	}
	public int deleteOffice(Map<String, Object> parameter){
		return systemConfigMapper.deleteOffice(parameter);
	}
	
	
	public int insertAccount(Map<String, Object> parameter){
		return systemConfigMapper.insertAccount(parameter);
	}
	public int insertEquipment(Map<String, Object> parameter){
		return systemConfigMapper.insertEquipment(parameter);
	}
	public int insertFacility(Map<String, Object> parameter){
		return systemConfigMapper.insertFacility(parameter);
	}
	public int insertGoods(Map<String, Object> parameter){
		return systemConfigMapper.insertGoods(parameter);
	}
	public int insertMeetingRoom(Map<String, Object> parameter){
		return systemConfigMapper.insertMeetingRoom(parameter);
	}
	public int insertOffice(Map<String, Object> parameter){
		return systemConfigMapper.insertOffice(parameter);
	}
	
	
	
	
	public int updateAccount(Map<String, Object> parameter){
		return systemConfigMapper.updateAccount(parameter);
	}
	public int updateEquipment(Map<String, Object> parameter){
		return systemConfigMapper.updateEquipment(parameter);
	}
	public int updateFacility(Map<String, Object> parameter){
		return systemConfigMapper.updateFacility(parameter);
	}
	public int updateGoods(Map<String, Object> parameter){
		return systemConfigMapper.updateGoods(parameter);
	}
	public int updateMeetingRoom(Map<String, Object> parameter){
		return systemConfigMapper.updateMeetingRoom(parameter);
	}
	public int updateOffice(Map<String, Object> parameter){
		return systemConfigMapper.updateOffice(parameter);
	}
	
		
	public int insertUpdateOpTime(Map<String, Object> parameter){
		return systemConfigMapper.insertUpdateOpTime(parameter);
	}
	public int updateTimeFacility(Map<String, Object> parameter){
		return systemConfigMapper.updateTimeFacility(parameter);
	}
	public int updateTimeMeetingRoom(Map<String, Object> parameter){
		return systemConfigMapper.updateTimeMeetingRoom(parameter);
	}
	
	
	
	 public List<Object> idCheck(Map<String, Object> parameter){
			return systemConfigMapper.idCheck(parameter);
		}
	 
	 public List<Object> getBranchList(Map<String, Object> parameter){
			return systemConfigMapper.getBranchList(parameter);
		}
	 
	 public List<Object> getOpTimeList(Map<String, Object> parameter){
			return systemConfigMapper.getOpTimeList(parameter);
		}
	 
	 	 
	 
	 public List<Object> getAccountList(Map<String, Object> parameter){
			return systemConfigMapper.getAccountList(parameter);
	 }	 
	 public List<Object> getEquipmentList(Map<String, Object> parameter){
			return systemConfigMapper.getEquipmentList(parameter);
	 }	 
	 public List<Object> getFacilityList(Map<String, Object> parameter){
			return systemConfigMapper.getFacilityList(parameter);
	 }	 
	 public List<Object> getGoodsList(Map<String, Object> parameter){
			return systemConfigMapper.getGoodsList(parameter);
	 }	 
	 public List<Object> getMeetingRoomList(Map<String, Object> parameter){
			return systemConfigMapper.getMeetingRoomList(parameter);
	 }	 
	 public List<Object> getOfficeList(Map<String, Object> parameter){
			return systemConfigMapper.getOfficeList(parameter);
	 }	 

	 
	 
	 
//	 public int insertMeetingRoom(Map<String, Object> parameter){
//			return systemConfigMapper.insertMeetingRoom(parameter);
//		}
//
//	 public int insertAccount(Map<String, Object> parameter){
//			return systemConfigMapper.insertAccount(parameter);
//		}
}
