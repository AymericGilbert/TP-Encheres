package fr.eni.tp.encheres.bll;

import java.util.List;

import fr.eni.tp.encheres.bo.ArticlesVendu;
import fr.eni.tp.encheres.bo.Categorie;

public interface ArticleService {
	
	List<ArticlesVendu> consulterArticles();
	
	ArticlesVendu consulterArticleParNo(long noArticle);
	
	List<Categorie> consulterCategorie();
	Categorie consulterCategorieParNo(long noCategorie);
	
	//void creerArticle(ArticlesVendu article) throws BusinessException;

}
