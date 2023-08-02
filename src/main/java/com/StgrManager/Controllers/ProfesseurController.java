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

import com.StgrManager.Entities.Professeur;
import com.StgrManager.Services.ProfesseurService;

@RestController
@RequestMapping("/professeur")
public class ProfesseurController {
	private final ProfesseurService professeurService;

	public ProfesseurController(ProfesseurService professeurService) {
		this.professeurService = professeurService;
	}

	@GetMapping
	public List<Professeur> getProfesseur(){
		return professeurService.getProfesseur();
	}
	
	@PostMapping
	public ResponseEntity<String> creeProfesseur(@Valid @RequestBody Professeur professeur){
		return professeurService.creeProfesseur(professeur);
	}
	
	@PutMapping("/{professeurId}")
	public ResponseEntity<String> updateProfesseur(@Valid @RequestBody Professeur professeur, @PathVariable Long professeurId){
		return professeurService.updateProfesseur(professeur,professeurId);
	}
	
	@GetMapping("/desactiver/{professeurId}")
	public ResponseEntity<String> desactiverProfesseur(@PathVariable Long professeurId){
		return professeurService.desactiverProfesseur(professeurId);
	}
	
	@DeleteMapping("/{professeurId}")
	public ResponseEntity<Void> suprimerProfesseur(@PathVariable Long professeurId){
		return professeurService.suprimerProfesseur(professeurId);
	}
	
}
