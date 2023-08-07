package com.StgrManager.Services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.StgrManager.Entities.Matiere;
import com.StgrManager.Entities.Professeur;
import com.StgrManager.Entities.Stagiaire;
import com.StgrManager.Repositories.MatiereRepository;
import com.StgrManager.Repositories.ProfesseurRepository;
import com.StgrManager.Repositories.StagiaireRepository;

@Service
public class ProfesseurService {

	private final ProfesseurRepository professeurRepository;
	private final MatiereRepository matiereRepository;
	private final StagiaireRepository stagiaireRepository;

	public ProfesseurService(ProfesseurRepository professeurRepository, MatiereRepository matiereRepository,
			StagiaireRepository stagiaireRepository) {
		this.professeurRepository = professeurRepository;
		this.matiereRepository = matiereRepository;
		this.stagiaireRepository = stagiaireRepository;
	}

	public List<Professeur> getProfesseur() {
		return professeurRepository.findAllActif();
	}

	public Set<Map<String, Object>> getProfesseurInfo() {
		List<Object[]> professeurInfo = professeurRepository.getProfInfo();
		Set<Map<String, Object>> professeurs = new HashSet<>();
		for (Object[] professeur: professeurInfo) {
			Map<String, Object> professeurInf = new HashMap<String, Object>();
			professeurInf.put("id",professeur[0]);
			professeurInf.put("nom",(String)professeur[1] + " " + professeur[2]);
			professeurs.add(professeurInf);
		}
		
		return professeurs;
	}

	public ResponseEntity<String> creeProfesseur(Professeur professeur) {
		if (professeurRepository.existsByNomAndPrenom(professeur.getNom(), professeur.getPrenom())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Il y a déjà un professeur avec ce nom et ce prénom");
		}

		Long numero = professeurRepository.getGrandNumero();
		if (numero == null) {
			professeur.setNumero(1L);
		} else {
			professeur.setNumero(numero + 1);
		}

		Matiere matiere = matiereRepository.findById(professeur.getMatiereId()).orElseThrow(() -> new IllegalArgumentException("Identifiant du matiere invalide"));
		professeur.setMatiere(matiere);

		Set<Stagiaire> listDesEleves = new HashSet<Stagiaire>();
		for (Long stagiaireId : professeur.getStagiaires_ids()) {
			Stagiaire stagiaire = stagiaireRepository.findById(stagiaireId).orElseThrow(() -> new IllegalArgumentException("Il n'y a aucun stagiaire avec cet identifiant : " + stagiaireId));
			stagiaire.getListe_des_professeurs().add(professeur);
			listDesEleves.add(stagiaire);
		}
		
		professeurRepository.save(professeur);
		stagiaireRepository.saveAll(listDesEleves);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	public ResponseEntity<String> updateProfesseur(Professeur professeur, Long professeurId) {
		Professeur professeurAncient = professeurRepository.findById(professeurId)
				.orElseThrow(() -> new IllegalArgumentException("Identifiant du professeur invalide"));

		if (!professeurAncient.getMatiere().getId().equals(professeur.getMatiereId())) {
			Matiere matiere = matiereRepository.findById(professeur.getMatiereId())
					.orElseThrow(() -> new IllegalArgumentException("Identifiant du matiere invalide"));
			professeurAncient.setMatiere(matiere);
		}

		if (!professeurAncient.getNom().equals(professeur.getNom())
				|| !professeurAncient.getPrenom().equals(professeur.getPrenom())) {

			if (professeurRepository.existsByNomAndPrenom(professeur.getNom(), professeur.getPrenom())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Il y a déjà un professeur avec ce nom et ce prénom");
			}
		}

		for(Stagiaire eleve : professeurAncient.getListe_des_eleves()) {
			eleve.getListe_des_professeurs().remove(professeurAncient);
		}
		Set<Stagiaire> listDesEleves = new HashSet<Stagiaire>();
		for (Long stagiaireId : professeur.getStagiaires_ids()) {
			Stagiaire stagiaire = stagiaireRepository.findById(stagiaireId).orElseThrow(() -> new IllegalArgumentException("Il n'y a aucun stagiaire avec cet identifiant : " + stagiaireId));
			stagiaire.getListe_des_professeurs().add(professeurAncient);
			listDesEleves.add(stagiaire);
		}
		
		professeurAncient.update(professeur);
		
		professeurRepository.save(professeurAncient);
			stagiaireRepository.saveAll(listDesEleves);

		return ResponseEntity.ok().build();
	}

	public ResponseEntity<String> desactiverProfesseur(Long professeurId) {

		Professeur professeur = professeurRepository.findById(professeurId)
				.orElseThrow(() -> new IllegalArgumentException("Il n'y a aucun article associé à cet identifiant"));

		professeur.setEtat("desactive");
		professeurRepository.save(professeur);
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<Void> suprimerProfesseur(Long professeurId) {
		professeurRepository.findById(professeurId)
				.orElseThrow(() -> new IllegalArgumentException("Il n'y a aucun article associé à cet identifiant"));

		professeurRepository.deleteById(professeurId);
		return ResponseEntity.ok().build();
	}

}
