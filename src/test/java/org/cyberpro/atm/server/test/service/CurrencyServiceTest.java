package org.cyberpro.atm.server.test.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.cyberpro.atm.server.entity.currency.CurrencyConversionRate;
import org.cyberpro.atm.server.service.impl.CurrencyService;
import org.junit.Before;
import org.junit.Test;

public class CurrencyServiceTest {

	CurrencyService currencyService;

	@Before
	public void init() {
		currencyService = new CurrencyService();

	}

	@Test
	public void currencyConverter_conversionindicatorDivide_shouldBeHundredAndFifty() throws Exception {
		BigDecimal rateValue = new BigDecimal("0.1");
		BigDecimal value = new BigDecimal("1500.00");
		CurrencyConversionRate ccr = new CurrencyConversionRate();
		ccr.setConversionIndicator("/");
		ccr.setRate(rateValue);

		assertEquals(currencyService.currencyConverter(value, ccr), new BigDecimal("150.000"));
	}

	@Test
	public void currencyConverter_conversionindicatorMultiply_shouldBeThreeThousand() throws Exception {
		BigDecimal rateValue = new BigDecimal("2");
		BigDecimal value = new BigDecimal("1500.00");
		CurrencyConversionRate ccr = new CurrencyConversionRate();
		ccr.setConversionIndicator("/");
		ccr.setRate(rateValue);

		assertEquals(currencyService.currencyConverter(value, ccr), new BigDecimal("3000.00"));
	}

}
