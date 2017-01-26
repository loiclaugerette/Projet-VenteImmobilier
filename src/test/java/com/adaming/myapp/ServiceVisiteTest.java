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

public class ServiceVisiteTest {

	//=========================
	// Attributes
	//=========================

	private static ClassPathXmlApplicationContext context;
	
	private static IServiceVisite serviceVisite;
	
	private static IServiceAgentImmobilier serviceAgentImmobilier;
	private static IServiceClient serviceClient;
	private static IServiceBien serviceBien;

	//=========================
	// Before / After
	//=========================

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		serviceVisite = (IServiceVisite)context.getBean("ServiceVisiteImpl");
		serviceAgentImmobilier = (IServiceAgentImmobilier)context.getBean("ServiceAgentImmobilierImpl");
		serviceClient = (IServiceClient)context.getBean("ServiceClientImpl");
		serviceBien = (IServiceBien)context.getBean("ServiceBienImpl");
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
		Visite visite = new Visite(new Date());
		AgentImmobilier agentImmobilier = new AgentImmobilier("Nom Agent Visite Test" + (int)(Math.random() * 1000), 
				"Prenom Client Visite Test" + (int)(Math.random() * 1000));
		Client client = new Client("Nom Agent Visite Test" + (int)(Math.random() * 1000), 
				"Prenom Client Visite Test" + (int)(Math.random() * 1000));
		Bien bien = new Bien("Type Bien Visite Test", "Description Bien Visite Test", 0.0, 0, 0, 
				new Adresse("Rue Bien Visite Test", 0, "Ville Bien Visite Test", "Pays Bien Visite Test"));
		try {
			serviceAgentImmobilier.add(agentImmobilier);
			serviceClient.add(client);
			serviceBien.add(bien);
			serviceVisite.add(visite, agentImmobilier.getIdAgent(), client.getIdClient(), bien.getIdBien());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertNotNull(visite.getIdVisite());
	}

	@Test
	@Ignore
	public void testGetOne() {
		List<Visite> visites = new ArrayList<Visite>();
		try {
			visites = serviceVisite.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Visite visite = serviceVisite.getOne(visites.get(0).getIdVisite());
		assertNotNull(visite);
	}

	@Test
	@Ignore
	public void testGetAll() {
		List<Visite> visites = new ArrayList<Visite>();
		try {
			visites = serviceVisite.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertThat(visites.isEmpty(), IsEqual.equalTo(false));
	}

	@Test
	@Ignore
	public void testUpdate() {
		List<Visite> visites = new ArrayList<Visite>();
		try {
			visites = serviceVisite.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Visite visite = serviceVisite.getOne(visites.get(0).getIdVisite());
		visite.setDateVisite(null);
		serviceVisite.update(visite);
		assertThat(visite.getDateVisite(), 
				IsEqual.equalTo(serviceVisite.getOne(visites.get(0).getIdVisite()).getDateVisite()));
	}

}
