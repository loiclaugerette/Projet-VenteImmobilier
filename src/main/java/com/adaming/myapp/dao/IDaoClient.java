package com.adaming.myapp.dao;

import java.util.List;

import com.adaming.myapp.entities.Client;

public interface IDaoClient {
	
	/**
	 * @param client
	 * @return
	 */
	Client add(final Client client);
	
	/**
	 * @param idClient
	 * @return
	 */
	Client getOne(final Long idClient);
	
	/**
	 * @return
	 */
	List<Client> getAll();
	
	/**
	 * @param client
	 * @return
	 */
	Client update(final Client client);

}
