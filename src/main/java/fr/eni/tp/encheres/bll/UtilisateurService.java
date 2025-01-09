package fr.eni.tp.encheres.bll;

import org.springframework.validation.BindingResult;

import fr.eni.tp.encheres.bo.Utilisateur;
import fr.eni.tp.encheres.exception.BusinessException;

public interface UtilisateurService {
	
	void add(Utilisateur nouvelUtilisateur) throws BusinessException;
	
	Utilisateur findByEmail(String emailUtilisateur);
	
	Utilisateur findByNo(long NoUtilisateur);
	
	boolean validateUtilisateur(Utilisateur utilisateur, BindingResult bindingResult1, BindingResult bindingResult2);
	
	void update(Utilisateur utilisateur) throws BusinessException;
	
	void delete(Utilisateur utilisateur) throws BusinessException;
	
	void deduireCredit(long noUtilisateur, int montantEnchere);

	void rembourserPoints(long noUtilisateurs, int montantEnchere);
	
	
	
	
}
