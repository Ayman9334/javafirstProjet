package com.StgrManager.Entities;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "stagiaires", uniqueConstraints = @UniqueConstraint(columnNames = { "nom",
		"prenom" }))
public class Stagiaire extends Personne implements UserDetails {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "La date de naissanse ne peut pas être vide")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate date_de_naissance;

	@Transient
	private Integer age;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "stagiaire_prof", joinColumns = @JoinColumn(name = "stagiaire_id"), inverseJoinColumns = @JoinColumn(name = "professeur_id"))
	@JsonIgnore
	private Set<Professeur> liste_des_professeurs;

	@ManyToOne
	@JoinColumn(name = "etablissement_id")
	@JsonIgnore
	private Etablissement etablissement;

	@Transient
	private Map<String, Object> etablissementInfo;

	@Transient
	private Set<Map<String, Object>> liste_des_professeursInfo;

	@Transient
	private Long etablissementId;

	@Transient
	private Set<Long> professeursIds;

	@NotEmpty(message = "Le login ne peut pas être vide")
	@Column(nullable = false)
	private String login;

	@Size(max = 20, message = "Le mot de passe ne peut pas dépasser 20 caractères")
	@NotEmpty(message = "Le mot de passe ne peut pas être vide")
	@Column(nullable = false)
	private String mot_de_passe;

	public Stagiaire() {
	}

	public Stagiaire(String nom, String prenom, String adresse,
			LocalDate date_de_naissance, String login, String mot_de_passe,
			Long etablissementId, Set<Long> professeursIds) {
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setAdresse(adresse);
		this.date_de_naissance = date_de_naissance;
		this.login = login;
		this.mot_de_passe = mot_de_passe;
		this.etablissementId = etablissementId;
		this.professeursIds = professeursIds;
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

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	public Long getEtablissementId() {
		return etablissementId;
	}
	
	public void setEtablissementId(Long etablissementId) {
		this.etablissementId = etablissementId;
	}

	public Map<String, Object> getEtablissementInfo() {
		etablissementInfo = new HashMap<>();
		etablissementInfo.put("id", etablissement.getId());
		etablissementInfo.put("libelle", etablissement.getLibelle());
		return etablissementInfo;
	}

	public Set<Map<String, Object>> getListe_des_professeursInfo() {
		liste_des_professeursInfo = new HashSet<>();
		if (liste_des_professeurs != null) {
			for (Professeur prof : liste_des_professeurs) {
				Map<String, Object> profInfo = new HashMap<>();
				profInfo.put("id", prof.getId());
				profInfo.put("nom", prof.getNom());
				profInfo.put("prenom", prof.getPrenom());
				liste_des_professeursInfo.add(profInfo);
			}
		}
		return liste_des_professeursInfo;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	public Set<Long> getProfesseursIds() {
		return professeursIds;
	}

	public void setProfesseursIds(Set<Long> professeursIds) {
		this.professeursIds = professeursIds;
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
