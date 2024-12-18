package fr.eni.tp.encheres.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.eni.tp.encheres.bll.ArticleService;
import fr.eni.tp.encheres.bo.Categorie;

@Component
public class StringToCategorieConverter implements Converter<String, Categorie> {

	private ArticleService articleService;
	
	
	
	public StringToCategorieConverter(ArticleService articleService) {
		this.articleService = articleService;
	}



	@Override
	public Categorie convert(String noCategorie) {
		
		return this.articleService.consulterCategorieParNo(Long.parseLong(noCategorie));
	}

}
