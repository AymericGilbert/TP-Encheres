package fr.eni.tp.encheres.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.tp.encheres.bll.UtilisateurService;
import fr.eni.tp.encheres.bo.Utilisateur;
import fr.eni.tp.encheres.exception.BusinessException;


@Controller
@SessionAttributes({"utilisateurSession"})
public class UtilisateurController {
	
	private UtilisateurService utilisateurService;

	public UtilisateurController(UtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}
	
	@GetMapping("/mon-profil-creation")
    public String afficherFormulaireCreationProfil(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "mon-profil-creation";
    }

    @PostMapping("/creationUtilisateur")
    public String creerUtilisateur(@ModelAttribute Utilisateur nouvelUtilisateur) {
    	try {
			this.utilisateurService.add(nouvelUtilisateur);
			// Rajouter l'état "connecté"
		} catch (BusinessException e) {
			e.printStackTrace();
		}
        return "redirect:/";
    }
    
    @GetMapping("/mon-profil")
    public String afficherMonProfil(@ModelAttribute("utilisateurSession") Utilisateur utilisateur, Model model) {
    	if (utilisateur == null) {
            // L'utilisateur n'est pas connecté, on redirige vers la page login
            return "redirect:/";
        }
        model.addAttribute("utilisateur", utilisateur);
        return "mon-profil"; 
    }
    
    @GetMapping("/session")
    public String enregistrementSession(@ModelAttribute("utilisateurSession") Utilisateur utilisateurSession, Principal principal) {
    	Utilisateur utilisateur = utilisateurService.findByEmail(principal.getName());
    	System.out.println(utilisateur);
    	if (utilisateur != null) {
    		utilisateurSession.setPseudo(utilisateur.getPseudo());
    		utilisateurSession.setNom(utilisateur.getNom());
    		utilisateurSession.setPrenom(utilisateur.getPrenom());
    		utilisateurSession.setEmail(utilisateur.getEmail());
    		utilisateurSession.setTelephone(utilisateur.getTelephone());
    		utilisateurSession.setRue(utilisateur.getRue());
    		utilisateurSession.setCodePostal(utilisateur.getCodePostal());
    		utilisateurSession.setVille(utilisateur.getVille());
    		utilisateurSession.setMotDePasse(utilisateur.getMotDePasse());
    		utilisateurSession.setCredit(utilisateur.getCredit());
    		utilisateurSession.setNoUtilisateur(utilisateur.getNoUtilisateur());
    		System.out.println(utilisateurSession.getNoUtilisateur());
    		System.out.println("L'utilisateur " + utilisateurSession.getPseudo() + " est bien enregistré dans la session");
			return "redirect:/";
		}
    	
    	return "redirect:/login";
    }
    
    //Crée une instance vide d'utilisateur pour pouvoir l'enregistrer dans la session
    @ModelAttribute("utilisateurSession")
    public Utilisateur mettreEnSessionUtilisateur() {
    	return new Utilisateur();
    }
    
    @GetMapping("/mon-profil-detail")
    public String viewDetailProfil(@ModelAttribute("utilisateurSession") Utilisateur utilisateur, Model model) {
    	model.addAttribute("utilisateur", utilisateur);
        return "mon-profil-detail"; 
    }
    
    @PostMapping("/mon-profil-detail")
    public String modifierUtilisateur(@ModelAttribute Utilisateur utilisateur) {
    	try {
			this.utilisateurService.update(utilisateur);
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
        return "redirect:/session";
    }
    
    @PostMapping("/deleteUtilisateur")
    public String supprimerCompte(@ModelAttribute Utilisateur utilisateur) {
    	try {
    		System.out.println("Notre utilisateur : " + utilisateur);
			this.utilisateurService.delete(utilisateur);
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
        return "redirect:/login";
    }
	
	
}
