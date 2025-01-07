package fr.eni.tp.encheres.dal;

import java.util.List;

import fr.eni.tp.encheres.bo.ArticlesVendu;

public interface ArticleDAO {

	void create(ArticlesVendu articles);
	
	ArticlesVendu read(long noArticle);
	
	List<ArticlesVendu> findAll();
	
	
	
	void update(ArticlesVendu articlesVendu);
	
	String findArticle(long noArticle);
	
	void mettreAJourmeilleurOffre(long noArticle, int montantEnchere);
	
	//boolean etatVenteArticle(boolean etatVente);
}
