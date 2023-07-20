package com.StgrManager.Controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
		matiereService.creeMatiere(matiere);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
