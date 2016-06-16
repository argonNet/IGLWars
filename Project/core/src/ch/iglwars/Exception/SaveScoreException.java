package ch.iglwars.Exception;

/**
 * Exception levée lorsque la sauvegarde du score sur le serveur n'as pas réussi
 */
public class SaveScoreException extends Exception {

    public SaveScoreException(){
        super("Une erreur est survenue lors de la sauvegarde des scores.");
    }
}
