package org.cyberpro.atm.server.service.impl;

import java.util.List;

import org.cyberpro.atm.server.Application;
import org.cyberpro.atm.server.entity.teller.AutomatedTellerMachine;
import org.cyberpro.atm.server.repository.AtmRepository;
import org.cyberpro.atm.server.service.IAtmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lmichelson
 *
 */
@Service
public class AtmService implements IAtmService {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	private AtmRepository repository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cyberpro.atm.server.service.IAtmService#getAll()
	 */
	public List<AutomatedTellerMachine> getAll() {
		log.info("+---------------------------------------------+");
		log.info("+ AtmService: Get list of all ATMs");
		log.info("+---------------------------------------------+");

		List<AutomatedTellerMachine> list = repository.findAll();

		return list;
	}

}
