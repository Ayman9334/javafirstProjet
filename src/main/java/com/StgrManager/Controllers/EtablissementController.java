package com.StgrManager.Controllers;

import java.util.List;
import java.util.Map;

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

import com.StgrManager.Entities.Etablissement;
import com.StgrManager.Services.EtablissementService;

@RestController
@RequestMapping("/etablissement")
public class EtablissementController {

	private final EtablissementService etablissementService;

	public EtablissementController(EtablissementService etablissementService) {
		this.etablissementService = etablissementService;
	}

	@GetMapping
	public List<Etablissement> getEtablissement() {
		return etablissementService.getEtablissement();
	}

	@PostMapping
	public ResponseEntity<Map<String,String>> creerEtablissement(@Valid @RequestBody Etablissement etablissement) {
		return etablissementService.creeEtablissement(etablissement);
	}

	@PutMapping("/{etablissementId}")
	public ResponseEntity<Map<String, String>> updateEtablissement(@Valid @RequestBody Etablissement etablissement, @PathVariable Long etablissementId) {
		return etablissementService.updateEtablissement(etablissement, etablissementId);
	}
	
	@PutMapping("/desactiver/{etablissementId}") 
	public ResponseEntity<Void> desactiverEtablissement(@PathVariable Long etablissementId){
		return etablissementService.desactiverEtablissement(etablissementId);
	}
	
	@DeleteMapping("/{etablissementId}")
	public ResponseEntity<Void> suprimerEtablissement(@PathVariable Long etablissementId) {
		return etablissementService.suprimerEtablissement(etablissementId);
	}
}
