package org.cyberpro.atm.server.controller;

import java.util.List;

import org.cyberpro.atm.server.builder.account.ClientAccountRequestBuilder;
import org.cyberpro.atm.server.entity.account.ClientAccount;
import org.cyberpro.atm.server.service.ClientAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientAccountController extends AbstractApiController {

	private static final Logger log = LoggerFactory.getLogger(ClientAccountController.class);

	@Autowired
	ClientAccountService clientAccountService;

	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	public ResponseEntity<List<ClientAccount>> accounts(
			@RequestParam(value = "clientId", required = false) Integer clientId,
			@RequestParam(value = "order", required = false) String orderBy) throws Exception {
		log.info("+---------------------------------------------+");
		log.info("+ accounts()");
		log.info("+---------------------------------------------+");

		ClientAccountRequestBuilder builder = new ClientAccountRequestBuilder(clientAccountService);
		if (clientId != null) {
			builder.byClientId(clientId);
		}

		if (orderBy != null) {
			builder.byOrder(orderBy);
		}

		List<ClientAccount> list = builder.send();

		log.info("+ Result Size" + list.size());
		log.info("+---------------------------------------------+");

		if (list.isEmpty()) {
			throw new Exception("No accounts to display");
		}

		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "/account/{accountNumber}", method = RequestMethod.GET)
	public ClientAccount account(@PathVariable("accountNumber") String accountNumber) throws Exception {
		log.info("+---------------------------------------------+");
		log.info("+ account(" + accountNumber + ")");
		log.info("+---------------------------------------------+");

		ClientAccount response = clientAccountService.findByAccountNumber(accountNumber);

		if (response == null) {
			throw new Exception("No accounts to display");
		}

		log.info("+ Found " + response.getClientAccountNumber());
		log.info("+---------------------------------------------+");

		return response;
	}

}