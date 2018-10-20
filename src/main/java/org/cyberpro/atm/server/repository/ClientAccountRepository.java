package org.cyberpro.atm.server.repository;

import java.util.List;

import org.cyberpro.atm.server.entity.client.ClientAccount;
import org.springframework.data.repository.CrudRepository;

public interface ClientAccountRepository extends CrudRepository<ClientAccount, Long> {

	List<ClientAccount> findAll();

	ClientAccount findByClientAccountNumber(String accountNumber);

	List<ClientAccount> findByClient_ClientId(Integer clientId);

	List<ClientAccount> findByClient_ClientIdOrderByDisplayBalanceDesc(Integer clientId);
}
