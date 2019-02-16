package org.Serveur;


import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;


import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * attend une connexion, on envoie une question puis on attend une réponse, jusqu'à la découverte de la bonne réponse
 * le client s'identifie (som, niveau)
 */
public class Serveur {

  SocketIOServer serveur;


  public Serveur(Configuration config) {
    // creation du serveur
    serveur = new SocketIOServer(config);

    System.out.println("[SERVEUR] Création des écouteurs d'évenements");

    // on accept une connexion
    serveur.addConnectListener(new ConnectListener() {
      public void onConnect(SocketIOClient client) {
        System.out.println("[SERVEUR] Un nouveau client s'est conencté. Bienvenu " + client.getSessionId());
      }
    });

  }

  public static final void main(String[] args) {
    try {
      System.setOut(new PrintStream(System.out, true, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    Configuration config = new Configuration();
    config.setHostname("127.0.0.1");
    config.setPort(8080);


    Serveur serveur = new Serveur(config);
    serveur.run();

  }

  private void run() {

    serveur.start();
    System.out.println("[SERVEUR] Serveur démarré, en attente des clients pour se connecter.");
  }


}