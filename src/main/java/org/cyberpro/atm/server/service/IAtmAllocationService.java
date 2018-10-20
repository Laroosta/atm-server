package org.cyberpro.atm.server.service;

import java.util.List;

import org.cyberpro.atm.server.entity.teller.AtmAllocation;

public interface IAtmAllocationService {
	public List<AtmAllocation> getAll();
}
