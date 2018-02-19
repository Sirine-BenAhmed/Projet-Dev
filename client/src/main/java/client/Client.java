package client;

import data.Couleur;

import java.util.concurrent.TimeUnit;

public class Client {



    public final static void main(String[] args) {
        ControleurIO io = new ControleurIO();
        Client c = new Client(io);
        io.connect();

        java.util.Random generateur = new java.util.Random();

        Couleur couleur = new Couleur(generateur.nextInt(256),generateur.nextInt(256),generateur.nextInt(256));
        c.setCouleur(couleur);
        c.quitter();
        System.out.println("client > termine "+couleur);


        if ((args != null) && (args.length >= 1)) {
            // un thread de connexion http ne se ferme pas...
            // cela fait une déconnexion brutale...

            try {
                TimeUnit.SECONDS.sleep(2); // cela passe avec 2... mais c'est purement au pif...
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
    }


    private Couleur c = new Couleur(96, 128, 0);
    private ControleurIO controleur;

    public Client(ControleurIO io) {
        this.controleur = io;
    }


    public void setCouleur(Couleur couleur) {
        this.c = couleur;
        if (controleur.isConnected()) controleur.envoyerCouleur(this.c);
    }

    private void quitter() {
        controleur.disconnect();
    }

}