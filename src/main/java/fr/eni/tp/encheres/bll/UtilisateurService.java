package fr.eni.tp.encheres.bll;

import fr.eni.tp.encheres.bo.Utilisateur;
import fr.eni.tp.encheres.exception.BusinessException;

public interface UtilisateurService {
	
	void add(Utilisateur nouvelUtilisateur) throws BusinessException;
	
	Utilisateur findByEmail(String emailUtilisateur);
	
	void update(Utilisateur utilisateur);
	
	void deduireCredit(long noUtilisateur, int montantEnchere);

	void rembourserPoints(long noUtilisateurs, int montantEnchere);
	
	
}
