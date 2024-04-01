package com.attendance.monitor.util;

import com.attendance.monitor.entity.Attendance;
import com.attendance.monitor.model.AttendanceData;

public class Util {

	public static Attendance convertFromAttendanceDataToAttendance(AttendanceData data) {
		Attendance obj = new Attendance();
		obj.setEmployeeId(data.getEmployeeId());
		obj.setFirstSwipeIn(data.getFirstSwipeIn());
		obj.setLastSwipeOut(data.getLastSwipeOut());
		obj.setRecordDate(data.getRecordDate());
		obj.setTotalHour(data.getTotalHour());
		obj.setState(data.getState().name());
		
		return obj;
	}
}
