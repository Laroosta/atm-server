package org.cyberpro.atm.server.builder.account;

import java.util.List;

import org.cyberpro.atm.server.builder.AbstractRequestBuilder;
import org.cyberpro.atm.server.entity.account.ClientAccount;
import org.cyberpro.atm.server.service.impl.ClientAccountService;

/**
 * @author lmichelson
 *
 */
public class ClientAccountRequestBuilder extends AbstractRequestBuilder<List<ClientAccount>> {

	ClientAccountService service;

	private Integer clientId;

	/**
	 * @param service
	 */
	public ClientAccountRequestBuilder(ClientAccountService service) {
		this.service = service;
	}

	/**
	 * @param clientId
	 * @return
	 */
	public ClientAccountRequestBuilder byClientId(Integer clientId) {
		this.clientId = clientId;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cyberpro.atm.server.builder.IRequestBuilder#send()
	 */
	@Override
	public List<ClientAccount> send() {

		if (this.clientId != null) {
			return invokeServiceByOrder(clientId);
		}

		return service.findAll();
	}

	/**
	 * @param clientId
	 * @return
	 */
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
