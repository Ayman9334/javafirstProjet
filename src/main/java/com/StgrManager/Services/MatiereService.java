package com.StgrManager.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.StgrManager.Entities.Matiere;
import com.StgrManager.Repositories.MatiereRepository;

@Service
public class MatiereService {

	private final MatiereRepository matiereRepository;

	public MatiereService(MatiereRepository matiereRepository) {
		this.matiereRepository = matiereRepository;
	}

	public List<Matiere> getMatiere() {
		return matiereRepository.findAllActif();
	}

	public void creeMatiere(Matiere matiere) {
		matiereRepository.save(matiere);
	}

}
