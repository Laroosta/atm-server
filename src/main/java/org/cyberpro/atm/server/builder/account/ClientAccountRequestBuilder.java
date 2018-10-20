package org.cyberpro.atm.server.builder.account;

import java.util.List;

import org.cyberpro.atm.server.builder.AbstractRequestBuilder;
import org.cyberpro.atm.server.entity.account.ClientAccount;
import org.cyberpro.atm.server.service.ClientAccountService;

public class ClientAccountRequestBuilder extends AbstractRequestBuilder<List<ClientAccount>> {

	ClientAccountService service;

	private Integer clientId;

	public ClientAccountRequestBuilder(ClientAccountService service) {
		this.service = service;
	}

	public ClientAccountRequestBuilder byClientId(Integer clientId) {
		this.clientId = clientId;
		return this;
	}

	@Override
	public List<ClientAccount> send() {

		if (this.clientId != null) {
			return invokeServiceByOrder(clientId);
		}

		return service.getAll();
	}

	private List<ClientAccount> invokeServiceByOrder(Integer clientId) {
		if ("UN".equalsIgnoreCase(this.order)) {
			return service.findByClientId(clientId);
		}

		if ("ASC".equalsIgnoreCase(this.order)) {
			return service.findByClient_ClientIdOrderByDisplayBalanceAsc(clientId);
		}

		return service.findByClient_ClientIdOrderByDisplayBalanceDesc(this.clientId);
	}

}
