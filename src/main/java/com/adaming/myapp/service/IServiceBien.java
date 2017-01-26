package com.adaming.myapp.service;

import java.util.Date;
import java.util.List;

import com.adaming.myapp.entities.Bien;

public interface IServiceBien {
	
	/**
	 * @param Bien
	 * @return
	 */
	Bien add(final Bien Bien);
	
	/**
	 * @param idBien
	 * @return
	 */
	Bien getOne(final Long idBien);
	
	/**
	 * @return
	 * @throws Exception
	 */
	List<Bien> getAll() throws Exception;
	
	/**
	 * @param Bien
	 * @return
	 */
	Bien update(final Bien Bien);
	
	/**
	 * @return
	 * @throws Exception
	 */
	List<Bien> getAllDisponible() throws Exception;
	
	/**
	 * @param idBien
	 * @param date
	 * @return
	 */
	Boolean isDisponible(final Long idBien, final Date date);
	
	/**
	 * @param idBien
	 * @param idClient
	 * @return
	 * @throws Exception
	 */
	Bien acheter(final Long idBien, final Long idClient) throws Exception;

	/**
	 * @param idClient
	 * @return
	 * @throws Exception 
	 */
	List<Bien> getBiensByClient(final Long idClient) throws Exception;

}
