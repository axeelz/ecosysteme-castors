
/**
 * Idir CHAMI et Axel ZAREB (LU2IN002 2022dec)
 * 
 * Gestion d'un Castor Enfant
 * Un castor enfant ne ramasse pas du tout de bois car il est trop jeune
 *
 */

public class CastorEnfant extends Castor {

    public CastorEnfant(Terrain t) {
        super(t);
    }

    public void recolterBois() {
        Ressource r = t.getCase(posx, posy);
        if (r instanceof Bois) {
            System.out.println(this + " est trop jeune pour ramasser du bois");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " enfant";
    }
}