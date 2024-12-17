package fr.eni.tp.encheres.dal;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import fr.eni.tp.encheres.bo.Utilisateur;

public class UtilisateurDAOImpl implements UtilisateurDAO {

	private static String FIND_BY_NO = " ";
	
	private static String INSERT  = " ";
	
	//private static String COUNT_NOM  = " ";
	
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public UtilisateurDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
}

	@Override
	public Utilisateur read(long noUtilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createUtilisateur(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		
	}

	//@Override
	public boolean compteDejaExistant(String nom) {
		// TODO Auto-generated method stub
		return false;
	}

}
