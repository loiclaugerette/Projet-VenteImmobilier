package com.adaming.myapp.service;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.dao.IDaoAgentImmobilier;
import com.adaming.myapp.entities.AgentImmobilier;
import com.adaming.myapp.entities.Visite;
import com.adaming.myapp.exceptions.AlreadyExistingPersonException;
import com.adaming.myapp.exceptions.NullListException;

@Transactional(readOnly = true)
public class ServiceAgentImmobilierImpl implements IServiceAgentImmobilier {

	//=========================
	// Attributes
	//=========================
	
	private final Logger LOGGER = Logger.getLogger("ServiceAgentImmobilierImpl");
	
	private IDaoAgentImmobilier dao;

	//=========================
	// Getter / Setter
	//=========================

	public void setDao(IDaoAgentImmobilier dao) {
		this.dao = dao;
		LOGGER.info("<--------------- doaAgentImmobilier injected --------------->");
	}
	
	public ServiceAgentImmobilierImpl() {
		LOGGER.info("New Instance Service "+ServiceAgentImmobilierImpl.class);
	}

	//=========================
	// Methods
	//=========================

	/* (non-Javadoc)
	 * @see com.adaming.myapp.service.IServiceAgentImmobilier#add(com.adaming.myapp.entities.AgentImmobilier)
	 */
	@Override
	@Transactional(readOnly = false)
	public AgentImmobilier add(AgentImmobilier agentImmobilier) throws Exception {
		List<AgentImmobilier> agentsImmobilier = dao.getAll();
		for (AgentImmobilier ai:agentsImmobilier) {
			if(agentImmobilier.getNomAgent().equals(ai.getNomAgent()) &&
					agentImmobilier.getPrenomAgent().equals(ai.getPrenomAgent())) {
				throw new AlreadyExistingPersonException("AgentImmobilier with nom " + ai.getNomAgent() + 
						" and prenom " + ai.getPrenomAgent() + " is already existing");
			}
		}
		LOGGER.info("<--------------- ServiceAgentImmobilier : add --------------->");
		return dao.add(agentImmobilier);
	}

	/* (non-Javadoc)
	 * @see com.adaming.myapp.service.IServiceAgentImmobilier#getOne(java.lang.Long)
	 */
	@Override
	public AgentImmobilier getOne(Long idAgentImmobilier) {
		LOGGER.info("<--------------- ServiceAgentImmobilier : getOne --------------->");
		return dao.getOne(idAgentImmobilier);
	}

	/* (non-Javadoc)
	 * @see com.adaming.myapp.service.IServiceAgentImmobilier#getAll()
	 */
	@Override
	public List<AgentImmobilier> getAll() throws Exception {
		List<AgentImmobilier> agentImmobiliers = dao.getAll();
		if (agentImmobiliers.size() <= 0) {
			throw new NullListException("No AgentImmobilier in DataBase");
		}
		LOGGER.info("<--------------- ServiceAgentImmobilier : getAll --------------->");
		return agentImmobiliers;
	}

	/* (non-Javadoc)
	 * @see com.adaming.myapp.service.IServiceAgentImmobilier#update(com.adaming.myapp.entities.AgentImmobilier)
	 */
	@Override
	@Transactional(readOnly = false)
	public AgentImmobilier update(AgentImmobilier agentImmobilier) {
		LOGGER.info("<--------------- ServiceAgentImmobilier : update --------------->");
		return dao.update(agentImmobilier);
	}
	
	/* (non-Javadoc)
	 * @see com.adaming.myapp.service.IServiceAgentImmobilier#isDisponible(java.lang.Long, java.util.Date)
	 */
	@Override
	public Boolean isDisponible(Long idAgentImmobilier, Date date) {
		AgentImmobilier agentImmobilier = getOne(idAgentImmobilier);
		List<Visite> visites = agentImmobilier.getVisites();
		for (Visite visite:visites) {
			if ((visite.getDateVisite().getTime() / 1000 / 60 / 60 / 24) == (date.getTime() / 1000 / 60 / 60 / 24)) {
				return false;
			}
		}
		LOGGER.info("<--------------- ServiceAgentImmobilier : isDisponible --------------->");
		return true;
	}

}
