package com.attendance.monitor.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.monitor.entity.Attendance;
import com.attendance.monitor.entity.AttendanceCompositePrimaryKey;

public interface AttendanceRepository extends JpaRepository<Attendance, AttendanceCompositePrimaryKey>{

}
