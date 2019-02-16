package org.Client;


public class Article {
	
		protected String name; // Nom

		//Obtenir le nom
		public String getName() {
			return name;
		}

		protected int getWood = 0; // Ressources de production - bois
		
		protected int getStone = 0; //Ressources de production  - pierre
		
		protected int getOre = 0; //Consommation de production - minéraux

		protected int getBrick = 0;
		
		protected int getGlass = 0;
		
		protected int getCloth = 0;
		
		protected int getPaper = 0;
		
		protected int getCoin = 0;
		
		/**
		 * Production double ressource pierre -bois
		 */
		protected int getWoodStone = 0;
		
		protected int getWoodOre = 0;
		
		protected int getWoodBrick = 0;
		
		protected int getStoneOre = 0;
		
		protected int getStoneBrick = 0;
		
		protected int getOreBrick = 0;
		
		protected int getWoodStoneOreBrick = 0; //Production de quatre ressources - bois, pierre, minéraux et briques
		
		protected int getGlassClothPaper = 0; //Production de trois ressources - papier de verre
	
}
