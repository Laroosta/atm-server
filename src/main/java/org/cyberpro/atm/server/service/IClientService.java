package org.cyberpro.atm.server.service;

import java.util.List;

import org.cyberpro.atm.server.entity.client.Client;

/**
 * @author lmichelson
 *
 */
public interface IClientService {
	/**
	 * @return
	 */
	public List<Client> getAll();

	/**
	 * @param clientId
	 * @return
	 */
	public Client findByClientId(Integer clientId);
}
