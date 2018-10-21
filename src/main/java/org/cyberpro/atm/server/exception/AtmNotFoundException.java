package org.cyberpro.atm.server.exception;

public class AtmNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String errorMessage = "ATM not registered or unfunded";

	public AtmNotFoundException() {
		super(errorMessage);
	}

	public AtmNotFoundException(Throwable err) {
		super(errorMessage, err);
	}

}
