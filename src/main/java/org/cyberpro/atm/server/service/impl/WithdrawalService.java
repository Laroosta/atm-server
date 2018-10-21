package org.cyberpro.atm.server.service.impl;

import java.math.BigDecimal;

import org.cyberpro.atm.server.ApplicationConstants;
import org.cyberpro.atm.server.entity.account.ClientAccount;
import org.cyberpro.atm.server.service.IWithdrawalService;
import org.cyberpro.atm.server.util.AtmUtil;

/**
 * @author lmichelson
 *
 */
public class WithdrawalService implements IWithdrawalService {

	private ClientAccount clientAccount;

	private static final BigDecimal LIMIT = new BigDecimal("0.00");

	public WithdrawalService(ClientAccount clientAccount) {
		this.clientAccount = clientAccount;
	}

	public ClientAccount getClientAccount() {
		return clientAccount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.cyberpro.atm.server.service.IWithdrawalService#canWithdraw(java.math.
	 * BigDecimal)
	 */
	@Override
	public Boolean canWithdraw(BigDecimal amount) {

		if (isTransactional()) {
			return false;
		}

		if (containsCents(amount)) {
			return false;
		}

		if (isChequeAccount(getClientAccount()) && isWithinLimit(amount, ApplicationConstants.CHEQUE_ACCOUNT_LIMIT)) {
			return true;
		}

		if (isCreditCardAccount(getClientAccount())
				&& isWithinLimit(amount, clientAccount.getCreditCardLimit().getAccountLimit())) {
			return true;
		}

		if (isWithinLimit(amount, LIMIT)) {
			return true;
		}

		return false;
	}

	/**
	 * @param amount
	 * @return
	 */
	private boolean containsCents(BigDecimal amount) {
		BigDecimal devisor = new BigDecimal("10.00");

		if (amount.remainder(devisor).doubleValue() == 0.00) {
			return false;
		}

		return true;
	}

	/**
	 * @return
	 */
	private boolean isTransactional() {
		return !getClientAccount().getAccountType().getTransactional();
	}

	/**
	 * @param amount
	 * @param limit
	 * @return
	 */
	private boolean isWithinLimit(BigDecimal amount, BigDecimal limit) {
		return getClientAccount().getDisplayBalance().subtract(amount).compareTo(limit) == 1;
	}

	/**
	 * @param clientAccount
	 * @return
	 */
	private boolean isChequeAccount(ClientAccount clientAccount) {
		return ApplicationConstants.CHEQUE_ACCOUNT_TYPE_CODE
				.equalsIgnoreCase(clientAccount.getAccountType().getAccountTypeCode());
	}

	/**
	 * @param clientAccount
	 * @return
	 */
	private boolean isCreditCardAccount(ClientAccount clientAccount) {
		return ApplicationConstants.CREDITCARD_ACCOUNT_TYPE_CODE
				.equalsIgnoreCase(clientAccount.getAccountType().getAccountTypeCode());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cyberpro.atm.server.service.IWithdrawalService#withdraw(java.math.
	 * BigDecimal)
	 */
	@Override
	public ClientAccount withdraw(BigDecimal amount) {
		return deductFunds(amount);
	}

	/**
	 * @param amount
	 */
	private ClientAccount deductFunds(BigDecimal amount) {
		BigDecimal newValue = new BigDecimal(clientAccount.getDisplayBalance().toString());

		ClientAccount newClientAccount = (ClientAccount) AtmUtil.deepCopy(clientAccount);
		newClientAccount.setDisplayBalance(newValue.subtract(amount));
		return newClientAccount;
	}

}
