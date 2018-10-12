package com.anylogic.iot.api.common.vo;

import java.util.ArrayList;
import java.util.List;

public class ResultSvcContextVO {
	
	List<DstrContextVO> dstrList;
	
	public void makeContext(){
		if(dstrList == null) dstrList = new ArrayList<DstrContextVO>();
	}
	
	public void addItem(ResultSvcListVO svc){
		
		DstrContextVO dv = null;
		for(DstrContextVO dstr : dstrList) if(dstr.getDstrCd().equals(svc.dstrCd)){
			dv = dstr;
			break;
		}
		if(dv == null){
			dv = new DstrContextVO();
			dv.makeContext();
			dv.setDstrCd(svc.dstrCd);
			dv.setDstrNm(svc.dstrNm);
			
			dstrList.add(dv);
		}
		
		dv.addItem(svc);
	}
	
	public List<DstrContextVO> getDstrList() {
		return dstrList;
	}
}
