package fr.eni.tp.encheres.bo;

import java.io.Serializable;

public class Retrait implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String rue;
	private int codePostal;
	private String ville;
	
	public Retrait(String rue, int codePostal, String ville) {
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
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
	@Override
	public String toString() {
		return "Retrait [rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + "]";
	}
	
	
}
