package com.StgrManager.professeur;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.StgrManager.matiere.Matiere;
import com.StgrManager.personne.Personne;
import com.StgrManager.stagiaire.Stagiaire;

@Entity
@Table(name = "professeurs")
public class Professeur extends Personne {

	@NotNull
	@ManyToOne
	@JoinColumn(name = "matiere_id")
	private Matiere matiere;
	@ManyToMany(mappedBy = "liste_des_professeurs")
	private Set<Stagiaire> liste_des_eleves;

	public Professeur() {
	}

	public Professeur(Long numero, String nom, String prenom, String adresse) {
		this.numero = numero;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
	}

	public Professeur(String nom, String prenom, String adresse) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
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
	
	@Override
	public String toString() {
		return "Professeur [numero=" + numero + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + "]";
	}

}
