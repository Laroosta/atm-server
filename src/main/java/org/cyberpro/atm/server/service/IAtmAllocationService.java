package org.cyberpro.atm.server.service;

import java.util.List;

import org.cyberpro.atm.server.entity.teller.AtmAllocation;

/**
 * @author lmichelson
 *
 */
public interface IAtmAllocationService {
	/**
	 * @return
	 */
	public List<AtmAllocation> getAll();
}
