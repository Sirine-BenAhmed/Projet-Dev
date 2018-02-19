package client;

import data.Couleur;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.simple.JSONObject;

import java.net.URISyntaxException;

public class ControleurIO {

    Socket mSocket;

    public boolean isConnected() {
        return connected;
    }

    Object synchro = new Object();
    boolean connected = false;


    public void connect() {
        mSocket = null;
        try {
            mSocket = IO.socket("http://127.0.0.1:10101");
            // connexion
            mSocket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        mSocket.on("connect", new Emitter.Listener() {

            @Override
            public void call(Object... objects) {
                synchronized (synchro) {
                    connected = true;
                    mSocket.off("connect");
                    synchro.notify();

                }

            }
        });


        synchronized (synchro) {
            try {
                synchro.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void disconnect() {
        if (mSocket!=null) {
            mSocket.disconnect();
            mSocket.close();
        }
    }

    public void envoyerCouleur(Couleur c) {



        JSONObject obj = new JSONObject();
        obj.put("rouge", c.getRouge());
        obj.put("vert", c.getVert());
        obj.put("bleu", c.getBleu());
        System.out.println("client > j'envoie avec simplejson : "+obj.toJSONString()+" pour "+c.affichage());
        mSocket.emit("envoiCouleur", obj, new io.socket.client.Ack() {
            @Override
            public void call(Object... objects) {
                synchronized (synchro) {
                    synchro.notify();
                }
            }
        });

        synchronized (synchro) {
            try {
                synchro.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // avec une autre lib, celle par défaut dans socket.io
        org.json.JSONObject o = new org.json.JSONObject(c);
        System.out.println("client > avec org.json : "+o.toString());
        mSocket.emit("envoiCouleur", o, new io.socket.client.Ack() {
            @Override
            public void call(Object... objects) {
                synchronized (synchro) {
                    synchro.notify();
                }
            }
        });

        synchronized (synchro) {
            try {
                synchro.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}