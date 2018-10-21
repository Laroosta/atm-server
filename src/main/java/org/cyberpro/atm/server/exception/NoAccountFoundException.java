package org.cyberpro.atm.server.exception;

public class NoAccountFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String errorMessage = "No accounts to display";

	public NoAccountFoundException() {
		super(errorMessage);
	}

	public NoAccountFoundException(Throwable err) {
		super(errorMessage, err);
	}

}
