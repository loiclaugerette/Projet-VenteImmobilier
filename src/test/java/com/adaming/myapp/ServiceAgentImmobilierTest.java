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

public class ServiceAgentImmobilierTest {

	//=========================
	// Attributes
	//=========================
	
	private static ClassPathXmlApplicationContext context;

	private static IServiceAgentImmobilier serviceAgentImmobilier;
	
	private static IServiceClient serviceClient;
	private static IServiceBien serviceBien;	
	private static IServiceVisite serviceVisite;

	//=========================
	// Before / After
	//=========================

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("app.xml");
		serviceAgentImmobilier = (IServiceAgentImmobilier)context.getBean("ServiceAgentImmobilierImpl");
		serviceClient = (IServiceClient)context.getBean("ServiceClientImpl");
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
		AgentImmobilier agentImmobilier = new AgentImmobilier("Nom Agent Test" + (int)(Math.random() * 1000), 
				"Prenom Agent Test" + (int)(Math.random() * 1000));
		try {
			serviceAgentImmobilier.add(agentImmobilier);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertNotNull(agentImmobilier.getIdAgent());
	}

	@Test
	public void testGetOne() {
		List<AgentImmobilier> agentsImmobilier = new ArrayList<AgentImmobilier>();
		try {
			agentsImmobilier = serviceAgentImmobilier.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		AgentImmobilier agentImmobilier = serviceAgentImmobilier.getOne(agentsImmobilier.get(0).getIdAgent());
		assertNotNull(agentImmobilier);
	}

	@Test
	@Ignore
	public void testGetAll() {
		List<AgentImmobilier> agentsImmobilier = new ArrayList<AgentImmobilier>();
		try {
			agentsImmobilier = serviceAgentImmobilier.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		assertThat(agentsImmobilier.isEmpty(), IsEqual.equalTo(false));
	}

	@Test
	@Ignore
	public void testUpdate() {
		List<AgentImmobilier> agentsImmobilier = new ArrayList<AgentImmobilier>();
		try {
			agentsImmobilier = serviceAgentImmobilier.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		AgentImmobilier agentImmobilier = serviceAgentImmobilier.getOne(agentsImmobilier.get(0).getIdAgent());
		agentImmobilier.setNomAgent("Nouveau Nom Agent Test");
		serviceAgentImmobilier.update(agentImmobilier);
		assertThat(agentImmobilier.getNomAgent(), 
				IsEqual.equalTo(serviceAgentImmobilier.getOne(agentsImmobilier.get(0).getIdAgent()).getNomAgent()));
	}

	@Test
	@Ignore
	public void testIsDisponible() {
		AgentImmobilier agentImmobilier = new AgentImmobilier("Nom Agent Test" + (int)(Math.random() * 1000), 
				"Prenom Agent Test" + (int)(Math.random() * 1000));
		try {
			serviceAgentImmobilier.add(agentImmobilier);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Date date = new Date();
		Client client = new Client("Nom Client Agent Test" + (int)(Math.random() * 1000), 
				"Prenom Client Agent Test" + (int)(Math.random() * 1000));
		try {
			serviceClient.add(client);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Bien bien = new Bien("maison", "A proximite du cimetiere", 80.0, 6, 3, 
				new Adresse("rue des citrons", 52000, "Chaumont", "France"));
		serviceBien.add(bien);
		Visite visite = new Visite(date);
		serviceVisite.add(visite, agentImmobilier.getIdAgent(), client.getIdClient(), bien.getIdBien());
		assertThat(serviceAgentImmobilier.isDisponible(agentImmobilier.getIdAgent(), date), 
				IsEqual.equalTo(false));
	}

}
