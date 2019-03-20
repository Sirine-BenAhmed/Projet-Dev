package donnees;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    private ArrayList<Carte> cartes ;
    
  //création des cartes marrons de l'age 1
    Carte carte1 = new Carte("Chantier");
    
	Carte carte2 = new Carte("Cavite");
	
	Carte carte3 = new Carte("bassin-Argileux");
	
	Carte carte4 = new Carte("Filon");
	
	Carte carte5 = new Carte("Friche");

	Carte carte6 = new Carte("Excavation");
	
	Carte carte7 = new Carte("Fosse-Argileuse");
	
	Carte carte8 = new Carte("Exploitation-Forestiere");
	
	Carte carte9 = new Carte("Gisement");
	
	Carte carte10 = new Carte("Mine");
	
	Carte carte11 = new Carte("Scierie");

	Carte carte12 = new Carte("Carriere");
	
	Carte carte13 = new Carte("Briqueterie");
	
	Carte carte14 = new Carte("Fonderie");
	

    public ArrayList<Carte> getCartes() {
        return cartes;
    }

    public void setCartes(ArrayList<Carte> cartes) {
        this.cartes = cartes;
    }

    public Main() {
    	cartes = new ArrayList<>();
    	cartes.add(carte1);
    	cartes.add(carte2);
    	cartes.add(carte3);
    	cartes.add(carte4);
    	cartes.add(carte5);
    	cartes.add(carte6);
    	cartes.add(carte7);
    	cartes.add(carte8);
    	cartes.add(carte9);
    	cartes.add(carte10);
    	cartes.add(carte11);
    	cartes.add(carte12);
    	cartes.add(carte13);
    	cartes.add(carte14);
    	
    	Collections.shuffle(cartes);
    	
    }
    public Main(ArrayList<Carte> cartes) {
        this.cartes = cartes;
    }


    public void ajouterCarte(Carte c) {
        getCartes().add(c);
    }


    public String toString()  {
        String texte = "[";

        for(Carte c : cartes) texte += c +" ; ";

        if (texte.length() > 4) texte = texte.substring(0, texte.length()-3);

        texte += "]";
        return texte;
    }
}
