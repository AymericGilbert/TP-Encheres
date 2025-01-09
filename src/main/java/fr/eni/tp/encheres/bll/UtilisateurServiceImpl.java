package fr.eni.tp.encheres.bll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import fr.eni.tp.encheres.bo.Utilisateur;
import fr.eni.tp.encheres.dal.UtilisateurDAO;
import fr.eni.tp.encheres.exception.BusinessException;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
	
	@Autowired
	private UtilisateurDAO utilisateurDAO;

	@Override
	public Utilisateur findByEmail(String emailUtilisateur) {
		return utilisateurDAO.readByEmailUtilisateur(emailUtilisateur);
	}
	
	@Override
	public Utilisateur findByNo(long NoUtilisateur) {
		return utilisateurDAO.readByNoUtilisateur(NoUtilisateur);
	}
	
	@Override
	public boolean validateUtilisateur(Utilisateur utilisateur, BindingResult bindingResult1, BindingResult bindingResult2) {
		// Vérification de l'unicité du pseudo et de l'email
        if (utilisateurDAO.existByPseudo(utilisateur.getPseudo(), utilisateur.getNoUtilisateur()) ||
        		utilisateurDAO.existByEmail(utilisateur.getEmail(), utilisateur.getNoUtilisateur())) {
        	
            if (utilisateurDAO.existByPseudo(utilisateur.getPseudo(), utilisateur.getNoUtilisateur())) {
				bindingResult1.rejectValue("pseudo", "error.pseudo", "Le pseudo est déjà utilisé.");
			}
			if (utilisateurDAO.existByEmail(utilisateur.getEmail(), utilisateur.getNoUtilisateur())) {
				bindingResult2.rejectValue("email", "error.email", "Cet email est déjà utilisé.");
			}
        }

        // Validation de l'email au format correct (ceci est déjà effectué avec l'annotation @Email)
        if (bindingResult1.hasErrors() ||  bindingResult2.hasErrors()) {
            return false; // Si des erreurs sont présentes, la validation échoue
        }

        return true; // Sinon, l'utilisateur est valide
	}

	@Override
	public void update(Utilisateur utilisateur) throws BusinessException {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String passwordCrypt = passwordEncoder.encode(utilisateur.getMotDePasse());
		utilisateur.setMotDePasse(passwordCrypt);
		utilisateurDAO.update(utilisateur);	
	}
	
	@Override
	public void delete(Utilisateur utilisateur) throws BusinessException {
		utilisateurDAO.delete(utilisateur);
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

	@Override
	public void deduireCredit(long noUtilisateur, int montantEnchere) {
		int creditsActuels = utilisateurDAO.totalCreditUtilisateur(noUtilisateur);
		utilisateurDAO.updateCredit(noUtilisateur, creditsActuels - montantEnchere);
	}

	@Override
	public void rembourserPoints(long noUtilisateurs, int montantEnchere) {
		int creditsActuels = utilisateurDAO.totalCreditUtilisateur(noUtilisateurs);
		utilisateurDAO.updateCredit(noUtilisateurs, creditsActuels + montantEnchere);
		}

	
}
