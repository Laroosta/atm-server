package org.cyberpro.atm.server.pojo;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author lmichelson
 *
 */
public class WithdrawResponse {

	private String status;
	private Map<BigDecimal, Integer> denominations;
	private String accountNumber;
	private BigDecimal newDisplayBalance;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Map<BigDecimal, Integer> getDenominations() {
		return denominations;
	}

	public void setDenominations(Map<BigDecimal, Integer> denominations) {
		this.denominations = denominations;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getNewDisplayBalance() {
		return newDisplayBalance;
	}

	public void setNewDisplayBalance(BigDecimal newDisplayBalance) {
		this.newDisplayBalance = newDisplayBalance;
	}

}
