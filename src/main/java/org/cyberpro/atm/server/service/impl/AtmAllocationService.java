package org.cyberpro.atm.server.service.impl;

import java.util.List;

import org.cyberpro.atm.server.Application;
import org.cyberpro.atm.server.entity.teller.AtmAllocation;
import org.cyberpro.atm.server.repository.AtmAllocationRepository;
import org.cyberpro.atm.server.service.IAtmAllocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lmichelson
 *
 */
@Service
public class AtmAllocationService implements IAtmAllocationService {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	private AtmAllocationRepository repository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cyberpro.atm.server.service.IAtmAllocationService#getAll()
	 */
	public List<AtmAllocation> getAll() {
		log.info("+---------------------------------------------+");
		log.info("+ AtmAllocationService: Get list of all ATMs");

		List<AtmAllocation> list = repository.findAll();

		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cyberpro.atm.server.service.IAtmAllocationService#getAll()
	 */
	public List<AtmAllocation> findByAtm_AtmId(Integer atmId) {
		log.info("+---------------------------------------------+");
		log.info("+ AtmAllocationService: Get list of all ATMs");

		List<AtmAllocation> list = repository.findByAtm_AtmId(atmId);

		return list;
	}

}
