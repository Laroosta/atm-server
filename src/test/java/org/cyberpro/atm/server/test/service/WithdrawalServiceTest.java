package org.cyberpro.atm.server.test.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.cyberpro.atm.server.entity.account.AccountType;
import org.cyberpro.atm.server.entity.account.ClientAccount;
import org.cyberpro.atm.server.entity.account.CreditCardLimit;
import org.cyberpro.atm.server.service.impl.WithdrawalService;
import org.junit.Before;
import org.junit.Test;

/**
 * @author lmichelson
 *
 */
public class WithdrawalServiceTest {

	ClientAccount clientAccount;

	WithdrawalService withdrawalService;

	AccountType accountType;

	@Before
	public void init() {
		clientAccount = new ClientAccount();
		accountType = new AccountType();
		clientAccount.setAccountType(accountType);
	}

	@Test
	public void canWithdraw_isNotTransactional_shouldBeFalse() throws Exception {
		clientAccount.getAccountType().setTransactional(false);
		clientAccount.setDisplayBalance(new BigDecimal("1000.00"));
		withdrawalService = new WithdrawalService(clientAccount);
		BigDecimal amount = new BigDecimal("100.00");

		boolean expected = false;

		assertEquals(expected, withdrawalService.canWithdraw(amount));
	}

	@Test
	public void canWithdraw_amountHasCents_shouldBeFalse() throws Exception {
		clientAccount.getAccountType().setTransactional(true);
		clientAccount.setDisplayBalance(new BigDecimal("1000.00"));
		withdrawalService = new WithdrawalService(clientAccount);
		BigDecimal amount = new BigDecimal("100.50");

		boolean expected = false;

		assertEquals(expected, withdrawalService.canWithdraw(amount));
	}

	@Test
	public void canWithdraw_amountHasCoins_shouldBeFalse() throws Exception {
		clientAccount.getAccountType().setTransactional(true);
		clientAccount.setDisplayBalance(new BigDecimal("1000.00"));
		withdrawalService = new WithdrawalService(clientAccount);
		BigDecimal amount = new BigDecimal("105.00");

		boolean expected = false;

		assertEquals(expected, withdrawalService.canWithdraw(amount));
	}

	@Test
	public void canWithdraw_hasZeroFunds_shouldBeFalse() throws Exception {
		clientAccount.getAccountType().setTransactional(true);
		clientAccount.setDisplayBalance(new BigDecimal("0.00"));
		withdrawalService = new WithdrawalService(clientAccount);
		BigDecimal amount = new BigDecimal("100.00");

		boolean expected = false;

		assertEquals(expected, withdrawalService.canWithdraw(amount));
	}

	@Test
	public void canWithdraw_amountGreaterThanLimit_shouldBeFalse() throws Exception {
		clientAccount.getAccountType().setTransactional(true);
		clientAccount.setDisplayBalance(new BigDecimal("10.00"));
		withdrawalService = new WithdrawalService(clientAccount);
		BigDecimal amount = new BigDecimal("100.00");

		boolean expected = false;

		assertEquals(expected, withdrawalService.canWithdraw(amount));
	}

	@Test
	public void canWithdraw_hasNegativeBalance_shouldBeFalse() throws Exception {
		clientAccount.getAccountType().setTransactional(true);
		clientAccount.setDisplayBalance(new BigDecimal("-1000.00"));
		withdrawalService = new WithdrawalService(clientAccount);
		BigDecimal amount = new BigDecimal("100.00");

		boolean expected = false;

		assertEquals(expected, withdrawalService.canWithdraw(amount));
	}

	@Test
	public void canWithdraw_hasPositiveBalance_shouldBeTrue() throws Exception {
		clientAccount.getAccountType().setTransactional(true);
		clientAccount.setDisplayBalance(new BigDecimal("1000.00"));
		withdrawalService = new WithdrawalService(clientAccount);
		BigDecimal amount = new BigDecimal("100.00");

		boolean expected = true;

		assertEquals(expected, withdrawalService.canWithdraw(amount));
	}

	@Test
	public void canWithdraw_hasNegativeAndIsChequeAndIsWithinLimit_shouldBeTrue() throws Exception {
		clientAccount.getAccountType().setTransactional(true);
		clientAccount.getAccountType().setAccountTypeCode("CHQ");
		clientAccount.setDisplayBalance(new BigDecimal("-1000.00"));
		withdrawalService = new WithdrawalService(clientAccount);
		BigDecimal amount = new BigDecimal("100.00");

		boolean expected = true;

		assertEquals(expected, withdrawalService.canWithdraw(amount));
	}

	@Test
	public void canWithdraw_hasNegativeAndIsChequeAndIsBeyondLimit_shouldBeFalse() throws Exception {
		clientAccount.getAccountType().setTransactional(true);
		clientAccount.getAccountType().setAccountTypeCode("CHQ");
		clientAccount.setDisplayBalance(new BigDecimal("-9000.00"));
		withdrawalService = new WithdrawalService(clientAccount);
		BigDecimal amount = new BigDecimal("10000.00");

		boolean expected = false;

		assertEquals(expected, withdrawalService.canWithdraw(amount));
	}

	@Test
	public void canWithdraw_hasNegativeAndIsCreditCardAndIsWithinLimit_shouldBeTrue() throws Exception {
		clientAccount.getAccountType().setTransactional(true);
		clientAccount.getAccountType().setAccountTypeCode("CCRD");
		clientAccount.setDisplayBalance(new BigDecimal("-1000.00"));
		CreditCardLimit ccrdLimit = new CreditCardLimit();
		ccrdLimit.setAccountLimit(new BigDecimal("-10000.00"));
		clientAccount.setCreditCardLimit(ccrdLimit);
		withdrawalService = new WithdrawalService(clientAccount);
		BigDecimal amount = new BigDecimal("100.00");

		boolean expected = true;

		assertEquals(expected, withdrawalService.canWithdraw(amount));
	}

	@Test
	public void canWithdraw_hasNegativeAndIsCreditCardAndIsBeyondLimit_shouldBeFalse() throws Exception {
		clientAccount.getAccountType().setTransactional(true);
		clientAccount.getAccountType().setAccountTypeCode("CCRD");
		clientAccount.setDisplayBalance(new BigDecimal("-9000.00"));
		CreditCardLimit ccrdLimit = new CreditCardLimit();
		ccrdLimit.setAccountLimit(new BigDecimal("-10000.00"));
		clientAccount.setCreditCardLimit(ccrdLimit);
		withdrawalService = new WithdrawalService(clientAccount);
		BigDecimal amount = new BigDecimal("10000.00");

		boolean expected = false;

		assertEquals(expected, withdrawalService.canWithdraw(amount));
	}

	@Test
	public void withdraw_amountOfOneHundred_shouldBeDeducted() throws Exception {
		clientAccount.getAccountType().setTransactional(true);
		clientAccount.getAccountType().setAccountTypeCode("CHQ");
		clientAccount.setDisplayBalance(new BigDecimal("1000.00"));
		withdrawalService = new WithdrawalService(clientAccount);
		BigDecimal amount = new BigDecimal("100.00");

		ClientAccount updatedClientAccount = withdrawalService.withdraw(amount);

		BigDecimal expected = new BigDecimal("900.00");

		assertEquals(expected, updatedClientAccount.getDisplayBalance());
	}

}
