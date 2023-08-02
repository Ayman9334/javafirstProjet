package com.StgrManager.Entities;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "stagiaires", uniqueConstraints = @UniqueConstraint(columnNames = { "nom",
		"prenom" }))
public class Stagiaire extends Personne implements UserDetails {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "Ce champ ne peut pas être vide")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date_de_naissance;

	@Transient
	private Integer age;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "stagiaire_prof", joinColumns = @JoinColumn(name = "stagiaire_id"), inverseJoinColumns = @JoinColumn(name = "professeur_id"))
	private Set<Professeur> liste_des_professeurs;
	
	@ManyToOne
	@JoinColumn(name = "etablissement_id")
	private Etablissement etablissement;

	@Transient
	private Long etablissementId;
	
	@NotEmpty(message = "Ce champ ne peut pas être vide")
	@Column(nullable = false)
	private String login;

	@Size(max = 20, message = "Ce champ ne peut pas dépasser 20 caractères")
	@NotEmpty(message = "Ce champ ne peut pas être vide")
	@Column(nullable = false)
	private String mot_de_passe;
	
	public Stagiaire() {
	}

	public Stagiaire(String nom, String prenom, String adresse,
			LocalDate date_de_naissance, String login, String mot_de_passe, Long etablissementId) {
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setAdresse(adresse);
		this.date_de_naissance = date_de_naissance;
		this.login = login;
		this.mot_de_passe = mot_de_passe;
		this.setEtablissementId(etablissementId);
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

	public Long getEtablissementId() {
		return etablissementId;
	}

	public void setEtablissementId(Long etablissementId) {
		this.etablissementId = etablissementId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMot_de_passe() {
		return mot_de_passe;
	}

	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}

	public void update(Stagiaire stagiaire) {
		this.setNom(stagiaire.getNom());
		this.setPrenom(stagiaire.getPrenom());
		this.setAdresse(stagiaire.getAdresse());
		this.date_de_naissance = stagiaire.getDate_de_naissance();
		this.login = stagiaire.getLogin();
		this.mot_de_passe = stagiaire.getMot_de_passe();
	}
	
	// userDetails methods

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return mot_de_passe;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
