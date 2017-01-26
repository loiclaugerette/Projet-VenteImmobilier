package com.adaming.myapp.webservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adaming.myapp.entities.AgentImmobilier;
import com.adaming.myapp.service.IServiceAgentImmobilier;

@RestController
@RequestMapping(value = "/AgentImmobilier")
public class RestAgentImmobilier {
	
	//=========================
	// Attributes
	//=========================
	
	@Autowired
	private IServiceAgentImmobilier serviceAgentImmobilier;
	
	//=========================
	// Methods
	//=========================

	/**
	 * @param agentImmobilier
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public AgentImmobilier add(@RequestBody AgentImmobilier agentImmobilier) {
		try {
			serviceAgentImmobilier.add(agentImmobilier);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return agentImmobilier;
	}
	
	/**
	 * @param idAgentImmobilier
	 * @return
	 */
	@RequestMapping(value = "/getOne/{idAgentImmobilier}", method = RequestMethod.GET)
	public AgentImmobilier getOne(@PathVariable Long idAgentImmobilier) {
		return serviceAgentImmobilier.getOne(idAgentImmobilier);
	}
	
	/**
	 * @return
	 */
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public List<AgentImmobilier> getAll() {
		List<AgentImmobilier> agentsImmobilier;
		try {
			agentsImmobilier = serviceAgentImmobilier.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<AgentImmobilier>();
		}
		return agentsImmobilier;
	}
	
	/**
	 * @param agentImmobilier
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public AgentImmobilier update(@RequestBody AgentImmobilier agentImmobilier) {
		return serviceAgentImmobilier.update(agentImmobilier);
	}
	
	/**
	 * @param idAgentImmobilier
	 * @param date
	 * @return
	 */
	@RequestMapping(value = "/isDisponible/{idAgentImmobilier}", method = RequestMethod.PUT)
	public Boolean isDisponible(@PathVariable Long idAgentImmobilier, @RequestBody Date date) {
		return serviceAgentImmobilier.isDisponible(idAgentImmobilier, date);
	}
	
}
