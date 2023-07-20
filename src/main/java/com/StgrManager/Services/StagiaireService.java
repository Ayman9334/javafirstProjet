package com.StgrManager.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.StgrManager.Entities.Etablissement;
import com.StgrManager.Entities.Stagiaire;
import com.StgrManager.Repositories.EtablissementRepository;
import com.StgrManager.Repositories.StagiaireRepository;

@Service
public class StagiaireService {

	private final StagiaireRepository stagiaireRepository;
	private final EtablissementRepository etablissementRepository;

	public StagiaireService(StagiaireRepository stagiaireRepository, EtablissementRepository etablissementRepository) {
		this.stagiaireRepository = stagiaireRepository;
		this.etablissementRepository = etablissementRepository;
	}

	public List<Stagiaire> getAllStagiaire() {
		return stagiaireRepository.findAllActif();
	}

	public void creeStagiaire(Stagiaire stagiaire, String etablissementId) {
		Integer age = stagiaire.getAge();

		if (age == null || age < 10 || age > 23) {
		    throw new IllegalStateException("Erreur dans le champ de date de naissance");
		}
		
		List<Stagiaire> stgr = stagiaireRepository.findAllByNomAndPrenom(stagiaire.getNom(),stagiaire.getPrenom());
		
		if(stgr.isEmpty()) {
			
		}

		Etablissement etablissement = etablissementRepository.findById(etablissementId)
				.orElseThrow(() -> new IllegalArgumentException("Identifiant d'etablissement invalide"));

		stagiaire.setEtablissement(etablissement);
		stagiaireRepository.save(stagiaire);
	}

}
