package fr.afpa.formation.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.afpa.formation.Application;
import fr.afpa.formation.entity.Sujet;
import fr.afpa.formation.service.SujetServiceImp;
import fr.afpa.formation.service.exception.SujetAllreadyExistsException;
import fr.afpa.formation.service.exception.SujetNotAviableException;
import fr.afpa.formation.service.exception.SujetNotFoundExcpetion;
import fr.afpa.formation.service.exception.SujetNotValidException;
import fr.afpa.formation.validator.SujetValider;

@Controller
public class SujetController {
	Log log = LogFactory.getLog(Application.class);

	@Autowired
	private SujetServiceImp sujetService;
	
	@InitBinder
	public void initBoutiqueItemBinder(WebDataBinder dataBinder) {
	dataBinder.setValidator(new SujetValider());
	
	}
	
	@GetMapping("/")
	public String debut() {
		return "redirect:sujets";
	}

	@GetMapping("/sujets")
	public String start(Model model) throws SujetNotAviableException, SujetNotFoundExcpetion {
		List<Sujet> sujets = sujetService.list();
		model.addAttribute("sujets", sujets);
		return "index";
	}
	
	@GetMapping("/sujets/{id}/details")
	public String detail2(@PathVariable("id") long id, Model model) throws SujetNotAviableException, SujetNotFoundExcpetion {
		Sujet sujetToPrint = sujetService.findById(id);
		model.addAttribute("sujet", sujetToPrint);
		return "sujetDetail";
	}	

	@GetMapping("/sujets/create")
	public String create(Model model) {
		model.addAttribute("sujet", new Sujet());
		return "sujetCreate";
	}

	@PostMapping("/sujets/create")
	public String save(Sujet sujet, Model model) throws SujetNotValidException, SujetAllreadyExistsException {
		sujetService.create(sujet);
		return "redirect:/sujets/"+sujet.getId()+"/details";
	}
	
	@GetMapping("/sujets/{id}/delete")
	public String delete(@PathVariable("id") long id, Model model) throws SujetNotFoundExcpetion  {
		sujetService.deleteById(id);
		return "redirect:/";
	}	

	
	@GetMapping("/sujets/{id}/update")
	public String update(@PathVariable("id") long id, Model model) throws SujetNotFoundExcpetion  {
		model.addAttribute("sujet", sujetService.findById(id));
		return "sujetUpdate";
	}	
	
	@PostMapping("/sujets/{id}/update")
	public String updateSave(Sujet sujet, Model model) throws SujetNotValidException, SujetNotFoundExcpetion  {
		sujetService.update(sujet);
		return "redirect:/";
	}	
	
}
