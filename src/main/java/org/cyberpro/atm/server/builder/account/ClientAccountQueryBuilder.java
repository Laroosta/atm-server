package org.cyberpro.atm.server.builder.account;

import java.util.List;

import org.cyberpro.atm.server.builder.AbstractQueryBuilder;
import org.cyberpro.atm.server.entity.account.ClientAccount;
import org.cyberpro.atm.server.service.impl.ClientAccountService;

/**
 * @author lmichelson
 *
 */
public class ClientAccountQueryBuilder extends AbstractQueryBuilder<ClientAccount> {

	ClientAccountService service;

	private Integer clientId;

	private String accountNumber;

	/**
	 * @param service
	 */
	public ClientAccountQueryBuilder(ClientAccountService service) {
		this.service = service;
	}

	/**
	 * @param clientId
	 * @return
	 */
	public ClientAccountQueryBuilder byClientId(Integer clientId) {
		this.clientId = clientId;
		return this;
	}

	/**
	 * @param accountNumber
	 * @return
	 */
	public ClientAccount byAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
		return service.findByAccountNumber(this.accountNumber);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cyberpro.atm.server.builder.IRequestBuilder#send()
	 */
	@Override
	public List<ClientAccount> findAll() {

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
