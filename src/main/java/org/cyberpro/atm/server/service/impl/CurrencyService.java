package org.cyberpro.atm.server.service.impl;

import java.math.BigDecimal;

import org.cyberpro.atm.server.entity.currency.CurrencyConversionRate;
import org.cyberpro.atm.server.service.ICurrencyService;
import org.springframework.stereotype.Service;

/**
 * @author lmichelson
 *
 */
@Service
public class CurrencyService implements ICurrencyService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.cyberpro.atm.server.service.ICurrencyService#currencyConverter(java.math.
	 * BigDecimal, org.cyberpro.atm.server.entity.currency.CurrencyConversionRate)
	 */
	@Override
	public BigDecimal currencyConverter(BigDecimal value, CurrencyConversionRate rate) {
		if (rate.getConversionIndicator().contains("*")) {
			return value.multiply(rate.getRate());
		}

		return value.divide(rate.getRate(), 2);

	}

}
