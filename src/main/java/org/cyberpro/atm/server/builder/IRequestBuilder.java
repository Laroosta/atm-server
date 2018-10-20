package org.cyberpro.atm.server.builder;

public interface IRequestBuilder<T> {

	public AbstractRequestBuilder<T> byOrder(String order);

	public T send();

}
