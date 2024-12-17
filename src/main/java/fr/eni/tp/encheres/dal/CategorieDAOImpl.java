package fr.eni.tp.encheres.dal;

import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import fr.eni.tp.encheres.bo.Categorie;

public class CategorieDAOImpl implements CategorieDAO {

	private static String FIND_BY_NO = " ";
	
	private static String FIND_ALL = " ";
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public CategorieDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
}

	@Override
	public Categorie read(long noCategorie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categorie> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
