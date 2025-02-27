package fr.eni.tp.encheres.dal;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import fr.eni.tp.encheres.bo.Utilisateur;


@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO {

	private static String FIND_BY_EMAIL = "SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit, no_utilisateur FROM UTILISATEURS WHERE email = :email";
	
	private static String INSERT  = "INSERT INTO UTILISATEURS ( pseudo, nom, prenom, email, telephone, "
								    + "rue, code_postal, ville, mot_de_passe, credit, administrateur ) "
									+ "VALUES (:pseudo, :nom, :prenom, :email, :telephone, :rue, :code_postal, :ville, "
									+ ":mot_de_passe, :credit, :administrateur)";
	
	private static final String UPDATE = "UPDATE UTILISATEURS SET pseudo = :pseudo, nom = :nom, prenom = :prenom, email = :email, "
										  + "telephone = :telephone, rue = :rue, code_postal = :code_postal, ville = :ville, "
										  + "mot_de_passe = :mot_de_passe WHERE no_utilisateur = :no_utilisateur";
	
	private static final String DELETE = "DELETE FROM UTILISATEURS WHERE email = :email";
	
	private static final String FIND_PSEUDO = "SELECT pseudo FROM UTILISATEURS WHERE pseudo = :pseudo";
	
	private static final String FIND_EMAIL = "SELECT email FROM UTILISATEURS WHERE email = :email";
	
	private static final String FIND_BY_NO = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = :noUtilisateur";
	
	private static final String UPDATE_CREDIT_BY_NO = "UPDATE utilisateurs SET credit = :credit WHERE no_utilisateur = :noUtilisateur";
	
	private static final String FIND_CREDIT_BY_NO = "SELECT credit FROM utilisateurs WHERE no_utilisateur= :noUtilisateur";
	
	
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public UtilisateurDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
}

	@Override
	public Utilisateur readByEmailUtilisateur(String emailUtilisateur) {
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("email", emailUtilisateur);
		return this.jdbcTemplate.queryForObject(FIND_BY_EMAIL, map, new BeanPropertyRowMapper<>(Utilisateur.class));
	}
	
	@Override
	public boolean existByPseudo(String pseudoUtilisateur, long noUtilisateur) {
		String requete = FIND_PSEUDO;
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("pseudo", pseudoUtilisateur);
		
		if (noUtilisateur != 0) {
			map.addValue("noUtilisateur", noUtilisateur);
			requete += " AND no_utilisateur <> :noUtilisateur";
		}
		
		List<String> pseudos = jdbcTemplate.queryForList(requete, map, String.class);
		
		if (pseudos.isEmpty()) {
			return false;
		} 
		
		return true;
	}
	
	@Override
	public boolean existByEmail(String emailUtilisateur, long noUtilisateur) {
		String requete = FIND_EMAIL;
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("email", emailUtilisateur);
		
		if (noUtilisateur != 0) {
			map.addValue("noUtilisateur", noUtilisateur);
			requete += " AND no_utilisateur <> :noUtilisateur";
		}
		
		List<String> emails = jdbcTemplate.queryForList(requete, map, String.class);
		
		if (emails.isEmpty()) {
			return false;
		}
		
		return true;
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
		map.addValue("no_utilisateur", utilisateur.getNoUtilisateur());
		
		this.jdbcTemplate.update(UPDATE, map);
	}
	
	@Override
	public void delete(Utilisateur utilisateur) {
		
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("email", utilisateur.getEmail());
		
		this.jdbcTemplate.update(DELETE, map);
		System.out.println("L'utilisateur à été supprimé");
	}

	@Override
	public void updateCredit(long noUtilisateur, int credit) {
		MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("credit", credit);
        map.addValue("noUtilisateur", noUtilisateur);
        System.out.println("Mise à jour des crédits pour noUtilisateur : " + noUtilisateur + " avec montant : " + credit);


        this.jdbcTemplate.update(UPDATE_CREDIT_BY_NO, map);
		
	}

	@Override
	public int totalCreditUtilisateur(long noUtilisateur) {
		 MapSqlParameterSource map = new MapSqlParameterSource();
	     map.addValue("noUtilisateur", noUtilisateur);
	     return jdbcTemplate.queryForObject(FIND_CREDIT_BY_NO, map, Integer.class);
	}

	@Override
	public Utilisateur readByNoUtilisateur(long noUtilisateur) {
		MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("noUtilisateur", noUtilisateur);

        return this.jdbcTemplate.queryForObject(FIND_BY_NO, map, new BeanPropertyRowMapper<>(Utilisateur.class));

	
	}
	
}
