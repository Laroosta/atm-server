package org.cyberpro.atm.server.builder;

import java.util.List;

/**
 * @author lmichelson
 *
 * @param <T>
 */
public interface IQueryBuilder<T> {

	/**
	 * @param order
	 * @return
	 */
	public AbstractQueryBuilder<T> byOrder(String order);

	/**
	 * @return
	 */
	public List<T> findAll();

}
