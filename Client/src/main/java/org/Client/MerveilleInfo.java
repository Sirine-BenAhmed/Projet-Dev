package org.Client;

public class MerveilleInfo {
	
	Merveille[] merveille = new Merveille[16];
	
	 //Intraclasse
	 
	public MerveilleInfo() {
		
		merveille[0] = new Merveille("Babylon");//(name, side)
		

		merveille[1] = new Merveille("Rhodes");
		

		merveille[2] = new Merveille("Halicarnassus");
		

		merveille[3] = new Merveille("Giza");
		

		merveille[4] = new Merveille("Alexandria");


		merveille[5] = new Merveille("Olympia");

		
		merveille[6] = new Merveille("Ephesus");
	}

		
		
	/*
	 * Distribuer au hasard
	 */
	public void melanger(int a, int b) {
		for (int i = a; i < b; i++) {
			int m = (int) Math.floor(Math.random() * (b - a)) + a;
			int n = (int) Math.floor(Math.random() * (b - a)) + a;
			merveille[15] = merveille[m];
			merveille[m] = merveille[n];
			merveille[n] = merveille[15];
		}
	}

}
