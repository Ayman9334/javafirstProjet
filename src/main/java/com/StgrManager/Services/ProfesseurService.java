package com.StgrManager.Services;

import java.util.List;

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

	public void creeProfesseur(Professeur professeur, Long matiereId) {
		Matiere matiere = matiereRepository.findById(matiereId)
				.orElseThrow(() -> new IllegalArgumentException("Identifiant du matiere invalide"));
		professeur.setMatiere(matiere);
		professeurRepository.save(professeur);
	}

}
