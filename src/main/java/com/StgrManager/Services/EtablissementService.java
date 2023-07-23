package com.StgrManager.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	public ResponseEntity<Map<String, String>> creeEtablissement(Etablissement etablissement) {

		if (etablissementRepository.existsByLibelle(etablissement.getLibelle())) {
			Map<String, String> response = new HashMap<>();
			response.put("libelle", "L'élément existe déjà avec ce libelle");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		etablissementRepository.save(etablissement);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	public ResponseEntity<Map<String, String>> updateEtablissement(Etablissement etablissement, Long etablissementId) {
		Etablissement etablissementAncient = etablissementRepository.findById(etablissementId)
				.orElseThrow(() -> new IllegalStateException("Il n'y a aucun établissement avec ce ID"));

		if (!etablissementAncient.getLibelle().equals(etablissement.getLibelle())) {
			if (etablissementRepository.existsByLibelle(etablissement.getLibelle())) {
				Map<String, String> response = new HashMap<>();
				response.put("libelle", "L'élément existe déjà avec ce libelle");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
		}

		etablissementAncient.update(etablissement);
		etablissementRepository.save(etablissementAncient);
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<Void> desactiverEtablissement(Long etablissementId) {
		Etablissement etablissement = etablissementRepository.findById(etablissementId)
				.orElseThrow(() -> new IllegalStateException("il n'y a aucun article associé à cet identifiant"));
		etablissement.setEtat("desactive");
		etablissementRepository.save(etablissement);
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<Void> suprimerEtablissement(Long etablissementId) {
		etablissementRepository.findById(etablissementId)
				.orElseThrow(() -> new IllegalStateException("il n'y a aucun article associé à cet identifiant"));
		etablissementRepository.deleteById(etablissementId);
		return ResponseEntity.ok().build();
	}

}
