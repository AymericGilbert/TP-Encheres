package fr.eni.tp.encheres.dal;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.tp.encheres.bo.Retrait;
@Repository
public class RetraitDAOImpl implements RetraitDAO {

	private static String FIND_BY_NO  = "SELECT a.no_article, r.rue, r.code_postal, r.ville FROM RETRAITS"
									  + "inner join ARTICLES_VENDUS a on r.no_article = a.no_article WHERE r.no_article = :no_article";
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public RetraitDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
}

	@Override
	public Retrait read(long noArticle) {
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("no_article", noArticle);
		return this.jdbcTemplate.queryForObject(FIND_BY_NO, map, new BeanPropertyRowMapper<>(Retrait.class));
	}
	
	
}
