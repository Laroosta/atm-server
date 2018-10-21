package org.cyberpro.atm.server.service;

import org.cyberpro.atm.server.entity.account.ClientAccount;
import org.cyberpro.atm.server.pojo.WithdrawRequest;
import org.cyberpro.atm.server.pojo.WithdrawResponse;

/**
 * @author lmichelson
 *
 */
public interface IWithrawalTrxService {

	/**
	 * @param requestBody
	 * @param clientAccount
	 * @return
	 * @throws Exception
	 */
	public WithdrawResponse makeWithdrawal(WithdrawRequest requestBody, ClientAccount clientAccount) throws Exception;

}
