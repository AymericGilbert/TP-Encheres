package fr.eni.tp.encheres.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fr.eni.tp.encheres.bo.ArticlesVendu;
import fr.eni.tp.encheres.bo.Categorie;
import fr.eni.tp.encheres.bo.Retrait;
import fr.eni.tp.encheres.bo.Utilisateur;
import fr.eni.tp.encheres.security.SecuriteConfig;
@Repository
public class ArticleDAOImpl implements ArticleDAO {

	private static String INSERT  = "INSERT INTO [ARTICLES_VENDUS] (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) VALUES"
			                            + " ( :nom_article, :description, :date_debut_encheres,:date_fin_encheres , :prix_initial, :prix_vente, :no_utilisateur, :no_categorie)";

	
	private static String FIND_BY_NO  = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente,"
			      							+ " u.no_utilisateur, u.pseudo, u.nom, u.prenom, u.email, u.telephone, u.rue, u.code_postal, u.ville, "
			      							+ "c.no_categorie,c.libelle FROM ARTICLES_VENDUS a "
			      							+ "inner join UTILISATEURS u ON a.no_utilisateur = u.no_utilisateur "
			      							+ "inner join CATEGORIES c on a.no_categorie = c.no_categorie WHERE a.no_article = :no_article ";
			
	
	private static String FIND_ALL = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente,"
										 + " u.no_utilisateur,  u.pseudo, u.nom, u.prenom, u.email, u.telephone, u.rue, u.code_postal, u.ville, "
										 + "c.no_categorie,c.libelle FROM ARTICLES_VENDUS a "
										 + "inner join UTILISATEURS u ON a.no_utilisateur = u.no_utilisateur "
										 + "inner join CATEGORIES c on a.no_categorie = c.no_categorie ";
	
	private static String FIND_BY_ARTICLE  = "SELECT nom_article FROM ARTICLES_VENDUS WHERE no_article = :no_article";
	
	private static String COUNT_BY_ARTICLE  = "SELECT COUNT (*) FROM ARTICLES_VENDUS WHERE nom_article = :nom_article";
	
	private static String  UPDATE  = "UPDATE ARTICLES_VENDUS SET prix_vente = :prix_vente  WHERE no_article = :prix_vente";
	
	private static String UPDATE_PRIX = "UPDATE ARTICLES_VENDUS SET prix_vente = :montantEnchere WHERE no_article = :noArticle";
	
	//utilisation de 1=1 pour permmettre de decomplexifié la syntaxe permettant d'utiliser AND dans la suite de la methode
	private static final String FIND_BY_NAME = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, "
												+ "a.prix_initial, a.prix_vente, a.no_utilisateur, a.no_categorie, u.no_utilisateur,  u.pseudo, u.nom, u.prenom, u.email, "
												+ "u.telephone, u.rue, u.code_postal, u.ville, c.libelle "
												+ "FROM ARTICLES_VENDUS a "
												+ "inner JOIN Categories c ON a.no_categorie = c.no_categorie "
												+ "inner join UTILISATEURS u ON a.no_utilisateur = u.no_utilisateur "
												+ "LEFT JOIN ENCHERES e on e.no_article = a.no_article "
												+ "WHERE 1 = 1";

	
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
		try {
			return this.jdbcTemplate.queryForObject(FIND_BY_NO, map, new ArticleRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		
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
			utilisateur.setPseudo(rs.getString("pseudo"));
			utilisateur.setNom(rs.getString("nom"));
			utilisateur.setPrenom(rs.getString("prenom"));
			utilisateur.setEmail(rs.getString("email"));
			utilisateur.setTelephone(rs.getString("telephone"));
			utilisateur.setRue(rs.getString("rue"));
			utilisateur.setCodePostal(rs.getInt("code_postal"));
			a.setVendeur(utilisateur);
			
			Retrait retrait = new Retrait();
			retrait.setRue(rs.getString("rue"));
			retrait.setCodePostal(rs.getInt("code_postal"));
			retrait.setVille(rs.getString("ville"));
			
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
	@Override
	public List<ArticlesVendu> rechercheArticle(String nomArticle, Long no_categorie, Boolean encheresOuvertes, Boolean encheresEnCours, Boolean encheresRemportees,
	        Boolean ventesEnCours, Boolean ventesNonDebutees, Boolean ventesTerminees, Utilisateur utilisateur) {
		String requete = FIND_BY_NAME;
		MapSqlParameterSource map = new MapSqlParameterSource();
		if (nomArticle != null && !nomArticle.trim().isEmpty()) {
			requete += " AND a.nom_article LIKE :nomArticle";
			// ici "%" sert de caractere générique pour effectuer une recherche dans une colonne texte
			map.addValue("nomArticle", "%" + nomArticle + "%");
		}
		// sql marche
		if (no_categorie != null && !no_categorie.equals(0L) && !no_categorie.equals("Toutes")) {  //!no_categorie.equals(0L) && !no_categorie.equals("Toutes")
			requete += " AND c.no_categorie = :no_categorie";
			map.addValue("no_categorie", no_categorie);
		}
		 if (Boolean.TRUE.equals(encheresOuvertes)) {
			 requete += " AND a.date_fin_encheres > GETDATE()";
			 map.addValue("currentUser", utilisateur.getNoUtilisateur());
		 }
			// sql marche
		 if (Boolean.TRUE.equals(encheresEnCours)) {
			        requete += " AND e.no_utilisateur = :currentUser AND a.date_debut_encheres <= GETDATE() AND a.date_fin_encheres > GETDATE()";
			        map.addValue("currentUser", utilisateur.getNoUtilisateur());
			    }
			    if (Boolean.TRUE.equals(encheresRemportees)) {
			        requete += " AND e.no_utilisateur = :currentUser AND a.date_fin_encheres <= GETDATE()";
			        map.addValue("currentUser", utilisateur.getNoUtilisateur());
			    }
			 // sql marche
			    if (Boolean.TRUE.equals(ventesEnCours)) {
			        requete += " AND a.no_utilisateur = :currentUser AND a.date_debut_encheres <= GETDATE() AND a.date_fin_encheres > GETDATE()";
			        map.addValue("currentUser", utilisateur.getNoUtilisateur());
			    }
			    if (Boolean.TRUE.equals(ventesNonDebutees)) {
			        requete += " AND a.no_utilisateur = :currentUser AND a.date_debut_encheres > GETDATE()";
			        map.addValue("currentUser", utilisateur.getNoUtilisateur());
			    }
			    if (Boolean.TRUE.equals(ventesTerminees)) {
			        requete += " AND a.no_utilisateur = :currentUser AND a.date_fin_encheres <= GETDATE()";
			        map.addValue("currentUser", utilisateur.getNoUtilisateur());
			    }
			    System.out.println("Requête SQL : " + requete);
			    System.out.println("Paramètres : " + map.getValues());
			    
		return jdbcTemplate.query(requete, map, new ArticleRowMapper());
	}

		//es que date_enchere et date_fin_enchere doivent correspondre (logiquement 
		//non car date enchere refererences la date ou un utilisateur a encherie

}

