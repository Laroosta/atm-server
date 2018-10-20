package org.cyberpro.atm.server.repository;

import java.util.List;

import org.cyberpro.atm.server.entity.client.Client;
import org.cyberpro.atm.server.entity.currency.CurrencyConversionRate;
import org.springframework.data.repository.CrudRepository;

/**
 * @author lmichelson
 *
 */
public interface CurrencyConversionRateRepository extends CrudRepository<CurrencyConversionRate, Long> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<CurrencyConversionRate> findAll();

	/**
	 * @param currencyCode
	 * @return
	 */
	Client findByCurrencyCode(String currencyCode);
}
