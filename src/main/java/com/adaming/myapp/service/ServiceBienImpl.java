package com.adaming.myapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.transaction.annotation.Transactional;

import com.adaming.myapp.dao.IDaoBien;
import com.adaming.myapp.entities.Bien;
import com.adaming.myapp.entities.Visite;
import com.adaming.myapp.exceptions.NullListException;

@Transactional(readOnly = true)
public class ServiceBienImpl implements IServiceBien {

	//=========================
	// Attributes
	//=========================
	
	private final Logger LOGGER = Logger.getLogger("ServiceBienImpl");

	private IDaoBien dao;

	//=========================
	// Getter / Setter
	//=========================

	public void setDao(IDaoBien dao) {
		this.dao = dao;
		LOGGER.info("<--------------- doaBien injected --------------->");
	}

	//=========================
	// Methods
	//=========================

	/* (non-Javadoc)
	 * @see com.adaming.myapp.service.IServiceBien#add(com.adaming.myapp.entities.Bien)
	 */
	@Override
	@Transactional(readOnly = false)
	public Bien add(Bien bien) {
		LOGGER.info("<--------------- ServiceBien : add --------------->");
		return dao.add(bien);
	}

	/* (non-Javadoc)
	 * @see com.adaming.myapp.service.IServiceBien#getOne(java.lang.Long)
	 */
	@Override
	public Bien getOne(Long idBien) {
		LOGGER.info("<--------------- ServiceBien : getOne --------------->");
		return dao.getOne(idBien);
	}

	/* (non-Javadoc)
	 * @see com.adaming.myapp.service.IServiceBien#getAll()
	 */
	@Override
	public List<Bien> getAll() throws Exception {
		List<Bien> biens = dao.getAll();
		if (biens.size() <= 0) {
			throw new NullListException("No Bien in DataBase");
		}
		LOGGER.info("<--------------- ServiceBien : getAll --------------->");
		return biens;
	}

	/* (non-Javadoc)
	 * @see com.adaming.myapp.service.IServiceBien#update(com.adaming.myapp.entities.Bien)
	 */
	@Override
	@Transactional(readOnly = false)
	public Bien update(Bien bien) {
		LOGGER.info("<--------------- ServiceBien : update --------------->");
		return dao.update(bien);
	}
	
	/* (non-Javadoc)
	 * @see com.adaming.myapp.service.IServiceBien#getAllDisponible()
	 */
	@Override
	public List<Bien> getAllDisponible() throws Exception {
		List<Bien> biensDisponible = new ArrayList<Bien>();
		List<Bien> biens = getAll();
		for (Bien bien:biens) {
			if (bien.getDisponible()) {
				biensDisponible.add(bien);
			}
		}
		LOGGER.info("<--------------- ServiceBien : getAllDisponible --------------->");
		return biensDisponible;
	}
	
	/* (non-Javadoc)
	 * @see com.adaming.myapp.service.IServiceBien#isDisponible(java.lang.Long, java.util.Date)
	 */
	@Override
	public Boolean isDisponible(Long idBien, Date date) {
		Bien bien = getOne(idBien);
		if (!bien.getDisponible()) {
			return false;
		}
		List<Visite> visites = bien.getVisites();
		for (Visite visite:visites) {
			if ((visite.getDateVisite().getTime() / 1000 / 60 / 60 / 24) == (date.getTime() / 1000 / 60 / 60 / 24)) {
				return false;
			}
		}
		LOGGER.info("<--------------- ServiceBien : isDisponible --------------->");
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.adaming.myapp.service.IServiceBien#acheter(java.lang.Long, java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = false)
	public Bien acheter(final Long idBien, final Long idClient) throws Exception {
		LOGGER.info("<--------------- ServiceBien : acheter --------------->");
		return dao.acheter(idBien, idClient);
	}

	/* (non-Javadoc)
	 * @see com.adaming.myapp.service.IServiceBien#getBiensByClient(java.lang.Long)
	 */
	@Override
	public List<Bien> getBiensByClient(Long idClient) throws Exception {
		List<Bien> biensByClient = dao.getBiensByClient(idClient);
		if (biensByClient.size() <= 0) {
			throw new NullListException("No Bien for the Client");
		}
		return biensByClient;
	}

}
