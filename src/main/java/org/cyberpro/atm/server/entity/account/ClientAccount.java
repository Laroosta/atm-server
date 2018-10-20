package org.cyberpro.atm.server.entity.account;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

import org.cyberpro.atm.server.entity.client.Client;
import org.cyberpro.atm.server.entity.currency.Currency;

/**
 * @author lmichelson
 *
 */
@Entity
public class ClientAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 10)
	private String clientAccountNumber;

	@OneToOne
	@JoinColumn(name = "CLIENT_ID")
	private Client client;

	@OneToOne
	@JoinColumn(name = "ACCOUNT_TYPE_CODE")
	private AccountType accountType;

	@OneToOne
	@JoinColumn(name = "CURRENCY_CODE")
	private Currency currency;

	private BigDecimal displayBalance;

	@Transient
	private String formattedDisplayBalance;

	public String getClientAccountNumber() {
		return clientAccountNumber;
	}

	public void setClientAccountNumber(String clientAccountNumber) {
		this.clientAccountNumber = clientAccountNumber;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public BigDecimal getDisplayBalance() {
		return displayBalance;
	}

	public void setDisplayBalance(BigDecimal displayBalance) {
		this.displayBalance = displayBalance;
	}

	@PostLoad
	private void postLoad() {
		String currencyCode = this.getCurrency().getCurrencyCode();
		int precision = this.getCurrency().getDecimalPlaces();
		String displayBalance = this.getDisplayBalance().setScale(precision).toString();

		this.formattedDisplayBalance = String.format("%1$s %2$s", currencyCode, displayBalance);
	}

	@Transient
	@Access(AccessType.PROPERTY)
	public String getFormattedDisplayBalance() {
		return this.formattedDisplayBalance;
	}

}
