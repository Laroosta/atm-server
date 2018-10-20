package org.cyberpro.atm.server.repository;

import java.util.List;

import org.cyberpro.atm.server.entity.client.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {

	List<Client> findAll();

	Client findByClientId(Integer clientId);
}
