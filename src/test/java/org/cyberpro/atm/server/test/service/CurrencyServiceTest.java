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
	public void currencyConverter_conversionindicatorDivide_shouldBeFifteenThousand() throws Exception {
		BigDecimal rateValue = new BigDecimal("0.1");
		BigDecimal value = new BigDecimal("1500.00");
		CurrencyConversionRate ccr = new CurrencyConversionRate();
		ccr.setConversionIndicator("/");
		ccr.setRate(rateValue);

		BigDecimal expected = new BigDecimal("15000.00");

		assertEquals(expected, currencyService.currencyConverter(value, ccr));
	}

	@Test
	public void currencyConverter_conversionindicatorMultiply_shouldBeThreeThousand() throws Exception {
		BigDecimal rateValue = new BigDecimal("2");
		BigDecimal value = new BigDecimal("1500.00");
		CurrencyConversionRate ccr = new CurrencyConversionRate();
		ccr.setConversionIndicator("*");
		ccr.setRate(rateValue);

		BigDecimal expected = new BigDecimal("3000.00");

		assertEquals(expected, currencyService.currencyConverter(value, ccr));
	}

}
