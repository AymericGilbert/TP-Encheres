package fr.eni.tp.encheres.dal;

import fr.eni.tp.encheres.bo.Utilisateur;

public interface UtilisateurDAO {

	Utilisateur readByEmailUtilisateur(String emailUtilisateur);
	
	void createUtilisateur(Utilisateur utilisateur);
	
	void update(Utilisateur utilisateur);
	
	void delete(Utilisateur utilisateur);
	
	Utilisateur readByNoUtilisateur(long noUtilisateur);
	
	boolean existByPseudo(String pseudoUtilisateur, long noUtilisateur);
	
	boolean existByEmail(String emailUtilisateur, long noUtilisateur);
	
	void updateCredit(long noUtilisateur, int credit );
	
	int totalCreditUtilisateur(long noUtilisateur);

}
