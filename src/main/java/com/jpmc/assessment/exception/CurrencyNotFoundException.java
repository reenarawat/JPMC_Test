package com.jpmc.assessment.exception;

/**
 * @author Reena
 *
 */
public class CurrencyNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CurrencyNotFoundException(String message) {
		super(message);
	}

	public CurrencyNotFoundException(Throwable throwable) {
		super(throwable);
	}

	public CurrencyNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
