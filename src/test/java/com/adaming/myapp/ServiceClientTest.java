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

public class ServiceClientTest {

	//=========================
	// Attributes
	//=========================

	private static ClassPathXmlApplicationContext context;

	private static IServiceClient serviceClient;
	
	private static IServiceAgentImmobilier serviceAgentImmobilier;
	private static IServiceBien serviceBien;
	private static IServiceVisite serviceVisite;
	
	//=========================
	// Before / After
	//=========================

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		serviceClient = (IServiceClient)context.getBean("ServiceClientImpl");
		serviceAgentImmobilier = (IServiceAgentImmobilier)context.getBean("ServiceAgentImmobilierImpl");
		serviceBien = (IServiceBien)context.getBean("ServiceBienImpl");
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
		Client client = new Client("Nom Client Test" + (int)(Math.random() * 1000), 
				"Prenom Client Test" + (int)(Math.random() * 1000));
		try {
			serviceClient.add(client);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertNotNull(client.getIdClient());
	}

	@Test
	@Ignore
	public void testGetOne() {
		List<Client> clients = new ArrayList<Client>();
		try {
			clients = serviceClient.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Client client = serviceClient.getOne(clients.get(0).getIdClient());
		assertNotNull(client);
	}

	@Test
	@Ignore
	public void testGetAll() {
		List<Client> clients = new ArrayList<Client>();
		try {
			clients = serviceClient.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertThat(clients.isEmpty(), IsEqual.equalTo(false));
	}

	@Test
	@Ignore
	public void testUpdate() {
		List<Client> clients = new ArrayList<Client>();
		try {
			clients = serviceClient.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Client client = serviceClient.getOne(clients.get(0).getIdClient());
		client.setNomClient("Nouveau Nom Client Test" + (int)(Math.random() * 1000));
		serviceClient.update(client);
		assertThat(client.getNomClient(), 
				IsEqual.equalTo(serviceClient.getOne(clients.get(0).getIdClient()).getNomClient()));
	}

	@Test
	@Ignore
	public void testIsDisponible() {
		AgentImmobilier agentImmobilier = new AgentImmobilier("Nom Agent Client Test" + (int)(Math.random() * 1000), 
				"Prenom Agent Client Test" + (int)(Math.random() * 1000));
		try {
			serviceAgentImmobilier.add(agentImmobilier);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Date date = new Date();
		Client client = new Client("Nom Client Client Test" + (int)(Math.random() * 1000), 
				"Prenom Client Client Test" + (int)(Math.random() * 1000));
		try {
			serviceClient.add(client);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Bien bien = new Bien("Type Bien Client Test", "Description Bien Client Test", 0.0, 0, 0, 
				new Adresse("Rue Adresse Bien Client Test", 0, "Ville Adresse Bien Client Test", "Pays Adresse Bien Client Test"));
		serviceBien.add(bien);
		Visite visite = new Visite(date);
		serviceVisite.add(visite, agentImmobilier.getIdAgent(), client.getIdClient(), bien.getIdBien());
		assertThat(serviceAgentImmobilier.isDisponible(agentImmobilier.getIdAgent(), date), 
				IsEqual.equalTo(false));
	}

}
