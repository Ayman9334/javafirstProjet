package com.StgrManager.Entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "professeurs", uniqueConstraints = @UniqueConstraint(columnNames = {"nom","prenom"}))
public class Professeur extends Personne {

	@ManyToOne
	@JoinColumn(name = "matiere_id")
	private Matiere matiere;
	@ManyToMany(mappedBy = "liste_des_professeurs", cascade = CascadeType.ALL)
	private Set<Stagiaire> liste_des_eleves;

	public Professeur() {
	}

	public Professeur(Long numero, String nom, String prenom, String adresse) {
		this.setNumero(numero);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setAdresse(adresse);
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
	
	public void update(String nom, String prenom, String adresse) {
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setAdresse(adresse);
	}

}
