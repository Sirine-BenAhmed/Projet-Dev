package data;

import org.junit.Test;

import static org.junit.Assert.*;

public class CouleurTest {

    /**
     * Test de toInt pour les valeurs aux bornes : noir(0,0,0) et le blanc(255,255,255)
     * @throws Exception
     */
    @Test
    public void toInt() throws Exception {
        Couleur c = new Couleur(0, 0, 0);
        int valeur = c.toInt();
        assertEquals("la couleur obtenu est 0xFF000000", 0xFF000000, valeur);

        c = new Couleur(255, 255, 255);
        valeur = c.toInt();
        assertEquals("la couleur obtenu est 0xFFFFFFFF", 0xFFFFFFFF, valeur);

    }

}