package fr.eni.tp.encheres.dal;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.tp.encheres.bo.Categorie;
@Repository
public class CategorieDAOImpl implements CategorieDAO {

	private static final String FIND_BY_NO = "SELECT no_categorie, libelle FROM CATEGORIES WHERE no_categorie = :no_categorie";
	
	private static String FIND_ALL = "SELECT no_categorie, libelle FROM CATEGORIES ";
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public CategorieDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
}

	@Override
	public Categorie read(long noCategorie) {
		String demande = FIND_BY_NO;
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("no_categorie", noCategorie);
		return this.jdbcTemplate.queryForObject(demande, map, new BeanPropertyRowMapper<>(Categorie.class));
	}

	@Override
	public List<Categorie> findAll() {
		
		return this.jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Categorie.class));
	}

}
