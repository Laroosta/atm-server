package org.cyberpro.atm.server.service;

import java.util.List;

import org.cyberpro.atm.server.entity.client.ClientAccount;

public interface IClientAccountService {
	public List<ClientAccount> getAll();

	public ClientAccount findByAccountNumber(String accountNumber);
}
