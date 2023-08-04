package com.StgrManager.Entities;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "professeurs", uniqueConstraints = @UniqueConstraint(columnNames = { "nom", "prenom" }))
public class Professeur extends Personne {

	@ManyToOne
	@JoinColumn(name = "matiere_id", nullable = false)
	@JsonIgnore
	private Matiere matiere;
	@ManyToMany(mappedBy = "liste_des_professeurs", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Stagiaire> liste_des_eleves;
	@Transient
	private Map<String, Object> matiereInfo;
	@Transient
	private Set<Map<String, Object>> liste_des_elevesInfo;
	@Transient
	private Long matiereId;
	@Transient
	private Set<Long> stagiaires_ids;

	public Professeur() {
	}

	public Professeur(String nom, String prenom, String adresse, Long matiereId, Set<Long> stagiaires_ids) {
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setAdresse(adresse);
		this.matiereId = matiereId;
		this.stagiaires_ids = stagiaires_ids;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Set<Stagiaire> getListe_des_eleves() {
		return liste_des_eleves;
	}

	public void setListe_des_eleves(Set<Stagiaire> liste_des_eleves) {
		this.liste_des_eleves = liste_des_eleves;
	}

	public Map<String, Object> getMatiereInfo() {
		matiereInfo = new HashMap<>();
		matiereInfo.put("id", matiere.getId());
		matiereInfo.put("libelle", matiere.getLibelle());
		return matiereInfo;
	}

	public Set<Map<String, Object>> getListe_des_elevesInfo() {
		liste_des_elevesInfo = new HashSet<>();
		if (liste_des_eleves != null) {
			for (Stagiaire stagiaire : liste_des_eleves) {
				Map<String, Object> eleve = new HashMap<>();
				eleve.put("id", stagiaire.getId());
				eleve.put("nom", stagiaire.getNom());
				eleve.put("prenom", stagiaire.getPrenom());
				liste_des_elevesInfo.add(eleve);
			}
		}
		return liste_des_elevesInfo;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	public Long getMatiereId() {
		return matiereId;
	}

	public void setMatiereId(Long matiereId) {
		this.matiereId = matiereId;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	public Set<Long> getStagiaires_ids() {
		return stagiaires_ids;
	}

	public void setStagiaires_ids(Set<Long> stagiaires_ids) {
		this.stagiaires_ids = stagiaires_ids;
	}

	public void update(Professeur professeur) {
		this.setNom(professeur.getNom());
		this.setPrenom(professeur.getPrenom());
		this.setAdresse(professeur.getAdresse());
	}

}
