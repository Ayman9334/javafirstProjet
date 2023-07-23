package com.StgrManager.Entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@MappedSuperclass
public abstract class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String etat = "actif";

	@ManyToOne
	@JoinColumn(name = "createur_id")
	private Stagiaire createur;

	@ManyToOne
	@JoinColumn(name = "dernier_modificateur_id")
	private Stagiaire dernier_modificateur;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	@CreationTimestamp
	private Date cree_le;

	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date modifie_le;

	@PrePersist
	public void prePersist() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Stagiaire authenticatedStagiaire = (Stagiaire) authentication.getPrincipal();
		this.createur = authenticatedStagiaire;
		this.dernier_modificateur = authenticatedStagiaire;
	}

	@PreUpdate
	public void preUpdate() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Stagiaire authenticatedStagiaire = (Stagiaire) authentication.getPrincipal();
		this.dernier_modificateur = authenticatedStagiaire;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		if (etat == "actif" || etat == "desactive") {
			this.etat = etat;
		} else {
			throw new IllegalArgumentException("Valeur de statut invalide. Le statut doit être soit 'actif' ou 'desactive'");
		}
	}

	public Stagiaire getCreateur() {
		return createur;
	}

	public void setCreateur(Stagiaire createur) {
		this.createur = createur;
	}

	public Stagiaire getDernier_modificateur() {
		return dernier_modificateur;
	}

	public void setDernier_modificateur(Stagiaire dernier_modificateur) {
		this.dernier_modificateur = dernier_modificateur;
	}

	public Date getCree_le() {
		return cree_le;
	}

	public void setCree_le(Date cree_le) {
		this.cree_le = cree_le;
	}

	public Date getModifie_le() {
		return modifie_le;
	}

	public void setModifie_le(Date modifie_le) {
		this.modifie_le = modifie_le;
	}

}
