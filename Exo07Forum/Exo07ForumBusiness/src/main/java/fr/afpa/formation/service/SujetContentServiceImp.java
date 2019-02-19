package fr.afpa.formation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afpa.formation.entity.SujetContent;
//import fr.afpa.formation.interfaceService.SujetContentService;
import fr.afpa.formation.interfaceService.IService;
import fr.afpa.formation.repository.SujetContentRepository;
import fr.afpa.formation.service.exception.SujetContentAllreadyExistsException;
import fr.afpa.formation.service.exception.SujetContentNotAviableException;
import fr.afpa.formation.service.exception.SujetContentNotFoundExcpetion;
import fr.afpa.formation.service.exception.SujetContentNotValidException;

@Service
public class SujetContentServiceImp implements IService<SujetContent, Exception>{

	@Autowired
	SujetContentRepository sujetContentRepository;

//	@Override
	public List<SujetContent> list() throws SujetContentNotAviableException {
		List<SujetContent> sujetContents = (List<SujetContent>) sujetContentRepository.findAll();

		if (sujetContents == null || sujetContents.size() == 0) {
			throw new SujetContentNotAviableException();
		}
		return sujetContents;
	}

//	@Override
	public SujetContent create(SujetContent sujetContent) throws SujetContentNotValidException, SujetContentAllreadyExistsException {
		if (sujetContent == null) {
			throw new SujetContentNotValidException();
		}

		if (sujetContent.getId() != null) {

			Optional<SujetContent> sujetContentFromDB = sujetContentRepository.findById(sujetContent.getId());

			if (sujetContentFromDB.isPresent()) {
				throw new SujetContentAllreadyExistsException();
			} else {
				throw new SujetContentNotValidException();
			}
		}
		return sujetContentRepository.save(sujetContent);
	}

//	@Override
	public SujetContent update(SujetContent sujetContent) throws SujetContentNotValidException, SujetContentNotFoundExcpetion {
		if (sujetContent == null) {
			throw new SujetContentNotValidException();
		}

		if (sujetContent.getId() == null) {
			throw new SujetContentNotValidException();
		} else {

			Optional<SujetContent> sujetContentFromDB = sujetContentRepository.findById(sujetContent.getId());

			if (!sujetContentFromDB.isPresent()) {
				throw new SujetContentNotFoundExcpetion();
			}
		}

		return sujetContentRepository.save(sujetContent);
	}

//	@Override
	public List<SujetContent> createAll(List<SujetContent> sujetContents)
			throws SujetContentNotValidException, SujetContentAllreadyExistsException {
		if (sujetContents == null || sujetContents.size() == 0) {
			throw new SujetContentNotValidException();
		}

		for (SujetContent sujetContent : sujetContents) {
			if (sujetContent == null) {
				throw new SujetContentNotValidException();
			}
			
			if (sujetContent.getId() != null) {

				Optional<SujetContent> sujetContentFromDB = sujetContentRepository.findById(sujetContent.getId());

				if (sujetContentFromDB.isPresent()) {
					throw new SujetContentAllreadyExistsException();
				} else {
					throw new SujetContentNotValidException();
				}
			}
		}
		return (List<SujetContent>) sujetContentRepository.saveAll(sujetContents);
	}


//	@Override
	public List<SujetContent> updateAll(List<SujetContent> sujetContents) throws SujetContentNotFoundExcpetion, SujetContentNotValidException {
		if (sujetContents == null) {
			throw new SujetContentNotValidException();
		}

		if (sujetContents.size() == 0) {
			throw new SujetContentNotValidException();
		}

		for (SujetContent sujetContent : sujetContents) {
			if (sujetContent == null) {
				throw new SujetContentNotValidException();
			}

			if (sujetContent.getId() == null) {
				throw new SujetContentNotValidException();
			} else {

				Optional<SujetContent> sujetContentFromDB = sujetContentRepository.findById(sujetContent.getId());

				if (!sujetContentFromDB.isPresent()) {
					throw new SujetContentNotFoundExcpetion();
				}
			}
		}

		return (List<SujetContent>) sujetContentRepository.saveAll(sujetContents);
	}


//	@Override
	public void delete(SujetContent sujetContent)
			throws SujetContentNotValidException, SujetContentNotFoundExcpetion, SujetContentNotAviableException {

		if (sujetContent == null) {
			throw new SujetContentNotValidException();
		} else if (sujetContent.getId() == null) {
			throw new SujetContentNotFoundExcpetion();
		}

		if (!list().contains(sujetContent)) {
			throw new SujetContentNotAviableException();
		}

		sujetContentRepository.delete(sujetContent);
	}

//	@Override
	public void deleteAll(List<SujetContent> sujetContents) throws SujetContentNotFoundExcpetion {
		if (sujetContents == null) {
			throw new SujetContentNotFoundExcpetion();
		}

		if (sujetContents.size() == 0) {
			throw new SujetContentNotFoundExcpetion();
		}
		for (SujetContent sujetContent : sujetContents) {
			if (sujetContent.getId() == null) {
				throw new SujetContentNotFoundExcpetion();
			}
		}

		sujetContentRepository.deleteAll(sujetContents);
	}

//	@Override
	public void deleteById(Long id) throws SujetContentNotFoundExcpetion {
		if (id == null) {
			throw new SujetContentNotFoundExcpetion();
		}
		sujetContentRepository.deleteById(id);
	}

//	@Override
	public SujetContent findById(Long id) throws SujetContentNotFoundExcpetion {
		if (id == null) {
			throw new SujetContentNotFoundExcpetion();
		}
//		SujetContent sujetContent = sujetContentRepository.findById(id).get();
//		if(sujetContentRepository == null) {
//			 System.out.println("sujetContentRepository NULL !!!!!");
//			
//		}
		Optional<SujetContent> sujetContent = sujetContentRepository.findById(id);
		
		if(sujetContent == null || !sujetContent.isPresent()) {
			throw new SujetContentNotFoundExcpetion();			
		}
		return sujetContent.get();
	}

}