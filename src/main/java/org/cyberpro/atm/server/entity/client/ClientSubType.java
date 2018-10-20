package org.cyberpro.atm.server.entity.client;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ClientSubType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(length = 4, nullable = false)
	private String clientSubTypeCode;

	@OneToOne
	@JoinColumn(name = "CLIENT_TYPE_CODE", nullable = false)
	private ClientType clientType;

	@Column(length = 255, nullable = false)
	private String description;

	public String getClientSubTypeCode() {
		return clientSubTypeCode;
	}

	public void setClientSubTypeCode(String clientSubTypeCode) {
		this.clientSubTypeCode = clientSubTypeCode;
	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
