package com.StgrManager.professeur;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProfesseurService {

	private final ProfesseurRepository professeurRepository;

	public ProfesseurService(ProfesseurRepository professeurRepository) {
		this.professeurRepository = professeurRepository;
	}

	public List<Professeur> getProfesseur() {
		return professeurRepository.findAllActif();
	}

	public void creeProfesseur(Professeur professeur) {
		professeurRepository.save(professeur);
	}

}
