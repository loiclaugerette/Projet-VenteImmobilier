package com.adaming.myapp.dao;

import java.util.List;

import com.adaming.myapp.entities.Bien;

public interface IDaoBien {
	
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
	 */
	List<Bien> getAll();
	
	/**
	 * @param Bien
	 * @return
	 */
	Bien update(final Bien Bien);
	
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
	 */
	List<Bien> getBiensByClient(final Long idClient);

}
