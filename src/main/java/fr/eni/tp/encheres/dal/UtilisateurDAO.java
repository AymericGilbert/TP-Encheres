package fr.eni.tp.encheres.dal;

import fr.eni.tp.encheres.bo.Utilisateur;

public interface UtilisateurDAO {

	Utilisateur read (long noUtilisateur);
	
	void createUtilisateur(Utilisateur utilisateur);
	
	boolean compteDejaExistant(String nom);
}
