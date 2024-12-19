package fr.eni.tp.encheres.bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import fr.eni.tp.encheres.bo.Utilisateur;
import fr.eni.tp.encheres.dal.UtilisateurDAO;
import fr.eni.tp.encheres.exception.BusinessException;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
	
	@Autowired
	private UtilisateurDAO utilisateurDAO;

	@Override
	public Utilisateur findByPseudo(String pseudoUtilisateur) {
		return utilisateurDAO.read(pseudoUtilisateur);
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
			
			PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			String passwordCrypt = passwordEncoder.encode(nouvelUtilisateur.getMotDePasse());
			nouvelUtilisateur.setMotDePasse(passwordCrypt);
			utilisateurDAO.createUtilisateur(nouvelUtilisateur);
			System.out.println(passwordCrypt);
			System.out.println("Nouvel Utilisateur créé :" + nouvelUtilisateur.getPseudo());
			
		//} else {
		//	throw be;
		//}
		
	}
	
}
