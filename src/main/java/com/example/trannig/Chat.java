package com.example.trannig;

public class Chat {

	private String fromUser;
	private String toUser;
	private String message;
	

	public Chat(String fromUser, String toUser, String message) {
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.message = message;
	}
	

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}