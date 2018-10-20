package org.cyberpro.atm.server.service;

import java.util.List;

import org.cyberpro.atm.server.entity.teller.AutomatedTellerMachine;

public interface IAtmService {
	public List<AutomatedTellerMachine> getAll();
}
