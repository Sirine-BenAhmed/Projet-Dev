package lanceur;

import client.Client;
import serveur.Serveur;

public class Lanceur {

    Serveur serveur;

    public Lanceur(int nbClient) {
        Thread leServeur = new Thread(new Runnable() {
            @Override
            public void run() {
                serveur = new Serveur();
            }
        });
        leServeur.start();

        for(int i = 0; i < nbClient; i++) {
            // lancement séquentiel des clients
            System.out.println("[lanceur] lancement de Client n° "+i+" sur "+nbClient);

            try {
                Client.main(null); // ou ré-écrire ici du code de lancement d'un client
            } finally {
                System.out.println("[lanceur] Fait : Client n° "+i);
            }
        }

        // fin
        try  {
            serveur.stop();
        } finally {
            System.out.println("serveur terminé");

            // il reste un thread non terminé dans socketIO...
            /* WARNING] thread Thread[OkHttp ConnectionPool,5,lanceur.Lanceur] was interrupted but is still alive after waiting at least 15000msecs
            [WARNING] thread Thread[OkHttp ConnectionPool,5,lanceur.Lanceur] will linger despite being asked to die via interruption
            [WARNING] NOTE: 1 thread(s) did not finish despite being asked to  via interruption. This is not a problem with exec:java, it is a problem with the running code. Although not serious, it should be remedied.
            [WARNING] Couldn't destroy threadgroup org.codehaus.mojo.exec.ExecJavaMojo$IsolatedThreadGroup[name=lanceur.Lanceur,maxpri=10]
            java.lang.IllegalThreadStateException */
            // on sort l'artillerie lourde
            System.exit(0);
        }
    }


    public static void main(String[] args) {
        int nbClient = 1;
        if (args.length >= 1) {
           nbClient = Integer.parseInt(args[0]);
           if (nbClient <= 0) nbClient = 1;
        }
        new Lanceur(nbClient);


    }
}
