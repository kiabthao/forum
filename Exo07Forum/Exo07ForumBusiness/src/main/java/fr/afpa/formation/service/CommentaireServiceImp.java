package fr.afpa.formation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afpa.formation.entity.Commentaire;
import fr.afpa.formation.entity.SujetContent;
//import fr.afpa.formation.interfaceService.CommentaireService;
import fr.afpa.formation.interfaceService.IService;
import fr.afpa.formation.repository.CommentaireRepository;
import fr.afpa.formation.service.exception.CommentaireAllreadyExistsException;
import fr.afpa.formation.service.exception.CommentaireNotAviableException;
import fr.afpa.formation.service.exception.CommentaireNotFoundExcpetion;
import fr.afpa.formation.service.exception.CommentaireNotValidException;

@Service
public class CommentaireServiceImp implements IService<Commentaire, Exception>{

	@Autowired
	CommentaireRepository commentaireRepository;

//	@Override
	public List<Commentaire> list() throws CommentaireNotAviableException {
		List<Commentaire> commentaires = (List<Commentaire>) commentaireRepository.findAll();

		if (commentaires == null || commentaires.size() == 0) {
			throw new CommentaireNotAviableException();
		}
		return commentaires;
	}

//	@Override
	public Commentaire create(Commentaire commentaire) throws CommentaireNotValidException, CommentaireAllreadyExistsException {
		if (commentaire == null) {
			throw new CommentaireNotValidException();
		}

		if (commentaire.getId() != null) {

			Optional<Commentaire> commentaireFromDB = commentaireRepository.findById(commentaire.getId());

			if (commentaireFromDB.isPresent()) {
				throw new CommentaireAllreadyExistsException();
			} else {
				throw new CommentaireNotValidException();
			}
		}
		return commentaireRepository.save(commentaire);
	}

//	@Override
	public Commentaire update(Commentaire commentaire) throws CommentaireNotValidException, CommentaireNotFoundExcpetion {
		if (commentaire == null) {
			throw new CommentaireNotValidException();
		}

		if (commentaire.getId() == null) {
			throw new CommentaireNotValidException();
		} else {

			Optional<Commentaire> commentaireFromDB = commentaireRepository.findById(commentaire.getId());

			if (!commentaireFromDB.isPresent()) {
				throw new CommentaireNotFoundExcpetion();
			}
		}

		return commentaireRepository.save(commentaire);
	}

//	@Override
	public List<Commentaire> createAll(List<Commentaire> commentaires)
			throws CommentaireNotValidException, CommentaireAllreadyExistsException {
		if (commentaires == null || commentaires.size() == 0) {
			throw new CommentaireNotValidException();
		}

		for (Commentaire commentaire : commentaires) {
			if (commentaire == null) {
				throw new CommentaireNotValidException();
			}
			
			if (commentaire.getId() != null) {

				Optional<Commentaire> commentaireFromDB = commentaireRepository.findById(commentaire.getId());

				if (commentaireFromDB.isPresent()) {
					throw new CommentaireAllreadyExistsException();
				} else {
					throw new CommentaireNotValidException();
				}
			}
		}
		return (List<Commentaire>) commentaireRepository.saveAll(commentaires);
	}


//	@Override
	public List<Commentaire> updateAll(List<Commentaire> commentaires) throws CommentaireNotFoundExcpetion, CommentaireNotValidException {
		if (commentaires == null) {
			throw new CommentaireNotValidException();
		}

		if (commentaires.size() == 0) {
			throw new CommentaireNotValidException();
		}

		for (Commentaire commentaire : commentaires) {
			if (commentaire == null) {
				throw new CommentaireNotValidException();
			}

			if (commentaire.getId() == null) {
				throw new CommentaireNotValidException();
			} else {

				Optional<Commentaire> commentaireFromDB = commentaireRepository.findById(commentaire.getId());

				if (!commentaireFromDB.isPresent()) {
					throw new CommentaireNotFoundExcpetion();
				}
			}
		}

		return (List<Commentaire>) commentaireRepository.saveAll(commentaires);
	}


//	@Override
	public void delete(Commentaire commentaire)
			throws CommentaireNotValidException, CommentaireNotFoundExcpetion, CommentaireNotAviableException {

		if (commentaire == null) {
			throw new CommentaireNotValidException();
		} else if (commentaire.getId() == null) {
			throw new CommentaireNotFoundExcpetion();
		}

		if (!list().contains(commentaire)) {
			throw new CommentaireNotAviableException();
		}

		commentaireRepository.delete(commentaire);
	}

//	@Override
	public void deleteAll(List<Commentaire> commentaires) throws CommentaireNotFoundExcpetion {
		if (commentaires == null) {
			throw new CommentaireNotFoundExcpetion();
		}

		if (commentaires.size() == 0) {
			throw new CommentaireNotFoundExcpetion();
		}
		for (Commentaire commentaire : commentaires) {
			if (commentaire.getId() == null) {
				throw new CommentaireNotFoundExcpetion();
			}
		}

		commentaireRepository.deleteAll(commentaires);
	}

//	@Override
	public void deleteById(Long id) throws CommentaireNotFoundExcpetion {
		if (id == null) {
			throw new CommentaireNotFoundExcpetion();
		}
		commentaireRepository.deleteById(id);
	}

//	@Override
	public Commentaire findById(Long id) throws CommentaireNotFoundExcpetion {
		if (id == null) {
			throw new CommentaireNotFoundExcpetion();
		}
//		Commentaire commentaire = commentaireRepository.findById(id).get();
//		if(commentaireRepository == null) {
//			 System.out.println("commentaireRepository NULL !!!!!");
//			
//		}
		Optional<Commentaire> commentaire = commentaireRepository.findById(id);
		
		if(commentaire == null || !commentaire.isPresent()) {
			throw new CommentaireNotFoundExcpetion();			
		}
		return commentaire.get();
	}

//	@Override
	public SujetContent findSujetContentByCommentaireId(Long id) throws CommentaireNotFoundExcpetion {
		if (id == null) {
			throw new CommentaireNotFoundExcpetion();
		}

		Optional<Commentaire> commentaire = commentaireRepository.findById(id);
		
		if(commentaire == null || !commentaire.isPresent()) {
			throw new CommentaireNotFoundExcpetion();			
		}
		
		//TODO EXCEPTION FOR THE CASE WHENE THE FINDBY RETURN A NULL
		SujetContent sujetContent = commentaireRepository.findSujetContentByCommentaireId(id);
		return sujetContent;
	}

}