package org.cyberpro.atm.server.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.cyberpro.atm.server.entity.account.ClientAccount;
import org.cyberpro.atm.server.entity.teller.AtmAllocation;
import org.cyberpro.atm.server.exception.AtmNotFoundException;
import org.cyberpro.atm.server.exception.NotEnoughNotesAvailableException;
import org.cyberpro.atm.server.pojo.WithdrawRequest;
import org.cyberpro.atm.server.pojo.WithdrawResponse;
import org.cyberpro.atm.server.service.IWithrawalTrxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lmichelson
 *
 */
@Service
public class WithdrawalTrxService implements IWithrawalTrxService {

	private static final Logger log = LoggerFactory.getLogger(WithdrawalTrxService.class);

	@Autowired
	ClientAccountService clientAccountService;

	@Autowired
	AtmAllocationService atmAllocationService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cyberpro.atm.server.service.IWithrawalTrxService#makeWithdrawal(org.
	 * cyberpro.atm.server.pojo.WithdrawRequest,
	 * org.cyberpro.atm.server.entity.account.ClientAccount)
	 */
	@Override
	@Transactional
	public WithdrawResponse makeWithdrawal(WithdrawRequest requestBody, ClientAccount clientAccount)
			throws AtmNotFoundException, NotEnoughNotesAvailableException, Exception {
		log.info("+ New Transaction Started");

		final BigDecimal amount = requestBody.getAmount();

		WithdrawalService withdrawalService = new WithdrawalService(clientAccount);

		// Performs checks to ensure the account can be withdrawn from
		if (!withdrawalService.canWithdraw(amount)) {
			throw new Exception("Insufficient funds");
		}

		// Withdraws the amount and creates a copy of the account
		ClientAccount updatedClientAccount = withdrawalService.withdraw(amount);
		if (updatedClientAccount == null) {
			throw new Exception("Error deducting funds");
		}

		// Denominations need to be checked before calling the save
		// throws an exception if this fails
		Map<BigDecimal, Integer> denominations = fetchDenominationsForAtm(requestBody, amount);

		log.info("+ Updating Client Account " + updatedClientAccount.getClientAccountNumber());
		clientAccountService.save(updatedClientAccount);
		log.info("+ Client Account Updated Successfully " + updatedClientAccount.getClientAccountNumber());

		WithdrawResponse response = new WithdrawResponse();
		response.setStatus("OK");
		response.setAccountNumber(updatedClientAccount.getClientAccountNumber());
		response.setDenominations(denominations);
		response.setNewDisplayBalance(updatedClientAccount.getDisplayBalance());
		return response;
	}

	/**
	 * @param requestBody
	 * @param amount
	 * @return
	 */
	private Map<BigDecimal, Integer> fetchDenominationsForAtm(WithdrawRequest requestBody, final BigDecimal amount)
			throws NotEnoughNotesAvailableException, AtmNotFoundException {
		List<AtmAllocation> atmAllocations = fetchAtmAllocations(requestBody);

		AtmDispenseService atmDispenseService = new AtmDispenseService(atmAllocations);
		Map<BigDecimal, Integer> denominations = atmDispenseService.calculateDenominations(amount);
		return denominations;
	}

	/**
	 * @param requestBody
	 * @return
	 * @throws AtmNotFoundException
	 */
	private List<AtmAllocation> fetchAtmAllocations(WithdrawRequest requestBody) throws AtmNotFoundException {
		List<AtmAllocation> atmAllocations = atmAllocationService.findByAtm_AtmId(requestBody.getAtmId());

		if (atmAllocations.size() == 0) {
			throw new AtmNotFoundException();
		}
		return atmAllocations;
	}

}
