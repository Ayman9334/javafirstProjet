package com.StgrManager.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/{etablissementId}")
	public ResponseEntity<Void> creeStagiaire(@Valid @RequestBody Stagiaire stagiaire ,@PathVariable String etablissementId){
		stagiaireService.creeStagiaire(stagiaire,etablissementId);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
