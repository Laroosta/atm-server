package org.cyberpro.atm.server.repository;

import java.util.List;

import org.cyberpro.atm.server.entity.client.Client;
import org.springframework.data.repository.CrudRepository;

/**
 * @author lmichelson
 *
 */
public interface ClientRepository extends CrudRepository<Client, Long> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Client> findAll();

	/**
	 * @param clientId
	 * @return
	 */
	Client findByClientId(Integer clientId);
}
