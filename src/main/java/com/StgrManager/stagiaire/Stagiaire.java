package com.StgrManager.stagiaire;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.StgrManager.personne.Personne;
import com.StgrManager.professeur.Professeur;

@Entity
@Table(name = "stagiaires")
public class Stagiaire extends Personne {

	@NotNull
	private LocalDate date_de_naissance;
	@Transient
	private Integer age;
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "stagiaire_prof", 
				joinColumns = @JoinColumn(name = "stagiaire_id"), 
				inverseJoinColumns = @JoinColumn(name = "professeur_id"))
	private Set<Professeur> liste_des_professeurs;

	// need login

	public Stagiaire() {
	}

	public Stagiaire(Long numero, String nom, String prenom, String adresse, LocalDate date_de_naissance) {
		this.numero = numero;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.date_de_naissance = date_de_naissance;
	}

	public Stagiaire(String nom, String prenom, String adresse, LocalDate date_de_naissance) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.date_de_naissance = date_de_naissance;
	}

	public LocalDate getDate_de_naissance() {
		return date_de_naissance;
	}

	public void setDate_de_naissance(LocalDate date_de_naissance) {
		this.date_de_naissance = date_de_naissance;
	}

	public Integer getAge() {
		return Period.between(date_de_naissance, LocalDate.now()).getYears();
	}

	public Set<Professeur> getListe_des_professeurs() {
		return liste_des_professeurs;
	}

	@Override
	public String toString() {
		return "Stagiaire [numero=" + numero + ", nom=" + nom + ", prenom=" + prenom + ", date_de_naissance="
				+ date_de_naissance + ", age=" + age + ", adresse=" + adresse + "]";
	}

}
