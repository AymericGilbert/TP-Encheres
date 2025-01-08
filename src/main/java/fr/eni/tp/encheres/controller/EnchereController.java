package fr.eni.tp.encheres.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.tp.encheres.bll.ArticleService;
import fr.eni.tp.encheres.bll.UtilisateurService;
import fr.eni.tp.encheres.bo.ArticlesVendu;
import fr.eni.tp.encheres.bo.Categorie;
import fr.eni.tp.encheres.bo.Enchere;
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
		model.addAttribute("article", new ArticlesVendu());

	    List<ArticlesVendu> articleVendus = this.articleService.consulterArticles();
	    model.addAttribute("articles", articleVendus);

	    List<Categorie> categories = articleService.consulterCategorie();
	    model.addAttribute("categories", categories);
		
	    return "index";
		
	}
		// rechercher
		@RequestMapping(value = "/rechercher", method = {RequestMethod.GET, RequestMethod.POST})
	    public String rechercherEncheres(@RequestParam(name ="nomArticle", required = false) String nomArticle,
	    								 @RequestParam(name ="no_categorie", required = false) Long no_categorie,
	    								 Model model ) {
	  
		    if (nomArticle == null) nomArticle = "";
		    if (no_categorie == null) no_categorie = 0L;
		  
	        List<ArticlesVendu> encheres = articleService.rechercherEncheres(nomArticle, no_categorie);
	        model.addAttribute("encheres", encheres);
	        
	        model.addAttribute("categories", articleService.consulterCategorie());
	        
	        return "index"; 
	    }
	 
	
	@ModelAttribute("categorieSession")
	public List<Categorie> consulterCategorie(){
		return this.articleService.consulterCategorie();
	}
	
	@PostMapping("/")
	public String traiterFormulaire(@ModelAttribute("article") ArticlesVendu article, Model model) {
		System.out.println("Article reçu : " + article);
		
		 // Récupération des articles filtrés
        
        model.addAttribute("article", article);
        
        model.addAttribute("articles", articleService.rechercherEncheres(null, 0));
        
        // Liste des catégories
        List<Categorie> categories = articleService.consulterCategorie();
        model.addAttribute("categories", categories);
        
       
		
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
	       
	        return "redirect:/";
	    }    
	    
	    // methode pour view-acquereur
	    
	    
	    //reprendre  pages enchere manque methode sur utilisateurDAO (findbypseudo )
	    //methode boolean pour faire enchere sur article service prenant noarticle pseudo et l'attribut de la classe retrait (private Utilisateur encherit;)
	    //plus methode controller get et post qui vont bien
	    
	    @GetMapping("/view-acquereur")
	    public String afficherDetailsArticle(@RequestParam("noArticle") long noArticle, Model model) {
	        ArticlesVendu article = articleService.consulterArticleParNo(noArticle);
	        model.addAttribute("article", article);
	        
	        if (article == null) {
	            System.err.println("Aucun article trouvé avec l'ID : " + noArticle);
	            model.addAttribute("error", "Article introuvable.");
	            return "redirect:/";
	        }
	       System.out.println("Article trouvé" + noArticle);
	        
	        Enchere encheres = new Enchere();
	        model.addAttribute("encheres", encheres);
	        return "view-acquereur";
	    }
	    
	  @PostMapping("/article/{noArticle}/encherir")
	  public String faireEnchere(@PathVariable("noArticle") long noArticle, @ModelAttribute Enchere encheres, Principal principal, Model model) {
		  String email = principal.getName();
		  System.out.println("enchere effectuer" + email);
		  Utilisateur utilisateur = utilisateurService.findByEmail(email);
		  model.addAttribute("utilisateur", utilisateur);
		  
		  try {
	            articleService.encherir(noArticle, email, encheres.getMontantEnchere());
	            model.addAttribute("success", "Enchère soumise avec succès !" + email);
	        } catch (Exception e) {
	            model.addAttribute("error", e.getMessage());
	            System.err.println("Erreur lors de l'enchère : " + e.getMessage());
	        }
		  
		  return "redirect:/view-acquereur?noArticle=" + noArticle ;
	  }
}
