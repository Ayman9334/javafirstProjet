package com.StgrManager.Services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.StgrManager.Entities.Matiere;
import com.StgrManager.Entities.Professeur;
import com.StgrManager.Repositories.MatiereRepository;
import com.StgrManager.Repositories.ProfesseurRepository;

@Service
public class ProfesseurService {

	private final ProfesseurRepository professeurRepository;
	private final MatiereRepository matiereRepository;

	public ProfesseurService(ProfesseurRepository professeurRepository, MatiereRepository matiereRepository) {
		this.professeurRepository = professeurRepository;
		this.matiereRepository = matiereRepository;
	}

	public List<Professeur> getProfesseur() {
		return professeurRepository.findAllActif();
	}

	public ResponseEntity<String> creeProfesseur(Professeur professeur) {
		Matiere matiere = matiereRepository.findById(professeur.getMatiereId())
				.orElseThrow(() -> new IllegalArgumentException("Identifiant du matiere invalide"));

		if (professeurRepository.existsByNomAndPrenom(professeur.getNom(), professeur.getPrenom())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Il y a déjà un professeur avec ce nom et ce prénom");
		}

		Long numero = professeurRepository.getGrandNumero();
		if (numero == null) {
			professeur.setNumero(1L);
		} else {
			professeur.setNumero(numero + 1);
		}

		professeur.setMatiere(matiere);
		professeurRepository.save(professeur);
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

		professeurAncient.update(professeur);
		professeurRepository.save(professeurAncient);

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
