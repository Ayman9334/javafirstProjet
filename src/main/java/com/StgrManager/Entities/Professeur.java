package com.StgrManager.Entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "professeurs", uniqueConstraints = @UniqueConstraint(columnNames = { "nom", "prenom" }))
public class Professeur extends Personne {

	@ManyToOne
	@JoinColumn(name = "matiere_id", nullable = false)
	private Matiere matiere;
	@ManyToMany(mappedBy = "liste_des_professeurs", cascade = CascadeType.ALL)
	private Set<Stagiaire> liste_des_eleves;
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

	public Long getMatiereId() {
		return matiereId;
	}

	public void setMatiereId(Long matiereId) {
		this.matiereId = matiereId;
	}

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
