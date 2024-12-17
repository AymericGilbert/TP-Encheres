package fr.eni.tp.encheres.dal;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import fr.eni.tp.encheres.bo.Utilisateur;

public class UtilisateurDAOImpl implements UtilisateurDAO {

	private static String FIND_BY_NO = "SELECT no_utilisateur, pseudo, nom, prenom FROM UTILISATEURS WHERE no_utilisateur = :no_utilisateur";
	
	private static String INSERT  = "INSERT INTO UTILISATEURS ( pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur ) "
									+ "VALUES (:idFilm, :idParticipant)";
	
	private static final String UPDATE = "UPDATE UTILISATEURS SET pseudo = :pseudo, nom = :nom, prenom = :prenom, email = :email, "
										  + "telephone = :telephone, rue = :rue, code_postal = :code_postal, ville = :ville, "
										  + "mot_de_passe = :mot_de_passe, credit = :credit, administrateur = :administrateur";
	
	//private static String COUNT_NOM  = " ";
	
	private static final String FIND_ALL = "SELECT email, nom, prenom FROM FORMATEURS";
	
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public UtilisateurDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
}

	@Override
	public Utilisateur read(long noUtilisateur) {
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("no_utilisateur", noUtilisateur);
		return this.jdbcTemplate.queryForObject(FIND_BY_NO, map, new BeanPropertyRowMapper<>(Utilisateur.class));
	}

	@Override
	public void createUtilisateur(Utilisateur utilisateur) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("pseudo", utilisateur.getPseudo());
		map.addValue("nom", utilisateur.getNom());
		map.addValue("prenom", utilisateur.getPrenom());
		map.addValue("email", utilisateur.getEmail());
		map.addValue("telephone", utilisateur.getTelephone());
		map.addValue("rue", utilisateur.getRue());
		map.addValue("code_postal", utilisateur.getCodePostal());
		map.addValue("ville", utilisateur.getVille());
		map.addValue("mot_de_passe", utilisateur.getMotDePasse());
		map.addValue("credit", utilisateur.getCredit());
		map.addValue("administrateur", utilisateur.isAdministrateur());
		
		this.jdbcTemplate.update(INSERT, map, keyHolder);
		if (keyHolder != null && keyHolder.getKey() != null) {
			utilisateur.setNoUtilisateur(keyHolder.getKey().longValue());
		}
		
	}

	//@Override
	public boolean compteDejaExistant(String nom) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(Utilisateur utilisateur) {
		
		MapSqlParameterSource map = new MapSqlParameterSource();
		
		map.addValue("pseudo", utilisateur.getPseudo());
		map.addValue("nom", utilisateur.getNom());
		map.addValue("prenom", utilisateur.getPrenom());
		map.addValue("email", utilisateur.getEmail());
		map.addValue("telephone", utilisateur.getTelephone());
		map.addValue("rue", utilisateur.getRue());
		map.addValue("code_postal", utilisateur.getCodePostal());
		map.addValue("ville", utilisateur.getVille());
		map.addValue("mot_de_passe", utilisateur.getMotDePasse());
		map.addValue("credit", utilisateur.getCredit());
		map.addValue("administrateur", utilisateur.isAdministrateur());
		
		this.jdbcTemplate.update(UPDATE, map);
		
	}

	@Override
	public List<Utilisateur> findAll() {
		return this.jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Utilisateur.class));
	}

}
