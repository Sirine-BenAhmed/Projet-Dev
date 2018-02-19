package serveur;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import data.Couleur;

import java.util.concurrent.TimeUnit;


public class Serveur implements DataListener<Couleur> {


    private final SocketIOServer server;

    public final static void main(String[] args) {
        new Serveur();

        try {
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public Serveur() {

        Configuration config = new Configuration();
        config.setHostname("127.0.0.1");
        config.setPort(10101);

        // creation du serveur
        server = new SocketIOServer(config);
        server.start();

        server.addEventListener("envoiCouleur", Couleur.class, this);
    }

    @Override
    public void onData(SocketIOClient socketIOClient, Couleur t, AckRequest ackRequest) throws Exception {
        System.out.println("serveur > couleur recue : "+t.affichage());
    }

    public void stop() {
        server.stop();
    }
}
