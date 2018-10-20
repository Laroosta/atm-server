package org.cyberpro.atm.server.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author lmichelson
 *
 */
public class CurrencyAccountBalance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String accountNumber;
	private String currency;
	private BigDecimal currencyBalance;
	private BigDecimal conversionRate;
	private BigDecimal zarAmount;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getCurrencyBalance() {
		return currencyBalance;
	}

	public void setCurrencyBalance(BigDecimal currencyBalance) {
		this.currencyBalance = currencyBalance;
	}

	public BigDecimal getConversionRate() {
		return conversionRate;
	}

	public void setConversionRate(BigDecimal conversionRate) {
		this.conversionRate = conversionRate;
	}

	public BigDecimal getZarAmount() {
		return zarAmount;
	}

	public void setZarAmount(BigDecimal zarAmount) {
		this.zarAmount = zarAmount;
	}

}
