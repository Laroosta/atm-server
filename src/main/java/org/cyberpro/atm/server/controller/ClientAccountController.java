package org.cyberpro.atm.server.controller;

import java.util.List;

import org.cyberpro.atm.server.builder.account.ClientAccountQueryBuilder;
import org.cyberpro.atm.server.builder.account.CurrencyAccountBalanceQueryBuilder;
import org.cyberpro.atm.server.builder.account.TrxAccountBalanceQueryBuilder;
import org.cyberpro.atm.server.entity.account.ClientAccount;
import org.cyberpro.atm.server.pojo.CurrencyAccountBalance;
import org.cyberpro.atm.server.pojo.TransactionalAccountBalance;
import org.cyberpro.atm.server.pojo.WithdrawRequest;
import org.cyberpro.atm.server.pojo.WithdrawResponse;
import org.cyberpro.atm.server.service.impl.ClientAccountService;
import org.cyberpro.atm.server.service.impl.WithdrawalTrxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lmichelson
 *
 */
@RestController
public class ClientAccountController extends AbstractApiController {

	private static final Logger log = LoggerFactory.getLogger(ClientAccountController.class);

	@Autowired
	ClientAccountService clientAccountService;

	@Autowired
	WithdrawalTrxService withrawalTrxService;

	/**
	 * Find all accounts, filter by clientId, change order of sort by Display
	 * Balance. Use order to switch sorting ('ASC' or 'DESC') Default: 'DESC'
	 * 
	 * @param clientId
	 * @param orderBy
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/server/accounts", method = RequestMethod.GET)
	public ResponseEntity<List<ClientAccount>> accounts(
			@RequestParam(value = "clientId", required = false) Integer clientId,
			@RequestParam(value = "order", required = false) String orderBy) throws Exception {
		log.info("+---------------------------------------------+");
		log.info("+ accounts()");

		ClientAccountQueryBuilder queryBuilder = new ClientAccountQueryBuilder(clientAccountService);
		if (clientId != null) {
			queryBuilder.byClientId(clientId);
		}

		if (orderBy != null) {
			queryBuilder.byOrder(orderBy);
		}

		List<ClientAccount> list = queryBuilder.findAll();

		log.info("+---------------------------------------------+");

		if (list.isEmpty()) {
			throw new Exception("No accounts to display");
		}

		return ResponseEntity.ok().body(list);
	}

	/**
	 * Find accounts by account number
	 * 
	 * @param accountNumber
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/server/account/{accountNumber}", method = RequestMethod.GET)
	public ResponseEntity<ClientAccount> account(@PathVariable("accountNumber") String accountNumber) throws Exception {
		log.info("+---------------------------------------------+");
		log.info("+ account(" + accountNumber + ")");

		ClientAccountQueryBuilder queryBuilder = new ClientAccountQueryBuilder(clientAccountService);

		ClientAccount result = queryBuilder.byAccountNumber(accountNumber);

		if (result == null) {
			throw new Exception("No accounts to display");
		}

		log.info("+---------------------------------------------+");

		return ResponseEntity.ok().body(result);
	}

	/**
	 * Find all Transactional balances by clientId sorted by Display Balance. Use
	 * order to switch sorting ('ASC' or 'DESC') Default: 'DESC'
	 * 
	 * @param clientId
	 * @param orderBy
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/atm/accounts/client/{clientId}/trx", method = RequestMethod.GET)
	public ResponseEntity<List<TransactionalAccountBalance>> trxAccounts(@PathVariable("clientId") Integer clientId,
			@RequestParam(value = "order", required = false) String orderBy) throws Exception {
		log.info("+---------------------------------------------+");
		log.info("+ trxAccounts()");

		TrxAccountBalanceQueryBuilder queryBuilder = new TrxAccountBalanceQueryBuilder(clientAccountService);
		if (clientId != null) {
			queryBuilder.byClientId(clientId);
		}

		if (orderBy != null) {
			queryBuilder.byOrder(orderBy);
		}

		List<TransactionalAccountBalance> list = queryBuilder.findAll();

		log.info("+---------------------------------------------+");

		if (list.isEmpty()) {
			throw new Exception("No accounts to display");
		}

		return ResponseEntity.ok().body(list);
	}

	/**
	 * Find all Currency balances by clientId sorted by ZAR Currency Balance. Use
	 * order to switch sorting ('ASC' or 'DESC') Default: 'DESC'
	 * 
	 * @param clientId
	 * @param orderBy
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/atm/accounts/client/{clientId}/currency", method = RequestMethod.GET)
	public ResponseEntity<List<CurrencyAccountBalance>> currencyAccounts(@PathVariable("clientId") Integer clientId,
			@RequestParam(value = "order", required = false) String orderBy) throws Exception {
		log.info("+---------------------------------------------+");
		log.info("+ currencyAccounts()");

		CurrencyAccountBalanceQueryBuilder queryBuilder = new CurrencyAccountBalanceQueryBuilder(clientAccountService);
		if (clientId != null) {
			queryBuilder.byClientId(clientId);
		}

		if (orderBy != null) {
			queryBuilder.byOrder(orderBy);
		}

		List<CurrencyAccountBalance> list = queryBuilder.findAll();

		log.info("+---------------------------------------------+");

		if (list.isEmpty()) {
			throw new Exception("No accounts to display");
		}

		return ResponseEntity.ok().body(list);
	}

	/**
	 * POST Resource to allow a client to withdraw a specified amount from an ATM
	 * 
	 * @param clientId
	 * @param requestBody
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/atm/accounts/client/withdraw", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public ResponseEntity<WithdrawResponse> withdraw(@RequestBody WithdrawRequest requestBody) throws Exception {
		final Integer clientId = requestBody.getClientId();
		final String accountNumber = requestBody.getAccountNumber();
		log.info("+---------------------------------------------+");
		log.info("+ withdraw(" + clientId + ")");
		log.info("+ accountNumber : " + accountNumber);

		ClientAccountQueryBuilder queryBuilder = new ClientAccountQueryBuilder(clientAccountService);
		ClientAccount clientAccount = queryBuilder.byAccountNumber(accountNumber);

		if (clientAccount == null) {
			throw new Exception("Account not found");
		}

		if (clientAccount.getClient().getClientId() != clientId) {
			throw new Exception("ClientId does not match Account Number");
		}

		WithdrawResponse response = withrawalTrxService.makeWithdrawal(requestBody, clientAccount);

		return ResponseEntity.ok().body(response);
	}

}