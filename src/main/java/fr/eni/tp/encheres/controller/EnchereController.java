package fr.eni.tp.encheres.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.tp.encheres.bll.ArticleService;
import fr.eni.tp.encheres.bll.UtilisateurService;
import fr.eni.tp.encheres.bo.ArticlesVendu;
import fr.eni.tp.encheres.bo.Categorie;

@Controller
@SessionAttributes("categorieSession")
public class EnchereController {

	private ArticleService articleService;
	private UtilisateurService utilisateurService;
	
	public EnchereController(ArticleService articleService, UtilisateurService utilisateurService) {
		this.articleService = articleService;
		this.utilisateurService = utilisateurService;
	}
	
	@GetMapping
	public String afficherEnchere(Model model) {
		model.addAttribute("articleVendus", new ArticlesVendu());
		
		List<ArticlesVendu> articleVendus = this.articleService.consulterArticles();
		System.out.println(articleVendus);
		model.addAttribute("articles", articleVendus);
		
		List<Categorie> categories = articleService.consulterCategorie();
		model.addAttribute("categories" , categories);
		
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
		return "index";
	}
}
