package com.StgrManager.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "etablissements")
public class Etablissement extends BaseEntity {

	@NotEmpty(message = "Le libelle ne peut pas être vide")
	@Size(max = 200, message = "Le libelle doit être inférieure à 200 caractères")
	@Column(unique = true, nullable = false)
	private String libelle;
	@Size(max = 255, message = "L'adresse doit être inférieure à 255 caractères")
	private String adresse;

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

	public void update(Etablissement etablissement) {
		this.libelle = etablissement.getLibelle();
		this.adresse = etablissement.getAdresse();
	}
}
