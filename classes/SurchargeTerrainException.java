/**
 * Idir CHAMI et Axel ZAREB (LU2IN002 2022dec)
 * 
 * Gestion d'une exception lorsque le terrain est surchargé
 *
 */

public class SurchargeTerrainException extends Exception {
    public SurchargeTerrainException(String message) {
        super(message);
    }
}
