package com.StgrManager.Entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "matieres")
public class Matiere extends BaseEntity {

	@NotNull(message = "Le numero ne peut pas être vide")
	@Column(nullable = false, unique = true)
	private Long numero;
	@NotEmpty(message = "Le libelle ne peut pas être vide")
	@Size(max = 200, message = "La matière doit être inférieure à 200 caractères")
	@Column(nullable = false)
	private String libelle;
	@OneToMany(mappedBy = "matiere", cascade = CascadeType.ALL)
	private Set<Professeur> liste_des_profs;

	public Matiere() {
	}

	public Matiere(Long numero, String libelle) {
		this.numero = numero;
		this.libelle = libelle;
	}

	public Matiere(String libelle) {
		this.libelle = libelle;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Set<Professeur> getListe_des_profs() {
		return liste_des_profs;
	}

}
