
/**
 * Idir CHAMI et Axel ZAREB (LU2IN002 2022dec)
 * 
 * Gestion d'un castor
 *
 */

public abstract class Castor {
    // Terrain
    protected Terrain t;

    // Gestion des déplacements
    protected int posx;
    protected int posy;
    private int dirx;
    private int diry;

    // Attributs du castor
    public final int id;
    protected int energie;
    protected int nbBoisCastor;

    // Constantes
    public static final double PROBA_CHANGER_DIRECTION = 0.3;
    public static final int NB_BOIS_NECESSAIRE = 10; // nombre de bois pour construire un barrage

    // Tableau contenant les coordonnées des castors
    private static int[][] tabCastor;

    // Attributs communs à tous les castors
    protected static int nbBoisTotalRecoltes = 0;
    private static int nbCastors;

    public Castor(int energie, Terrain t) {
        this.energie = energie;
        this.t = t;
        if (nbCastors == 0) {
            tabCastor = new int[t.nbLignes][t.nbColonnes];
        } else {
            tabCastor = getTabCastor();
        }
        dirx = (int) (Math.random() * 3) - 1; // valeur entre -1 et 1
        diry = (int) (Math.random() * 3) - 1;
        initialisePosition();
        nbCastors++;
        id = nbCastors;
    }

    public Castor(Terrain t) {
        this((int) (Math.random() * (30 - 10) + 10), t);
    }

    public void initialisePosition() {
        // On place le fruit à une case non vide
        int x = (int) (Math.random() * t.nbLignes);
        int y = (int) (Math.random() * t.nbColonnes);
        while (!t.caseEstVide(x, y) && tabCastor[x][y] == 1) { // Si une ressource ou un agent est présent, on
                                                               // recommence
            x = (int) (Math.random() * t.nbLignes);
            y = (int) (Math.random() * t.nbColonnes);
        }
        tabCastor[x][y] = 1;
        posx = x;
        posy = y;
    }

    public void seDeplacer() {
        int newx, newy;
        newx = (posx + dirx + t.nbLignes) % t.nbLignes;
        newy = (posy + diry + t.nbColonnes) % t.nbColonnes;
        seDeplacer(newx, newy);
        double changeDir = Math.random(); // entre 0 et 1
        if (changeDir < PROBA_CHANGER_DIRECTION) {
            dirx = (int) (Math.random() * 3) - 1;
            diry = (int) (Math.random() * 3) - 1;
        }
        energie--;
    }

    private void seDeplacer(int xnew, int ynew) {
        if (tabCastor[xnew][ynew] != 1) {
            tabCastor[posx][posy] = 0;
            posx = xnew;
            posy = ynew;
            tabCastor[xnew][ynew] = 1;
        }
        // dans le cas ou il y'a déja un agent, le castor reste à sa place.
    }

    // Pas utilisé mais était dans le sujet du projet
    public double distance(int x, int y) {
        return Math.sqrt((x - posx) * (x - posx) + (y - posy) * (y - posy));
    }

    public abstract void recolterBois();

    public static int[][] getTabCastor() {
        return tabCastor;
    }

    public void mangerFruit() {
        if (!t.caseEstVide(posx, posy)) {
            Ressource r = t.getCase(posx, posy);
            if (r instanceof Mangeable) {
                energie += ((Mangeable) r).getGainEnergie();

                ((Ressource) r).initialisePosition();
                t.videCase(posx, posy);
            }
        }
    }

    public boolean estMort() {
        if (energie <= 0) {

            System.out.print(this + " est mort ! ");
            nbBoisTotalRecoltes -= nbBoisCastor;

            if (t.caseEstVide(posx, posy) && nbBoisCastor != 0) {
                t.setCase(posx, posy, new Bois(t, nbBoisCastor));
                System.out.print(" il a laissé derriere lui " + nbBoisCastor + " bois !");
            }
            System.out.print("\n");
            nbBoisCastor = 0;
            nbCastors--;
            tabCastor[posx][posy] = 0;
            return true;
        }
        return false;
    }

    public static int getNbBoisTotalRecoltes() {
        return nbBoisTotalRecoltes;
    }

    public int getEnergie() {
        return energie;
    }

    public int getNbBoisCastor() {
        return nbBoisCastor;
    }

    public static int getNbCastors() {
        return nbCastors;
    }

    public String toString() {
        return "Castor " + id;
    }
}
