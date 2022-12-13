
/**
 * Idir CHAMI et Axel ZAREB (LU2IN002 2022dec)
 * 
 * Gestion d'un Castor Bourrin
 * Un castor bourrin ramasse tous les bois sur le terrain
 *
 */

public class CastorBourrin extends Castor {

    public CastorBourrin(Terrain t) {
        super(t);
    }

    public void recolterBois() {
        Ressource r = t.getCase(posx, posy);
        if (r instanceof Bois) {
            nbBoisCastor += r.getQuantite();
            nbBoisTotalRecoltes += nbBoisCastor;
            t.videCase(posx, posy);
            r.initialisePosition();
            System.out.println(this + " a récolté du bois, il en a maintenant " + nbBoisCastor);
            energie--;
        }
    }

    public String toString() {
        return super.toString() + " bourrin";
    }
}
