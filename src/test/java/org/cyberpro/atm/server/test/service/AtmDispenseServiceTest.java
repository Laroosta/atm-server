package org.cyberpro.atm.server.test.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.cyberpro.atm.server.entity.teller.AtmAllocation;
import org.cyberpro.atm.server.entity.teller.Denomination;
import org.cyberpro.atm.server.exception.NotEnoughNotesAvailableException;
import org.cyberpro.atm.server.service.impl.AtmDispenseService;
import org.junit.Before;
import org.junit.Test;

/**
 * @author lmichelson
 *
 */
public class AtmDispenseServiceTest {

	AtmDispenseService atmDispenseService;

	@Before
	public void init() {
		List<AtmAllocation> atmAllocationList = new ArrayList<>();

		Map<Integer, Integer> dummyData = new HashMap<>();
		dummyData.put(10, 10);
		dummyData.put(20, 10);
		dummyData.put(50, 10);
		dummyData.put(100, 10);
		dummyData.put(200, 10);

		for (Entry<Integer, Integer> entry : dummyData.entrySet()) {
			AtmAllocation atmAllocation = new AtmAllocation();
			atmAllocation.setCount(entry.getValue());
			Denomination denomination = new Denomination();
			denomination.setValue(new BigDecimal(entry.getKey().doubleValue()));
			atmAllocation.setDenomination(denomination);

			atmAllocationList.add(atmAllocation);
		}

		atmDispenseService = new AtmDispenseService(atmAllocationList);
	}

	@Test
	public void calculateDenominations_amountZero_shouldDenominateToZero() throws Exception {
		BigDecimal amount = new BigDecimal("0.00");
		Map<BigDecimal, Integer> result = atmDispenseService.calculateDenominations(amount);

		assertEquals(0, result.size());
	}

	@Test
	public void calculateDenominations_amountOneHundred_shouldOnlyHaveOneDenomination() throws Exception {
		BigDecimal amount = new BigDecimal("100.00");
		Map<BigDecimal, Integer> result = atmDispenseService.calculateDenominations(amount);

		assertEquals(1, result.size());
	}

	@Test
	public void calculateDenominations_amountOneHundred_shouldDenominateToOne_OneHundredNote() throws Exception {
		BigDecimal amount = new BigDecimal("100.00");
		Map<BigDecimal, Integer> result = atmDispenseService.calculateDenominations(amount);

		BigDecimal hundredNote = new BigDecimal("100");

		assertEquals(1, result.get(hundredNote).intValue());
	}

	@Test
	public void calculateDenominations_amountOneHundredAndFifty_shouldDenominateToOneHundredNoteAndOneFiftyNote()
			throws Exception {
		BigDecimal amount = new BigDecimal("150.00");
		Map<BigDecimal, Integer> result = atmDispenseService.calculateDenominations(amount);

		BigDecimal hundredNote = new BigDecimal("100");
		BigDecimal fiftyNote = new BigDecimal("50");

		assertEquals(1, result.get(hundredNote).intValue());
		assertEquals(1, result.get(fiftyNote).intValue());
	}

	@Test
	public void calculateDenominations_amountThreeHundredAndFifty_shouldDenominateToOne_TwoHundredNoteOne_HundredNoteAndOneFiftyNote()
			throws Exception {
		BigDecimal amount = new BigDecimal("350.00");
		Map<BigDecimal, Integer> result = atmDispenseService.calculateDenominations(amount);

		BigDecimal twoHundredNote = new BigDecimal("200");
		BigDecimal hundredNote = new BigDecimal("100");
		BigDecimal fiftyNote = new BigDecimal("50");

		assertEquals(1, result.get(twoHundredNote).intValue());
		assertEquals(1, result.get(hundredNote).intValue());
		assertEquals(1, result.get(fiftyNote).intValue());
	}

	@Test
	public void calculateDenominations_amountThreeHundredAndFiftyWhenAtmReachedNoteLimit_shouldDenominateToThree_OneHundredNotesAndOneFiftyNote()
			throws Exception {
		List<AtmAllocation> atmAllocationList = new ArrayList<>();

		Map<Integer, Integer> dummyData = new HashMap<>();
		dummyData.put(10, 10);
		dummyData.put(20, 10);
		dummyData.put(50, 10);
		dummyData.put(100, 10);
		dummyData.put(200, 0);

		for (Entry<Integer, Integer> entry : dummyData.entrySet()) {
			AtmAllocation atmAllocation = new AtmAllocation();
			atmAllocation.setCount(entry.getValue());
			Denomination denomination = new Denomination();
			denomination.setValue(new BigDecimal(entry.getKey().doubleValue()));
			atmAllocation.setDenomination(denomination);

			atmAllocationList.add(atmAllocation);
		}

		atmDispenseService = new AtmDispenseService(atmAllocationList);

		BigDecimal amount = new BigDecimal("350.00");
		Map<BigDecimal, Integer> result = atmDispenseService.calculateDenominations(amount);

		BigDecimal hundredNote = new BigDecimal("100");
		BigDecimal fiftyNote = new BigDecimal("50");

		assertEquals(3, result.get(hundredNote).intValue());
		assertEquals(1, result.get(fiftyNote).intValue());
	}

	@Test
	public void calculateDenominations_amountThreeHundredAndEightyWhenAtmReachedNoteLimit_shouldDenominateToTwoHundredOneHundredFiftyOneTwentyOneTen()
			throws Exception {
		List<AtmAllocation> atmAllocationList = new ArrayList<>();

		Map<Integer, Integer> dummyData = new HashMap<>();
		dummyData.put(10, 1);
		dummyData.put(20, 1);
		dummyData.put(50, 1);
		dummyData.put(100, 1);
		dummyData.put(200, 1);

		for (Entry<Integer, Integer> entry : dummyData.entrySet()) {
			AtmAllocation atmAllocation = new AtmAllocation();
			atmAllocation.setCount(entry.getValue());
			Denomination denomination = new Denomination();
			denomination.setValue(new BigDecimal(entry.getKey().doubleValue()));
			atmAllocation.setDenomination(denomination);

			atmAllocationList.add(atmAllocation);
		}

		atmDispenseService = new AtmDispenseService(atmAllocationList);

		BigDecimal amount = new BigDecimal("380.00");
		Map<BigDecimal, Integer> result = atmDispenseService.calculateDenominations(amount);

		BigDecimal twoHundredNote = new BigDecimal("200");
		BigDecimal hundredNote = new BigDecimal("100");
		BigDecimal fiftyNote = new BigDecimal("50");
		BigDecimal twentyNote = new BigDecimal("20");
		BigDecimal tenNote = new BigDecimal("10");

		assertEquals(1, result.get(twoHundredNote).intValue());
		assertEquals(1, result.get(hundredNote).intValue());
		assertEquals(1, result.get(fiftyNote).intValue());
		assertEquals(1, result.get(twentyNote).intValue());
		assertEquals(1, result.get(tenNote).intValue());
	}

	@Test
	public void calculateDenominations_amountOnThousandFiveHundred_shouldDispenseEntireBank() throws Exception {
		List<AtmAllocation> atmAllocationList = new ArrayList<>();

		Map<Integer, Integer> dummyData = new HashMap<>();
		dummyData.put(10, 50);
		dummyData.put(20, 5);
		dummyData.put(50, 4);
		dummyData.put(100, 3);
		dummyData.put(200, 2);

		for (Entry<Integer, Integer> entry : dummyData.entrySet()) {
			AtmAllocation atmAllocation = new AtmAllocation();
			atmAllocation.setCount(entry.getValue());
			Denomination denomination = new Denomination();
			denomination.setValue(new BigDecimal(entry.getKey().doubleValue()));
			atmAllocation.setDenomination(denomination);

			atmAllocationList.add(atmAllocation);
		}

		atmDispenseService = new AtmDispenseService(atmAllocationList);

		BigDecimal amount = new BigDecimal("1500.00");
		Map<BigDecimal, Integer> result = atmDispenseService.calculateDenominations(amount);

		BigDecimal twoHundredNote = new BigDecimal("200");
		BigDecimal hundredNote = new BigDecimal("100");
		BigDecimal fiftyNote = new BigDecimal("50");
		BigDecimal twentyNote = new BigDecimal("20");
		BigDecimal tenNote = new BigDecimal("10");

		assertEquals(2, result.get(twoHundredNote).intValue());
		assertEquals(3, result.get(hundredNote).intValue());
		assertEquals(4, result.get(fiftyNote).intValue());
		assertEquals(5, result.get(twentyNote).intValue());
		assertEquals(50, result.get(tenNote).intValue());
	}

	@Test(expected = NotEnoughNotesAvailableException.class)
	public void calculateDenominations_amountFourHundredWhenAtmReachedNoteLimit_shouldShowMessageToWithdrawThreeHundredAndEightyInstead()
			throws Exception {
		List<AtmAllocation> atmAllocationList = new ArrayList<>();

		Map<Integer, Integer> dummyData = new HashMap<>();
		dummyData.put(10, 1);
		dummyData.put(20, 1);
		dummyData.put(50, 1);
		dummyData.put(100, 1);
		dummyData.put(200, 1);

		for (Entry<Integer, Integer> entry : dummyData.entrySet()) {
			AtmAllocation atmAllocation = new AtmAllocation();
			atmAllocation.setCount(entry.getValue());
			Denomination denomination = new Denomination();
			denomination.setValue(new BigDecimal(entry.getKey().doubleValue()));
			atmAllocation.setDenomination(denomination);

			atmAllocationList.add(atmAllocation);
		}

		atmDispenseService = new AtmDispenseService(atmAllocationList);

		BigDecimal amount = new BigDecimal("400.00");
		atmDispenseService.calculateDenominations(amount);

	}

}
