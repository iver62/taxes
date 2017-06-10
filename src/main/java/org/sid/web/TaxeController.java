package org.sid.web;

import java.util.ArrayList;

import javax.validation.Valid;

import org.sid.dao.EntrepriseRepository;
import org.sid.dao.TaxeRepository;
import org.sid.entities.Entreprise;
import org.sid.entities.Taxe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaxeController {
	
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	@Autowired
	private TaxeRepository taxeRepository;
	
	@RequestMapping(value="/entreprises", method=RequestMethod.GET)
	public String index(Model model,
			@RequestParam(name="motCle", defaultValue="") String motCle,
			@RequestParam(name="page", defaultValue="0") int p, 
			@RequestParam(name="size", defaultValue="4") int s) {
		Page<Entreprise> pageEntreprises = entrepriseRepository.chercher("%"+motCle+"%", new PageRequest(p, s));
		model.addAttribute("listEntreprises", pageEntreprises.getContent());
		int[] pages = new int[pageEntreprises.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", motCle);
		return "entreprises";
	}
	
	@RequestMapping(value="/formEntreprise")
	public String form(Model model) {
		model.addAttribute("entreprise", new Entreprise());
		return "formEntreprise";
	}
	
	@RequestMapping(value="/saveEntreprise")
	public String save(Model model, @Valid Entreprise entreprise, BindingResult bindingResult) { // BindingResult pour gérer les erreurs du formulaire
		if (bindingResult.hasErrors()) { // S'il y a des erreurs 
			return "formEntreprise"; // Retour vers le formulaire
		}
		entrepriseRepository.save(entreprise);
		return "redirect:/entreprises";
	}
	
	@RequestMapping(value="/taxes")
	public String taxes(Model model, 
			@RequestParam(name="code", defaultValue="-1") Long code) {
		model.addAttribute("entreprises", entrepriseRepository.findAll());
		if (code == -1) {
			model.addAttribute("taxes", new ArrayList<Taxe>());
		}
		else {
			Entreprise entreprise = new Entreprise();
			entreprise.setCode(code);
			model.addAttribute("taxes", taxeRepository.findByEntreprise(entreprise));
		}
		return "taxes";
	}

}
