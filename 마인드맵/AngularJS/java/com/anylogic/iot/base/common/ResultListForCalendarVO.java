package com.anylogic.iot.base.common;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
/**
 * VO 객체가 아닌 List를 리턴하는 경우 Page 정보와 같이 리턴
 */

public class ResultListForCalendarVO implements Serializable{

	private static final long serialVersionUID = 1953666707942320442L;
	private int totalConference = 0;
	private int useBookingCount = 0;
	private int completeBookingCount = 0;
	private int availableConferenceCount = 0;

	private List<Object> branchDataRows;
	private List<Object> floorDataRows;
	private List<Object> meetingRoomDataRows;
	private List<Object> bookingDataRows;
	private List<Object> meetingMemberDataRows;
	private List<Object> innerMeetingMemberDataRows;
	private List<Object> outterMeetingMemberDataRows;

	public int getTotalConference() {
		return totalConference;
	}

	public void setTotalConference(int totalConference) {
		this.totalConference = totalConference;
	}

	public int getUseBookingCount() {
		return useBookingCount;
	}

	public void setUseBookingCount(int useBookingCount) {
		this.useBookingCount = useBookingCount;
	}

	public int getCompleteBookingCount() {
		return completeBookingCount;
	}

	public void setCompleteBookingCount(int completeBookingCount) {
		this.completeBookingCount = completeBookingCount;
	}

	public int getAvailableConferenceCount() {
		return availableConferenceCount;
	}

	public void setAvailableConferenceCount(int availableConferenceCount) {
		this.availableConferenceCount = availableConferenceCount;
	}
	
	public List<Object> getBranchDataRows() {
		return branchDataRows;
	}

	public void setBranchDataRows(List<Object> branchDataRows) {
		this.branchDataRows = branchDataRows;
	}

	public List<Object> getFloorDataRows() {
		return floorDataRows;
	}

	public void setFloorDataRows(List<Object> floorDataRows) {
		this.floorDataRows = floorDataRows;
	}

	public List<Object> getMeetingRoomDataRows() {
		return meetingRoomDataRows;
	}

	public void setMeetingRoomDataRows(List<Object> meetingRoomDataRows) {
		this.meetingRoomDataRows = meetingRoomDataRows;
	}

	public List<Object> getBookingDataRows() {
		return bookingDataRows;
	}

	public void setBookingDataRows(List<Object> bookingDataRows) {
		this.bookingDataRows = bookingDataRows;
	}

	public List<Object> getMeetingMemberDataRows() {
		return meetingMemberDataRows;
	}

	public void setMeetingMemberDataRows(List<Object> meetingMemberDataRows) {
		this.meetingMemberDataRows = meetingMemberDataRows;
	}

	public List<Object> getInnerMeetingMemberDataRows() {
		return innerMeetingMemberDataRows;
	}

	public void setInnerMeetingMemberDataRows(List<Object> innerMeetingMemberDataRows) {
		this.innerMeetingMemberDataRows = innerMeetingMemberDataRows;
	}

	public List<Object> getOutterMeetingMemberDataRows() {
		return outterMeetingMemberDataRows;
	}

	public void setOutterMeetingMemberDataRows(List<Object> outterMeetingMemberDataRows) {
		this.outterMeetingMemberDataRows = outterMeetingMemberDataRows;
	}
}
