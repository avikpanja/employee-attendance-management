package com.attendance.monitor.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@IdClass(AttendanceCompositePrimaryKey.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Attendance {

	@Id
	private LocalDate recordDate;
	@Id
	private Integer employeeId;
	private LocalTime firstSwipeIn;
	private LocalTime lastSwipeOut;
	private Integer totalHour;
	private String state;
}
