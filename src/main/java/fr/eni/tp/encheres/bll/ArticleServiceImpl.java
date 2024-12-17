package fr.eni.tp.encheres.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.tp.encheres.bo.ArticlesVendu;
import fr.eni.tp.encheres.bo.Categorie;
import fr.eni.tp.encheres.dal.ArticleDAO;
import fr.eni.tp.encheres.dal.CategorieDAO;

@Service
public class ArticleServiceImpl implements ArticleService {

	private ArticleDAO articleDAO;
	private CategorieDAO categorieDAO;
	
	
	public ArticleServiceImpl(ArticleDAO articleDAO, CategorieDAO categorieDAO) {
		this.articleDAO = articleDAO;
		this.categorieDAO = categorieDAO;
	}


	@Override
	public List<ArticlesVendu> consulterArticles() {
		return this.articleDAO.findAll();
	}


	@Override
	public ArticlesVendu consulterArticleParNo(long noArticle) {
		ArticlesVendu a = this.articleDAO.read(noArticle);
		return a;
	}


	@Override
	public List<Categorie> consulterCategorie() {
		return this.categorieDAO.findAll();
	}


	@Override
	public Categorie consulterCategorieParNo(long noArticle) {
		return this.categorieDAO.read(noArticle);
	}


	/*@Override
	public void creerArticle(ArticlesVendu article) throws BusinessException {
		BusinessException be = new BusinessException();
	 
		
	}
 
	private boolean validerArticleUnique(boolean etatVente, BusinessException be) {
		boolean etatVenteArticle = this.articleDAO.etatVenteArticle(etatVente);
		
		return null;
		
	}*/
	


}
