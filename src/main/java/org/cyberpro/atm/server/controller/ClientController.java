package org.cyberpro.atm.server.controller;

import java.util.List;

import org.cyberpro.atm.server.entity.client.Client;
import org.cyberpro.atm.server.service.impl.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lmichelson
 *
 */
@RestController
public class ClientController extends AbstractApiController {

	private static final Logger log = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	ClientService clientService;

	/**
	 * Finds all clients
	 * 
	 * @return
	 */
	@RequestMapping(value = "/server/clients", method = RequestMethod.GET)
	public List<Client> clients() {
		log.info("+---------------------------------------------+");
		log.info("+ clients()");

		List<Client> response = clientService.getAll();

		log.info("+---------------------------------------------+");

		return response;
	}

	/**
	 * Finds a client by its clientId
	 * 
	 * @param clientId
	 * @return
	 */
	@RequestMapping(value = "/server/client/{clientId}", method = RequestMethod.GET)
	public Client client(@PathVariable("clientId") Integer clientId) {
		log.info("+---------------------------------------------+");
		log.info("+ client(" + clientId + ")");

		Client response = clientService.findByClientId(clientId);

		log.info("+---------------------------------------------+");

		return response;
	}

}