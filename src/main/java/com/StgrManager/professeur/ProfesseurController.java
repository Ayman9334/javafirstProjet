package com.StgrManager.professeur;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	@PostMapping
	public ResponseEntity<Void> creeProfesseur(@RequestBody Professeur professeur){
		professeurService.creeProfesseur(professeur);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
