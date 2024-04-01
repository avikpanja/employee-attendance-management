package com.avikp.swipe.capture.util;

import com.avikp.swipe.capture.model.AttendanceState;

public class Util {

	public static AttendanceState getAttendanceState(int hours) {
		if(hours < 4) {
			return AttendanceState.ABESENT;
		} else if(hours >=4 && hours < 8) {
			return AttendanceState.HALF_DAY;
		} else {
			return AttendanceState.PRESENT;
		}
	}
}
