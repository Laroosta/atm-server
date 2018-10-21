package org.cyberpro.atm.server.builder.account;

import java.util.List;

import org.cyberpro.atm.server.builder.AbstractQueryBuilder;
import org.cyberpro.atm.server.pojo.CurrencyAccountBalance;
import org.cyberpro.atm.server.service.impl.ClientAccountService;

/**
 * @author lmichelson
 *
 */
public class CurrencyAccountBalanceQueryBuilder extends AbstractQueryBuilder<CurrencyAccountBalance> {

	ClientAccountService service;

	private Integer clientId;

	/**
	 * @param service
	 */
	public CurrencyAccountBalanceQueryBuilder(ClientAccountService service) {
		this.service = service;
	}

	/**
	 * @param clientId
	 * @return
	 */
	public CurrencyAccountBalanceQueryBuilder byClientId(Integer clientId) {
		this.clientId = clientId;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cyberpro.atm.server.builder.IRequestBuilder#send()
	 */
	@Override
	public List<CurrencyAccountBalance> findAll() {
		return invokeServiceByOrder(clientId);
	}

	/**
	 * @param clientId
	 * @return
	 */
	private List<CurrencyAccountBalance> invokeServiceByOrder(Integer clientId) {
		if ("ASC".equalsIgnoreCase(this.order)) {
			return service.findClientCurrencyAccountBalancesOrderByAsc(clientId);
		}

		return service.findClientCurrencyAccountBalancesOrderByDesc(clientId);
	}

}
