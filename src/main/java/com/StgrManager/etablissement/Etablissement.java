package com.StgrManager.etablissement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "etablissements")
public class Etablissement {
	
	@Id
	@NotNull
	@Size(max = 200, message = "L'etblissement doit être inférieure à 200 caractères")
	@Column(unique=true)
	private String libelle;
	@Size(max = 255, message = "L'adresse doit être inférieure à 255 caractères")
	private String adresse;
	private String etat = "actif";
	
	
	
	public Etablissement() {
	}

	public Etablissement(String libelle, String adresse) {
		this.libelle = libelle;
		this.adresse = adresse;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	@Override
	public String toString() {
		return "Etablissement [libelle=" + libelle + ", adresse=" + adresse + ", etat=" + etat + "]";
	}

}
