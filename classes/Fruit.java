
/**
 * Idir CHAMI et Axel ZAREB (LU2IN002 2022dec)
 * 
 * Gestion d'un fruit
 *
 */

public abstract class Fruit extends Ressource implements Mangeable {
    protected String nom;
    protected Terrain t;

    public Fruit(String n, Terrain t) {
        super(n, 1);
        nom = n;
        this.t = t;
    }

    public void sePlacer() {
        // On génère des coordonnées tant que la case n'est pas vide
        int x = (int) (Math.random() * t.nbLignes);
        int y = (int) (Math.random() * t.nbColonnes);
        while (!t.caseEstVide(x, y)) {
            x = (int) (Math.random() * t.nbLignes);
            y = (int) (Math.random() * t.nbColonnes);
        }
        setPosition(x, y);
        t.setCase(x, y, this);
    }

    public String getNom() {
        return nom;
    }

    public abstract int getGainEnergie();

    public String toString() {
        return nom + " est un " + super.type;
    }
}