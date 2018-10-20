package org.cyberpro.atm.server.entity.teller;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class AtmAllocation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -419976095334246003L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 10)
	private Integer atmAllocationId;

	@OneToOne
	@JoinColumn(name = "ATM_ID")
	private AutomatedTellerMachine atm;

	@OneToOne
	@JoinColumn(name = "DENOMINATION_ID")
	private Denomination denomination;

	@Column(length = 10)
	private Integer count;

	public Integer getAtmAllocationId() {
		return atmAllocationId;
	}

	public void setAtmAllocationId(Integer atmAllocationId) {
		this.atmAllocationId = atmAllocationId;
	}

	public AutomatedTellerMachine getAtm() {
		return atm;
	}

	public void setAtm(AutomatedTellerMachine atm) {
		this.atm = atm;
	}

	public Denomination getDenomination() {
		return denomination;
	}

	public void setDenomination(Denomination denomination) {
		this.denomination = denomination;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
