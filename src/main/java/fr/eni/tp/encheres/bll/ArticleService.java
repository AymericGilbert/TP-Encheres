package fr.eni.tp.encheres.bll;

import java.util.List;

import fr.eni.tp.encheres.bo.ArticlesVendu;
import fr.eni.tp.encheres.bo.Categorie;
import fr.eni.tp.encheres.bo.Enchere;
import fr.eni.tp.encheres.bo.Utilisateur;
import fr.eni.tp.encheres.exception.BusinessException;

public interface ArticleService {
	
	List<ArticlesVendu> consulterArticles();
	
	ArticlesVendu consulterArticleParNo(long noArticle);
	
	List<Categorie> consulterCategorie();
	Categorie consulterCategorieParNo(long noCategorie);
	
	void creerArticle(ArticlesVendu article) throws BusinessException;
	
	void encherir(long noArticle , String email, int montantEnchere) throws Exception;
	
	List<ArticlesVendu> rechercherEncheres(String nomArticle, long no_categorie, Boolean encheresOuvertes, Boolean encheresEnCours, Boolean encheresRemportees,
	        Boolean ventesEnCours, Boolean ventesNonDebutees, Boolean ventesTerminees, Utilisateur utilisateurSession);
	

	//boolean validerArticleUnique(boolean etatVente);
}
