package org.Client;

public class CarteInfo {
	
	private static Carte[] card = new Carte[149];
	
	public CarteInfo() {

		// Toutes les cartes de l'age 1----- On considère pour cette iteration que toutes cartes sont gratuites.
		
		card[0] = new Carte("Theatre", "Bleue", 1, 3);
		
		card[1] = new Carte("Autel", "Bleue", 1, 5);
		
		card[2] = new Carte("Bains", "Bleue", 1, 3);
		
		card[3] = new Carte("Bains", "Bleue", 1, 7);
		
		card[4] = new Carte("Preteur sur gages", "Bleue", 1, 4);

		card[5] = new Carte("Preteur sur gages", "Bleue", 1, 7);
		
		card[6] = new Carte("Bassin argileux", "Marron", 1, 3);
		
		card[7] = new Carte("Bassin argileux", "Marron", 1, 5);
		
		card[8] = new Carte("Fosse argileuse", "Marron", 1, 3);
		
		card[9] = new Carte("Filon", "Marron", 1, 3);
		
		card[10] = new Carte("Excavation", "Marron", 1, 4);
	
		card[11] = new Carte("Exploitation forestiere", "Marron", 1, 3);
		
		card[12] = new Carte("Filon", "Marron", 1, 4);
		
		card[13] = new Carte("Chantier", "Marron", 1, 3);
		
		card[14] = new Carte("Chantier", "Marron", 1, 4);
		
		card[15] = new Carte("Friche", "Marron", 1, 6);
		
		card[16] = new Carte("Mine", "Marron", 1, 6);
		
		card[17] = new Carte("Cavite", "Marron", 1, 5);
		
		card[18] = new Carte("Verrerie", "Grise", 1, 6);
		
		card[19] = new Carte("Presse", "Grise", 1, 3);
		
		card[20] = new Carte("Presse", "Grise", 1, 6);
		
		card[21] = new Carte("Metier a tisser", "Grise", 1, 3);
		
		card[22] = new Carte("Verrerie", "Grise", 1, 3);
		
		card[23] = new Carte("Metier a tisser", "Grise", 1, 6);
		
		card[24] = new Carte("Scriptorium", "Verte", 1, 3);
	
		card[25] = new Carte("Atelier", "Verte", 1, 7);
		
		card[26] = new Carte("Officine", "Verte", 1, 3);
		
		card[27] = new Carte("Scriptorium", "Verte", 1, 4);
		
		card[28] = new Carte("Officine", "Verte", 1, 5);
	
		card[29] = new Carte("Atelier", "Verte", 1, 3);
	
		card[30] = new Carte("Theatre", "Bleue", 1, 6);
		
		card[31] = new Carte("Autel", "Bleue", 1, 3);
		
		card[32] = new Carte("Tour de garde", "Rouge", 1, 4);
		
		card[33] = new Carte("Tour de garde", "Rouge", 1, 3);
	
		card[34] = new Carte("Caserne", "Rouge", 1, 3);
		
		card[35] = new Carte("Caserne", "Rouge", 1, 5);
		
		card[36] = new Carte("Palissade", "Rouge", 1, 7);
		
		card[37] = new Carte("Palissade", "Rouge", 1, 3);
		
		card[38] = new Carte("Gisement", "Marron", 1, 5);
		
		card[39] = new Carte("Cavite", "Marron", 1, 3);
		
		card[40] = new Carte("Comptoir est", "Jaune", 1, 3);
		
		card[41] = new Carte("Comptoir ouest", "Jaune", 1, 3);
		
		card[42] = new Carte("Comptoir est", "Jaune", 1, 7);
	
		card[43] = new Carte("Comptoir ouest", "Jaune", 1, 7);
		
		card[44] = new Carte("Taverne", "Jaune", 1, 4);
		
		card[45] = new Carte("Taverne", "Jaune", 1, 5);
		
		card[46] = new Carte("Taverne", "Jaune", 1, 7);
		
		card[47] = new Carte("Marche", "Jaune", 1, 6);
		
		card[48] = new Carte("Marche", "Jaune", 1, 3);
		
		
	}
	
	
	//Obtenir la carte par nom
	
	public static Carte getCardByName(String name) {
		for (int i = 0; i < 147; i++) {
			if (card[i].getName() == name) {
				return card[i];
			}
		}
		return null;
	}



	// Melanger les cartes au hasard dans le tableau
	
	private void melanger(int a, int b) {
		for (int i = a; i < b; i++) {
			int m = (int) Math.floor(Math.random() * (b - a)) + a;
			int n = (int) Math.floor(Math.random() * (b - a)) + a;
			card[148] = card[m];
			card[m] = card[n];
			card[n] = card[148];
		}
	}
}

		


	
