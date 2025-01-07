package fr.eni.tp.encheres.dal;

import java.util.List;

import fr.eni.tp.encheres.bo.Enchere;

public interface EnchereDAO {

	void create(Enchere enchere);
	
	List<Enchere> findall();
	
	void annulerVente(long noArticle);
	
	Enchere read(long noArticle);
	
	int laMeilleureOffre(int noArticle);
	
	void ajouterEnchere(long noUtilisateur, long noArticle, int montantEnchere);
}
