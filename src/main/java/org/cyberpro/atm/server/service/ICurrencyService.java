package org.cyberpro.atm.server.service;

import java.math.BigDecimal;

import org.cyberpro.atm.server.entity.currency.CurrencyConversionRate;

/**
 * @author lmichelson
 *
 */
public interface ICurrencyService {

	/**
	 * @param value
	 * @param rate
	 * @return
	 */
	public BigDecimal currencyConverter(BigDecimal value, CurrencyConversionRate rate);
}
