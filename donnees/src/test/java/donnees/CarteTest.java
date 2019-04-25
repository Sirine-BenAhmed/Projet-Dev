package donnees;

import donnees.Carte;

import static org.junit.jupiter.api.Assertions.*;

class CarteTest {

    String name;
    Carte carte;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        this.name = "Cavite";
        this.carte = new Carte ("Cavite");
    }


    @org.junit.jupiter.api.Test
    void equals() {
        Carte c = new Carte(name);
        Carte c1 = new Carte("Verrerie");

        System.out.println("Test d'egalité de deux cartes");

        this.carte.equals(c);

        this.carte.equals(c1);


        //assertEquals(c1, this.carte); // ce test ne va pas marcher, car les deux cartes ont deux noms différents
        assertEquals(c, this.carte);



    }
}