package fr.eni.tp.encheres.dal;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import fr.eni.tp.encheres.bo.Retrait;

public class RetraitDAOImpl implements RetraitDAO {

	private static String FIND_BY_NO  = " ";
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public RetraitDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
}

	@Override
	public Retrait read(long noArticle) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
