package fr.eni.tp.encheres.bo;

import java.io.Serializable;

public class Enchere implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dateEnchere;
	private int montantEnchere;
	//encherisseur
	private Utilisateur encherit;
	//article
	private ArticlesVendu concerne;
	
	

	public Enchere(String dateEnchere, int montantEnchere, Utilisateur encherit, ArticlesVendu concerne) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.encherit = encherit;
		this.concerne = concerne;
	}

	public Utilisateur getEncherit() {
		return encherit;
	}

	public void setEncherit(Utilisateur encherit) {
		this.encherit = encherit;
	}

	public ArticlesVendu getConcerne() {
		return concerne;
	}

	public void setConcerne(ArticlesVendu concerne) {
		this.concerne = concerne;
	}

	public String getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(String dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + ", encherit=" + encherit
				+ ", concerne=" + concerne + "]";
	}

	
	
	
}
