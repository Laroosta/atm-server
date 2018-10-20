package org.cyberpro.atm.server.controller;

import java.util.List;

import org.cyberpro.atm.server.builder.account.ClientAccountRequestBuilder;
import org.cyberpro.atm.server.builder.account.CurrencyAccountBalanceRequestBuilder;
import org.cyberpro.atm.server.builder.account.TrxAccountBalanceRequestBuilder;
import org.cyberpro.atm.server.entity.account.ClientAccount;
import org.cyberpro.atm.server.pojo.CurrencyAccountBalance;
import org.cyberpro.atm.server.pojo.TransactionalAccountBalance;
import org.cyberpro.atm.server.service.impl.ClientAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	/**
	 * @param clientId
	 * @param orderBy
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	public ResponseEntity<List<ClientAccount>> accounts(
			@RequestParam(value = "clientId", required = false) Integer clientId,
			@RequestParam(value = "order", required = false) String orderBy) throws Exception {
		log.info("+---------------------------------------------+");
		log.info("+ accounts()");

		ClientAccountRequestBuilder builder = new ClientAccountRequestBuilder(clientAccountService);
		if (clientId != null) {
			builder.byClientId(clientId);
		}

		if (orderBy != null) {
			builder.byOrder(orderBy);
		}

		List<ClientAccount> list = builder.send();

		log.info("+---------------------------------------------+");

		if (list.isEmpty()) {
			throw new Exception("No accounts to display");
		}

		return ResponseEntity.ok().body(list);
	}

	/**
	 * @param accountNumber
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/account/{accountNumber}", method = RequestMethod.GET)
	public ClientAccount account(@PathVariable("accountNumber") String accountNumber) throws Exception {
		log.info("+---------------------------------------------+");
		log.info("+ account(" + accountNumber + ")");

		ClientAccount response = clientAccountService.findByAccountNumber(accountNumber);

		if (response == null) {
			throw new Exception("No accounts to display");
		}

		log.info("+---------------------------------------------+");

		return response;
	}

	/**
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

		TrxAccountBalanceRequestBuilder builder = new TrxAccountBalanceRequestBuilder(clientAccountService);
		if (clientId != null) {
			builder.byClientId(clientId);
		}

		if (orderBy != null) {
			builder.byOrder(orderBy);
		}

		List<TransactionalAccountBalance> list = builder.send();

		log.info("+---------------------------------------------+");

		if (list.isEmpty()) {
			throw new Exception("No accounts to display");
		}

		return ResponseEntity.ok().body(list);
	}

	/**
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

		CurrencyAccountBalanceRequestBuilder builder = new CurrencyAccountBalanceRequestBuilder(clientAccountService);
		if (clientId != null) {
			builder.byClientId(clientId);
		}

		if (orderBy != null) {
			builder.byOrder(orderBy);
		}

		List<CurrencyAccountBalance> list = builder.send();

		log.info("+---------------------------------------------+");

		if (list.isEmpty()) {
			throw new Exception("No accounts to display");
		}

		return ResponseEntity.ok().body(list);
	}

}