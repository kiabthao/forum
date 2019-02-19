package fr.afpa.formation.controller;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.afpa.formation.Application;
import fr.afpa.formation.entity.Commentaire;
import fr.afpa.formation.entity.Sujet;
import fr.afpa.formation.entity.SujetContent;
import fr.afpa.formation.service.CommentaireServiceImp;
import fr.afpa.formation.service.SujetContentServiceImp;
import fr.afpa.formation.service.SujetServiceImp;
import fr.afpa.formation.service.exception.CommentaireAllreadyExistsException;
import fr.afpa.formation.service.exception.CommentaireNotFoundExcpetion;
import fr.afpa.formation.service.exception.CommentaireNotValidException;
import fr.afpa.formation.service.exception.SujetContentAllreadyExistsException;
import fr.afpa.formation.service.exception.SujetContentNotFoundExcpetion;
import fr.afpa.formation.service.exception.SujetContentNotValidException;
import fr.afpa.formation.service.exception.SujetNotFoundExcpetion;
import fr.afpa.formation.service.exception.SujetNotValidException;

@Controller
public class CommentaireController {
	Log log = LogFactory.getLog(Application.class);

	@Autowired
	private SujetServiceImp sujetService;

	@Autowired
	private SujetContentServiceImp sujetContentService;
	
	@Autowired
	private CommentaireServiceImp commentaireService;
	
//	@InitBinder
//	public void initBoutiqueItemBinder(WebDataBinder dataBinder) {
//	dataBinder.setValidator(new SujetContentValider());
//	}
	

//
	@GetMapping("/commentaire/{id}/delete")
	public String delete(@PathVariable("id") long id) throws CommentaireNotFoundExcpetion, SujetContentNotFoundExcpetion      {
		SujetContent sujetContent = sujetContentService.findById(id);
//		commentaireService.delete(sujetContent.getCommentaire());
		sujetContentService.deleteById(id);
		return "redirect:/sujets/"+sujetContent.getSujet().getId()+"/details";
	}
	
	@GetMapping("/sujets/{id}/commentaire/create")
	public String create(@PathVariable("id") long id, Model model) throws SujetNotFoundExcpetion  {
		
//		Long idContent = sujetService.findById(id).getId();
//		model.addAttribute("id", idContent.longValue());
		model.addAttribute("commentaire", new Commentaire());
		
		return "commentaireCreate";
	}
	
	@PostMapping("/sujets/{id}/commentaire/create")
	public String save(@PathVariable(name="id") long id, Commentaire commentaire, Model model) throws    SujetNotFoundExcpetion, CommentaireNotValidException, CommentaireAllreadyExistsException, SujetContentNotValidException, SujetContentAllreadyExistsException, SujetNotValidException  {
		Sujet sujet = sujetService.findById(id);	
		
		commentaireService.create(commentaire);
		
		SujetContent sujetContent = new SujetContent(null, commentaire);
		sujetContent.setSujet(sujet);		
		sujetContentService.create(sujetContent);
		
		sujet.getSujetContents().add(sujetContent);
		sujetService.update(sujet);
		
		return "redirect:/sujets/"+id+"/details";
	}

	
	@GetMapping("/commentaire/{id}/update")
	public String update(@PathVariable("id") long id, Model model) throws SujetContentNotFoundExcpetion    {
		model.addAttribute("commentaire", sujetContentService.findById(id).getCommentaire());
		return "commentaireUpdate";
	}	
	
	@PostMapping("/commentaire/{id}/update")
	public String updateSave(Commentaire commentaire, Model model) throws CommentaireNotValidException, CommentaireNotFoundExcpetion      {
//		log.info(commentaire.getId());
//		log.info(commentaire.getText());
		commentaireService.update(commentaire);
//		SujetContent sujetContent = commentaireService.findSujetContentByCommentaireId(commentaire.getId());
//		return "redirect:/sujets/"+sujetContent.getSujet().getId()+"/details";
		return "redirect:/";
	}

}
