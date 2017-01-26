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

import com.adaming.myapp.entities.Bien;
import com.adaming.myapp.service.IServiceBien;

@RestController
@RequestMapping(value = "/Bien")
public class RestBien {
	
	//=========================
	// Attributes
	//=========================
	
	@Autowired
	private IServiceBien serviceBien;
	
	//=========================
	// Methods
	//=========================

	/**
	 * @param Bien
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Bien add(@RequestBody Bien Bien) {
		return serviceBien.add(Bien);
	}
	
	/**
	 * @param idBien
	 * @return
	 */
	@RequestMapping(value = "/getOne/{idBien}", method = RequestMethod.GET)
	public Bien getOne(@PathVariable Long idBien) {
		return serviceBien.getOne(idBien);
	}
	
	/**
	 * @return
	 */
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public List<Bien> getAll() {
		List<Bien> biens;
		try {
			biens = serviceBien.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Bien>();
		}
		return biens;
	}
	
	/**
	 * @param Bien
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Bien update(@RequestBody Bien Bien) {
		return serviceBien.update(Bien);
	}
	
	/**
	 * @return
	 */
	@RequestMapping(value = "/getAllDisponible", method = RequestMethod.GET)
	public List<Bien> getAllDisponible() {
		List<Bien> biensDisponible;
		try {
			biensDisponible = serviceBien.getAllDisponible();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Bien>();
		}
		return biensDisponible;
	}
	
	/**
	 * @param idBien
	 * @param date
	 * @return
	 */
	@RequestMapping(value = "/isDisponible/{idBien}", method = RequestMethod.PUT)
	public Boolean isDisponible(@PathVariable Long idBien, @RequestBody Date date) {
		return serviceBien.isDisponible(idBien, date);
	}
	
	/**
	 * 
	 * @param idBien
	 * @param idClient
	 * @return
	 */
	@RequestMapping(value = "/acheter/{idBien}/{idClient}", method = RequestMethod.PUT)
	public Bien acheter(@PathVariable Long idBien, @PathVariable Long idClient) {
		Bien bien = null;
		try {
			bien = serviceBien.acheter(idBien, idClient);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bien;
	}
	
	/**
	 * @param idClient
	 * @return
	 */
	@RequestMapping(value = "/getBiensByClient/{idClient}", method = RequestMethod.GET)
	public List<Bien> getBiensByClient(@PathVariable Long idClient) {
		List<Bien> biensByClient;
		try {
			biensByClient = serviceBien.getBiensByClient(idClient);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Bien>();
		}
		return biensByClient;
	}
	
}
