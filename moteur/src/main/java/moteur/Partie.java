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


    public void setParticipant(ArrayList<Participant> p) {
    	this.participants = p;
    }
    public ArrayList<Participant> getParticipant(){return this.participants;}
    
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
                        lancerNouveauTour();
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
                    System.out.println("serveur > "+p+" a joue la carte "+carte);
                    // puis lui supprimer de sa main la carte jouée
                    p.getMain().getCartes().remove(carte);
                    System.out.println("serveur > il reste a "+p+" les cartes "+p.getMain().getCartes());

                    p.setAjoue(true);
                    // on change la mains si tous joueurs ont joués
                    if (tousJoues()){
                        changerMains();
                        prepareNouveauTours();
                        if(p.getMain().getCartes().size()>1){
                            lancerNouveauTour();
                        }
                    }
                    // etc.
                }
            }
        });
    }

    protected void débuterLeJeu() {
        // création des merveilles, au début de simples noms
    	
    
        Merveille[] merveilles = new Merveille[CONFIG.NB_JOUEURS];
        merveilles[0] = new Merveille("Babylon");//(name, side)
		merveilles[1] = new Merveille("Rhodes");
		merveilles[2] = new Merveille("Halicarnassus");
		merveilles[3] = new Merveille("Giza");
		/*
		merveilles[4] = new Merveille("Alexandria");
		merveilles[5] = new Merveille("Olympia");
		merveilles[6] = new Merveille("Ephesus");
        */
        // instanciation des données de cartes
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

        if(tousRecusMerveille()){
            // random pour tiré des cartes aléatoire pour 7 cartes par joueurs  ====> issue N°#47
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
        }


    }

    public boolean tousIndentifiés() {
        boolean resultat = true;
        if(participants.size()!=CONFIG.NB_JOUEURS){
            return false;
        }
        for(Participant p : participants) {
            // pas nom, pas identifié
            if (p.getNom() == null) {
                resultat = false;
                break;
            }
        }

        return resultat;
    }
    // permet de verifier si tous les joueurs ont joués
    public boolean tousJoues() {
        boolean resultat = true;
        for(Participant p : participants) {
            // pas joués, sort de la boucle
            if (!p.isAjoue()) {
                resultat = false;
                break;
            }
        }

        return resultat;
    }
    // permet de verifier si tous les joueurs ont récuperés leurs mains
    protected boolean tousRecusMerveille() {
        boolean resultat = true;
        for(Participant p : participants) {
            // pas reçu de merveille, sort de la boucle
            if (p.getMerveille() == null) {
                resultat = false;
                break;
            }
        }

        return resultat;
    }
    // verifier si tous ont récu leurs mains
    protected boolean tousRecusMain() {
        boolean resultat = true;
        for(Participant p : participants) {
            // pas reçu de main, sort de la boucle
            if (p.getMain() == null) {
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

    // passage de carte avec une carte en moins ===> issue N° 49

    protected void changerMains(){

        for (int i=0 ; i<participants.size(); i++){
            participants.get(i).setMainPrecedente(participants.get(i).getMain());
        }

        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("------------------------------------SERVEUR > LES MAINS ONT ETE CHANGEES---------------------------------");
        System.out.println("-------------------------------------------------------------------------------------------");



        for (int i=0 ; i<participants.size(); i++){
            if((i+1)==participants.size()){
                participants.get(i).setMain(participants.get(0).getMainPrecedente());
                System.out.println("serveur > Je suis "+participants.get(i).getNom()+" j'ai recuperé la main de "+participants.get(0).getNom()+" qui contient les cartes"+participants.get(0).getMainPrecedente().getCartes());
            }
            else{
                participants.get(i).setMain(participants.get(i+1).getMainPrecedente());
                System.out.println("serveur > Je suis "+participants.get(i).getNom()+" j'ai recuperé la main de "+participants.get(i+1).getNom()+" qui contient les cartes"+participants.get(i+1).getMainPrecedente().getCartes());
            }
        }
    }

    // pour verifier avant le nouveau tour de jeu
    protected void prepareNouveauTours(){
        for (Participant p : participants){
            p.setAjoue(false);
        }
    }

    // permet de jouer un nouveau tour

    protected void lancerNouveauTour(){
        for (Participant p:participants){
            p.getSocket().sendEvent(MESSAGES.CEST_VOTRE_TOUR, p.getMain());
        }
    }


    /**
     * méthode pour retrouver un participant à partir de la socket cliente (disponible à la réception d'un message)
     * @param socketIOClient le client qui vient d'envoyer un message au serveur
     * @return le Participant correspondant à la socketIOClient
     */
    protected Participant retrouveParticipant(SocketIOClient socketIOClient) {
        Participant p = null;

        for(Participant part : participants) {
            if (part.getSocket().equals(socketIOClient)) {
                p = part;
                break;
            }
        }
        return p;
    }


/*
    public static final void main(String  [] args) {
        Partie p = new Partie();
        p.démarrer();
    }
    */
}
