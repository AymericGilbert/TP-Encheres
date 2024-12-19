package fr.eni.tp.encheres.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.tp.encheres.bll.ArticleService;
import fr.eni.tp.encheres.bll.UtilisateurService;
import fr.eni.tp.encheres.bo.ArticlesVendu;
import fr.eni.tp.encheres.bo.Categorie;
import fr.eni.tp.encheres.bo.Retrait;
import fr.eni.tp.encheres.bo.Utilisateur;
import fr.eni.tp.encheres.exception.BusinessException;

@Controller
@RequestMapping("/")
@SessionAttributes("categorieSession")
public class EnchereController {

	private ArticleService articleService;
	private UtilisateurService utilisateurService;
	
	public EnchereController(ArticleService articleService, UtilisateurService utilisateurService) {
		this.articleService = articleService;
		this.utilisateurService = utilisateurService;
	}
	
	
	
	
	// page d'acceuil
	@GetMapping
	public String afficherEnchere(Model model) {
		model.addAttribute("articleVendus", new ArticlesVendu());

	    List<ArticlesVendu> articleVendus = this.articleService.consulterArticles();
	    model.addAttribute("articles", articleVendus);

	    List<Categorie> categories = articleService.consulterCategorie();
	    model.addAttribute("categories", categories);
		
	    return "index";
		
	}
	
	@ModelAttribute("categorieSession")
	public List<Categorie> consulterCategorie(){
		return this.articleService.consulterCategorie();
	}
	
	@PostMapping("/")
	public String traiterFormulaire(@ModelAttribute ArticlesVendu article, Model model) {
		System.out.println("Article reçu : " + article);
		 // Récupération des articles filtrés
        ArticlesVendu articlesFiltres = articleService.consulterArticleParNo(article.getNoArticle());
        model.addAttribute("articles", articlesFiltres);

        // Liste des catégories
        List<Categorie> categories = articleService.consulterCategorie();
        model.addAttribute("categories", categories);
        
        model.addAttribute("articleVendus", article);
		
        return "index";
	}
	
	//page vendre-article
	
	 @GetMapping("/vendre-article")
	    public String afficherFormulaireVente(Model model) {
		 	ArticlesVendu article = new ArticlesVendu();
		    article.setLieuRetrait(new Retrait());
		    List<Categorie> categories = articleService.consulterCategorie() ;
		    
	        model.addAttribute("articleVendus", article);
	        model.addAttribute("categories", categories);
	        return "vendre-article";
	    }

	    @PostMapping("/vendre-article")
	    public String enregistrerArticle(@ModelAttribute ArticlesVendu article, Model model) {
	       System.out.println("Article enregistré : " + article);
	       System.out.println("Retrait : " + article.getLieuRetrait());
	       	Utilisateur u = new Utilisateur();
	       	u.setNoUtilisateur(1);
	       	
	       	article.setVendeur(u);
	       //il faut que je set le vendeur article avec utilisateur connecter
	       try {
			articleService.creerArticle(article);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       

	       
	        return "redirect:/index";
	    }
}
