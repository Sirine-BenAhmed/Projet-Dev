package moteur;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import config.CONFIG;
import config.MESSAGES;
import donnees.Carte;
import donnees.DonneeCarte;
import donnees.Main;
import donnees.Merveille;

import java.util.ArrayList;

public class Partie {
    SocketIOServer serveur;
    private ArrayList<Participant> participants;


    public Partie() {

        // création du serveur (peut-être externalisée)
        Configuration config = new Configuration();
        config.setHostname(CONFIG.IP);
        config.setPort(CONFIG.PORT);
        serveur = new SocketIOServer(config);

        // init de la liste des participants
        participants = new ArrayList<>();

        // abonnement aux connexions

        serveur.addConnectListener(new ConnectListener() {

            @Override
            public void onConnect(SocketIOClient socketIOClient) {


                System.out.println("serveur > connexion de "+socketIOClient.getRemoteAddress());
                System.out.println("serveur > connexion de "+socketIOClient);

                // mémorisation du participant
                // ajout d'une limitation sur le nombre de joueur
                if (participants.size() < CONFIG.NB_JOUEURS) {
                    Participant p = new Participant(socketIOClient);
                    participants.add(p);
                }
            }
        });



        // réception de l'identification du joueur
        serveur.addEventListener(MESSAGES.MON_NOM, String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String s, AckRequest ackRequest) throws Exception {
                Participant p = retrouveParticipant(socketIOClient);
                if (p != null) {
                    p.setNom(s);
                    System.out.println("serveur > identification de "+p.getNom()+" ("+socketIOClient.getRemoteAddress()+")");

                    if (tousIndentifiés()) {
                        débuterLeJeu();
                    }
                }
            }
        });


        // réception de la carte jouée
        serveur.addEventListener(MESSAGES.JE_JOUE, Carte.class, new DataListener<Carte>() {
            @Override
            public void onData(SocketIOClient socketIOClient, Carte carte, AckRequest ackRequest) throws Exception {
                // retrouver le participant
                Participant p = retrouveParticipant(socketIOClient);
                
                if (p != null) {
                    System.out.println("serveur > "+p+" a joue "+carte);
                    // puis lui supprimer de sa main la carte jouée
                    p.getMain().getCartes().remove(carte);
                    System.out.println("serveur > il reste a "+p+" les cartes "+p.getMain().getCartes());
<<<<<<< HEAD
                    p.setAjoue(true);
                    if (tousIndentifiés()){
                    }
=======
                    int participantsIndex =(participants.indexOf(p) + 1) % participants.size();
                    Participant voisin = participants.get(participantsIndex);
                    voisin.setMain(p.getMain());
                    System.out.println("serveur > Je suis "+voisin+" j'ai recuperé la main de "+p+" qui contient les cartes"+p.getMain().getCartes());
                    
                    

>>>>>>> a67e209a77bea17cbdd536072ea2b418ec600e6d
                    // etc.
                }
            }
        });
    }

    private void débuterLeJeu() {
        // création des merveilles, au début de simples noms
        Merveille[] merveilles = new Merveille[CONFIG.NB_JOUEURS];
        
        merveilles[0] = new Merveille("Babylon");//(name, side)
		

		merveilles[1] = new Merveille("Rhodes");
		

		merveilles[2] = new Merveille("Halicarnassus");
		

		merveilles[3] = new Merveille("Giza");

//		merveilles[4] = new Merveille("Alexandria");
//
//
//		merveilles[5] = new Merveille("Olympia");
//
//		
//		merveilles[6] = new Merveille("Ephesus");


        DonneeCarte donneeCarte = new DonneeCarte();
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("------------------------------------ DEBUT DU JEU------------------------------------------");
        System.out.println("-------------------------------------------------------------------------------------------");

        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("------------------------------------ENVOIE DES MERVEILLES---------------------------------");
        System.out.println("-------------------------------------------------------------------------------------------");
        for(int i = 0; i < CONFIG.NB_JOUEURS; i++) {
        	
//           merveilles[i] = new Merveille("merveille"+i);
        	
            // association joueur - merveille
            participants.get(i).setMerveille(merveilles[i]);
            System.out.println("serveur > envoie a "+participants.get(i)+" sa merveille "+merveilles[i]);

            // envoi de la merveille au joueur
            participants.get(i).getSocket().sendEvent(MESSAGES.ENVOI_DE_MERVEILLE, merveilles[i]);
        }

        for(int i = 0; i < CONFIG.NB_JOUEURS; i++) {
            Main main = new Main();
            for(int j=0; j<7; j++){
                int aleatoire = (int)(Math.random()*donneeCarte.getMesCartes().size());
                main.ajouterCarte(donneeCarte.getMesCartes().get(aleatoire));
                donneeCarte.getMesCartes().remove(aleatoire);
            }
            participants.get(i).setMain(main);
            participants.get(i).getSocket().sendEvent(MESSAGES.ENVOI_DE_MAIN, main);
        }
        /*
        // création des cartes initiales
        Main[] mains = new Main[CONFIG.NB_JOUEURS];
        
        for(int i = 0; i < CONFIG.NB_JOUEURS; i++) {
            mains[i] = new Main();
         //   for(int j = 0 ; j < 8; j++) {
         //       mains[i].ajouterCarte(mains[i].getCartes().get(j));
         //       
         //   }
            // association main initiale - joueur
            participants.get(i).setMain(mains[i]);
            // envoi de la main au joueur
            participants.get(i).getSocket().sendEvent(MESSAGES.ENVOI_DE_MAIN, mains[i]);

        }
        */

    }

    private boolean tousIndentifiés() {
        boolean resultat = true;
        for(Participant p : participants) {
            // pas nom, pas identifié
            if (p.getNom() == null) {
                resultat = false;
                break;
            }
        }

        return resultat;
    }


    public void démarrer() {
        // démarrage du serveur
        serveur.start();
    }


    /**
     * méthode pour retrouver un participant à partir de la socket cliente (disponible à la réception d'un message)
     * @param socketIOClient le client qui vient d'envoyer un message au serveur
     * @return le Participant correspondant à la socketIOClient
     */
    private Participant retrouveParticipant(SocketIOClient socketIOClient) {
        Participant p = null;

        for(Participant part : participants) {
            if (part.getSocket().equals(socketIOClient)) {
                p = part;
                break;
            }
        }
        return p;
    }



    public static final void main(String  [] args) {
        Partie p = new Partie();
        p.démarrer();
    }
}
