package org.cyberpro.atm.server.service;

import java.util.List;

import org.cyberpro.atm.server.Application;
import org.cyberpro.atm.server.entity.client.ClientAccount;
import org.cyberpro.atm.server.repository.ClientAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientAccountService implements IClientAccountService {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	private ClientAccountRepository repository;

	public List<ClientAccount> getAll() {
		log.info("+---------------------------------------------+");
		log.info("+ ClientAccount: Get list of all ClientAccounts");
		log.info("+---------------------------------------------+");

		List<ClientAccount> list = repository.findAll();

		log.info("+ Found : " + list.size() + " ClientAccounts");
		log.info("+---------------------------------------------+");

		return list;
	}

	public ClientAccount findByAccountNumber(String accountNumber) {
		log.info("+---------------------------------------------+");
		log.info("+ ClientAccount: findByAccountNumber " + accountNumber);
		log.info("+---------------------------------------------+");

		ClientAccount account = repository.findByClientAccountNumber(accountNumber);

		log.info("+ Found : " + account.getClientAccountNumber());
		log.info("+---------------------------------------------+");

		return account;
	}

	public List<ClientAccount> findByClientId(Integer clientId) {
		log.info("+---------------------------------------------+");
		log.info("+ ClientAccount: ClientAccount for client : " + clientId);
		log.info("+---------------------------------------------+");

		List<ClientAccount> list = repository.findByClient_ClientId(clientId);

		log.info("+ Found : " + list.size());
		log.info("+---------------------------------------------+");

		return list;
	}

	public List<ClientAccount> findByClient_ClientIdOrderByDisplayBalanceDesc(Integer clientId) {
		log.info("+---------------------------------------------+");
		log.info("+ ClientAccount: ClientAccount for client : " + clientId);
		log.info("+ Order Display Balance By: DESC : " + clientId);
		log.info("+---------------------------------------------+");

		List<ClientAccount> list = repository.findByClient_ClientIdOrderByDisplayBalanceDesc(clientId);

		log.info("+ Found : " + list.size());
		log.info("+---------------------------------------------+");

		return list;
	}

	public List<ClientAccount> findByClient_ClientIdOrderByDisplayBalanceAsc(Integer clientId) {
		log.info("+---------------------------------------------+");
		log.info("+ ClientAccount: ClientAccount for client : " + clientId);
		log.info("+ Order Display Balance By: ASC : " + clientId);
		log.info("+---------------------------------------------+");

		List<ClientAccount> list = repository.findByClient_ClientIdOrderByDisplayBalanceAsc(clientId);

		log.info("+ Found : " + list.size());
		log.info("+---------------------------------------------+");

		return list;
	}

}
