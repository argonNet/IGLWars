package ch.iglwars.Level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import ch.iglwars.Player.Player;

/**
 * Classe de base pour la gestion des niveaux de jeu
 */
public abstract class Level {

    private List<Object> LevelContent;
    private int currentRunningElementIndex;
    private long lastDelayStartTime;

    /**
     * Permet d'ajouter une salve
     */
    protected void addSalve(Salve salve){
        LevelContent.add(salve);
    }

    /**
     * Permet d'ajouter un délai entre les salves
     */
    protected void addDelayBetweenSalve(Integer delay){
        LevelContent.add(delay);
    }

    /**
     * Constructeur, intègre la construction des éléments nécessaire à la gestion du niveau
     */
    public Level(){
        currentRunningElementIndex = 0;
        lastDelayStartTime = Long.MAX_VALUE;
        LevelContent = new ArrayList<Object>();
    }

    /**
     * Méthode de base pour lancer un niveau de jeu. Cette méthode est appelé à chaque passage
     * dans la boucle de rendu
     */
    public void Run(SpriteBatch batch){

        if(currentRunningElementIndex < LevelContent.size() - 1) {
            if (LevelContent.get(currentRunningElementIndex) instanceof Integer) {

                if (lastDelayStartTime == Long.MAX_VALUE) {
                    lastDelayStartTime = TimeUtils.millis();
                }

                //Le délai entre les salve est terminé alors on passe a la suite
                if (TimeUtils.millis() - lastDelayStartTime > ((Integer) LevelContent.get(currentRunningElementIndex)).intValue()) {
                    currentRunningElementIndex++;
                    lastDelayStartTime = Long.MAX_VALUE;
                }


            } else if (LevelContent.get(currentRunningElementIndex) instanceof Salve) {

                //Si la salve tourne encore alors on l'affiche sinon on passe a l'élément suivant
                if (((Salve) LevelContent.get(currentRunningElementIndex)).isGone()) {
                    currentRunningElementIndex++;
                }
            }
        }

        //Affichage de toutes les salves qui ont été lancée
        for (int i = 0; i <= currentRunningElementIndex;i++){ //TODO : Optimiser la boucle pour n'afficher que le salve disponible à l'ecran
            if(LevelContent.get(i) instanceof Salve){
                ((Salve)LevelContent.get(i)).Run(batch);
            }
        }

        Player.getInstance().draw(batch);
    }

}
