package fr.eni.tp.encheres.dal;

import java.util.List;

import fr.eni.tp.encheres.bo.ArticlesVendu;
import fr.eni.tp.encheres.bo.Enchere;

public interface EnchereDAO {

	void create(Enchere enchere);
	
	List<Enchere> findall();
	
	void annulerVente(long noArticle);
	
	Enchere read(long noArticle);
	
	int laMeilleureOffre(long noArticle);
	
	void ajouterEnchere(long noUtilisateur, long noArticle, int montantEnchere);
	
	long exBestEncherisseur(long noArticle);
	
	
}
