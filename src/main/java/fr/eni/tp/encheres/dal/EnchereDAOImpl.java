package fr.eni.tp.encheres.dal;

import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import fr.eni.tp.encheres.bo.Enchere;

public class EnchereDAOImpl implements EnchereDAO {

	private static String INSERT = " ";
	
	private static String FIND_ALL  = " ";
	
	private static String  DELETE_VENTE = " ";
	
	private static String  FIND_BY_NO = " ";
	
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public EnchereDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
}

	@Override
	public void create(Enchere enchere) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Enchere> findall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void annulerVente(long noArticle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enchere read(long noArticle) {
		// TODO Auto-generated method stub
		return null;
	}

}
