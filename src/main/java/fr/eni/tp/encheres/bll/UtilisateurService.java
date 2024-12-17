package fr.eni.tp.encheres.bll;

import java.util.List;


import fr.eni.tp.encheres.bo.Utilisateur;
import fr.eni.tp.encheres.exception.BusinessException;

public interface UtilisateurService {
	
	void add(Utilisateur nouvelUtilisateur) throws BusinessException;
	
	List<Utilisateur> getUtilisateurs();
	
	Utilisateur findById(long noUtilisateur);
	
	void update(Utilisateur utilisateur);
	
}
