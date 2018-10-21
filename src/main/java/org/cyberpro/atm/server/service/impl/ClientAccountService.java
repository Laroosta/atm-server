package org.cyberpro.atm.server.service.impl;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.cyberpro.atm.server.Application;
import org.cyberpro.atm.server.entity.account.ClientAccount;
import org.cyberpro.atm.server.entity.currency.CurrencyConversionRate;
import org.cyberpro.atm.server.pojo.CurrencyAccountBalance;
import org.cyberpro.atm.server.pojo.TransactionalAccountBalance;
import org.cyberpro.atm.server.repository.ClientAccountRepository;
import org.cyberpro.atm.server.repository.CurrencyConversionRateRepository;
import org.cyberpro.atm.server.service.IClientAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lmichelson
 *
 */
@Service
public class ClientAccountService implements IClientAccountService {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	private ClientAccountRepository repository;

	@Autowired
	private CurrencyConversionRateRepository ccrRepository;

	@Autowired
	private CurrencyService currencyService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cyberpro.atm.server.service.IClientAccountService#findAll()
	 */
	public List<ClientAccount> findAll() {
		log.info("+---------------------------------------------+");
		log.info("+ ClientAccount: Get list of all ClientAccounts");

		List<ClientAccount> list = repository.findAll();

		log.info("+ Found : " + list.size() + " ClientAccounts");
		log.info("+---------------------------------------------+");

		return list;
	}

	/**
	 * @param accountNumber
	 * @return
	 */
	public ClientAccount findByAccountNumber(String accountNumber) {
		log.info("+---------------------------------------------+");
		log.info("+ ClientAccount: findByAccountNumber " + accountNumber);

		ClientAccount account = repository.findByClientAccountNumber(accountNumber);

		log.info("+ Found : " + account.getClientAccountNumber());
		log.info("+---------------------------------------------+");

		return account;
	}

	/**
	 * @param clientId
	 * @return
	 */
	public List<ClientAccount> findByClientId(Integer clientId) {
		log.info("+---------------------------------------------+");
		log.info("+ ClientAccount: ClientAccount for client : " + clientId);

		List<ClientAccount> list = repository.findByClient_ClientId(clientId);

		log.info("+ Found : " + list.size());
		log.info("+---------------------------------------------+");

		return list;
	}

	/**
	 * @param clientId
	 * @return
	 */
	public List<ClientAccount> findByClient_ClientIdOrderByDisplayBalanceDesc(Integer clientId) {
		log.info("+---------------------------------------------+");
		log.info("+ ClientAccount: ClientAccount for client : " + clientId);
		log.info("+ Order Display Balance By: DESC : " + clientId);

		List<ClientAccount> list = repository.findByClient_ClientIdOrderByDisplayBalanceDesc(clientId);

		log.info("+ Found : " + list.size());
		log.info("+---------------------------------------------+");

		return list;
	}

	/**
	 * @param clientId
	 * @return
	 */
	public List<ClientAccount> findByClient_ClientIdOrderByDisplayBalanceAsc(Integer clientId) {
		log.info("+---------------------------------------------+");
		log.info("+ ClientAccount: ClientAccount for client : " + clientId);
		log.info("+ Order Display Balance By: ASC : " + clientId);

		List<ClientAccount> list = repository.findByClient_ClientIdOrderByDisplayBalanceAsc(clientId);

		log.info("+ Found : " + list.size());
		log.info("+---------------------------------------------+");

		return list;
	}

	/**
	 * Finds all Client Accounts and sorts the DisplayBalance Descending
	 * 
	 * @param clientId
	 * @return
	 */
	public List<TransactionalAccountBalance> findClientTrxAccountBalancesOrderByDesc(Integer clientId) {
		log.info("+---------------------------------------------+");
		log.info("+ ClientAccount: ClientAccount for client : " + clientId);
		log.info("+ Order Display Balance By: DESC : " + clientId);

		List<ClientAccount> list = this.findByClient_ClientIdOrderByDisplayBalanceDesc(clientId);

		List<TransactionalAccountBalance> result = mapToTrxAccountBalance(list);

		log.info("+ Found : " + result.size());
		log.info("+---------------------------------------------+");

		return result;
	}

	/**
	 * Finds all Client Accounts and sorts the DisplayBalance Ascending
	 * 
	 * @param clientId
	 * @return
	 */
	public List<TransactionalAccountBalance> findClientTrxAccountBalancesOrderByAsc(Integer clientId) {
		log.info("+---------------------------------------------+");
		log.info("+ ClientAccount: ClientAccount for client : " + clientId);
		log.info("+ Order Display Balance By: DESC : " + clientId);

		List<ClientAccount> list = this.findByClient_ClientIdOrderByDisplayBalanceAsc(clientId);

		List<TransactionalAccountBalance> result = mapToTrxAccountBalance(list);

		log.info("+ Found : " + result.size());
		log.info("+---------------------------------------------+");

		return result;
	}

	/**
	 * Maps a list of ClientAccount to TransactionalAccountBalance
	 * 
	 * @param list
	 * @return
	 */
	private List<TransactionalAccountBalance> mapToTrxAccountBalance(List<ClientAccount> list) {
		return list.stream().map(a -> new TransactionalAccountBalance(a.getClientAccountNumber(),
				a.getAccountType().getDescription(), a.getDisplayBalance())).collect(Collectors.toList());
	}

	/**
	 * Finds all Client Accounts and sorts the DisplayBalance Descending
	 * 
	 * @param clientId
	 * @return
	 */
	public List<CurrencyAccountBalance> findClientCurrencyAccountBalancesOrderByDesc(Integer clientId) {
		log.info("+---------------------------------------------+");
		log.info("+ ClientAccount: ClientAccount for client : " + clientId);
		log.info("+ Order Display Balance By: DESC : " + clientId);

		List<ClientAccount> list = this.findByClient_ClientIdOrderByDisplayBalanceDesc(clientId);

		List<CurrencyAccountBalance> result = mapToCurrencyAccountBalance(list, true);

		log.info("+ Found : " + result.size());
		log.info("+---------------------------------------------+");

		return result;
	}

	/**
	 * Finds all Client Accounts and sorts the DisplayBalance Ascending
	 * 
	 * @param clientId
	 * @return
	 */
	public List<CurrencyAccountBalance> findClientCurrencyAccountBalancesOrderByAsc(Integer clientId) {
		log.info("+---------------------------------------------+");
		log.info("+ ClientAccount: ClientAccount for client : " + clientId);
		log.info("+ Order Display Balance By: DESC : " + clientId);

		List<ClientAccount> list = this.findByClient_ClientIdOrderByDisplayBalanceAsc(clientId);

		List<CurrencyAccountBalance> result = mapToCurrencyAccountBalance(list, false);

		log.info("+ Found : " + result.size());
		log.info("+---------------------------------------------+");

		return result;
	}

	/**
	 * Maps a list of ClientAccount to TransactionalAccountBalance
	 * 
	 * @param list
	 * @return
	 */
	private List<CurrencyAccountBalance> mapToCurrencyAccountBalance(List<ClientAccount> list, boolean isReversed) {
		List<CurrencyConversionRate> rateList = ccrRepository.findAll();

		Stream<CurrencyAccountBalance> result = list.stream().map(a -> {
			CurrencyConversionRate rate = findCurrencyConvRate(a.getCurrency().getCurrencyCode(), rateList);
			BigDecimal convertedValue = currencyService.currencyConverter(a.getDisplayBalance(), rate);
			return new CurrencyAccountBalance(a, rate.getRate(), convertedValue);
		});

		if (isReversed) {
			return result.sorted(Comparator.comparing(CurrencyAccountBalance::getZarAmount).reversed())
					.collect(Collectors.toList());
		}

		return result.sorted(Comparator.comparing(CurrencyAccountBalance::getZarAmount)).collect(Collectors.toList());

	}

	/**
	 * Finds the first CurrencyConversionRate in the list that matches the
	 * currencyCode
	 * 
	 * @param currencyCode
	 * @param rateList
	 * @return
	 */
	private CurrencyConversionRate findCurrencyConvRate(String currencyCode, List<CurrencyConversionRate> rateList) {
		Optional<CurrencyConversionRate> result = rateList.stream()
				.filter(r -> r.getCurrencyCode().equalsIgnoreCase(currencyCode)).findFirst();

		return result.get();

	}

	public ClientAccount save(ClientAccount clientAccount) {
		return repository.save(clientAccount);
	}

}
