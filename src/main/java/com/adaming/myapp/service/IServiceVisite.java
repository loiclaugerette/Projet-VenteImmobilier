package com.adaming.myapp.service;

import java.util.List;

import com.adaming.myapp.entities.Visite;
import com.adaming.myapp.exceptions.NullListException;

public interface IServiceVisite {
	
	/**
	 * @param visite
	 * @param idAgentImmobilier
	 * @param idClient
	 * @param idBien
	 * @return
	 */
	Visite add(final Visite visite, final Long idAgentImmobilier, final Long idClient, final Long idBien);
	
	/**
	 * @param idVisite
	 * @return
	 */
	Visite getOne(final Long idVisite);
	
	/**
	 * @return
	 * @throws Exception
	 */
	List<Visite> getAll() throws Exception;
	
	/**
	 * @param visite
	 * @return
	 */
	Visite update(final Visite visite);
	
	/**
	 * @param idClient
	 * @return
	 * @throws NullListException 
	 */
	List<Visite> getVisitesByClient(final Long idClient) throws Exception;
	
}
