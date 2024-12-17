package fr.eni.tp.encheres.dal;

import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import fr.eni.tp.encheres.bo.ArticlesVendu;

public class ArticleDAOImpl implements ArticleDAO {

	private static String INSERT  = "INSERT INTO [ARTICLES_VENDUS] (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) VALUES"
			                            + " ( :nom_article, :description, :date_debut_encheres,:date_fin_encheres , :prix_initial, :prix_vente, :no_utilisateur, :no_categorie)";
	
	private static String FIND_BY_NO  = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente"
			      							+ " u.no_utilisateur, c.no_categorie FROM ARTICLES_VENDUS "
			      							+ "inner join UTILISATEURS u on a.no_utilisateur = u.no_utilisateur "
			      							+ "inner join CATEGORIES c on c.no_categorie = a.no_categorie WHERE a.no_article = :no_article ";
			
	
	private static String FIND_ALL = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente,"
									 	+ " u.no_utilisateur, c.no_categorie FROM ARTICLES_VENDUS "
									 	+ "inner join UTILISATEURS u on a.no_utilisateur = u.no_utilisateur "
									 	+ "inner join CATEGORIES c on c.no_categorie = a.no_categorie ";
	
	private static String FIND_BY_ARTICLE  = "SELECT nom_article FROM ARTICLES_VENDUS WHERE no_article = :no_article";
	
	private static String COUNT_BY_ARTICLE  = "SELECT COUNT (*) FROM ARTICLES_VENDUS WHERE nom_article = :nom_article";
	
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public ArticleDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
}

	@Override
	public void create(ArticlesVendu articles) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArticlesVendu read(long noArticle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticlesVendu> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<ArticlesVendu> findArticle(long noArticle) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean etatVenteArticle(boolean etatVente) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
