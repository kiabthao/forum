package fr.afpa.formation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.afpa.formation.entity.Commentaire;
import fr.afpa.formation.entity.SujetContent;

public interface CommentaireRepository extends CrudRepository<Commentaire, Long>{
	
	@Query("SELECT sc FROM Commentaire c, SujetContent sc WHERE c.id = sc.commentaire.id AND c.id LIKE :toto%")
	SujetContent findSujetContentByCommentaireId (@Param("toto") Long id);

}
