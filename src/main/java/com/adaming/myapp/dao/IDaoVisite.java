/*
 * IDaoVisite
 * Version: 1.0.0
 * Date: 06/01/2017
 * Author: Etienne Lorteau
 */

package com.adaming.myapp.dao;

import java.util.List;

import com.adaming.myapp.entities.Visite;

public interface IDaoVisite {
	
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
	 */
	List<Visite> getAll();
	
	/**
	 * @param visite
	 * @return
	 */
	Visite update(final Visite visite);
	
	/**
	 * @param idClient
	 * @return
	 */
	List<Visite> getVisitesByClient(final Long idClient);

}
