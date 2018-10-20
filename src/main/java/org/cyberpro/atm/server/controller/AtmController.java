package org.cyberpro.atm.server.controller;

import java.util.List;

import org.cyberpro.atm.server.Application;
import org.cyberpro.atm.server.entity.teller.AtmAllocation;
import org.cyberpro.atm.server.entity.teller.AutomatedTellerMachine;
import org.cyberpro.atm.server.service.impl.AtmAllocationService;
import org.cyberpro.atm.server.service.impl.AtmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AtmController extends AbstractApiController {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	AtmService atmService;

	@Autowired
	AtmAllocationService atmAllocService;

	@RequestMapping(value = "/atm", method = RequestMethod.GET)
	public List<AutomatedTellerMachine> atm() {
		log.info("+---------------------------------------------+");
		log.info("+ Calling ATM Resource");
		log.info("+---------------------------------------------+");

		List<AutomatedTellerMachine> response = atmService.getAll();

		log.info("+ Result Size" + response.size());
		log.info("+---------------------------------------------+");

		return response;
	}

	@RequestMapping(value = "/allocation", method = RequestMethod.GET)
	public List<AtmAllocation> allocation() {
		log.info("+---------------------------------------------+");
		log.info("+ Calling ATM Allocation Resource");
		log.info("+---------------------------------------------+");

		List<AtmAllocation> response = atmAllocService.getAll();

		log.info("+ Result Size" + response.size());
		log.info("+---------------------------------------------+");

		return response;
	}

}