package org.cyberpro.atm.server.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lmichelson
 *
 */
public class WithdrawRequest {

	private Integer clientId;
	private Integer atmId;
	private BigDecimal amount;
	private String accountNumber;
	private Date timestamp;
	private String hash;

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getAtmId() {
		return atmId;
	}

	public void setAtmId(Integer atmId) {
		this.atmId = atmId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getHash() {
		return hash;
	}

}
