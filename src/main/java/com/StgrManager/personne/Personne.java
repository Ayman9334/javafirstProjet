package com.StgrManager.personne;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class Personne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long numero;
	@NotEmpty(message = "Ce champ ne peut pas être vide")
	@Column(nullable = false)
	@Size(max = 120, message = "Le nom doit être inférieure à 120 caractères")
	protected String nom;
	@Size(max = 120, message = "Le prenom doit être inférieure à 120 caractères")
	protected String prenom;
	@Size(max = 255, message = "L'adresse doit être inférieure à 255 caractères")
	protected String adresse;
	private String etat = "actif";

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
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
}
