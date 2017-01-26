package com.adaming.myapp.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.dao.IDaoClient;
import com.adaming.myapp.entities.Client;
import com.adaming.myapp.entities.Visite;
import com.adaming.myapp.exceptions.AlreadyExistingPersonException;
import com.adaming.myapp.exceptions.NullListException;

@Transactional(readOnly = true)
public class ServiceClientImpl implements IServiceClient {

	//=========================
	// Attributes
	//=========================
	
	private final Logger LOGGER = Logger.getLogger("ServiceClientImpl");

	private IDaoClient dao;

	//=========================
	// Getter / Setter
	//=========================

	public void setDao(IDaoClient dao) {
		this.dao = dao;
		LOGGER.info("<--------------- doaClient injected --------------->");
	}

	//=========================
	// Methods
	//=========================

	/* (non-Javadoc)
	 * @see com.adaming.myapp.service.IServiceClient#add(com.adaming.myapp.entities.Client)
	 */
	@Override
	@Transactional(readOnly = false)
	public Client add(Client client) throws Exception {
		List<Client> clients = dao.getAll();
		for (Client c:clients) {
			if(client.getNomClient().equals(c.getNomClient()) &&
					client.getPrenomClient().equals(c.getPrenomClient())) {
				throw new AlreadyExistingPersonException("Client with nom " + c.getNomClient() + 
						" and prenom " + c.getPrenomClient() + " is already existing");
			}
		}
		LOGGER.info("<--------------- ServiceClient : add --------------->");
		return dao.add(client);
	}

	/* (non-Javadoc)
	 * @see com.adaming.myapp.service.IServiceClient#getOne(java.lang.Long)
	 */
	@Override
	public Client getOne(Long idClient) {
		LOGGER.info("<--------------- ServiceClient : getOne --------------->");
		return dao.getOne(idClient);
	}

	/* (non-Javadoc)
	 * @see com.adaming.myapp.service.IServiceClient#getAll()
	 */
	@Override
	public List<Client> getAll() throws Exception {
		List<Client> clients = dao.getAll();
		if (clients.size() <= 0) {
			throw new NullListException("No Client in DataBase");
		}
		LOGGER.info("<--------------- ServiceClient : getAll --------------->");
		return clients;
	}

	/* (non-Javadoc)
	 * @see com.adaming.myapp.service.IServiceClient#update(com.adaming.myapp.entities.Client)
	 */
	@Override
	@Transactional(readOnly = false)
	public Client update(Client client) {
		LOGGER.info("<--------------- ServiceClient : update --------------->");
		return dao.update(client);
	}
	
	/* (non-Javadoc)
	 * @see com.adaming.myapp.service.IServiceClient#isDisponible(java.lang.Long, java.util.Date)
	 */
	@Override
	public Boolean isDisponible(Long idClient, Date date) {
		Calendar cal = Calendar.getInstance();
	    cal.clear();
	    cal.setTime(date);
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    date = cal.getTime();
		Client client = getOne(idClient);
		List<Visite> visites = client.getVisites();
		for (Visite visite:visites) {
			if ((visite.getDateVisite().getTime() / 1000 / 60 / 60 / 24) == (date.getTime() / 1000 / 60 / 60 / 24)) {
				return false;
			}
		}
		LOGGER.info("<--------------- ServiceClient : isDisponible --------------->");
		return true;
	}

}
