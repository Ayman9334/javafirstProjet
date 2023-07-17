package com.StgrManager.professeur;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	@PostMapping(path = "/{matiereId}")
	public ResponseEntity<Void> creeProfesseur(@Valid @RequestBody Professeur professeur, @PathVariable Long matiereId){
		professeurService.creeProfesseur(professeur,matiereId);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
