package org.cyberpro.atm.server.integrationtest.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.cyberpro.atm.server.entity.account.ClientAccount;
import org.cyberpro.atm.server.pojo.WithdrawRequest;
import org.cyberpro.atm.server.service.impl.ClientAccountService;
import org.cyberpro.atm.server.service.impl.WithdrawalTrxService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WithdrawalTrxServiceIntegrationTest {

	@Autowired
	ClientAccountService clientAccountService;

	@Autowired
	WithdrawalTrxService withdrawalTrxServiceTest;

	@Test
	public void makeWithdrawal_nineHundredRandFromClient1_shouldBePersistedToDatabase() throws Exception {
		final String accountNumber = "4067342946";

		WithdrawRequest wr = new WithdrawRequest();
		wr.setAccountNumber(accountNumber);
		wr.setAmount(new BigDecimal("100.00"));
		wr.setAtmId(1);
		wr.setClientId(1);

		ClientAccount clientAccount = clientAccountService.findByAccountNumber(accountNumber);

		clientAccount.setDisplayBalance(new BigDecimal("1000.00"));

		withdrawalTrxServiceTest.makeWithdrawal(wr, clientAccount);

		BigDecimal expected = new BigDecimal("900.000");

		ClientAccount persistedClientAccount = clientAccountService.findByAccountNumber(accountNumber);

		assertEquals(expected, persistedClientAccount.getDisplayBalance());

	}

}
