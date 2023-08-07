package com.StgrManager.Services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.StgrManager.Entities.Etablissement;
import com.StgrManager.Entities.Professeur;
import com.StgrManager.Entities.Stagiaire;
import com.StgrManager.Repositories.EtablissementRepository;
import com.StgrManager.Repositories.ProfesseurRepository;
import com.StgrManager.Repositories.StagiaireRepository;

@Service
public class StagiaireService {

	private final StagiaireRepository stagiaireRepository;
	private final EtablissementRepository etablissementRepository;
	private final ProfesseurRepository professeurRepository;

	public StagiaireService(StagiaireRepository stagiaireRepository, EtablissementRepository etablissementRepository,
			ProfesseurRepository professeurRepository) {
		this.stagiaireRepository = stagiaireRepository;
		this.etablissementRepository = etablissementRepository;
		this.professeurRepository = professeurRepository;
	}

	public List<Stagiaire> getAllStagiaire() {
		return stagiaireRepository.findAllActif();
	}
	
	public Set<Map<String, Object>> getStagiaireInfo() {
		List<Object[]> stagiairesInfo = stagiaireRepository.getStagiaireInfo();
		Set<Map<String, Object>> stagiaires = new HashSet<>();
		for (Object[] stagiaireInfo : stagiairesInfo) {
			Map<String, Object> stagiaire = new HashMap<String, Object>();
			stagiaire.put("id", stagiaireInfo[0]);
			stagiaire.put("nom", stagiaireInfo[1]);
			stagiaires.add(stagiaire);
		}
		
		return stagiaires;
	}

	public ResponseEntity<String> creeStagiaire(Stagiaire stagiaire) {

		Integer age = stagiaire.getAge();
		if (age == null || age < 10 || age > 23) {
			return ResponseEntity.badRequest().body("Le stagiaire devez avoir entre 10 et 23 ans");
		}

		if (stagiaireRepository.existsByLogin(stagiaire.getLogin())) {
			return ResponseEntity.badRequest().body("Il y a déjà un stagiaire avec ce login");
		}

		if (stagiaireRepository.existsByNomAndPrenom(stagiaire.getNom(), stagiaire.getPrenom())) {
			return ResponseEntity.badRequest().body("Il y a déjà un stagiaire avec ce nom et ce prénom");
		}

		Long numero = stagiaireRepository.getGrandNumero();
		if (numero == null) {
			stagiaire.setNumero(1L);
		} else {
			stagiaire.setNumero(numero + 1);
		}

		Etablissement etablissement = etablissementRepository.findById(stagiaire.getEtablissementId())
				.orElseThrow(() -> new IllegalArgumentException("Identifiant d'etablissement invalide"));
		stagiaire.setEtablissement(etablissement);

		Set<Professeur> professeurs = new HashSet<>();
		for (Long profId : stagiaire.getProfesseursIds()) {
			Professeur professeur = professeurRepository.findById(profId).orElseThrow(
					() -> new IllegalArgumentException("Il n'y a aucun professeur avec cet identifiant : " + profId));
			professeurs.add(professeur);
		}
		stagiaire.setListe_des_professeurs(professeurs);

		stagiaireRepository.save(stagiaire);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	public ResponseEntity<String> updateStagiaire(Stagiaire stagiaire, Long stagiaireId) {
		Stagiaire stagiaireAncient = stagiaireRepository.findById(stagiaireId)
				.orElseThrow(() -> new IllegalArgumentException("Identifiant du stagiaire invalide"));

		Integer age = stagiaire.getAge();
		if (age == null || age < 10 || age > 23) {
			return ResponseEntity.badRequest().body("Le stagiaire devez avoir entre 10 et 23 ans");
		}

		if (!stagiaireAncient.getLogin().equals(stagiaire.getLogin())) {
			if (stagiaireRepository.existsByLogin(stagiaire.getLogin())) {
				return ResponseEntity.badRequest().body("Il y a déjà un stagiaire avec ce login");
			}
		}

		if (!stagiaireAncient.getNom().equals(stagiaire.getNom())
				|| stagiaireAncient.getPrenom() == null
				|| !stagiaireAncient.getPrenom().equals(stagiaire.getPrenom())) {
			if (stagiaireRepository.existsByNomAndPrenom(stagiaire.getNom(), stagiaire.getPrenom())) {
				return ResponseEntity.badRequest().body("Il y a déjà un stagiaire avec ce nom et ce prénom");
			}
		}

		if (!stagiaireAncient.getEtablissement().getId().equals(stagiaire.getEtablissementId())) {
			Etablissement etablissement = etablissementRepository.findById(stagiaire.getEtablissementId())
					.orElseThrow(() -> new IllegalArgumentException("Identifiant d'etablissement invalide"));
			stagiaireAncient.setEtablissement(etablissement);
		}

		Set<Professeur> professeurs = new HashSet<>();
		for (Long profId : stagiaire.getProfesseursIds()) {
			Professeur professeur = professeurRepository.findById(profId).orElseThrow(
					() -> new IllegalArgumentException("Il n'y a aucun professeur avec cet identifiant : " + profId));
			professeurs.add(professeur);
		}
		stagiaireAncient.setListe_des_professeurs(professeurs);

		stagiaireAncient.update(stagiaire);
		stagiaireRepository.save(stagiaireAncient);
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<Void> desactiverStagiaire(Long stagiaireId) {
		Stagiaire stagiaire = stagiaireRepository.findById(stagiaireId)
				.orElseThrow(() -> new IllegalArgumentException("Identifiant d'etablissement invalide"));

		stagiaire.setEtat("desactive");
		stagiaireRepository.save(stagiaire);
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<Void> suprimerStagiaire(Long stagiaireId) {
		stagiaireRepository.findById(stagiaireId)
				.orElseThrow(() -> new IllegalArgumentException("Identifiant d'etablissement invalide"));

		stagiaireRepository.deleteById(stagiaireId);
		return ResponseEntity.ok().build();
	}

}
