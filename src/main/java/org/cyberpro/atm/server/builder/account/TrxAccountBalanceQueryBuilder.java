package org.cyberpro.atm.server.builder.account;

import java.util.List;

import org.cyberpro.atm.server.builder.AbstractQueryBuilder;
import org.cyberpro.atm.server.pojo.TransactionalAccountBalance;
import org.cyberpro.atm.server.service.impl.ClientAccountService;

/**
 * @author lmichelson
 *
 */
public class TrxAccountBalanceQueryBuilder extends AbstractQueryBuilder<TransactionalAccountBalance> {

	ClientAccountService service;

	private Integer clientId;

	/**
	 * @param service
	 */
	public TrxAccountBalanceQueryBuilder(ClientAccountService service) {
		this.service = service;
	}

	/**
	 * @param clientId
	 * @return
	 */
	public TrxAccountBalanceQueryBuilder byClientId(Integer clientId) {
		this.clientId = clientId;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cyberpro.atm.server.builder.IRequestBuilder#send()
	 */
	@Override
	public List<TransactionalAccountBalance> findAll() {
		return invokeServiceByOrder(clientId);
	}

	/**
	 * @param clientId
	 * @return
	 */
	private List<TransactionalAccountBalance> invokeServiceByOrder(Integer clientId) {
		if ("ASC".equalsIgnoreCase(this.order)) {
			return service.findClientTrxAccountBalancesOrderByAsc(clientId);
		}

		return service.findClientTrxAccountBalancesOrderByDesc(clientId);
	}

}
