package com.StgrManager.matiere;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MatiereRepository extends JpaRepository<Matiere, Long> {
	
	@Query("SELECT m FROM Matiere m WHERE m.etat = 'actif'")
	List<Matiere> findAllActif();
}
