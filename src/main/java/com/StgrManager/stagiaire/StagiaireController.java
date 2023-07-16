package com.StgrManager.stagiaire;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stagiaire")
public class StagiaireController {
	private final StagiaireService stagiaireService;

	public StagiaireController(StagiaireService stagiaireService) {
		this.stagiaireService = stagiaireService;
	}
	
	@GetMapping
	public ResponseEntity<List<Stagiaire>> getAllStagaire() {
		return ResponseEntity.ok(stagiaireService.getAllStagaire());
	}
	
	
}
