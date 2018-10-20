package org.cyberpro.atm.server.repository;

import java.util.List;

import org.cyberpro.atm.server.entity.teller.AutomatedTellerMachine;
import org.springframework.data.repository.CrudRepository;

/**
 * @author lmichelson
 *
 */
public interface AtmRepository extends CrudRepository<AutomatedTellerMachine, Long> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<AutomatedTellerMachine> findAll();

}
