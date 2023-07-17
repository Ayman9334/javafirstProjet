package com.StgrManager.stagiaire;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.StgrManager.etablissement.Etablissement;
import com.StgrManager.personne.Personne;
import com.StgrManager.professeur.Professeur;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "stagiaires" , uniqueConstraints = @UniqueConstraint(columnNames = {"nom","prenom"}))
public class Stagiaire extends Personne {

	@NotNull(message = "Ce champ ne peut pas être vide")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date_de_naissance;
	@Transient
	@Min(value = 10, message = "L'age minimale des stagiaires est 10ans")
    @Max(value = 23, message = "L'age maximale des stagiaires est 23ans")
	private Integer age;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "stagiaire_prof", 
				joinColumns = @JoinColumn(name = "stagiaire_id"), 
				inverseJoinColumns = @JoinColumn(name = "professeur_id"))
	private Set<Professeur> liste_des_professeurs;
	@ManyToOne
	@JoinColumn(name = "etablissement_id")
	private Etablissement etablissement;
	

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

	public Etablissement getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(Etablissement etablissement) {
		this.etablissement = etablissement;
	}

	public void setListe_des_professeurs(Set<Professeur> liste_des_professeurs) {
		this.liste_des_professeurs = liste_des_professeurs;
	}

	@Override
	public String toString() {
		return "Stagiaire [numero=" + numero + ", nom=" + nom + ", prenom=" + prenom + ", date_de_naissance="
				+ date_de_naissance + ", age=" + age + ", adresse=" + adresse + "]";
	}

}
