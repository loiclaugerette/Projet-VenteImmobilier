package com.adaming.myapp.exceptions;

@SuppressWarnings("serial")
public class NullListException extends Exception {

	public NullListException() {
	}

	public NullListException(String message) {
		super(message);
	}

	public NullListException(Throwable cause) {
		super(cause);
	}

	public NullListException(String message, Throwable cause) {
		super(message, cause);
	}

	public NullListException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
