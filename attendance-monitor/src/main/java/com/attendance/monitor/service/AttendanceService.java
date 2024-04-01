package com.attendance.monitor.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.monitor.entity.AttendanceCompositePrimaryKey;
import com.attendance.monitor.exception.RecordNotFoundException;
import com.attendance.monitor.repo.AttendanceRepository;

@Service
public class AttendanceService {

	@Autowired private AttendanceRepository attendanceRepository;
	
	public int fetchTotalHoursSpentInOneDay(Integer employyeId, LocalDate date) {
		AttendanceCompositePrimaryKey pk = new AttendanceCompositePrimaryKey();
		pk.setEmployeeId(employyeId);
		pk.setRecordDate(date);
		return attendanceRepository
					.findById(pk)
					.map(obj -> obj.getTotalHour())
					.orElseThrow(() -> 
						new RecordNotFoundException("Attendance not found for the day - " + date)
					);
	}
	
}
