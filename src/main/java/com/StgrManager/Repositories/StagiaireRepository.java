package com.StgrManager.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.StgrManager.Entities.Stagiaire;


public interface StagiaireRepository extends JpaRepository<Stagiaire, Long>{
	@Query("SELECT s FROM Stagiaire s WHERE s.etat = 'actif'")
	List<Stagiaire> findAllActif();
	
	List<Stagiaire> findAllByNomAndPrenom(String nom, String prenom);
	
	Stagiaire findByLogin(String login);
}
