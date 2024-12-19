package fr.eni.tp.encheres.dal;

import fr.eni.tp.encheres.bo.Utilisateur;

public interface UtilisateurDAO {

	Utilisateur read(String pseudoUtilisateur);
	
	void createUtilisateur(Utilisateur utilisateur);
	
	void update(Utilisateur utilisateur);
	
	boolean compteDejaExistant(String nom);
	
	Utilisateur findUtilisateur();
}
