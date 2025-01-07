package fr.eni.tp.encheres.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fr.eni.tp.encheres.bo.ArticlesVendu;
import fr.eni.tp.encheres.bo.Categorie;
import fr.eni.tp.encheres.bo.Utilisateur;
@Repository
public class ArticleDAOImpl implements ArticleDAO {

	private static String INSERT  = "INSERT INTO [ARTICLES_VENDUS] (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) VALUES"
			                            + " ( :nom_article, :description, :date_debut_encheres,:date_fin_encheres , :prix_initial, :prix_vente, :no_utilisateur, :no_categorie)";
	
	private static String FIND_BY_NO  = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente,"
			      							+ " u.no_utilisateur, c.no_categorie,c.libelle FROM ARTICLES_VENDUS a "
			      							+ "inner join UTILISATEURS u on a.no_utilisateur = u.no_utilisateur "
			      							+ "inner join CATEGORIES c on a.no_categorie = c.no_categorie WHERE a.no_article = :no_article ";
			
	
	private static String FIND_ALL = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente,"
									 	+ " u.no_utilisateur, c.no_categorie,c.libelle FROM ARTICLES_VENDUS a "
									 	+ "inner join UTILISATEURS u on a.no_utilisateur = u.no_utilisateur "
									 	+ "inner join CATEGORIES c on a.no_categorie = c.no_categorie ";
	
	private static String FIND_BY_ARTICLE  = "SELECT nom_article FROM ARTICLES_VENDUS WHERE no_article = :no_article";
	
	private static String COUNT_BY_ARTICLE  = "SELECT COUNT (*) FROM ARTICLES_VENDUS WHERE nom_article = :nom_article";
	
	private static String  UPDATE  = "UPDATE ARTICLES_VENDUS SET prix_vente = :prix_vente  WHERE no_article = :prix_vente";
	
	private static String UPDATE_PRIX = "UPDATE ARTICLES_VENDUS SET prix_vente = :montantEnchere WHERE no_article = :noArticle";
	
	

	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public ArticleDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
}
	@Override
	public void create(ArticlesVendu articles) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("nom_article", articles.getNomArticle());
		map.addValue("description", articles.getDescription());
		map.addValue("date_debut_encheres", articles.getDateDebutEncheres());
		map.addValue("date_fin_encheres", articles.getDateFinEncheres());
		map.addValue("prix_initial", articles.getMiseAPrix());
		map.addValue("prix_vente", articles.getPrixVente());
		map.addValue("no_utilisateur", articles.getVendeur().getNoUtilisateur());
		map.addValue("no_categorie", articles.getCategorieArticle().getNoCategorie());
		
		this.jdbcTemplate.update(INSERT, map, keyHolder);
		if (keyHolder != null && keyHolder.getKey() != null) {
			articles.setNoArticle(keyHolder.getKey().longValue());
		}
	}

	@Override 
	public ArticlesVendu read(long noArticle) {
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("no_article", noArticle);
		return this.jdbcTemplate.queryForObject(FIND_BY_NO, map, new ArticleRowMapper());
	}

	@Override
	public List<ArticlesVendu> findAll() {
		return this.jdbcTemplate.query(FIND_ALL, new ArticleRowMapper());
	}
	
	@Override
	public String findArticle(long noArticle) {
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("no_article", noArticle);
		return jdbcTemplate.queryForObject(FIND_BY_ARTICLE, map, String.class);
	}
	
	//@Override
	public boolean etatVenteArticle(boolean etatVente) {
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("no_article", etatVente);
		
		int nbArticle = jdbcTemplate.queryForObject(COUNT_BY_ARTICLE, map, Integer.class);
		return nbArticle > 0 ? true : false;
	}

	class ArticleRowMapper implements RowMapper<ArticlesVendu>{

		@Override
		public ArticlesVendu mapRow(ResultSet rs, int rowNum) throws SQLException {
			ArticlesVendu a = new ArticlesVendu();
			a.setNoArticle(rs.getLong("no_article"));
			a.setNomArticle(rs.getString("nom_article"));
			a.setDescription(rs.getString("description"));
			a.setDateDebutEncheres(rs.getTimestamp("date_debut_encheres").toLocalDateTime());
			a.setDateFinEncheres(rs.getTimestamp("date_fin_encheres").toLocalDateTime());
			a.setMiseAPrix(rs.getInt("prix_initial"));
			a.setPrixVente(rs.getInt("prix_vente"));
			
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setNoUtilisateur(rs.getLong("no_utilisateur"));
			a.setVendeur(utilisateur);
			
			Categorie categorie = new Categorie();
			categorie.setNoCategorie(rs.getLong("no_categorie"));
			categorie.setLibelle(rs.getString("libelle"));
			a.setCategorieArticle(categorie);
			
			
			return a;
		}
		
		
	}


	@Override
	public void update(ArticlesVendu articlesVendu) {
		MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("prixVente", articlesVendu.getPrixVente());
        map.addValue("noArticle", articlesVendu.getNoArticle());

        jdbcTemplate.update(UPDATE, map);
  
	}
	@Override
	public void mettreAJourmeilleurOffre(long noArticle, int montantEnchere) {
		 MapSqlParameterSource map = new MapSqlParameterSource();
		 map.addValue("montantEnchere", montantEnchere);
		 map.addValue("noArticle", noArticle);

	        jdbcTemplate.update(UPDATE_PRIX, map);
		
	}

	

}
