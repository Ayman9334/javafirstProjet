package com.StgrManager.Entities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "matieres")
public class Matiere extends BaseEntity {

	@Column(nullable = false, unique = true, updatable = false)
	private Long numero;
	
	@NotEmpty(message = "Le libelle ne peut pas être vide")
	@Size(max = 200, message = "La matière doit être inférieure à 200 caractères")
	@Column(nullable = false)
	private String libelle;
	
	@OneToMany(mappedBy = "matiere", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Professeur> liste_des_profs;

	@Transient
	private Set<Map<String, Object>> liste_des_profsInfo;
	
	public Matiere() {
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

	public Set<Map<String, Object>> getListe_des_profsInfo() {
		liste_des_profsInfo = new HashSet<>();
		if (liste_des_profs != null) {
			for (Professeur prof : liste_des_profs) {
				Map<String,Object> profInfo = new HashMap<>();
				profInfo.put("id", prof.getId());
				profInfo.put("nom", prof.getNom());
				profInfo.put("prenom", prof.getPrenom());
				liste_des_profsInfo.add(profInfo);
			}
		}
		return liste_des_profsInfo;
	}

}
