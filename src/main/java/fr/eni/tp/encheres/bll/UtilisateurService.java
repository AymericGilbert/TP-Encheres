package fr.eni.tp.encheres.bll;

import fr.eni.tp.encheres.bo.Utilisateur;
import fr.eni.tp.encheres.exception.BusinessException;

public interface UtilisateurService {
	
	void add(Utilisateur nouvelUtilisateur) throws BusinessException;
	
	Utilisateur findByPseudo(String pseudoUtilisateur);
	
	void update(Utilisateur utilisateur);
	
}
