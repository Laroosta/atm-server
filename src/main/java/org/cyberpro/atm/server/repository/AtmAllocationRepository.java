package org.cyberpro.atm.server.repository;

import java.util.List;

import org.cyberpro.atm.server.entity.teller.AtmAllocation;
import org.springframework.data.repository.CrudRepository;

/**
 * @author lmichelson
 *
 */
public interface AtmAllocationRepository extends CrudRepository<AtmAllocation, Long> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<AtmAllocation> findAll();

	List<AtmAllocation> findByAtm_AtmId(Integer atmId);

}
