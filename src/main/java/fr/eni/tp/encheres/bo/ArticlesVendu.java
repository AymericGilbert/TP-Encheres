package fr.eni.tp.encheres.bo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class ArticlesVendu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long noArticle;
	private String nomArticle;
	private String description;
	private LocalDateTime dateDebutEncheres;
	private LocalDateTime dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private boolean etatVente;
	
	private Utilisateur vendeur;
	
	private Retrait lieuRetrait;
	
	private Categorie categorieArticle;
	
	private List<Enchere> encheres;

	
	
	public ArticlesVendu() {
		super();
	}



	public ArticlesVendu(long noArticle, String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int miseAPrix, int prixVente, boolean etatVente, Utilisateur vendeur,
			Retrait lieuRetrait, Categorie categorieArticle, List<Enchere> encheres) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
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



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public LocalDateTime getDateDebutEncheres() {
		return dateDebutEncheres;
	}



	public void setDateDebutEncheres(LocalDateTime dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}



	public LocalDateTime getDateFinEncheres() {
		return dateFinEncheres;
	}



	public void setDateFinEncheres(LocalDateTime dateFinEncheres) {
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
		return "ArticlesVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", vendeur=" + vendeur
				+ ", lieuRetrait=" + lieuRetrait + ", categorieArticle=" + categorieArticle + ", encheres=" + encheres
				+ "]";
	}
	
}