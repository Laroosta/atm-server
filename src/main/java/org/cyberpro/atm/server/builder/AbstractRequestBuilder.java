package org.cyberpro.atm.server.builder;

public abstract class AbstractRequestBuilder<T> implements IRequestBuilder<T> {

	protected String order;

	public AbstractRequestBuilder<T> byOrder(String order) {
		this.order = order;
		return this;
	}

	public String getOrder() {
		return order;
	}

}
