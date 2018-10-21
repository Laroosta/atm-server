package org.cyberpro.atm.server.pojo;

import java.math.BigDecimal;
import java.util.Map;

public class Dispense {
	Map<BigDecimal, Integer> available;
	Map<BigDecimal, Integer> denominations;
}
