package org.cyberpro.atm.server.entity.client;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(length = 10, nullable = false)
	private Integer clientId;
	@Column(length = 10)
	private String title;
	@Column(length = 255, nullable = false)
	private String name;
	@Column(length = 100)
	private String surname;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dob;

	@OneToOne
	@JoinColumn(name = "CLIENT_SUB_TYPE_CODE", nullable = false)
	private ClientSubType clientSubType;

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Timestamp dob) {
		this.dob = dob;
	}

	public ClientSubType getClientSubType() {
		return clientSubType;
	}

	public void setClientSubType(ClientSubType clientSubType) {
		this.clientSubType = clientSubType;
	}

}
