package com.adaming.myapp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
@SuppressWarnings("serial")
public class AgentImmobilier implements Serializable {

	//=========================
	// Attributes
	//=========================
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAgent;
	private String nomAgent;
	private String prenomAgent;
	
	@OneToMany(mappedBy = "agentImmobilier",fetch=FetchType.EAGER)
	private List<Visite> visites;
	
	//=========================
	// Constructor
	//=========================
	
	public AgentImmobilier() {
	}

	public AgentImmobilier(String nomAgent, String prenomAgent) {
		this.nomAgent = nomAgent;
		this.prenomAgent = prenomAgent;
	}

	//=========================
	// Getter / Setter
	//=========================

	public Long getIdAgent() {
		return idAgent;
	}

	public void setIdAgent(Long idAgent) {
		this.idAgent = idAgent;
	}

	public String getNomAgent() {
		return nomAgent;
	}

	public void setNomAgent(String nomAgent) {
		this.nomAgent = nomAgent;
	}

	public String getPrenomAgent() {
		return prenomAgent;
	}

	public void setPrenomAgent(String prenomAgent) {
		this.prenomAgent = prenomAgent;
	}

	@JsonIgnore
	public List<Visite> getVisites() {
		return visites;
	}

	@JsonSetter
	public void setVisites(List<Visite> visites) {
		this.visites = new ArrayList<Visite>(new HashSet<Visite>(visites));
	}

	//=========================
	// Methods
	//=========================

	@Override
	public String toString() {
		return "AgentImmobilier [idAgent=" + idAgent + ", nomAgent=" + nomAgent
				+ ", prenomAgent=" + prenomAgent + "]";
	}
	
}
