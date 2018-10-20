package org.cyberpro.atm.server.repository;

import java.util.List;

import org.cyberpro.atm.server.entity.client.Client;
import org.cyberpro.atm.server.entity.currency.CurrencyConversionRate;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyConversionRateRepository extends CrudRepository<CurrencyConversionRate, Long> {

	List<CurrencyConversionRate> findAll();

	Client findByCurrencyCode(String currencyCode);
}
