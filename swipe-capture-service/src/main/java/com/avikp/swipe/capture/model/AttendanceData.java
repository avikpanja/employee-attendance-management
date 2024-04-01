package com.avikp.swipe.capture.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import com.avikp.swipe.capture.util.Util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AttendanceData {

	private Integer employeeId;
	private LocalDate recordDate;
	private LocalTime firstSwipeIn;
	private LocalTime lastSwipeOut;
	private Integer totalHour;
	private AttendanceState state;

	public static AttendanceData newObject(LocalDate recordDate, LocalTime firstSwipeIn, LocalTime lastSwipeOut, Integer employeeId) {
		int hours = (int) Duration.between(firstSwipeIn, lastSwipeOut).toHours();
		//int hours = Duration.between(firstSwipeIn, lastSwipeOut).toMinutesPart();
		
		AttendanceData newObject = new AttendanceData();
		newObject.setEmployeeId(employeeId);
		newObject.setRecordDate(recordDate);
		newObject.setFirstSwipeIn(firstSwipeIn);
		newObject.setLastSwipeOut(lastSwipeOut);
		newObject.setTotalHour(hours);
		newObject.setState(Util.getAttendanceState(hours));
	
		return newObject;
	}
}
