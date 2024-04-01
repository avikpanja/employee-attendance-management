package com.avikp.swipe.capture.entity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSwipeDetails {

	private Integer empId;
	private List<LocalTime> inTimeList = new ArrayList<>();
	private List<LocalTime> outTimeList = new ArrayList<>();
	
	public EmployeeSwipeDetails() {}
	
	public EmployeeSwipeDetails(Integer empId) {
		this.empId = empId;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public List<LocalTime> getInTimeList() {
		return inTimeList;
	}

	public void setInTimeList(List<LocalTime> inTimeList) {
		this.inTimeList = inTimeList;
	}

	public List<LocalTime> getOutTimeList() {
		return outTimeList;
	}

	public void setOutTimeList(List<LocalTime> outTimeList) {
		this.outTimeList = outTimeList;
	}
}
