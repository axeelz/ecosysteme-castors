
/**
 * Idir CHAMI et Axel ZAREB (LU2IN002 2022dec)
 * 
 * Gestion d'un Castor Flemmard
 * Un castor flemmard a PROBA_RAMASSER_BOIS * 100 % de chances de ramasser du
 * bois, car sinon il a la flemme.
 *
 */

public class CastorFlemmard extends Castor {
    public static final double PROBA_RAMASSER_BOIS = 0.3;

    public CastorFlemmard(Terrain t) {
        super(t);
    }

    public void recolterBois() {
        Ressource r = t.getCase(posx, posy);
        double rand = Math.random();
        if (r instanceof Bois) {
            if (rand < PROBA_RAMASSER_BOIS) {
                nbBoisCastor += r.getQuantite();
                nbBoisTotalRecoltes += nbBoisCastor;
                t.videCase(posx, posy);
                r.initialisePosition();
                System.out.println(this + " a récolté du bois, il en à maintenant " + nbBoisCastor);
                energie--;
            } else {
                System.out.println(this + " a la flemme de ramasser du bois");
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + " flemmard";
    }

}