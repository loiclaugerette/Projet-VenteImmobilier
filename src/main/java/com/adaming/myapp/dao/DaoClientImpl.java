package com.adaming.myapp.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.adaming.myapp.entities.Client;

public class DaoClientImpl implements IDaoClient {
	
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
	 * @see com.adaming.myapp.dao.IDaoClient#add(com.adaming.myapp.entities.Client)
	 */
	@Override
	public Client add(Client client) {
		em.persist(client);
		LOGGER.info("<--------------- DaoClient : add --------------->");
		return client;
	}

	/* (non-Javadoc)
	 * @see com.adaming.myapp.dao.IDaoClient#getOne(java.lang.Long)
	 */
	@Override
	public Client getOne(Long idClient) {
		Client client = em.find(Client.class, idClient);
		LOGGER.info("<--------------- DaoClient : getOne --------------->");
		return client;
	}

	/* (non-Javadoc)
	 * @see com.adaming.myapp.dao.IDaoClient#getAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Client> getAll() {
		List<Client> clients = em.createQuery("from Client ai").getResultList();
		LOGGER.info("<--------------- DaoClient : getAll --------------->");
		return clients;
	}

	/* (non-Javadoc)
	 * @see com.adaming.myapp.dao.IDaoClient#update(com.adaming.myapp.entities.Client)
	 */
	@Override
	public Client update(Client client) {
		em.merge(client);
		LOGGER.info("<--------------- DaoClient : update --------------->");
		return client;
	}

}
