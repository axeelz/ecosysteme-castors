
/**
 * Idir CHAMI et Axel ZAREB (LU2IN002 2022dec)
 * 
 * Gestion d'un fruit mur, qui pourrit au bout de tempsPeremption itérations
 *
 */

public class FruitMur extends Fruit {
    private final int gainEnergie;
    private int tempsPeremption;
    private int maxTemps; // Un Fruit mûr peut prendre jusqu'a maxTemps itérations pour pourrir

    public FruitMur(String n, Terrain t, int max) {
        super(n, t);
        maxTemps = max;
        tempsPeremption = (int) (Math.random() * (maxTemps - 1) + 1);
        gainEnergie = (int) (Math.random() * 16);
    }

    public FruitMur(String n, Terrain t) {
        this(n, t, 15); // on a défini le temps max de péremption à 15 (mais c'est random)
    }

    public FruitPourri pourrirFruit() {
        // On décrémente le temps restant avant la péremption du fruit
        // Si il arrive à 0, la fonction renvoie un FruitPourri qu'on place
        // là ou était le FruitMur
        tempsPeremption--;
        if (tempsPeremption <= 0) {
            int x = getX();
            int y = getY();
            System.out.println("Un des fruits a pourri");
            FruitPourri fp = new FruitPourri(this, t);
            t.setCase(x, y, fp);
            return fp;
        }
        return null;
    }

    public int getGainEnergie() {
        return gainEnergie;
    }

    public String toString() {
        return super.toString() + " mûr qui rapporte " + gainEnergie + " d'energie";
    }
}
