package fr.eni.tp.encheres.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.tp.encheres.bo.ArticlesVendu;
import fr.eni.tp.encheres.bo.Enchere;
import fr.eni.tp.encheres.bo.Utilisateur;
@Repository
public class EnchereDAOImpl implements EnchereDAO {

	private static String INSERT = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere)"
								 + " VALUES (:no_utilisateur, :no_article, :date_enchere, :montant_enchere);";
	
	private static String FIND_ALL  = "SELECT e.date_enchere, e.montant_enchere,"
									+ " u.no_utilisateur, a.no_article FROM  ENCHERES"
									+ " inner join UTILISATEURS u on e.no_utilisateur = u.no_utilisateur"
									+ " inner join ARTICLES_VENDUS a on e.no_article = a.no_article";
	
	private static String  DELETE_VENTE = "DELETE FROM ENCHERES WHERE no_article = :no_article";
	
	private static String  FIND_BY_NO = "SELECT e.date_enchere, e.montant_enchere,"
											+ " u.no_utilisateur, a.no_article FROM  ENCHERES"
											+ " inner join UTILISATEURS u on e.no_utilisateur = u.no_utilisateur"
											+ " inner join ARTICLES_VENDUS a on e.no_article = a.no_article WHERE e.no_article = :no_article ";
	
	private static String FIND_OFFRE_BY_NO ="SELECT ISNULL(MAX(montant_enchere), 0) FROM ENCHERES WHERE no_article = :noArticle";
	
	private static String SELECT_COUNT = "SELECT COUNT(*) FROM ENCHERES WHERE no_utilisateur = :noUtilisateur AND no_article = :noArticle";
	
	private static String UPDATE_SET = "UPDATE ENCHERES SET montant_enchere = :montantEnchere, date_enchere = GETDATE() "
										+  "WHERE no_utilisateur = :noUtilisateur AND no_article = :noArticle";
	
	private static String INSERT_GETDATE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) "
            								+ "VALUES (:noUtilisateur, :noArticle, GETDATE(), :montantEnchere)";
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public EnchereDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
}

	@Override
	public void create(Enchere enchere) {
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("no_utilisateur", enchere.getEncherit().getNoUtilisateur());
		map.addValue("no_article", enchere.getConcerne().getNoArticle());
		map.addValue("date_enchere", enchere.getDateEnchere());
		map.addValue("montant_enchere", enchere.getMontantEnchere());
		
		this.jdbcTemplate.update(INSERT, map);
	}

	@Override
	public List<Enchere> findall() {
		return this.jdbcTemplate.query(FIND_ALL, new EnchereRawMapper());
	}

	@Override
	public void annulerVente(long noArticle) {
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("no_article", noArticle);
		this.jdbcTemplate.update(DELETE_VENTE, map);
	}

	@Override
	public Enchere read(long noArticle) {
		MapSqlParameterSource map= new MapSqlParameterSource();
		map.addValue("no_article", noArticle);
		return this.jdbcTemplate.queryForObject(FIND_BY_NO, map, new EnchereRawMapper());
	}

	class EnchereRawMapper implements RowMapper<Enchere>{

		@Override
		public Enchere mapRow(ResultSet rs, int rowNum) throws SQLException {
			Enchere e = new Enchere();
			e.setDateEnchere(rs.getString("date_enchere"));
			e.setMontantEnchere(rs.getInt("montant_enchere"));
			
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setNoUtilisateur(rs.getLong("no_utilisateur"));
			e.setEncherit(utilisateur);
			
			ArticlesVendu article = new ArticlesVendu();
			article.setNoArticle(rs.getLong("no_article"));
			e.setConcerne(article);
			return e;
		}
		
	}

	@Override
	public int laMeilleureOffre(long noArticle) {
		 MapSqlParameterSource map = new MapSqlParameterSource();
	        map.addValue("noArticle", noArticle);

	        return jdbcTemplate.queryForObject(FIND_OFFRE_BY_NO, map, Integer.class);
	}

	@Override
	public void ajouterEnchere(long noUtilisateur, long noArticle, int montantEnchere) {
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("noUtilisateur", noUtilisateur);
		map.addValue("noArticle", noArticle);
		     
		int count = jdbcTemplate.queryForObject(SELECT_COUNT, map, Integer.class);
		if (count > 0) {
			map.addValue("montantEnchere", montantEnchere);
			jdbcTemplate.update(UPDATE_SET, map);
		}else {
			map.addValue("montantEnchere", montantEnchere);
			jdbcTemplate.update(INSERT_GETDATE, map);
		}
	}
}
