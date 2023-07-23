package com.StgrManager.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.StgrManager.Entities.Etablissement;

public interface EtablissementRepository extends JpaRepository<Etablissement, Long> {

	@Query("SELECT e FROM Etablissement e WHERE e.etat = 'actif'")
	List<Etablissement> findAllActif();
	
	Boolean existsByLibelle(String libelle);
}
