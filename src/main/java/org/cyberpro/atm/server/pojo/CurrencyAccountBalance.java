package org.cyberpro.atm.server.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.cyberpro.atm.server.entity.account.ClientAccount;

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
	private String currencyCode;
	private BigDecimal currencyBalance;
	private BigDecimal conversionRate;
	private BigDecimal zarAmount;

	/**
	 * @param accountNumber
	 * @param currencyCode
	 * @param currencyBalance
	 * @param conversionRate
	 * @param zarAmount
	 */
	public CurrencyAccountBalance(ClientAccount clientAccount, BigDecimal rate, BigDecimal zarAmount) {
		this.accountNumber = clientAccount.getClientAccountNumber();
		this.currencyCode = clientAccount.getCurrency().getCurrencyCode();
		this.currencyBalance = clientAccount.getDisplayBalance();
		this.conversionRate = rate;
		this.zarAmount = zarAmount.setScale(2, RoundingMode.HALF_UP);
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCurrency() {
		return currencyCode;
	}

	public void setCurrency(String currency) {
		this.currencyCode = currency;
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
