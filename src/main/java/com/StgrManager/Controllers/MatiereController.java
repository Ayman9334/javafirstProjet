package com.StgrManager.Controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.StgrManager.Entities.Matiere;
import com.StgrManager.Services.MatiereService;

@RestController
@RequestMapping("/matiere")
public class MatiereController {

	private final MatiereService matiereService;

	public MatiereController(MatiereService matiereService) {
		this.matiereService = matiereService;
	}

	@GetMapping
	public List<Matiere> getMatiere() {
		return matiereService.getMatiere();
	}

	@PostMapping
	public ResponseEntity<Void> creeMatiere(@Valid @RequestBody Matiere matiere) {
		return matiereService.creeMatiere(matiere);
	}

	@PutMapping(path = "/{matiereId}")
	public ResponseEntity<Void> updateMatiere(@Valid @RequestBody Matiere matiere, @PathVariable Long matiereId) {
		return matiereService.updateMatiere(matiere, matiereId);
	}
	
	@PutMapping(path = "/desactiver/{matiereId}")
	public ResponseEntity<Void> desactiverMatiere(@PathVariable Long matiereId){
		return matiereService.desactiverMatiere(matiereId);
	}
	
	@DeleteMapping(path = "/{matiereId}")
	public ResponseEntity<Void> suprimerMatiere(@PathVariable Long matiereId) {
		return matiereService.suprimerMatiere(matiereId);
	}
}
