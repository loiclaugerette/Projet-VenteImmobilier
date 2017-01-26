package com.adaming.myapp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
@SuppressWarnings("serial")
public class Bien implements Serializable {

	//=========================
	// Attributes
	//=========================
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBien;
	private String typeBien;
	private String descriptionBien;
	private Double surface;
	private int nbrPieces;
	private int nbrChambres;
	
	@Embedded
	private Adresse adresse;
	
	private Boolean disponible;
	
	@ManyToOne
	@JoinColumn(name = "idAcheteur")
	private Client client;
	
	@OneToMany(mappedBy = "bien", fetch = FetchType.EAGER)
	private List<Visite> visites;
	
	//=========================
	// Constructor
	//=========================
	
	public Bien() {
	}

	public Bien(String typeBien, String descriptionBien, Double surface,
			int nbrPieces, int nbrChambres, Adresse adresse) {
		this.typeBien = typeBien;
		this.descriptionBien = descriptionBien;
		this.surface = surface;
		this.nbrPieces = nbrPieces;
		this.nbrChambres = nbrChambres;
		this.adresse = adresse;
		disponible = true;
	}

	//=========================
	// Getter / Setter
	//=========================

	public Long getIdBien() {
		return idBien;
	}

	public void setIdBien(Long idBien) {
		this.idBien = idBien;
	}

	public String getTypeBien() {
		return typeBien;
	}

	public void setTypeBien(String typeBien) {
		this.typeBien = typeBien;
	}

	public String getDescriptionBien() {
		return descriptionBien;
	}

	public void setDescriptionBien(String descriptionBien) {
		this.descriptionBien = descriptionBien;
	}

	public Double getSurface() {
		return surface;
	}

	public void setSurface(Double surface) {
		this.surface = surface;
	}

	public int getNbrPieces() {
		return nbrPieces;
	}

	public void setNbrPieces(int nbrPieces) {
		this.nbrPieces = nbrPieces;
	}

	public int getNbrChambres() {
		return nbrChambres;
	}

	public void setNbrChambres(int nbrChambres) {
		this.nbrChambres = nbrChambres;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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
		return "Bien [idBien=" + idBien + ", typeBien=" + typeBien
				+ ", descriptionBien=" + descriptionBien + ", surface="
				+ surface + ", nbrPieces=" + nbrPieces + ", nbrChambres="
				+ nbrChambres + ", adresse=" + adresse + ", disponible="
				+ disponible + "]";
	}
	
}
