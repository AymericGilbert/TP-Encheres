package fr.eni.tp.encheres.dal;

import fr.eni.tp.encheres.bo.Retrait;

public interface RetraitDAO {

	Retrait read(long noArticle);
}
