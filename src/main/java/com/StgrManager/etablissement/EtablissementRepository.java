package com.StgrManager.etablissement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EtablissementRepository extends JpaRepository<Etablissement, String> {

	@Query("SELECT e FROM Etablissement e WHERE e.etat = 'actif'")
	List<Etablissement> findAllActif();
}
