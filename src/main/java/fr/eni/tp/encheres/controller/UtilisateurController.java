package fr.eni.tp.encheres.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.tp.encheres.bll.UtilisateurService;
import fr.eni.tp.encheres.bo.Utilisateur;
import fr.eni.tp.encheres.exception.BusinessException;
import jakarta.validation.Valid;


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
    public String creerUtilisateur(@ModelAttribute @Valid Utilisateur nouvelUtilisateur, 
    							   BindingResult bindingResult1, BindingResult bindingResult2) {
    	try {
    		// On teste si notre utilisateur est validé (email et pseudo uniques et au bon format)
    		if (utilisateurService.validateUtilisateur(nouvelUtilisateur, bindingResult1, bindingResult2)) {
    			this.utilisateurService.add(nouvelUtilisateur);
    			return "redirect:/login";
            }
    		// Si il y à des erreurs, on les affichent
            if (bindingResult1.hasErrors() || bindingResult2.hasErrors()) {
                return "mon-profil-creation"; // Si des erreurs sont présentes, la validation échoue
            }
		} catch (BusinessException e) {
			e.printStackTrace();
		}
    	
    	return "mon-profil-creation";
    }
    
    @GetMapping("/profil")
    public String afficherProfil(@RequestParam(name = "noUtilisateur", required = false) Long noUtilisateurVendeur, 
    								@ModelAttribute("utilisateurSession") Utilisateur utilisateurSession, Model model) {
    	if (utilisateurSession == null) {
            // L'utilisateur n'est pas connecté, on redirige vers la page login
            return "redirect:/login";
        }
        if (noUtilisateurVendeur != null) {
        	Utilisateur utilisateurVendeur = utilisateurService.findByNo(noUtilisateurVendeur);
        	model.addAttribute("utilisateurVendeur", utilisateurVendeur);
		}
        
		return "profil"; 
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
    public String viewDetailProfil(@ModelAttribute("utilisateurSession") Utilisateur utilisateurSession, Model model) {
    	model.addAttribute("utilisateurSession", utilisateurSession);
        return "mon-profil-detail"; 
    }
    
    @PostMapping("/modificationProfil")
    public String modifierUtilisateur(@ModelAttribute("utilisateurSession") @Valid Utilisateur utilisateurSession,
    								  BindingResult bindingResult1, BindingResult bindingResult2) {
    	try {
    		if (utilisateurService.validateUtilisateur(utilisateurSession, bindingResult1, bindingResult2)) {
    			this.utilisateurService.update(utilisateurSession);
    			return "redirect:/session";
            }
    		if (bindingResult1.hasErrors() || bindingResult2.hasErrors()) {
                return "mon-profil-detail";
            }
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
        return "mon-profil-detail";
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
