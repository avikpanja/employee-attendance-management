package com.attendance.monitor.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AttendanceData {

	private Integer employeeId;
	private LocalDate recordDate;
	private LocalTime firstSwipeIn;
	private LocalTime lastSwipeOut;
	private Integer totalHour;
	private AttendanceState state;
	
	public AttendanceData() {}

	public AttendanceData(LocalDate recordDate, LocalTime firstSwipeIn, LocalTime lastSwipeOut, Integer totalHour,
			AttendanceState state, Integer employeeId) {
		this.recordDate = recordDate;
		this.firstSwipeIn = firstSwipeIn;
		this.lastSwipeOut = lastSwipeOut;
		this.totalHour = totalHour;
		this.state = state;
		this.employeeId = employeeId;
	}
	
}
