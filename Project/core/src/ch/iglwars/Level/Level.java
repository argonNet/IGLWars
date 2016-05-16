package ch.iglwars.Level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Classe de base pour la gestion des niveaux de jeu
 */
public abstract class Level {

    /**
     * Méthode de base pour lancer un niveau de jeu. Cette méthode est appelé à chaque passage
     * dans la boucle de rendu
     */
    public abstract void Run(SpriteBatch batch);
}
