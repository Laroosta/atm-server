package org.cyberpro.atm.server.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author lmichelson
 *
 */
public class TransactionalAccountBalance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String accountNumber;
	private String accountType;
	private BigDecimal accountBalance;

	/**
	 * @param accountNumber
	 * @param accountType
	 * @param accountBalance
	 */
	public TransactionalAccountBalance(String accountNumber, String accountType, BigDecimal accountBalance) {
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
}
