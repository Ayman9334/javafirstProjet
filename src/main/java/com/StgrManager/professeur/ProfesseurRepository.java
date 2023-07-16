package com.StgrManager.professeur;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesseurRepository extends JpaRepository<Professeur, Long>{
	@Query("SELECT p FROM Professeur p WHERE p.etat = 'actif'")
	List<Professeur> findAllActif();
}
