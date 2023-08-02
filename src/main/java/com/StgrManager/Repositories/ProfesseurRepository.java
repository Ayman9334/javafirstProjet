package com.StgrManager.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.StgrManager.Entities.Professeur;

public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {
	@Query("SELECT p FROM Professeur p WHERE p.etat = 'actif'")
	List<Professeur> findAllActif();

	@Query("SELECT MAX(p.numero) FROM Professeur p")
	Long getGrandNumero();

	Boolean existsByNomAndPrenom(String nom, String prenom);
}
