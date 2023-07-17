package com.StgrManager.stagiaire;

import java.util.List;

import org.springframework.stereotype.Service;

import com.StgrManager.etablissement.Etablissement;
import com.StgrManager.etablissement.EtablissementRepository;

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
		Etablissement etablissement = etablissementRepository.findById(etablissementId)
				.orElseThrow(() -> new IllegalArgumentException("Identifiant d'etablissement invalide"));
		
		stagiaire.setEtablissement(etablissement);
		stagiaireRepository.save(stagiaire);
	}

}
