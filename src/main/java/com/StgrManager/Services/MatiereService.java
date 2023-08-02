package com.StgrManager.Services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	public ResponseEntity<Void> creeMatiere(Matiere matiere) {
		Long GrandNumero = matiereRepository.getGrandNumero();
		if (GrandNumero == null) {
			matiere.setNumero(1L);
		}else {
			matiere.setNumero(GrandNumero + 1);
		}
		matiereRepository.save(matiere);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	public ResponseEntity<Void> updateMatiere(Matiere matiere, Long matiereId) {
		Matiere matiereAncient = matiereRepository.findById(matiereId)
				.orElseThrow(() -> new IllegalStateException("Il n'y a aucun matière avec ce ID"));
		matiereAncient.setLibelle(matiere.getLibelle());
		matiereRepository.save(matiereAncient);
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<Void> desactiverMatiere(Long matiereId) {
		Matiere matiere = matiereRepository.findById(matiereId)
				.orElseThrow(() -> new IllegalStateException("il n'y a aucun article associé à cet identifiant"));
		matiere.setEtat("desactive");
		matiereRepository.save(matiere);
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<Void> suprimerMatiere(Long matiereId) {
		matiereRepository.findById(matiereId)
				.orElseThrow(() -> new IllegalStateException("il n'y a aucun article associé à cet identifiant"));
		matiereRepository.deleteById(matiereId);
		return ResponseEntity.ok().build();
	}

}
