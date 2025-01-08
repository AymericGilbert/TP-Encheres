package fr.eni.tp.encheres.dal;

import fr.eni.tp.encheres.bo.Utilisateur;

public interface UtilisateurDAO {

	Utilisateur readByEmailUtilisateur(String emailUtilisateur);
	
	void createUtilisateur(Utilisateur utilisateur);
	
	void update(Utilisateur utilisateur);
	
	void delete(Utilisateur utilisateur);
	
	boolean compteDejaExistant(String nom);
	
	Utilisateur findUtilisateur();
	
	Utilisateur readByNoUtilisateur(long noUtilisateur);
	
	void updateCredit(long noUtilisateur, int credit );
	
	int totalCreditUtilisateur(long noUtilisateur);

}
