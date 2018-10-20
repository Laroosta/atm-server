package org.cyberpro.atm.server.entity.currency;

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
public class CurrencyConversionRate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(length = 3, nullable = false)
	private String currencyCode;
	@Column(length = 1, nullable = false)
	private String conversionIndicator;
	private BigDecimal rate;

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getConversionIndicator() {
		return conversionIndicator;
	}

	public void setConversionIndicator(String conversionIndicator) {
		this.conversionIndicator = conversionIndicator;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

}
