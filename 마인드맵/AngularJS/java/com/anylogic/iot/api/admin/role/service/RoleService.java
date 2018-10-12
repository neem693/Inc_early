package com.anylogic.iot.api.admin.role.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anylogic.iot.api.admin.role.mapper.RoleMapper;


/**
 * 
 * @author kim
 *
 */
@Service
public class RoleService {

	@Autowired
	private RoleMapper myMapper;

	public List<Object> selectMemberList(Map<String, Object> parameter) {
		return myMapper.selectMemberList(parameter);
	}

	public List<Object> selectMemberListAll(Map<String, Object> parameter) {
		return myMapper.selectMemberListAll(parameter);
	}


	public void updateMemberList(Map<String, Object> parameter) {
		myMapper.updateMemberList(parameter);
	}

	public List<Object> selectMemberDetail(Map<String, Object> parameter) {
		return myMapper.selectMemberDetail(parameter);
	}

	public void updateMemberPermit(Map<String, Object> parameter) {
		myMapper.updateMemberPermit(parameter);
	}

	public void updateMemberDetail(Map<String, Object> parameter) {
		myMapper.updateMemberDetail(parameter);
	}

	public void insertMember(Map<String, Object> parameter) {
		myMapper.insertMember(parameter);
	}
}
