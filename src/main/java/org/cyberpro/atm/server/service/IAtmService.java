package org.cyberpro.atm.server.service;

import java.util.List;

import org.cyberpro.atm.server.entity.teller.AutomatedTellerMachine;

/**
 * @author lmichelson
 *
 */
public interface IAtmService {
	/**
	 * @return
	 */
	public List<AutomatedTellerMachine> getAll();
}
