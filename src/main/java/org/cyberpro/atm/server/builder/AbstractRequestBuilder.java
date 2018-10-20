package org.cyberpro.atm.server.builder;

/**
 * @author lmichelson
 *
 * @param <T>
 */
public abstract class AbstractRequestBuilder<T> implements IRequestBuilder<T> {

	protected String order;

	public AbstractRequestBuilder<T> byOrder(String order) {
		this.order = order;
		return this;
	}

	/**
	 * @return
	 */
	public String getOrder() {
		return order;
	}

}
