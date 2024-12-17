package fr.eni.tp.encheres.bll;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import fr.eni.tp.encheres.bo.Utilisateur;
import fr.eni.tp.encheres.dal.UtilisateurDAO;
import fr.eni.tp.encheres.exception.BusinessException;
@Service
public class UtilisateurServiceImpl implements UtilisateurService {
	
	private UtilisateurDAO utilisateurDAO;

	@Override
	public List<Utilisateur> getUtilisateurs() {
		return utilisateurDAO.findAll();
	}

	@Override
	public Utilisateur findById(long noUtilisateur) {
		Utilisateur utilisateur = utilisateurDAO.read(noUtilisateur);
		return utilisateur;
	}

	@Override
	public void update(Utilisateur utilisateur) {
		utilisateurDAO.update(utilisateur);	
	}

	@Override
	@Transactional
	public void add(Utilisateur nouvelUtilisateur) throws BusinessException {
		//BusinessException be = new BusinessException();
		//boolean valide = validerEmailUnique(nouvelUtilisateur.getEmail(), be);
		//valide &= validerPseudoUnique(nouvelUtilisateur.getPseudo(), be);
		//autres regles de validation ...
		//la fonction creation ne fonctinneras que si cela valide la fonction validerEmailUnique
		//if (valide) {
			utilisateurDAO.createUtilisateur(nouvelUtilisateur);
			
			System.out.println("Nouvel Utilisateur créé :" + nouvelUtilisateur.getPseudo());
			
		//} else {
		//	throw be;
		//}
		
	}
	
}
