package com.adaming.myapp.dao;

import java.util.List;

import com.adaming.myapp.entities.AgentImmobilier;

public interface IDaoAgentImmobilier {
	
	/**
	 * @param agentImmobilier
	 * @return
	 */
	AgentImmobilier add(final AgentImmobilier agentImmobilier);
	
	/**
	 * @param idAgentImmobilier
	 * @return
	 */
	AgentImmobilier getOne(final Long idAgentImmobilier);
	
	/**
	 * @return
	 */
	List<AgentImmobilier> getAll();
	
	/**
	 * @param agentImmobilier
	 * @return
	 */
	AgentImmobilier update(final AgentImmobilier agentImmobilier);

}
