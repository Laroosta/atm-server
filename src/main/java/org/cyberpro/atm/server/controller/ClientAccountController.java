package org.cyberpro.atm.server.controller;

import java.util.List;

import org.cyberpro.atm.server.entity.client.ClientAccount;
import org.cyberpro.atm.server.service.ClientAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientAccountController extends AbstractApiController {

	private static final Logger log = LoggerFactory.getLogger(ClientAccountController.class);

	@Autowired
	ClientAccountService clientAccountService;

	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	public List<ClientAccount> accounts() {
		log.info("+---------------------------------------------+");
		log.info("+ accounts()");
		log.info("+---------------------------------------------+");

		List<ClientAccount> response = clientAccountService.getAll();

		log.info("+ Result Size" + response.size());
		log.info("+---------------------------------------------+");

		return response;
	}

	@RequestMapping(value = "/account/{accountNumber}", method = RequestMethod.GET)
	public ClientAccount account(@PathVariable("accountNumber") String accountNumber) {
		log.info("+---------------------------------------------+");
		log.info("+ account(" + accountNumber + ")");
		log.info("+---------------------------------------------+");

		ClientAccount response = clientAccountService.findByAccountNumber(accountNumber);

		log.info("+ Found " + response.getClientAccountNumber());
		log.info("+---------------------------------------------+");

		return response;
	}

	@RequestMapping(value = "/accounts/client/{clientId}", method = RequestMethod.GET)
	public List<ClientAccount> account(@PathVariable("clientId") Integer clientId) {
		log.info("+---------------------------------------------+");
		log.info("+ account(" + clientId + ")");
		log.info("+---------------------------------------------+");

		List<ClientAccount> response = clientAccountService.findByClientId(clientId);

		log.info("+ Result Size" + response.size());
		log.info("+---------------------------------------------+");

		return response;
	}

}