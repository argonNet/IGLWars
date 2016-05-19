package ch.iglwars.Enemy;

import com.badlogic.gdx.Gdx;

/**
 * Created by Argon on 14.05.16.
 */
public class SmallEnemy extends Enemy{

    public static float WIDTH = 48;

    /**
     * Construction de l'objet de base
     *
     * @param x Position initiale de l'enemi sur l'axe des X
     */
    public SmallEnemy(float x) {
        super(x);
    }

    /**
     * Définition des propriétés pour les petits enemis
     */
    protected void setProperties(){
        setTexture("enemy_small.png");
        setHeight(48);
    }

    /**
     * Gestion du déplacement de l'enemy de base
     */
    protected void setEnemyPositionInLoop(){
        this.setY(this.getY() - (200 * Gdx.graphics.getDeltaTime()));
    }


}
