package com.adaming.myapp;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hamcrest.core.IsEqual;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.adaming.myapp.entities.Adresse;
import com.adaming.myapp.entities.AgentImmobilier;
import com.adaming.myapp.entities.Bien;
import com.adaming.myapp.entities.Client;
import com.adaming.myapp.entities.Visite;
import com.adaming.myapp.service.IServiceAgentImmobilier;
import com.adaming.myapp.service.IServiceBien;
import com.adaming.myapp.service.IServiceClient;
import com.adaming.myapp.service.IServiceVisite;

public class ServiceBienTest {

	//=========================
	// Attributes
	//=========================

	private static ClassPathXmlApplicationContext context;

	private static IServiceBien serviceBien;
	
	private static IServiceAgentImmobilier serviceAgentImmobilier;
	private static IServiceClient serviceClient;
	private static IServiceVisite serviceVisite;
	
	//=========================
	// Before / After
	//=========================

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		serviceBien = (IServiceBien)context.getBean("ServiceBienImpl");
		serviceAgentImmobilier = (IServiceAgentImmobilier)context.getBean("ServiceAgentImmobilierImpl");
		serviceClient = (IServiceClient)context.getBean("ServiceClientImpl");
		serviceVisite = (IServiceVisite)context.getBean("ServiceVisiteImpl");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}
	
	//=========================
	// Tests
	//=========================

	@Test
	@Ignore
	public void testAdd() {
		Bien bien = new Bien("Type Bien Bien Test", "Description Bien Bien Test", 0.0, 0, 0, 
				new Adresse("Rue Adresse Bien Bien Test", 0, "Ville Adresse Bien Bien Test", "Pays Adresse Bien Bien Test"));
		serviceBien.add(bien);
		assertNotNull(bien.getIdBien());
	}

	@Test
	@Ignore
	public void testGetOne() {
		List<Bien> biens = new ArrayList<Bien>();
		try {
			biens = serviceBien.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Bien bien = serviceBien.getOne(biens.get(0).getIdBien());
		assertNotNull(bien);
	}

	@Test
	@Ignore
	public void testGetAll() {
		List<Bien> biens = new ArrayList<Bien>();
		try {
			biens = serviceBien.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertThat(biens.isEmpty(), IsEqual.equalTo(false));
	}

	@Test
	@Ignore
	public void testUpdate() {
		List<Bien> biens = new ArrayList<Bien>();
		try {
			biens = serviceBien.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Bien bien = serviceBien.getOne(biens.get(0).getIdBien());
		bien.setTypeBien("Nouveau Type Bien Test" + (int)(Math.random() * 1000));
		serviceBien.update(bien);
		assertThat(bien.getTypeBien(), 
				IsEqual.equalTo(serviceBien.getOne(biens.get(0).getIdBien()).getTypeBien()));
	}

	@Test
	@Ignore
	public void testGetAllDisponible() {
		List<Bien> biens = new ArrayList<Bien>();
		try {
			biens = serviceBien.getAllDisponible();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertThat(biens.isEmpty(), IsEqual.equalTo(false));
	}

	@Test
	@Ignore
	public void testIsDisponible() {
		AgentImmobilier agentImmobilier = new AgentImmobilier("Nom Agent Bien Test" + (int)(Math.random() * 1000), 
				"Prenom Agent Bien Test" + (int)(Math.random() * 1000));
		try {
			serviceAgentImmobilier.add(agentImmobilier);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Date date = new Date();
		Client client = new Client("Nom Client Bien Test" + (int)(Math.random() * 1000), 
				"Prenom Client Bien Test" + (int)(Math.random() * 1000));
		try {
			serviceClient.add(client);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Bien bien = new Bien("Type Bien Bien Test", "Description Bien Bien Test", 0.0, 0, 0, 
				new Adresse("Rue Adresse Bien Bien Test", 0, "Ville Adresse Bien Bien Test", "Pays Adresse Bien Bien Test"));
		serviceBien.add(bien);
		Visite visite = new Visite(date);
		serviceVisite.add(visite, agentImmobilier.getIdAgent(), bien.getIdBien(), bien.getIdBien());
		assertThat(serviceAgentImmobilier.isDisponible(agentImmobilier.getIdAgent(), date), 
				IsEqual.equalTo(false));
	}
	
	@Test
	@Ignore
	public void testAcheter() {
		Bien bien = new Bien("Type Bien Bien Test", "Description Bien Bien Test", 0.0, 0, 0, 
				new Adresse("Rue Adresse Bien Bien Test", 0, "Ville Adresse Bien Bien Test", "Pays Adresse Bien Bien Test"));
		serviceBien.add(bien);
		Client client = new Client("Nom Client Bien Test" + (int)(Math.random() * 1000), 
				"Prenom Client Bien Test" + (int)(Math.random() * 1000));
		try {
			serviceClient.add(client);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		try {
			serviceBien.acheter(bien.getIdBien(), client.getIdClient());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertThat(serviceBien.getOne(bien.getIdBien()).getDisponible(), IsEqual.equalTo(false));
	}

}
