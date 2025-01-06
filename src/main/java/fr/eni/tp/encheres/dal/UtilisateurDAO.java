package fr.eni.tp.encheres.dal;

import fr.eni.tp.encheres.bo.Utilisateur;

public interface UtilisateurDAO {

	Utilisateur read(String emailUtilisateur);
	
	void createUtilisateur(Utilisateur utilisateur);
	
	void update(Utilisateur utilisateur);
	
	boolean compteDejaExistant(String nom);
	
	Utilisateur findUtilisateur();
	
	void updateCredit(long noUtilisateur, int credit );

    int totalCreditUtilisateur(long noUtilisateur);
    
    Utilisateur readByNoUtilisateur(long noUtilisateur);
}
