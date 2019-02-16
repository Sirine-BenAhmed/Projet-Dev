package org.Client;

import org.Client.ConstructionArticle;

public class Merveille extends ConstructionArticle {
	
	private String name;
	

	

	//Constructeur
	 
	public Merveille(String name) {
		this.name = name;
	}

	
	protected Carte[] cards = new Carte[5];
	
	protected int max = 3; //Nombre maximum de couches pouvant être construites
	
	protected int age = 0;


}
