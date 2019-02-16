package org.Client;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Client {

  // Objet de synchro
  final Object attenteDéconnexion = new Object();
  Socket connexion;

  public Client(String urlServeur) {

    try {
      connexion = IO.socket(urlServeur);

      System.out.println("[CLIENT " + connexion.id() + "] Je prépare mes écouteurs");

      connexion.on("connect", new Emitter.Listener() {
        @Override
        public void call(Object... objects) {
          System.out.println("[CLIENT " + connexion.id() + "] Youpi ! Je suis connecté au serveur");

        }
      });

      connexion.on("disconnect", new Emitter.Listener() {
        @Override
        public void call(Object... objects) {
          System.out.println("[CLIENT " + connexion.id() + "] Je me suis fait déconnecté");
          connexion.disconnect();
          connexion.close();

          synchronized (attenteDéconnexion) {
            attenteDéconnexion.notify();
          }
        }
      });




    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

  }


  public Client() {


  }

  public static final void main(String[] args) {
    try {
      System.setOut(new PrintStream(System.out, true, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    Client client = new Client("http://127.0.0.1:8080");
    client.seConnecter();

  }

  private void seConnecter() {
    // on se connecte
    connexion.connect();

    System.out.println("[CLIENT " + connexion.id() + "] En attente de déconnexion");
    synchronized (attenteDéconnexion) {
      try {
        attenteDéconnexion.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
        System.err.println("[CLIENT " + connexion.id() + "] Erreur dans l'attente");
      }
    }
  }


}
