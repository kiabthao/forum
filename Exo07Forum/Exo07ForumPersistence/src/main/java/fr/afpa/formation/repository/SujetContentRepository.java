package fr.afpa.formation.repository;

import org.springframework.data.repository.CrudRepository;

import fr.afpa.formation.entity.Commentaire;
import fr.afpa.formation.entity.SujetContent;

public interface SujetContentRepository extends CrudRepository<SujetContent, Long>{

}
