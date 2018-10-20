package org.cyberpro.atm.server.repository;

import java.util.List;

import org.cyberpro.atm.server.entity.account.ClientAccount;
import org.springframework.data.repository.CrudRepository;

/**
 * @author lmichelson
 *
 */
public interface ClientAccountRepository extends CrudRepository<ClientAccount, Long> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<ClientAccount> findAll();

	/**
	 * @param accountNumber
	 * @return
	 */
	ClientAccount findByClientAccountNumber(String accountNumber);

	/**
	 * @param clientId
	 * @return
	 */
	List<ClientAccount> findByClient_ClientId(Integer clientId);

	/**
	 * @param clientId
	 * @return
	 */
	List<ClientAccount> findByClient_ClientIdOrderByDisplayBalanceDesc(Integer clientId);

	/**
	 * @param clientId
	 * @return
	 */
	List<ClientAccount> findByClient_ClientIdOrderByDisplayBalanceAsc(Integer clientId);
}
