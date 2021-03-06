package joueur;

import config.CONFIG;
import config.MESSAGES;
import donnees.Carte;
import donnees.Main;
import donnees.Merveille;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;

public class Joueur {


    private String nom;
    Socket connexion ;
    private Merveille merveille;
    private Main main;

    /*protected ArrayList<Carte> cartesEnMain = new ArrayList<Carte>() {
    }; */

    /*private ArrayList<Carte> newCartesEnMain = new ArrayList<Carte>(){
    };*/


    public Joueur(String un_joueur) {


        setNom(un_joueur);

        System.out.println("------------------------------------ CREATION DES JOUEURS ---------------------------------");
        System.out.println(nom +" > creation");
        try {
            // préparation de la connexion
            connexion = IO.socket("http://" + CONFIG.IP + ":" + CONFIG.PORT);
            

            // abonnement à la connexion
            connexion.on("connect", new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    System.out.println(getNom() + " > connecte");
                    System.out.println(getNom()+" > envoi de mon nom");
                    connexion.emit(MESSAGES.MON_NOM, getNom());
                }
            });

            
            // réception de la merveille
            connexion.on(MESSAGES.ENVOI_DE_MERVEILLE, new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    // réception du JSON
                    JSONObject merveilleJSON = (JSONObject)objects[0];
                    try {
                        // conversion du JSON en Merveille
                        String n = merveilleJSON.getString("nom");
                        // les merveilles ont toutes une ressource vide, pour illustrer avec un objet avec plus qu'une seule propriété
                        String ressource = merveilleJSON.getString("ressource");
                        Merveille m = new Merveille(n);
                        m.setRessource(ressource);

                        System.out.println("-------------------------------------------------------------------------------------------");
                        System.out.println("------------------------------------RECEPTION DES MERVEILLES-------------------------------");
                        System.out.println("-------------------------------------------------------------------------------------------");
                        // mémorisation de la merveille
                        System.out.println(nom+" > j'ai recu "+m);
                        setMerveille(m);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            // réception de la main
            connexion.on(MESSAGES.ENVOI_DE_MAIN, new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    // réception de l'objet JSON : une main
                    JSONObject mainJSON = (JSONObject)objects[0];
                    try {
                        Main m = new Main();
                        // la main ne contient qu'une liste de carte, c'est un JSONArray
                        JSONArray cartesJSON = mainJSON.getJSONArray("cartes");
                        // on recrée chaque carte
                        for(int j = 0 ; j < cartesJSON.length(); j++) {
                            JSONObject carteJSON = (JSONObject) cartesJSON.get(j);
                            Carte c = new Carte(carteJSON.getString("name"));
                            m.ajouterCarte(c);
                        }

                        System.out.println("-------------------------------------------------------------------------------------------");
                        System.out.println("------------------------------------RECEPTION DE LA MAIN-----------------------------------");
                        System.out.println("-------------------------------------------------------------------------------------------");
                        System.out.println(nom+" > j'ai recu les Cartes "+m);

                        // le joueur a reçu, il joue
                        jouer(m);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            connexion.on(MESSAGES.CEST_VOTRE_TOUR, new Emitter.Listener() {
                @Override
                public void call(Object... objects) {
                    JSONObject mainJSON = (JSONObject)objects[0];
                    try {
                        Main m = new Main();
                        // la main ne contient qu'une liste de carte, c'est un JSONArray
                        JSONArray cartesJSON = mainJSON.getJSONArray("cartes");
                        // on recrée chaque carte
                        for(int j = 0 ; j < cartesJSON.length(); j++) {
                            JSONObject carteJSON = (JSONObject) cartesJSON.get(j);
                            Carte c = new Carte(carteJSON.getString("name"));
                            m.ajouterCarte(c);
                        }

                        System.out.println("-------------------------------------------------------------------------------------------");
                        System.out.println("------------------------------------TOUR_JOUEURS-----------------------------------");
                        System.out.println("-------------------------------------------------------------------------------------------");
                        System.out.println(nom+" > c'est mon tour "+m);
                        jouer(m);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (
        URISyntaxException e) {
            e.printStackTrace();
        }
    }


    private void jouer(Main m) {
        // ne fonctionne pas dans Android
        Random r = new Random();
        int indiceCarte= r.nextInt(m.getCartes().size());
        JSONObject carteAJouer = new JSONObject(m.getCartes().get(indiceCarte)) ;

        //int indiceCarte= r.nextInt(m.getCartes().size());
        //JSONObject carteAJouer = new JSONObject(m.getCartes().get(indiceCarte)) ;

        // dans Android, il faudrait faire :
        // JSONObject pieceJointe = new JSONObject();
        // pieceJointe.put("name", m.getCartes().get(0).getName());
        // et il faudrait faire cela entre try / catch

        // modifi

        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("------------------------------------"+(8 - m.getCartes().size())+" TOURS DE JEU------------------------------------------");
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println(nom + " > Joue  "+carteAJouer.toString());
        connexion.emit(MESSAGES.JE_JOUE, carteAJouer);
    }

    // methode pour defosser une carte

    /*public void defoseerCarte(Carte c){
        for (int i =0; i<newCartesEnMain.size();i++){

        }
    }
    */
    public void démarrer() {
        // connexion effective
        if (connexion != null) connexion.connect();
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

/*
    public static final void main(String  [] args) {
        Joueur j = new Joueur("toto");
        j.démarrer();
    }
*/

    public void setMerveille(Merveille merveille) {
        this.merveille = merveille;
    }

    public Merveille getMerveille() {
        return merveille;
    }
}
