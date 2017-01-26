package com.adaming.myapp.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.adaming.myapp.entities.AgentImmobilier;

public class DaoAgentImmobilierImpl implements IDaoAgentImmobilier {
	
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
	 * @see com.adaming.myapp.dao.IDaoAgentImmobilier#add(com.adaming.myapp.entities.AgentImmobilier)
	 */
	@Override
	public AgentImmobilier add(AgentImmobilier agentImmobilier) {
		em.persist(agentImmobilier);
		LOGGER.info("<--------------- DaoAgentImmobilier : add --------------->");
		return agentImmobilier;
	}

	/* (non-Javadoc)
	 * @see com.adaming.myapp.dao.IDaoAgentImmobilier#getOne(java.lang.Long)
	 */
	@Override
	public AgentImmobilier getOne(Long idAgentImmobilier) {
		AgentImmobilier agentImmobilier = em.find(AgentImmobilier.class, idAgentImmobilier);
		LOGGER.info("<--------------- DaoAgentImmobilier : getOne --------------->");
		return agentImmobilier;
	}

	/* (non-Javadoc)
	 * @see com.adaming.myapp.dao.IDaoAgentImmobilier#getAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AgentImmobilier> getAll() {
		List<AgentImmobilier> agentsImmobilier = em.createQuery("from AgentImmobilier ai").getResultList();
		LOGGER.info("<--------------- DaoAgentImmobilier : getAll --------------->");
		return agentsImmobilier;
	}

	/* (non-Javadoc)
	 * @see com.adaming.myapp.dao.IDaoAgentImmobilier#update(com.adaming.myapp.entities.AgentImmobilier)
	 */
	@Override
	public AgentImmobilier update(AgentImmobilier agentImmobilier) {
		em.merge(agentImmobilier);
		LOGGER.info("<--------------- DaoAgentImmobilier : update --------------->");
		return agentImmobilier;
	}

}
