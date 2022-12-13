
/**
 * Idir CHAMI et Axel ZAREB (LU2IN002 2022dec)
 * 
 * Gestion d'un fruit pourri
 *
 */

public class FruitPourri extends Fruit {
    private final int gainEnergie;

    public FruitPourri(String n, Terrain t) {
        super(n, t);
        super.sePlacer();
        gainEnergie = -1 * ((int) (Math.random() * 16));
    }

    // Constructeur par copie qui sert à transformer un fruit mûr en fuit pourri
    public FruitPourri(FruitMur fm, Terrain t) {
        // On rajoute une croix devant le nom pour différencier
        // les fruits murs des pourris sur le tableau
        super("X " + fm.getNom(), t);
        int x = fm.getX();
        int y = fm.getY();
        gainEnergie = -1 * ((int) (Math.random() * 16)); // perte d'énergie (négatif)
        t.videCase(x, y);
        fm.initialisePosition(); // on sort le fruit mur du tableau
        setPosition(x, y);
    }

    public int getGainEnergie() {
        return gainEnergie;
    }

    public String toString() {
        return super.toString() + " pourri qui fait perdre " + (-gainEnergie) + " d'energie";
    }
}
