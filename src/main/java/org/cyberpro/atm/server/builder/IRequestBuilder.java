package org.cyberpro.atm.server.builder;

/**
 * @author lmichelson
 *
 * @param <T>
 */
public interface IRequestBuilder<T> {

	/**
	 * @param order
	 * @return
	 */
	public AbstractRequestBuilder<T> byOrder(String order);

	/**
	 * @return
	 */
	public T send();

}
