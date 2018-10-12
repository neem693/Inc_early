
package com.anylogic.iot.api.admin.tour.mapper;

import java.util.List;
import java.util.Map;

import com.anylogic.iot.api.admin.member.vo.MemberInfoVO;
import com.anylogic.iot.api.admin.member.vo.SendMsgLogVO;
import com.anylogic.iot.api.admin.tour.vo.TourInfoVO;

public interface TourMapper {

	List<Map<String, Object>> getTourApplicantsListExcel(Map<String, Object> parameter);

	List<Object> getTourApplicantsList(Map<String, Object> parameter);

	void deleteTourApplicantsList(Map<String, Object> target);

	int addTourApplicants(Map<String, Object> parameter);

	List<Object> getManagersEmailList();
}



