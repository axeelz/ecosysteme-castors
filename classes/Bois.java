
/**
 * Idir CHAMI et Axel ZAREB (LU2IN002 2022dec)
 * 
 * Gestion d'un bois
 *
 */

public class Bois extends Ressource {
    protected Terrain t;

    // On crée 2 constructeurs dont un qui prend un int aléatoire qui sera
    // la quantité de bois sur la case. Il permet d'afficher la quantité de bois
    // dans le nom.

    public Bois(Terrain t, int quantiteGeneree) {
        super(quantiteGeneree + " Bois", quantiteGeneree);
        this.t = t;
    }

    public Bois(Terrain t) {
        this(t, (int) (Math.random() * (3 - 1) + 1));
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

    public String toString() {
        return "Je suis un bois";
    }
}
