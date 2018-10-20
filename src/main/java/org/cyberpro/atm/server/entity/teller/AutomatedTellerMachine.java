package org.cyberpro.atm.server.entity.teller;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lmichelson
 *
 */
@Entity
@Table(name = "ATM")
public class AutomatedTellerMachine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8190827117432773852L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 10)
	private Integer atmId;
	@Column(length = 10)
	private String name;
	@Column(length = 255)
	private String location;

	public Integer getAtmId() {
		return atmId;
	}

	public void setAtmId(Integer atmId) {
		this.atmId = atmId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
