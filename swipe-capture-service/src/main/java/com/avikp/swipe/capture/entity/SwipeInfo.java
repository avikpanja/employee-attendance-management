package com.avikp.swipe.capture.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "swipe-info")
public class SwipeInfo {

	@Id
	private Integer id;
	private List<EmployeeSwipeDetails> empSwipeDetailsList;
	
	public SwipeInfo() {}
	
	public SwipeInfo(Integer id, List<EmployeeSwipeDetails> empSwipeDetailsList) {
		this.empSwipeDetailsList = empSwipeDetailsList;
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public List<EmployeeSwipeDetails> getEmpSwipeDetailsList() {
		return empSwipeDetailsList;
	}
	
//	@Override
//	public String toString() {
//		return "SwipeInfo [id=" + id + ", empSwipeDetailsList=" + empSwipeDetailsList + "]";
//	}
//	
	
}
