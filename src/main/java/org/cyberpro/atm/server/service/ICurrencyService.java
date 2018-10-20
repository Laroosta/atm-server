package org.cyberpro.atm.server.service;

import java.math.BigDecimal;

import org.cyberpro.atm.server.entity.currency.CurrencyConversionRate;

public interface ICurrencyService {

	public BigDecimal currencyConverter(BigDecimal value, CurrencyConversionRate rate);
}
