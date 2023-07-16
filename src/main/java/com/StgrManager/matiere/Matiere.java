package com.StgrManager.matiere;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.StgrManager.professeur.Professeur;

@Entity
@Table(name = "matieres")
public class Matiere {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numero;
	@NotNull
	@Size(max = 200, message = "La matière doit être inférieure à 200 caractères")
	private String libelle;
	@OneToMany(mappedBy = "matiere", cascade = CascadeType.PERSIST)
	private Set<Professeur> liste_des_profs;
	private String etat = "actif";
	
	public Matiere() {
	}
	
	public Matiere(Long numero, String libelle) {
		this.numero = numero;
		this.libelle = libelle;
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
	
	public String getEtat() {
		return etat;
	}
	
	public void setEtat(String etat) {
		this.etat = etat;
	}
	
	@Override
	public String toString() {
		return "Matiere [numero=" + numero + ", libelle=" + libelle + ", liste_des_profs=" + liste_des_profs + ", etat="
				+ etat + "]";
	}
	
}
