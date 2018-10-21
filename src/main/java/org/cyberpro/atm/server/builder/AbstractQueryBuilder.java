package org.cyberpro.atm.server.builder;

/**
 * @author lmichelson
 *
 * @param <T>
 */
public abstract class AbstractQueryBuilder<T> implements IQueryBuilder<T> {

	protected String order;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.cyberpro.atm.server.builder.IRequestBuilder#byOrder(java.lang.String)
	 */
	public AbstractQueryBuilder<T> byOrder(String order) {
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
