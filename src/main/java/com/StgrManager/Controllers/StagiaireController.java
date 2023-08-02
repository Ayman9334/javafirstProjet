package com.StgrManager.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.StgrManager.Entities.Stagiaire;
import com.StgrManager.Services.StagiaireService;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/stagiaire")
public class StagiaireController {
	private final StagiaireService stagiaireService;

	public StagiaireController(StagiaireService stagiaireService) {
		this.stagiaireService = stagiaireService;
	}
	
	@GetMapping
	public List<Stagiaire> getAllStagiaire() {
		return stagiaireService.getAllStagiaire();
	}
	
	@PostMapping
	public ResponseEntity<String> creeStagiaire(@Valid @RequestBody Stagiaire stagiaire){
		return stagiaireService.creeStagiaire(stagiaire);
	}
	
	@PutMapping("/{stagiaireId}")
	public ResponseEntity<String> updateStagiaire(@Valid @RequestBody Stagiaire stagiaire, @PathVariable Long stagiaireId){
		return stagiaireService.updateStagiaire(stagiaire, stagiaireId);
	}

	@GetMapping("/desactiver/{stagiaireId}")
	public ResponseEntity<Void> desactiverStagiaire(@PathVariable Long stagiaireId){
		return stagiaireService.desactiverStagiaire(stagiaireId);
	}
	
	@DeleteMapping("/{stagiaireId}")
	public ResponseEntity<Void> suprimerStagiaire(@PathVariable Long stagiaireId){
		return stagiaireService.suprimerStagiaire(stagiaireId);
	}
	
}
