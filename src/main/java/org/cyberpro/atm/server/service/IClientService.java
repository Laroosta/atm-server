package org.cyberpro.atm.server.service;

import java.util.List;

import org.cyberpro.atm.server.entity.client.Client;

public interface IClientService {
	public List<Client> getAll();

	public Client findByClientId(Integer clientId);
}
