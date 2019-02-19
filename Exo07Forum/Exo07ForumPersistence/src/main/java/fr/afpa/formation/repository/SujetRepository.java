package fr.afpa.formation.repository;

import org.springframework.data.repository.CrudRepository;

import fr.afpa.formation.entity.Commentaire;
import fr.afpa.formation.entity.Sujet;

public interface SujetRepository extends CrudRepository<Sujet, Long>{

}
