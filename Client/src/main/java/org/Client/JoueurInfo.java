package org.Client;


public class JoueurInfo {
	
	protected int index;

	public JoueurInfo(int n) {
		index = n;
	}
	
	//Déjà titulaire d'une carte
	
	protected Carte[] card = new Carte[25];
	
	
	protected int cardNum = 0;  //Nombre de cartes déjà détenues

	
	protected int brownNum = 0; //Nombre de cartes de marrons
	
	
	protected int grayNum = 0;  //Nombre de cartes grises
	
	
	protected int blueNum = 0;  //Nombre de cartes bleues
	
	
	protected int greenNum = 0; //Nombre de cartes vertes
	
	
	protected int redNum = 0; //Nombre de carte rouge
	 
	
	protected int yellowNum = 0; //Nombre de carte jaune
	
	
	protected int purpleNum = 0; //Nombre de cartes violettes
	
	
	protected int freeNum = 0; //Nombre de cartes gratuites
	
	
	protected String[] freeBuild = new String[55]; //Carte gratuite

	/*
	 * Obtenir le groupe de cartes
	 */
	public Carte[] getCard() { 
		return card;
	}

	/**
	 * Ajouter des cartes au groupe de cartes
	 */
	public void addCard(Carte card) {
		this.card[cardNum++] = card;
	}




}
