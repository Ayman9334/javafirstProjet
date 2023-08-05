package com.StgrManager.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.StgrManager.Entities.Stagiaire;

public interface StagiaireRepository extends JpaRepository<Stagiaire, Long>{
	@Query("SELECT s FROM Stagiaire s WHERE s.etat = 'actif'")
	List<Stagiaire> findAllActif();
	
	@Query("SELECT MAX(s.numero) FROM Stagiaire s")
	Long getGrandNumero();
	
	Boolean existsByNomAndPrenom(String nom, String prenom);
	
	Stagiaire findByLogin(String login);
	
	@Query("SELECT s FROM Stagiaire s WHERE s.login = :login AND s.mot_de_passe = :motDePasse")
	Stagiaire findByLoginInfo(@Param("login") String login, @Param("motDePasse") String mot_de_passe);
	
	Boolean existsByLogin(String login);
	
}
