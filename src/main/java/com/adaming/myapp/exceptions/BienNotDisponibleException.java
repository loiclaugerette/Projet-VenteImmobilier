package com.adaming.myapp.exceptions;

@SuppressWarnings("serial")
public class BienNotDisponibleException extends Exception {

	public BienNotDisponibleException() {
	}

	public BienNotDisponibleException(String message) {
		super(message);
	}

	public BienNotDisponibleException(Throwable cause) {
		super(cause);
	}

	public BienNotDisponibleException(String message, Throwable cause) {
		super(message, cause);
	}

	public BienNotDisponibleException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
