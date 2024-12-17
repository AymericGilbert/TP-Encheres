package fr.eni.tp.encheres.bo;

import java.io.Serializable;
import java.util.List;

public class ArticlesVendu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long noArticle;
	private String nomArticle;
	private String descritpion;
	private String dateDebutEncheres;
	private String dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private boolean etatVente;
	
	private Utilisateur vendeur;
	
	private Retrait lieuRetrait;
	
	private Categorie categorieArticle;
	
	private List<Enchere> encheres;
	

	
	

	

	public ArticlesVendu(long noArticle, String nomArticle, String descritpion, String dateDebutEncheres,
			String dateFinEncheres, int miseAPrix, int prixVente, boolean etatVente, Utilisateur vendeur,
			Retrait lieuRetrait, Categorie categorieArticle, List<Enchere> encheres) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.descritpion = descritpion;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.vendeur = vendeur;
		this.lieuRetrait = lieuRetrait;
		this.categorieArticle = categorieArticle;
		this.encheres = encheres;
	}

	

	public long getNoArticle() {
		return noArticle;
	}



	public void setNoArticle(long noArticle) {
		this.noArticle = noArticle;
	}



	public String getNomArticle() {
		return nomArticle;
	}



	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}



	public String getDescritpion() {
		return descritpion;
	}



	public void setDescritpion(String descritpion) {
		this.descritpion = descritpion;
	}



	public String getDateDebutEncheres() {
		return dateDebutEncheres;
	}



	public void setDateDebutEncheres(String dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}



	public String getDateFinEncheres() {
		return dateFinEncheres;
	}



	public void setDateFinEncheres(String dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}



	public int getMiseAPrix() {
		return miseAPrix;
	}



	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}



	public int getPrixVente() {
		return prixVente;
	}



	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}



	public boolean isEtatVente() {
		return etatVente;
	}



	public void setEtatVente(boolean etatVente) {
		this.etatVente = etatVente;
	}



	public Utilisateur getVendeur() {
		return vendeur;
	}



	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}



	public Retrait getLieuRetrait() {
		return lieuRetrait;
	}



	public void setLieuRetrait(Retrait lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	}



	public Categorie getCategorieArticle() {
		return categorieArticle;
	}



	public void setCategorieArticle(Categorie categorieArticle) {
		this.categorieArticle = categorieArticle;
	}



	public List<Enchere> getEncheres() {
		return encheres;
	}



	public void setEncheres(List<Enchere> encheres) {
		this.encheres = encheres;
	}



	@Override
	public String toString() {
		return "ArticlesVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", descritpion=" + descritpion
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", vendeur=" + vendeur
				+ ", lieuRetrait=" + lieuRetrait + ", categorieArticle=" + categorieArticle + ", encheres=" + encheres
				+ "]";
	}



	
	
	
}
