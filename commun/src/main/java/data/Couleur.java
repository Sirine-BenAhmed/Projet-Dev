package data;

/**
 * Représentation des couleurs au format RGB : rouge vert bleu
 */
public class Couleur {

    /***
     * accesseur à la valeur du canal rouge
     * @return la valeur du canal rouge, entre 0 et 255
     */
    public int getRouge() {
        return rouge;
    }

    public void setRouge(int rouge) {
        this.rouge = rouge;
    }

    public int getVert() {
        return vert;
    }

    public void setVert(int vert) {
        this.vert = vert;
    }

    public int getBleu() {
        return bleu;
    }

    public void setBleu(int bleu) {
        this.bleu = bleu;
    }

    private int rouge = 0;
    private int vert = 0;
    private int bleu = 0;

    final static int MAX = 255;

    public Couleur()  {}


    public Couleur(int r, int v, int b) {
        this.rouge = r;
        this.vert = v;
        this.bleu = b;
    }

    public void inverser() {
        rouge = MAX-rouge;
        vert = MAX-vert;
        bleu = MAX-bleu;
    }


    /**
     * transforme la couleur en entier de 4 octets : 2e octet c'est le rouge, 3e c'est le vert, 4e octet c'est le bleu
     * @return un entier par décalage et masque des différents canaux de couleurs
     */
    public int toInt(){
        int r = (rouge << 16) & 0x00FF0000;
        int v = (vert << 8) & 0x0000FF00;
        int b = bleu & 0x000000FF;

        return 0xFF000000 | r | v | b; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
    }


    @Override
    public boolean equals(Object o) {
        boolean result= false;
        if (o instanceof Couleur) {
            Couleur c = (Couleur) o;
            result = (c.getRouge() == getRouge()) && (c.getBleu() == getBleu()) && (c.getVert() == getVert());
        }

        return result;
    }
    public String affichage() {
        return "couleur [rouge = "+rouge+" / vert = "+vert+ " bleu = "+bleu+"]";
    }
}
