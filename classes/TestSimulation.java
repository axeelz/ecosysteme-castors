
/**
 * Idir CHAMI et Axel ZAREB (LU2IN002 2022dec)
 * 
 * Classe test
 *
 */

public class TestSimulation {
    public static void main(String[] args) {
        int nbLignesTerrain = 10;
        int nbColonnesTerrain = 10;
        Terrain t = new Terrain(nbLignesTerrain, nbColonnesTerrain);

        int nbAgents = 10;
        int nbRessources = 20;

        try {
            Simulation s = new Simulation(nbRessources, nbAgents, t);
            s.simuler(); // boucle principale de la simulation
        } catch (SurchargeTerrainException e) {
            System.out.println(e);
        }
    }

}
