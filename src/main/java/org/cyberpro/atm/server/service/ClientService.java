package org.cyberpro.atm.server.service;

import java.util.List;

import org.cyberpro.atm.server.Application;
import org.cyberpro.atm.server.entity.client.Client;
import org.cyberpro.atm.server.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements IClientService {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	private ClientRepository repository;

	public List<Client> getAll() {
		log.info("+---------------------------------------------+");
		log.info("+ Client: Get list of all Clients");
		log.info("+---------------------------------------------+");

		List<Client> list = repository.findAll();

		log.info("+ Found : " + list.size() + " Client");
		log.info("+---------------------------------------------+");

		return list;
	}

	public Client findByClientId(Integer clientId) {
		log.info("+---------------------------------------------+");
		log.info("+ Found Client : " + clientId);
		log.info("+---------------------------------------------+");

		Client account = repository.findByClientId(clientId);

		return account;
	}

}
