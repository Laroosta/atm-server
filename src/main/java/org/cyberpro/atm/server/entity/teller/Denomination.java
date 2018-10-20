package org.cyberpro.atm.server.entity.teller;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author lmichelson
 *
 */
@Entity
public class Denomination implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8586171256897447283L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 10)
	private Integer denominationId;
	private BigDecimal value;

	@OneToOne
	@JoinColumn(name = "DENOMINATION_TYPE_CODE")
	private DenominationType dominationType;

	public Integer getDenominationId() {
		return denominationId;
	}

	public void setDenominationId(Integer denominationId) {
		this.denominationId = denominationId;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public DenominationType getDominationType() {
		return dominationType;
	}

	public void setDominationType(DenominationType dominationType) {
		this.dominationType = dominationType;
	}

}
