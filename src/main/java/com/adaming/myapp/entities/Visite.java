package com.adaming.myapp.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@SuppressWarnings("serial")
public class Visite implements Serializable {

	//=========================
	// Attributes
	//=========================
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVisite;
	private Date dateVisite;
	
	@ManyToOne
	@JoinColumn(name = "idAgentImmobilier")
	private AgentImmobilier agentImmobilier;

	@ManyToOne
	@JoinColumn(name = "idClient")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name = "idBien")
	private Bien bien;
	
	//=========================
	// Constructor
	//=========================
	
	public Visite() {
	}

	public Visite(Date dateVisite) {
		this.dateVisite = dateVisite;
	}

	//=========================
	// Getter / Setter
	//=========================

	public Long getIdVisite() {
		return idVisite;
	}

	public void setIdVisite(Long idVisite) {
		this.idVisite = idVisite;
	}

	public Date getDateVisite() {
		return dateVisite;
	}

	public void setDateVisite(Date dateVisite) {
		this.dateVisite = dateVisite;
	}

	public AgentImmobilier getAgentImmobilier() {
		return agentImmobilier;
	}

	public void setAgentImmobilier(AgentImmobilier agentImmobilier) {
		this.agentImmobilier = agentImmobilier;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Bien getBien() {
		return bien;
	}

	public void setBien(Bien bien) {
		this.bien = bien;
	}

	//=========================
	// Methods
	//=========================

	@Override
	public String toString() {
		return "Visite [idVisite=" + idVisite + ", dateVisite=" + dateVisite
				+ "]";
	}
	
}
