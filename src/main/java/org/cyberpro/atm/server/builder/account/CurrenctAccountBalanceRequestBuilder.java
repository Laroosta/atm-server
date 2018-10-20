package org.cyberpro.atm.server.builder.account;

import java.util.List;

import org.cyberpro.atm.server.builder.AbstractRequestBuilder;
import org.cyberpro.atm.server.pojo.TransactionalAccountBalance;
import org.cyberpro.atm.server.service.impl.ClientAccountService;

public class CurrenctAccountBalanceRequestBuilder extends AbstractRequestBuilder<List<TransactionalAccountBalance>> {

	ClientAccountService service;

	private Integer clientId;

	public CurrenctAccountBalanceRequestBuilder(ClientAccountService service) {
		this.service = service;
	}

	public CurrenctAccountBalanceRequestBuilder byClientId(Integer clientId) {
		this.clientId = clientId;
		return this;
	}

	@Override
	public List<TransactionalAccountBalance> send() {
		return invokeServiceByOrder(clientId);
	}

	private List<TransactionalAccountBalance> invokeServiceByOrder(Integer clientId) {
		if ("ASC".equalsIgnoreCase(this.order)) {
			return service.findClientTrxAccountBalancesOrderByAsc(clientId);
		}

		return service.findClientTrxAccountBalancesOrderByDesc(clientId);
	}

}
