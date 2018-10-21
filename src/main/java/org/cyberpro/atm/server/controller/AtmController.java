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

/**
 * @author lmichelson
 *
 */
@RestController
public class AtmController extends AbstractApiController {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	AtmService atmService;

	@Autowired
	AtmAllocationService atmAllocService;

	/**
	 * @return
	 */
	@RequestMapping(value = "/server/atm", method = RequestMethod.GET)
	public List<AutomatedTellerMachine> atm() {
		log.info("+---------------------------------------------+");
		log.info("+ Calling ATM Resource");

		List<AutomatedTellerMachine> response = atmService.getAll();

		log.info("+---------------------------------------------+");

		return response;
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/server/allocation", method = RequestMethod.GET)
	public List<AtmAllocation> allocation() {
		log.info("+---------------------------------------------+");
		log.info("+ Calling ATM Allocation Resource");

		List<AtmAllocation> response = atmAllocService.getAll();

		log.info("+---------------------------------------------+");

		return response;
	}

}