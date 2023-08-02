package com.StgrManager.Entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class Personne extends BaseEntity {

	@Column(nullable = false, unique = true)
	private Long numero;
	@NotEmpty(message = "Le nom ne peut pas être vide")
	@Column(nullable = false)
	@Size(max = 120, message = "Le nom doit être inférieure à 120 caractères")
	private String nom;
	@Size(max = 120, message = "Le prenom doit être inférieure à 120 caractères")
	private String prenom;
	@Size(max = 255, message = "L'adresse doit être inférieure à 255 caractères")
	private String adresse;

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

}
