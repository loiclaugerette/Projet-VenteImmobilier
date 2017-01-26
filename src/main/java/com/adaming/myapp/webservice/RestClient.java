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

import com.adaming.myapp.entities.Client;
import com.adaming.myapp.service.IServiceClient;

@RestController
@RequestMapping(value = "/Client")
public class RestClient {
	
	//=========================
	// Attributes
	//=========================
	
	@Autowired
	private IServiceClient serviceClient;
	
	//=========================
	// Methods
	//=========================

	/**
	 * @param client
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Client add(@RequestBody Client client) {
		try {
			serviceClient.add(client);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return client;
	}
	
	/**
	 * @param idClient
	 * @return
	 */
	@RequestMapping(value = "/getOne/{idClient}", method = RequestMethod.GET)
	public Client getOne(@PathVariable Long idClient) {
		return serviceClient.getOne(idClient);
	}
	
	/**
	 * @return
	 */
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public List<Client> getAll() {
		List<Client> clients;
		try {
			clients = serviceClient.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Client>();
		}
		return clients;
	}
	
	/**
	 * @param client
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Client update(@RequestBody Client client) {
		return serviceClient.update(client);
	}
	
	/**
	 * @param idClient
	 * @param date
	 * @return
	 */
	@RequestMapping(value = "/isDisponible/{idClient}", method = RequestMethod.PUT)
	public Boolean isDisponible(@PathVariable Long idClient, @RequestBody Date date) {
		return serviceClient.isDisponible(idClient, date);
	}
	
}
