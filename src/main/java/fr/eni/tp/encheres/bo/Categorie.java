package fr.eni.tp.encheres.bo;

import java.io.Serializable;
import java.util.List;

public class Categorie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long noCategorie;
	private String libelle;
	
	private List<ArticlesVendu> categorieArticle;
	

	public Categorie(long noCategorie, String libelle, List<ArticlesVendu> categorieArticle) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
		this.categorieArticle = categorieArticle;
	}

	public List<ArticlesVendu> getCategorieArticle() {
		return categorieArticle;
	}

	public void setCategorieArticle(List<ArticlesVendu> categorieArticle) {
		this.categorieArticle = categorieArticle;
	}

	public long getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(long noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + "]";
	}
	
	
}
