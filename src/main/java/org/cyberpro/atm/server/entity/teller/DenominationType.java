package org.cyberpro.atm.server.entity.teller;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author lmichelson
 *
 */
@Entity
public class DenominationType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1468772763648441968L;

	@Id
	@Column(length = 1)
	private String denominationTypeCode;
	@Column(length = 255)
	private String description;

	public String getDenominationTypeCode() {
		return denominationTypeCode;
	}

	public void setDenominationTypeCode(String denominationTypeCode) {
		this.denominationTypeCode = denominationTypeCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
