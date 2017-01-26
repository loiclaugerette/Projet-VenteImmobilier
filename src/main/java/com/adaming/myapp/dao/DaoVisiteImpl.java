package com.adaming.myapp.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.adaming.myapp.entities.AgentImmobilier;
import com.adaming.myapp.entities.Bien;
import com.adaming.myapp.entities.Client;
import com.adaming.myapp.entities.Visite;

public class DaoVisiteImpl implements IDaoVisite {
	
	//=========================
	// Attributes
	//=========================

	@PersistenceContext
	private EntityManager em;

	private final Logger LOGGER = Logger.getLogger("DaoChevalImpl");

	//=========================
	// Methods
	//=========================

	/* (non-Javadoc)
	 * @see com.adaming.myapp.dao.IDaoVisite#add(com.adaming.myapp.entities.Visite, java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@Override
	public Visite add(Visite visite, Long idAgentImmobilier, Long idClient, Long idBien) {
		visite.setAgentImmobilier(em.find(AgentImmobilier.class, idAgentImmobilier));
		visite.setClient(em.find(Client.class, idClient));
		visite.setBien(em.find(Bien.class, idBien));
		em.persist(visite);
		LOGGER.info("<--------------- DaoVisite : add --------------->");
		return visite;
	}

	/* (non-Javadoc)
	 * @see com.adaming.myapp.dao.IDaoVisite#getOne(java.lang.Long)
	 */
	@Override
	public Visite getOne(Long idVisite) {
		Visite visite = em.find(Visite.class, idVisite);
		LOGGER.info("<--------------- DaoVisite : getOne --------------->");
		return visite;
	}

	/* (non-Javadoc)
	 * @see com.adaming.myapp.dao.IDaoVisite#getAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Visite> getAll() {
		List<Visite> visites = em.createQuery("from Visite ai").getResultList();
		LOGGER.info("<--------------- DaoVisite : getAll --------------->");
		return visites;
	}

	/* (non-Javadoc)
	 * @see com.adaming.myapp.dao.IDaoVisite#update(com.adaming.myapp.entities.Visite)
	 */
	@Override
	public Visite update(Visite visite) {
		em.merge(visite);
		LOGGER.info("<--------------- DaoVisite : update --------------->");
		return visite;
	}

	/* (non-Javadoc)
	 * @see com.adaming.myapp.dao.IDaoVisite#getVisitesByClient(java.lang.Long)
	 */
	@Override
	public List<Visite> getVisitesByClient(Long idClient) {
		List<Visite> visitesByClient = em.find(Client.class, idClient).getVisites();
		LOGGER.info("<--------------- DaoVisite : getVisitesByClient --------------->");
		return visitesByClient;
	}

}
