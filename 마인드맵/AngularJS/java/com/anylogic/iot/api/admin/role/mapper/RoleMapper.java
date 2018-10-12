
package com.anylogic.iot.api.admin.role.mapper;

import java.util.List;
import java.util.Map;



public interface RoleMapper {

	public List<Object> selectMemberList(Map<String, Object> parameter);
	public List<Object> selectMemberListAll(Map<String, Object> parameter);

	public void updateMemberList(Map<String, Object> parameter);

	public List<Object> selectMemberDetail(Map<String, Object> parameter);

	public void updateMemberPermit(Map<String, Object> parameter);
	public void updateMemberDetail(Map<String, Object> parameter);

	public void insertMember(Map<String, Object> parameter);
	
}
