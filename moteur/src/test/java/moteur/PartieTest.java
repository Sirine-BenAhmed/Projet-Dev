package moteurTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import moteur.Participant;
import moteur.Partie;

public class PartieTest {
 Partie partie;
 ArrayList<Participant> participants;
 Participant p1, p2;

    @Before
    public void setUp() throws Exception {
        this.partie = new Partie();
        this.participants = new ArrayList<Participant>();


        this.p1 = new Participant("Kamel");
        this.p2 = new Participant("papi");

        this.participants.add(p1);
        this.participants.add(p2);


    }

    @Test
    void débuterLeJeu() {
        /*this.partie.setParticipant(participants);
        this.partie.débuterLeJeu();

        for(int i=0; i< this.partie.getParticipant().size(); i++){
            assertNotNull(this.partie.getParticipant().get(i).getMerveille());
        }
        */

    }

    @Test
    void tousIndentifiés(){
        /*this.partie.setParticipant(participants);

        boolean a = this.partie.tousIndentifiés();
        assertFalse(a);
    */
    }
}