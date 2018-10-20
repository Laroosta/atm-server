package org.cyberpro.atm.server.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.cyberpro.atm.server.entity.currency.CurrencyConversionRate;
import org.cyberpro.atm.server.service.ICurrencyService;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService implements ICurrencyService {

	@Override
	public BigDecimal currencyConverter(BigDecimal value, CurrencyConversionRate rate) {
		if (rate.getConversionIndicator().contains("*")) {
			return value.divide(rate.getRate(), 2, RoundingMode.HALF_UP);
		}

		return value.multiply(rate.getRate());

	}

}
