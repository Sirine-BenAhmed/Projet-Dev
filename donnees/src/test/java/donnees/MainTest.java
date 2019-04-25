package donnees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    Main main;
    private ArrayList<Carte> cartes ;
    Carte c1, c2, c3, c4, c5, c6, c7;

    @BeforeEach
    void setUp() {
        this.main = new Main();

        this.cartes = new ArrayList<Carte>();

        this.main.setCartes(cartes);

    }


    @Test
    void ajouterCarte() {
        this.main.ajouterCarte(c1);
        this.main.ajouterCarte(c2);
        this.main.ajouterCarte(c3);
        this.main.ajouterCarte(c4);
        this.main.ajouterCarte(c5);
        this.main.ajouterCarte(c6);
        this.main.ajouterCarte(c7);

        System.out.println("test de la taille du main de carte");
        assertEquals(7, this.main.getCartes().size());

        System.out.println("test si la main de carte n'est pas vide ");
        assertNotNull(this.main.getCartes());

    }

}