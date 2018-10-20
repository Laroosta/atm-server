package org.cyberpro.atm.server.service;

import java.util.List;

import org.cyberpro.atm.server.entity.account.ClientAccount;

/**
 * @author lmichelson
 *
 */
public interface IClientAccountService {
	/**
	 * @return
	 */
	public List<ClientAccount> findAll();

	/**
	 * @param accountNumber
	 * @return
	 */
	public ClientAccount findByAccountNumber(String accountNumber);
}
