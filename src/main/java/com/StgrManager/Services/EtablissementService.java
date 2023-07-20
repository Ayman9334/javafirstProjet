package com.StgrManager.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.StgrManager.Entities.Etablissement;
import com.StgrManager.Repositories.EtablissementRepository;

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
