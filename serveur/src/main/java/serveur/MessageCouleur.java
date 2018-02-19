package serveur;

import data.Couleur;

/**
 * classe devenu obsolète, on envoie directement une Couleur.
 * @deprecated
 */
public class MessageCouleur {

    public int getRouge() {
        return rouge;
    }

    public void setRouge(int rouge) {
        this.rouge = rouge;
    }

    public int getBleu() {
        return bleu;
    }

    public void setBleu(int bleu) {
        this.bleu = bleu;
    }

    public int getVert() {
        return vert;
    }

    public void setVert(int vert) {
        this.vert = vert;
    }

    int rouge;
    int bleu;
    int vert;


    public MessageCouleur() {

    }


    public MessageCouleur(int rouge, int vert, int bleu) {
        setBleu(bleu);
        setRouge(rouge);
        setVert(vert);
    }

    public MessageCouleur(Couleur c) {
        setBleu(c.getBleu());
        setRouge(c.getRouge());
        setVert(c.getVert());
    }

    public Couleur getCouleur() {
        return new Couleur(rouge, vert, bleu);
    }

}
