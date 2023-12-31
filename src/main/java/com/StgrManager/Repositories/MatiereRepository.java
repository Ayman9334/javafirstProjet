package com.StgrManager.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.StgrManager.Entities.Matiere;

public interface MatiereRepository extends JpaRepository<Matiere, Long> {

	@Query("SELECT m FROM Matiere m WHERE m.etat = 'actif'")
	List<Matiere> findAllActif();

	@Query("SELECT MAX(m.numero) FROM Matiere m")
	Long getGrandNumero();

}
