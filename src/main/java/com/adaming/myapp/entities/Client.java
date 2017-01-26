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
public class Client implements Serializable {

	//=========================
	// Attributes
	//=========================
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idClient;
	private String nomClient;
	private String prenomClient;
	
	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
	private List<Bien> biens;
	
	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
	private List<Visite> visites;
		
	//=========================
	// Constructor
	//=========================
	
	public Client() {
	}

	public Client(String nomClient, String prenomClient) {
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
	}

	//=========================
	// Getter / Setter
	//=========================

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getPrenomClient() {
		return prenomClient;
	}

	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}

	@JsonIgnore
	public List<Bien> getBiens() {
		return new ArrayList<Bien>(new HashSet<Bien>(biens));
	}

	@JsonSetter
	public void setBiens(List<Bien> biens) {
		this.biens = biens;
	}

	@JsonIgnore
	public List<Visite> getVisites() {
		return new ArrayList<Visite>(new HashSet<Visite>(visites));
	}

	@JsonSetter
	public void setVisites(List<Visite> visites) {
		this.visites = visites;
	}

	//=========================
	// Methods
	//=========================

	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", nomClient=" + nomClient
				+ ", prenomClient=" + prenomClient + "]";
	}
	
}
