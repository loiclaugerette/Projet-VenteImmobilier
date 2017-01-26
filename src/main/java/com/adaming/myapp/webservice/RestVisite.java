package com.adaming.myapp.webservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adaming.myapp.entities.Visite;
import com.adaming.myapp.service.IServiceVisite;

@RestController
@RequestMapping(value = "/Visite")
public class RestVisite {
	
	//=========================
	// Attributes
	//=========================
	
	@Autowired
	private IServiceVisite serviceVisite;
	
	//=========================
	// Methods
	//=========================

	/**
	 * @param visite
	 * @param idAgentImmobilier
	 * @param idClient
	 * @param idBien
	 * @return
	 */
	@RequestMapping(value = "/add/{idAgentImmobilier}/{idClient}/{idBien}", method = RequestMethod.POST)
	public Visite add(@RequestBody Visite visite, @PathVariable Long idAgentImmobilier, @PathVariable Long idClient, @PathVariable Long idBien) {
		return serviceVisite.add(visite, idAgentImmobilier, idClient, idBien);
	}
	
	/**
	 * @param idVisite
	 * @return
	 */
	@RequestMapping(value = "/getOne/{idVisite}", method = RequestMethod.GET)
	public Visite getOne(@PathVariable Long idVisite) {
		return serviceVisite.getOne(idVisite);
	}
	
	/**
	 * @return
	 */
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public List<Visite> getAll() {
		List<Visite> visites;
		try {
			visites = serviceVisite.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Visite>();
		}
		return visites;
	}
	
	/**
	 * @param visite
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Visite update(@RequestBody Visite visite) {
		return serviceVisite.update(visite);
	}
	
	@RequestMapping(value = "/getVisitesByClient/{idClient}", method = RequestMethod.GET)
	public List<Visite> getVisitesByClient(@PathVariable Long idClient) {
		List<Visite> visitesByClient;
		try {
			visitesByClient = serviceVisite.getVisitesByClient(idClient);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Visite>();
		}
		return visitesByClient;
	}
	
}
