
/**
 * Idir CHAMI et Axel ZAREB (LU2IN002 2022dec)
 * 
 * Gestion d'une simulation
 *
 */

import java.util.concurrent.TimeUnit;
// Le package qu'on utilise pour attendre entre les itérations

public class Simulation {
    // Terrain
    private Terrain t;

    // Tableaux contenant les agents et les ressources
    private Castor[] tabCastors;
    private Ressource[] tabRessources;

    // Constantes
    public static final int NB_ITERATIONS = 50;

    // Tableaux permettant de générer de manière aléatoire
    // les agents et les ressources, et de varier les types
    private String[] listeCastors = { "Bourrin", "Prudent", "Flemmard", "Enfant" };
    private String[] tabNoms = { "Banane", "Fraise", "Kiwi", "Pêche" };

    public Simulation(int nbRessources, int nbAgents, Terrain t) throws SurchargeTerrainException {
        if ((nbRessources + nbAgents) > t.nbLignes * t.nbColonnes) {
            throw new SurchargeTerrainException("Réduisez le nombre de ressources et/ou d'agents");
        }

        this.t = t;
        tabCastors = new Castor[nbAgents];

        tabRessources = new Ressource[nbRessources];
        for (int i = 0; i < nbAgents; i++) {
            int randomCastor = (int) (Math.random() * listeCastors.length);
            String type = listeCastors[randomCastor];
            switch (type) {
                case "Bourrin":
                    tabCastors[i] = new CastorBourrin(t);
                    break;
                case "Prudent":
                    tabCastors[i] = new CastorPrudent(t);
                    break;
                case "Flemmard":
                    tabCastors[i] = new CastorFlemmard(t);
                    break;
                case "Enfant":
                    tabCastors[i] = new CastorEnfant(t);
                    break;
                default:
                    tabCastors[i] = new CastorBourrin(t);
                    break;
            }
        }

        for (int i = 0; i < nbRessources; i++) {
            double rand = Math.random();
            if (i == nbRessources - 1) {
                int nbbois = getNbBoisTerrain();
                if (nbbois < Castor.NB_BOIS_NECESSAIRE) {
                    tabRessources[i] = new Bois(t, (Castor.NB_BOIS_NECESSAIRE) - nbbois);
                    return;
                }
            }
            if (rand <= 0.5) {
                int randomNom = (int) (Math.random() * tabNoms.length);
                tabRessources[i] = new FruitMur(tabNoms[randomNom], t);
                ((FruitMur) tabRessources[i]).sePlacer();
            } else {
                tabRessources[i] = new Bois(t);
                ((Bois) tabRessources[i]).sePlacer();
            }
        }

    }

    private int getNbBoisTerrain() {
        int cpt = 0;
        for (int i = 0; i < tabRessources.length; i++) {
            if (tabRessources[i] instanceof Bois) {
                cpt += tabRessources[i].getQuantite();
            }
        }
        return cpt;
    }

    // Boucle principale de la simulation
    public void simuler() {
        int nbItRestant = NB_ITERATIONS;
        for (int i = 0; i < NB_ITERATIONS; i++) {
            t.affiche(5);
            System.out.println("\nItérations restantes : " + (nbItRestant - 1));
            System.out.println("Nombre de castors : " + Castor.getNbCastors());
            System.out.println("Nombre total de bois récoltés : " + Castor.getNbBoisTotalRecoltes() + " / "
                    + Castor.NB_BOIS_NECESSAIRE);
            for (int j = 0; j < tabRessources.length; j++) {
                if (tabRessources[j] instanceof FruitMur) {
                    FruitMur f = (FruitMur) tabRessources[j];
                    FruitPourri fp = f.pourrirFruit();
                    if (fp != null) {
                        tabRessources[j] = fp;
                    }
                }
            }

            for (int j = 0; j < tabCastors.length; j++) {
                if (tabCastors[j] != null) {
                    tabCastors[j].seDeplacer();
                    tabCastors[j].mangerFruit();
                    tabCastors[j].recolterBois();
                    if (tabCastors[j].estMort())
                        tabCastors[j] = null;
                }
            }

            if (Castor.nbBoisTotalRecoltes >= Castor.NB_BOIS_NECESSAIRE) {
                System.out.println(
                        "Les castors ont récolté le nombre de bois nécéssaire à la création du barrage ! Victoire ! ");
                return;
            }

            if (Castor.getNbCastors() <= 0) {
                System.out.println("Tous les castors sont morts....");
                return;
            }

            // On attend deux secondes

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                System.out.println("Erreur lors de la commande sleep : " + e);
            }
            nbItRestant--;
        }

    }
}
