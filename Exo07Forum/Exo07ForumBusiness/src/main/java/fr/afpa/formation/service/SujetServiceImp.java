package fr.afpa.formation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afpa.formation.entity.Sujet;
//import fr.afpa.formation.interfaceService.SujetService;
import fr.afpa.formation.interfaceService.IService;
import fr.afpa.formation.repository.SujetRepository;
import fr.afpa.formation.service.exception.SujetAllreadyExistsException;
import fr.afpa.formation.service.exception.SujetNotAviableException;
import fr.afpa.formation.service.exception.SujetNotFoundExcpetion;
import fr.afpa.formation.service.exception.SujetNotValidException;

@Service
public class SujetServiceImp implements IService<Sujet, Exception>{

	@Autowired
	SujetRepository sujetRepository;

//	@Override
	public List<Sujet> list() throws SujetNotAviableException {
		List<Sujet> sujets = (List<Sujet>) sujetRepository.findAll();

		if (sujets == null || sujets.size() == 0) {
			throw new SujetNotAviableException();
		}
		return sujets;
	}

//	@Override
	public Sujet create(Sujet sujet) throws SujetNotValidException, SujetAllreadyExistsException {
		if (sujet == null) {
			throw new SujetNotValidException();
		}

		if (sujet.getId() != null) {

			Optional<Sujet> sujetFromDB = sujetRepository.findById(sujet.getId());

			if (sujetFromDB.isPresent()) {
				throw new SujetAllreadyExistsException();
			} else {
				throw new SujetNotValidException();
			}
		}
		return sujetRepository.save(sujet);
	}

//	@Override
	public Sujet update(Sujet sujet) throws SujetNotValidException, SujetNotFoundExcpetion {
		if (sujet == null) {
			throw new SujetNotValidException();
		}

		if (sujet.getId() == null) {
			throw new SujetNotValidException();
		} else {

			Optional<Sujet> sujetFromDB = sujetRepository.findById(sujet.getId());

			if (!sujetFromDB.isPresent()) {
				throw new SujetNotFoundExcpetion();
			}
		}

		return sujetRepository.save(sujet);
	}

//	@Override
	public List<Sujet> createAll(List<Sujet> sujets)
			throws SujetNotValidException, SujetAllreadyExistsException {
		if (sujets == null || sujets.size() == 0) {
			throw new SujetNotValidException();
		}

		for (Sujet sujet : sujets) {
			if (sujet == null) {
				throw new SujetNotValidException();
			}
			
			if (sujet.getId() != null) {

				Optional<Sujet> sujetFromDB = sujetRepository.findById(sujet.getId());

				if (sujetFromDB.isPresent()) {
					throw new SujetAllreadyExistsException();
				} else {
					throw new SujetNotValidException();
				}
			}
		}
		return (List<Sujet>) sujetRepository.saveAll(sujets);
	}


//	@Override
	public List<Sujet> updateAll(List<Sujet> sujets) throws SujetNotFoundExcpetion, SujetNotValidException {
		if (sujets == null) {
			throw new SujetNotValidException();
		}

		if (sujets.size() == 0) {
			throw new SujetNotValidException();
		}

		for (Sujet sujet : sujets) {
			if (sujet == null) {
				throw new SujetNotValidException();
			}

			if (sujet.getId() == null) {
				throw new SujetNotValidException();
			} else {

				Optional<Sujet> sujetFromDB = sujetRepository.findById(sujet.getId());

				if (!sujetFromDB.isPresent()) {
					throw new SujetNotFoundExcpetion();
				}
			}
		}

		return (List<Sujet>) sujetRepository.saveAll(sujets);
	}


//	@Override
	public void delete(Sujet sujet)
			throws SujetNotValidException, SujetNotFoundExcpetion, SujetNotAviableException {

		if (sujet == null) {
			throw new SujetNotValidException();
		} else if (sujet.getId() == null) {
			throw new SujetNotFoundExcpetion();
		}

		if (!list().contains(sujet)) {
			throw new SujetNotAviableException();
		}

		sujetRepository.delete(sujet);
	}

//	@Override
	public void deleteAll(List<Sujet> sujets) throws SujetNotFoundExcpetion {
		if (sujets == null) {
			throw new SujetNotFoundExcpetion();
		}

		if (sujets.size() == 0) {
			throw new SujetNotFoundExcpetion();
		}
		for (Sujet sujet : sujets) {
			if (sujet.getId() == null) {
				throw new SujetNotFoundExcpetion();
			}
		}

		sujetRepository.deleteAll(sujets);
	}

//	@Override
	public void deleteById(Long id) throws SujetNotFoundExcpetion {
		if (id == null) {
			throw new SujetNotFoundExcpetion();
		}
		sujetRepository.deleteById(id);
	}

//	@Override
	public Sujet findById(Long id) throws SujetNotFoundExcpetion {
		if (id == null) {
			throw new SujetNotFoundExcpetion();
		}
//		Sujet sujet = sujetRepository.findById(id).get();
//		if(sujetRepository == null) {
//			 System.out.println("sujetRepository NULL !!!!!");
//			
//		}
		Optional<Sujet> sujet = sujetRepository.findById(id);
		
		if(sujet == null || !sujet.isPresent()) {
			throw new SujetNotFoundExcpetion();			
		}
		return sujet.get();
	}

}