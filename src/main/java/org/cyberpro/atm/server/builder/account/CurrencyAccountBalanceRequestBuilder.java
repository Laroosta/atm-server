package org.cyberpro.atm.server.builder.account;

import java.util.List;

import org.cyberpro.atm.server.builder.AbstractRequestBuilder;
import org.cyberpro.atm.server.pojo.CurrencyAccountBalance;
import org.cyberpro.atm.server.service.impl.ClientAccountService;

public class CurrencyAccountBalanceRequestBuilder extends AbstractRequestBuilder<List<CurrencyAccountBalance>> {

	ClientAccountService service;

	private Integer clientId;

	public CurrencyAccountBalanceRequestBuilder(ClientAccountService service) {
		this.service = service;
	}

	public CurrencyAccountBalanceRequestBuilder byClientId(Integer clientId) {
		this.clientId = clientId;
		return this;
	}

	@Override
	public List<CurrencyAccountBalance> send() {
		return invokeServiceByOrder(clientId);
	}

	private List<CurrencyAccountBalance> invokeServiceByOrder(Integer clientId) {
		if ("ASC".equalsIgnoreCase(this.order)) {
			return service.findClientCurrencyAccountBalancesOrderByAsc(clientId);
		}

		return service.findClientCurrencyAccountBalancesOrderByDesc(clientId);
	}

}
