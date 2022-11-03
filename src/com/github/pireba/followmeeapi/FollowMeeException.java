package com.github.pireba.followmeeapi;

public class FollowMeeException extends Exception {
	
	private static final long serialVersionUID = 682078996855072801L;
	
	public FollowMeeException() {
		
	}
	
	public FollowMeeException(String message) {
		super(message);
	}
	
	public FollowMeeException(Throwable cause) {
		super(cause);
	}
	
	public FollowMeeException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public FollowMeeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}	
}