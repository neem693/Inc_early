
package com.anylogic.iot.api.admin.systemConfig.mapper;

import java.util.List;
import java.util.Map;

/**
 * <PRE>
 *  ClassName : AdminMapper
 * </PRE>
 * 
 * @version : 1.0
 * @date : 2015. 5. 21. 오전 9:37:56
 * @author : jkkim
 * @brief :
 */

public interface SystemConfigMapper {

	public List<Object> getDefaultList(Map<String, Object> parameter);
	public int deleteAccount(Map<String, Object> parameter);
	public int deleteEquipment(Map<String, Object> parameter);
	public int deleteFacility(Map<String, Object> parameter);
	public int deleteGoods(Map<String, Object> parameter);
	public int deleteMeetingRoom(Map<String, Object> parameter);
	public int deleteOffice(Map<String, Object> parameter);

	public int insertDevice(Map<String, Object> parameter);
	public int insertAccount(Map<String, Object> parameter);
	public int insertEquipment(Map<String, Object> parameter);
	public int insertFacility(Map<String, Object> parameter);
	public int insertGoods(Map<String, Object> parameter);
	public int insertMeetingRoom(Map<String, Object> parameter);
	public int insertOffice(Map<String, Object> parameter);

	public int updateAccount(Map<String, Object> parameter);
	public int updateEquipment(Map<String, Object> parameter);
	public int updateFacility(Map<String, Object> parameter);
	public int updateGoods(Map<String, Object> parameter);
	public int updateMeetingRoom(Map<String, Object> parameter);
	public int updateOffice(Map<String, Object> parameter);


	
	public int insertUpdateOpTime(Map<String, Object> parameter);
	public int updateTimeFacility(Map<String, Object> parameter);
	public int updateTimeMeetingRoom(Map<String, Object> parameter);

	
	
	public List<Object> idCheck(Map<String, Object> parameter);
	public List<Object> getBranchList(Map<String, Object> parameter);
	public List<Object> getOpTimeList(Map<String, Object> parameter);

	
	public List<Object> getAccountList(Map<String, Object> parameter);
	public List<Object> getEquipmentList(Map<String, Object> parameter);
	public List<Object> getFacilityList(Map<String, Object> parameter);
	public List<Object> getGoodsList(Map<String, Object> parameter);
	public List<Object> getMeetingRoomList(Map<String, Object> parameter);
	public List<Object> getOfficeList(Map<String, Object> parameter);

	
	
//	public int insertMeetingRoom(Map<String, Object> parameter);
//	public int insertAccount(Map<String, Object> parameter);

}
