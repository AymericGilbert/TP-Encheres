package fr.eni.tp.encheres.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.tp.encheres.bo.ArticlesVendu;
import fr.eni.tp.encheres.exception.BusinessException;
import fr.eni.tp.encheres.bo.Categorie;
import fr.eni.tp.encheres.bo.Utilisateur;
import fr.eni.tp.encheres.dal.ArticleDAO;
import fr.eni.tp.encheres.dal.CategorieDAO;
import fr.eni.tp.encheres.dal.UtilisateurDAO;

@Service
public class ArticleServiceImpl implements ArticleService {

	private ArticleDAO articleDAO;
	private UtilisateurDAO utilisateurDAO;
	private CategorieDAO categorieDAO;
	
	
	public ArticleServiceImpl(ArticleDAO articleDAO, UtilisateurDAO utilisateurDAO, CategorieDAO categorieDAO) {
		this.articleDAO = articleDAO;
		this.utilisateurDAO = utilisateurDAO;
		this.categorieDAO = categorieDAO;
	}

	@Override
	public List<ArticlesVendu> consulterArticles() {
		return this.articleDAO.findAll();
	}

	@Override
	public ArticlesVendu consulterArticleParId(long id) {
		return this.articleDAO.read(id);
	}

	@Override
	public List<Categorie> consulterCategorie() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categorie consulterCategorieParId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utilisateur> consulterUtilisateur() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur consulterUtilisateurParId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void creerArticle(ArticlesVendu article) throws BusinessException {
		// TODO Auto-generated method stub
		
	}


}
