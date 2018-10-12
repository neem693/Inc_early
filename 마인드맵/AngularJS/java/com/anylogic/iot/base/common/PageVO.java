package com.anylogic.iot.base.common;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class PageVO {
	@ApiModelProperty(required = true, value = "Total contents count", dataType = "totalCount.class")
	private int totalCount;

	@SuppressWarnings("rawtypes")
	@ApiModelProperty(required = true, value = "List of contents")
	private List lists;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	@SuppressWarnings("rawtypes")
	public List getLists() {
		return lists;
	}

	@SuppressWarnings("rawtypes")
	public void setLists(List lists) {
		this.lists = lists;
	}


}
