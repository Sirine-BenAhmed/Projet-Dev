package client;

import data.Couleur;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientTest {
    Client c ;

    @Mock
    ControleurIO io;

    @Before
    public void setUp() throws Exception {
        c = new Client(io);
        when(io.isConnected()).thenReturn(true, false, true);
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * scénario de test : on crée un client, on change la couleur
     * on verifie que la méthode envoieCouleur est appelée si on est connecté
     * attention à bien redéfinir la méthode équals
     */
    @Test
    public void scenario1() {
        Couleur couleur = new Couleur(0,0,0);
        Couleur oracle = new Couleur(0,0,0);
        c.setCouleur(couleur);
        verify(io,times(1)).envoyerCouleur(oracle);

        c.setCouleur(couleur);
        verify(io,times(1)).envoyerCouleur(oracle);

        c.setCouleur(couleur);
        verify(io,times(2)).envoyerCouleur(oracle);
    }

}