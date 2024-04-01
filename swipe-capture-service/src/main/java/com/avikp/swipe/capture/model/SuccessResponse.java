package com.avikp.swipe.capture.model;

import java.time.LocalDateTime;
import java.util.List;

public class SuccessResponse {

	private Integer status;
	private String messages;
	private LocalDateTime dateTime;
	public SuccessResponse(Integer status, String messages, LocalDateTime dateTime) {
		this.status = status;
		this.messages = messages;
		this.dateTime = dateTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessages() {
		return messages;
	}
	public void setMessages(String messages) {
		this.messages = messages;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
}
