package com.adaming.myapp.exceptions;

@SuppressWarnings("serial")
public class AlreadyExistingPersonException extends Exception {

	public AlreadyExistingPersonException() {
	}

	public AlreadyExistingPersonException(String message) {
		super(message);
	}

	public AlreadyExistingPersonException(Throwable cause) {
		super(cause);
	}

	public AlreadyExistingPersonException(String message, Throwable cause) {
		super(message, cause);
	}

	public AlreadyExistingPersonException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
