package fr.eni.tp.encheres.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import fr.eni.tp.encheres.bll.UtilisateurService;
import fr.eni.tp.encheres.bo.Utilisateur;
import fr.eni.tp.encheres.exception.BusinessException;

@Controller
public class UtilisateurController {
	
	private UtilisateurService utilisateurService;

	public UtilisateurController(UtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}
	
	@GetMapping("/mon-profil")
    public String afficherFormulaireCreationProfil(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "mon-profil";
    }

    @PostMapping("/mon-profil")
    public String creerUtilisateur(@ModelAttribute Utilisateur nouvelUtilisateur) {
    	try {
			this.utilisateurService.add(nouvelUtilisateur);
			// Rajouter l'état "connecté"
		} catch (BusinessException e) {
			e.printStackTrace();
		}
        return "redirect:/index"; // Une page de confirmation ou un autre traitement
    }
	
	
}
