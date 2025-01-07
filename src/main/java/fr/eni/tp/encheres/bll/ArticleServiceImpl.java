package fr.eni.tp.encheres.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.tp.encheres.bo.ArticlesVendu;
import fr.eni.tp.encheres.bo.Categorie;
import fr.eni.tp.encheres.bo.Utilisateur;
import fr.eni.tp.encheres.dal.ArticleDAO;
import fr.eni.tp.encheres.dal.CategorieDAO;
import fr.eni.tp.encheres.dal.EnchereDAO;
import fr.eni.tp.encheres.exception.BusinessException;

@Service
public class ArticleServiceImpl implements ArticleService {

	private ArticleDAO articleDAO;
	private CategorieDAO categorieDAO;
	private EnchereDAO enchereDAO;
	private UtilisateurService utilisateurService;
	
	
	


	public ArticleServiceImpl(ArticleDAO articleDAO, CategorieDAO categorieDAO, EnchereDAO enchereDAO,
			UtilisateurService utilisateurService) {
		this.articleDAO = articleDAO;
		this.categorieDAO = categorieDAO;
		this.enchereDAO = enchereDAO;
		this.utilisateurService = utilisateurService;
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
	public Categorie consulterCategorieParNo(long noCategorie) {
		return this.categorieDAO.read(noCategorie);
	}


	@Override
	public void creerArticle(ArticlesVendu article) throws BusinessException {
		BusinessException be = new BusinessException();
		this.articleDAO.create(article);
	 
		
	}


	@Override
	public void encherir(long noArticle, String email, int montantEnchere) throws Exception {
		System.out.println(noArticle +" - " + email + " - " + montantEnchere);
		
		ArticlesVendu article = articleDAO.read(noArticle);
		Utilisateur utilisateur = utilisateurService.findByEmail(email);
		
		if (montantEnchere <= article.getMiseAPrix()) {
			throw new Exception("l'enchere doit etre superieur au prix de vente");
		}
		if (montantEnchere > utilisateur.getCredit()) {
			throw new Exception("vous n'avez pas assez de credits");
		}
		int meilleureOffre = enchereDAO.laMeilleureOffre(montantEnchere);
		
		if (montantEnchere <= meilleureOffre) {
			throw new Exception("L'enchere doit etre superieur au prix de vente actuel");
		}
		//pour mettre a jour dans la table
		enchereDAO.ajouterEnchere(utilisateur.getNoUtilisateur(), noArticle, montantEnchere);
		
		//pour MAJ le PDV dans la table articleVendu
		articleDAO.mettreAJourmeilleurOffre(noArticle, montantEnchere);
		
		//pour mettre a jour les credit de l'utilisateur
		utilisateurService.deduireCredit(utilisateur.getNoUtilisateur(), montantEnchere);
		
		System.out.println("enchere réussie" + montantEnchere + " - à  " + utilisateur.getEmail());
	}
	
	
	
	
	/*private boolean validerArticleUnique(boolean etatVente, BusinessException be) {
		boolean etatVenteArticle = this.articleDAO.etatVenteArticle(etatVente);
		
		return true;
		
	}*/
	


}
