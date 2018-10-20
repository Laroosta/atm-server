package org.cyberpro.atm.server.entity.client;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClientType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(length = 2, nullable = false)
	private String clientTypeCode;
	@Column(length = 255, nullable = false)
	private String description;

	public String getClientTypeCode() {
		return clientTypeCode;
	}

	public void setClientType(String clientType) {
		this.clientTypeCode = clientType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
