package ch.iglwars.Exception;

/**
 * Exception levée lorsque le chargement des scores sur le serveur à échoué
 */
public class LoadScoreException extends Exception{

    public LoadScoreException(){
        super("Une erreur est survenue lors du chargement des scores.");
    }

}
