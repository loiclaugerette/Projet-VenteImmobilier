package com.adaming.myapp.service;

import java.util.Date;
import java.util.List;

import com.adaming.myapp.entities.AgentImmobilier;

public interface IServiceAgentImmobilier {
	
	/**
	 * @param agentImmobilier
	 * @return
	 * @throws Exception
	 */
	AgentImmobilier add(final AgentImmobilier agentImmobilier) throws Exception;
	
	/**
	 * @param idAgentImmobilier
	 * @return
	 */
	AgentImmobilier getOne(final Long idAgentImmobilier);
	
	/**
	 * @return
	 * @throws Exception
	 */
	List<AgentImmobilier> getAll() throws Exception;
	
	/**
	 * @param agentImmobilier
	 * @return
	 */
	AgentImmobilier update(final AgentImmobilier agentImmobilier);
	
	/**
	 * @param idAgentImmobilier
	 * @param date
	 * @return
	 */
	Boolean isDisponible(final Long idAgentImmobilier, final Date date);

}
