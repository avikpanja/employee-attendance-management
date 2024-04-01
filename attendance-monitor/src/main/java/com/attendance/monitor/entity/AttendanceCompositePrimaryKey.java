package com.attendance.monitor.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class AttendanceCompositePrimaryKey {

	private LocalDate recordDate;
	private Integer employeeId;
}
