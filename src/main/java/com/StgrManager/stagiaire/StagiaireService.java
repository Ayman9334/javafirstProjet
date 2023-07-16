package com.StgrManager.stagiaire;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class StagiaireService {
	
	private final StagiaireRepository stagiaireRepository;

	public StagiaireService(StagiaireRepository stagiaireRepository) {
		this.stagiaireRepository = stagiaireRepository;
	}

	public List<Stagiaire> getAllStagaire() {
		return stagiaireRepository.findAllActif();
	}
	
}
