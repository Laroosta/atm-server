package org.cyberpro.atm.server.entity.account;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author lmichelson
 *
 */
@Entity
public class CreditCardLimit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(length = 10, nullable = false)
	private String clientAccountNumber;
	@Column(length = 10, nullable = false)
	private BigDecimal accountLimit;

	public String getClientAccountNumber() {
		return clientAccountNumber;
	}

	public void setClientAccountNumber(String clientAccountNumber) {
		this.clientAccountNumber = clientAccountNumber;
	}

	public BigDecimal getAccountLimit() {
		return accountLimit;
	}

	public void setAccountLimit(BigDecimal accountLimit) {
		this.accountLimit = accountLimit;
	}

}
