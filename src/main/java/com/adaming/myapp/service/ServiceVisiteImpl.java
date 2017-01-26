package com.adaming.myapp.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.dao.IDaoVisite;
import com.adaming.myapp.entities.Visite;
import com.adaming.myapp.exceptions.NullListException;

@Transactional(readOnly = true)
public class ServiceVisiteImpl implements IServiceVisite {

	//=========================
	// Attributes
	//=========================
	
	private final Logger LOGGER = Logger.getLogger("ServiceVisiteImpl");

	private IDaoVisite dao;

	//=========================
	// Getter / Setter
	//=========================

	public void setDao(IDaoVisite dao) {
		this.dao = dao;
		LOGGER.info("<--------------- doaVisite injected --------------->");
	}

	//=========================
	// Methods
	//=========================

	/* (non-Javadoc)
	 * @see com.adaming.myapp.service.IServiceVisite#add(com.adaming.myapp.entities.Visite, java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = false)
	public Visite add(Visite visite, Long idAgentImmobilier, Long idClient, Long idBien) {
		LOGGER.info("<--------------- ServiceVisite : add --------------->");
		return dao.add(visite, idAgentImmobilier, idClient, idBien);
	}

	/* (non-Javadoc)
	 * @see com.adaming.myapp.service.IServiceVisite#getOne(java.lang.Long)
	 */
	@Override
	public Visite getOne(Long idVisite) {
		LOGGER.info("<--------------- ServiceVisite : getOne --------------->");
		return dao.getOne(idVisite);
	}

	/* (non-Javadoc)
	 * @see com.adaming.myapp.service.IServiceVisite#getAll()
	 */
	@Override
	public List<Visite> getAll() throws Exception {
		List<Visite> visites = dao.getAll();
		if (visites.size() <= 0) {
			throw new NullListException("No Visite in DataBase");
		}
		LOGGER.info("<--------------- ServiceVisite : getAll --------------->");
		return visites;
	}

	/* (non-Javadoc)
	 * @see com.adaming.myapp.service.IServiceVisite#update(com.adaming.myapp.entities.Visite)
	 */
	@Override
	@Transactional(readOnly = false)
	public Visite update(Visite visite) {
		LOGGER.info("<--------------- ServiceVisite : update --------------->");
		return dao.update(visite);
	}
	
	/* (non-Javadoc)
	 * @see com.adaming.myapp.service.IServiceVisite#getVisitesByClient(java.lang.Long)
	 */
	@Override
	public List<Visite> getVisitesByClient(final Long idClient) throws Exception{
		List<Visite> visitesByClient = dao.getVisitesByClient(idClient);
		if (visitesByClient.size() <= 0) {
			throw new NullListException("No visite for the Client");
		}
		return visitesByClient;
	}


}
