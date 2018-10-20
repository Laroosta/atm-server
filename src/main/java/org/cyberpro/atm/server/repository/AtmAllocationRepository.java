package org.cyberpro.atm.server.repository;

import java.util.List;

import org.cyberpro.atm.server.entity.teller.AtmAllocation;
import org.springframework.data.repository.CrudRepository;

public interface AtmAllocationRepository extends CrudRepository<AtmAllocation, Long> {

	List<AtmAllocation> findAll();

}
