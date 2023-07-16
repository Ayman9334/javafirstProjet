package com.StgrManager.etablissement;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EtablissementService {
	
	private final EtablissementRepository etablissementRepository;

	public EtablissementService(EtablissementRepository etablissementRepository) {
		this.etablissementRepository = etablissementRepository;
	}

	public List<Etablissement> getEtablissement() {
		return etablissementRepository.findAllActif();
	}

	public void creeEtablissement(Etablissement etablissement) {
		etablissementRepository.save(etablissement);
	}
	
}
