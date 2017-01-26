package com.adaming.myapp.service;

import java.util.Date;
import java.util.List;

import com.adaming.myapp.entities.Client;

public interface IServiceClient {
	
	/**
	 * @param client
	 * @return
	 * @throws Exception
	 */
	Client add(final Client client) throws Exception;
	
	/**
	 * @param idClient
	 * @return
	 */
	Client getOne(final Long idClient);
	
	/**
	 * @return
	 * @throws Exception
	 */
	List<Client> getAll() throws Exception;
	
	/**
	 * @param client
	 * @return
	 */
	Client update(final Client client);
	
	/**
	 * @param idClient
	 * @param date
	 * @return
	 */
	Boolean isDisponible(final Long idClient, final Date date);

}
