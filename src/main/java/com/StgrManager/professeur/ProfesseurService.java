package com.StgrManager.professeur;

import java.util.List;

import org.springframework.stereotype.Service;

import com.StgrManager.matiere.Matiere;
import com.StgrManager.matiere.MatiereRepository;

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

	public void creeProfesseur(Professeur professeur, Long matiereId) {
		Matiere matiere = matiereRepository.findById(matiereId)
				.orElseThrow(() -> new IllegalArgumentException("Identifiant du matiere invalide"));
		professeur.setMatiere(matiere);
		professeurRepository.save(professeur);
	}

}
