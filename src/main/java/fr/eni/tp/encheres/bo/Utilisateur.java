package fr.eni.tp.encheres.bo;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Utilisateur implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//numero utilisateur
	private long noUtilisateur ;
	
	@NotBlank(message = "Le pseudo est obligatoire")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Le pseudo ne doit contenir que des lettres et des chiffres")
	private String pseudo;
	
	@NotBlank(message = "Votre nom est obligatoire")
	private String nom;
	
	@NotBlank(message = "Votre prénom est obligatoire")
	private String prenom;
	
	@NotBlank(message = "L'email est obligatoire")
	@Email(message = "L'email doit être valide")
	@Pattern(regexp = "^[a-zA-Z]+\\.[a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z]{2,}$", 
	         message = "L'email doit être au format 'prenom.nom@domaine.com'")
	private String email;
	
	@NotBlank(message = "Le numéro de téléphone est obligatoire")
	@Pattern(regexp = "^[0-9]{10}$", message = "Le numéro de téléphone n'est pas valide")
	private String telephone;
	
	@NotBlank(message = "Votre adresse est obligatoire")
	private String rue;
	
	@NotNull(message = "Votre code Postal est obligatoire")
	@Min(value = 10000, message = "Le code postal n'est pas valide")
	@Max(value = 99999, message = "Le code postal n'est pas valide")
	private int codePostal;
	
	@NotBlank(message = "Votre ville est obligatoire")
	private String ville;
	
	@NotBlank(message = "Votre mot de passe est obligatoire")
	private String motDePasse;
	
	private int credit;
	
	private boolean administrateur;
	
	private List<ArticlesVendu> articlesVendu;
	private List<Enchere> encheres;
	
	

	public Utilisateur(long noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, int codePostal, String ville, String motDePasse, int credit, boolean administrateur,
			List<ArticlesVendu> articlesVendu, List<Enchere> encheres) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
		this.articlesVendu = articlesVendu;
		this.encheres = encheres;
	}
	

	public Utilisateur() {
		
	}


	public List<ArticlesVendu> getArticlesVendu() {
		return articlesVendu;
	}

	public void setArticlesVendu(List<ArticlesVendu> articlesVendu) {
		this.articlesVendu = articlesVendu;
	}

	public List<Enchere> getEncheres() {
		return encheres;
	}

	public void setEncheres(List<Enchere> encheres) {
		this.encheres = encheres;
	}

	public long getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(long noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public boolean isAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}

	@Override
	public String toString() {
		return "Utilisateur [noUtilisateur=" + noUtilisateur + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom="
				+ prenom + ", email=" + email + ", telephone=" + telephone + ", rue=" + rue + ", codePostal="
				+ codePostal + ", ville=" + ville + ", motDePasse=" + motDePasse + ", credit=" + credit
				+ ", administrateur=" + administrateur + ", articlesVendu=" + articlesVendu + ", encheres=" + encheres
				+ "]";
	}

	
	
	
}
