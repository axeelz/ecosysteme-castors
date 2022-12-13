
/**
 * Idir CHAMI et Axel ZAREB (LU2IN002 2022dec)
 * 
 * Gestion d'un Castor Prudent
 * Un castor prudent ne ramasse plus de bois lorsqu'il a 5 d'énergie ou moins.
 *
 */

public class CastorPrudent extends Castor {

    public CastorPrudent(Terrain t) {
        super(t);
    }

    public void recolterBois() {
        Ressource r = t.getCase(posx, posy);
        if (r instanceof Bois) {
            if (energie <= 5) {
                System.out.println(this + " ne se sent pas assez bien pour ramasser le bois");
            } else {
                nbBoisCastor += r.getQuantite();
                nbBoisTotalRecoltes += nbBoisCastor;
                t.videCase(posx, posy);
                r.initialisePosition();
                System.out.println(this + " a récolté du bois, il en a maintenant " + nbBoisCastor);
                energie--;
            }
        }

    }

    @Override
    public String toString() {
        return super.toString() + " prudent";
    }
}