package com.avikp.swipe.capture.model;

public class SwipeEvent {

	private Integer employeeId;
	private EventType eventType;
	
	public SwipeEvent(Integer employeeId, EventType eventType) {
		this.employeeId = employeeId;
		this.eventType = eventType;
	}

	public Integer getEmpId() {
		return employeeId;
	}

	public void setEmpId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public static enum EventType {
		SWIPE_IN,
		SWIPE_OUT
	}

	@Override
	public String toString() {
		return "SwipeEvent [employeeId=" + employeeId + ", eventType=" + eventType + "]";
	}
	
}
