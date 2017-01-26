package com.adaming.myapp.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.adaming.myapp.entities.Bien;
import com.adaming.myapp.entities.Client;
import com.adaming.myapp.exceptions.BienNotDisponibleException;

public class DaoBienImpl implements IDaoBien {
	
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
	 * @see com.adaming.myapp.dao.IDaoBien#add(com.adaming.myapp.entities.Bien)
	 */
	@Override
	public Bien add(Bien bien) {
		em.persist(bien);
		LOGGER.info("<--------------- DaoBien : add --------------->");
		return bien;
	}

	/* (non-Javadoc)
	 * @see com.adaming.myapp.dao.IDaoBien#getOne(java.lang.Long)
	 */
	@Override
	public Bien getOne(Long idBien) {
		Bien bien = em.find(Bien.class, idBien);
		LOGGER.info("<--------------- DaoBien : getOne --------------->");
		return bien;
	}

	/* (non-Javadoc)
	 * @see com.adaming.myapp.dao.IDaoBien#getAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Bien> getAll() {
		List<Bien> biens = em.createQuery("from Bien ai").getResultList();
		LOGGER.info("<--------------- DaoBien : getAll --------------->");
		return biens;
	}

	/* (non-Javadoc)
	 * @see com.adaming.myapp.dao.IDaoBien#update(com.adaming.myapp.entities.Bien)
	 */
	@Override
	public Bien update(Bien bien) {
		em.merge(bien);
		LOGGER.info("<--------------- DaoBien : update --------------->");
		return bien;
	}
	
	/* (non-Javadoc)
	 * @see com.adaming.myapp.dao.IDaoBien#acheter(java.lang.Long, java.lang.Long)
	 */
	@Override
	public Bien acheter(final Long idBien, final Long idClient) throws Exception {
		Bien bien = em.find(Bien.class, idBien);
		Client client = em.find(Client.class, idClient);
		if (!bien.getDisponible()) {
			throw new BienNotDisponibleException("Bien is already sold");
		}
		bien.setDisponible(false);
		bien.setClient(client);
		client.getBiens().add(bien);
		em.merge(client);
		em.merge(bien);
		LOGGER.info("<--------------- DaoBien : acheter --------------->");
		return bien;
	}
	
	/* (non-Javadoc)
	 * @see com.adaming.myapp.dao.IDaoBien#getBiensByClient(java.lang.Long)
	 */
	@Override
	public List<Bien> getBiensByClient(final Long idClient) {
		List<Bien> biensByClient = em.find(Client.class, idClient).getBiens();
		LOGGER.info("<--------------- DaoBien : getBiensByClient --------------->");
		return biensByClient;
	}

}
