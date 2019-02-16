package org.Client;

import org.Client.ConstructionArticle;

public class Commerce extends ConstructionArticle {
	
	/**
	 * Utilisation réelle des ressources - pièces d'or
	 */
	protected int realCoin = 0;

	/**
	 * Vide
	 */
	public void clear() {
		putWood = 0;
		putStone = 0;
		putOre = 0;
		putBrick = 0;
		putGlass = 0;
		putCloth = 0;
		putPaper = 0;
		putCoin = 0;
		realCoin = 0;
	}

}
