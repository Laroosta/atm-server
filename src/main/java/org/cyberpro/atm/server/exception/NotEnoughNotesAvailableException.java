package org.cyberpro.atm.server.exception;

public class NotEnoughNotesAvailableException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String message = "Amount not available, would you like to withdraw %s";

	public NotEnoughNotesAvailableException(double availableAmount) {
		super(String.format(message, availableAmount));
	}

	public NotEnoughNotesAvailableException(double availableAmount, Throwable err) {
		super(String.format(message, availableAmount), err);
	}

}
