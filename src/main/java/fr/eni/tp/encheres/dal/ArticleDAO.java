package fr.eni.tp.encheres.dal;

import java.util.List;

import fr.eni.tp.encheres.bo.ArticlesVendu;

public interface ArticleDAO {

	void create(ArticlesVendu articles);
	
	ArticlesVendu read(long noArticle);
	
	List<ArticlesVendu> findAll();
	
	List<ArticlesVendu> rechercheArticle(String nomArticle, Long no_categorie,
			Boolean encheresOuvertes, Boolean encheresEnCours, Boolean encheresRemportees,
	        Boolean ventesEnCours, Boolean ventesNonDebutees, Boolean ventesTerminees);
	
	void update(ArticlesVendu articlesVendu);
	
	String findArticle(long noArticle);
	
	void mettreAJourmeilleurOffre(long noArticle, int montantEnchere);
	
	//boolean etatVenteArticle(boolean etatVente);
}
