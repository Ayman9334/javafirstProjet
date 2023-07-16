package com.StgrManager.etablissement;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/etablissement")
public class EtablissementController {

	private final EtablissementService etablissementService;

	public EtablissementController(EtablissementService etablissementService) {
		this.etablissementService = etablissementService;
	}
	
	@GetMapping
	public List<Etablissement> getEtablissement(){
		return etablissementService.getEtablissement();
	}
	
	@PostMapping
	public ResponseEntity<Void> creeEtablissement(@RequestBody Etablissement etablissement){
		etablissementService.creeEtablissement(etablissement);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
