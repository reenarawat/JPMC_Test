package com.jpmc.assessment.exception;

/**
 * @author Reena
 *
 */
public class PropertyFileAccessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PropertyFileAccessException(String message) {
		super(message);
	}

	public PropertyFileAccessException(Throwable throwable) {
		super(throwable);
	}

	public PropertyFileAccessException(String message, Throwable throwable) {
		super(message, throwable);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
