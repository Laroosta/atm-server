package org.cyberpro.atm.server.service;

import java.math.BigDecimal;

import org.cyberpro.atm.server.entity.account.ClientAccount;

/**
 * @author lmichelson
 *
 */
public interface IWithdrawalService {

	/**
	 * @param amount
	 * @return
	 */
	public Boolean canWithdraw(BigDecimal amount);

	/**
	 * @param amount
	 * @return
	 */
	public ClientAccount withdraw(BigDecimal amount);

}
