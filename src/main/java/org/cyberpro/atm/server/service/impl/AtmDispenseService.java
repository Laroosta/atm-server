package org.cyberpro.atm.server.service.impl;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.cyberpro.atm.server.entity.teller.AtmAllocation;
import org.cyberpro.atm.server.exception.NotEnoughNotesAvailableException;
import org.cyberpro.atm.server.service.IAtmDispenseService;

/**
 * @author lmichelson
 *
 */
public class AtmDispenseService implements IAtmDispenseService {

	private List<AtmAllocation> atmAllocationList;

	/**
	 * On instantiation, sorts the atmAllocation list by note value from highest to
	 * lowest
	 * 
	 * @param atmAllocationList
	 */
	public AtmDispenseService(List<AtmAllocation> atmAllocationList) {
		this.atmAllocationList = atmAllocationList.stream()
				.sorted(Comparator.comparing(AtmAllocation::getDenomination, (o1, o2) -> {
					return o1.getValue().compareTo(o2.getValue());
				}).reversed()).collect(Collectors.toList());
	}

	/**
	 * @param amount
	 * @return
	 * @throws NotEnoughNotesAvailableException
	 */
	public Map<BigDecimal, Integer> calculateDenominations(BigDecimal amount) throws NotEnoughNotesAvailableException {
		Map<BigDecimal, Integer> result = new HashMap<>();

		if (amount.doubleValue() == 0.00) {
			return result;
		}

		BigDecimal remainingAmount = new BigDecimal(amount.toString());
		BigDecimal availableAmount = new BigDecimal("0.00");

		for (AtmAllocation atmAllocation : atmAllocationList) {
			if (remainingAmount.doubleValue() == 0.00) {
				break;
			}

			BigDecimal noteValue = atmAllocation.getDenomination().getValue();
			Integer availableNotes = atmAllocation.getCount();
			availableAmount = availableAmount.add(noteValue.multiply(new BigDecimal(availableNotes)));

			int numOfNotes = countNumberOfNotes(remainingAmount, noteValue);

			int deducedNotes = availableNotes - numOfNotes <= 0 ? availableNotes : numOfNotes;

			if (deducedNotes > 0) {
				result.put(noteValue, deducedNotes);
				remainingAmount = remainingAmount.subtract(noteValue.multiply(new BigDecimal(deducedNotes)));
			}
		}

		if (remainingAmount.doubleValue() > 0.00) {
			throw new NotEnoughNotesAvailableException(availableAmount.doubleValue());
		}

		return result;
	}

	/**
	 * @param amount
	 * @param noteValue
	 * @return
	 */
	private int countNumberOfNotes(BigDecimal amount, BigDecimal noteValue) {
		int count = 0;

		while (remainingIsGreaterThan(amount, noteValue)) {
			amount = amount.subtract(noteValue);
			count++;
		}

		return count;
	}

	/**
	 * @param remainingAmount
	 * @param noteValue
	 * @return
	 */
	private boolean remainingIsGreaterThan(BigDecimal remainingAmount, BigDecimal noteValue) {
		return remainingAmount.compareTo(noteValue) >= 0;
	}

}
