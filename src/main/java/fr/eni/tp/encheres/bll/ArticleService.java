package fr.eni.tp.encheres.bll;

import java.util.List;

import fr.eni.tp.encheres.bo.ArticlesVendu;
import fr.eni.tp.encheres.exception.BusinessException;
import fr.eni.tp.encheres.bo.Categorie;
import fr.eni.tp.encheres.bo.Utilisateur;

public interface ArticleService {
	
	List<ArticlesVendu> consulterArticles();
	ArticlesVendu consulterArticleParId(long id);
	
	List<Categorie> consulterCategorie();
	Categorie consulterCategorieParId(long id);
	
	List<Utilisateur> consulterUtilisateur();
	Utilisateur consulterUtilisateurParId(long id);
	
	void creerArticle(ArticlesVendu article) throws BusinessException;

}
