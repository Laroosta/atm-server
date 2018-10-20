package org.cyberpro.atm.server.repository;

import java.util.List;

import org.cyberpro.atm.server.entity.teller.AutomatedTellerMachine;
import org.springframework.data.repository.CrudRepository;

public interface AtmRepository extends CrudRepository<AutomatedTellerMachine, Long> {

	List<AutomatedTellerMachine> findAll();

}
