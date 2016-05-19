package ch.iglwars.Enemy;

import com.badlogic.gdx.Gdx;

import ch.iglwars.Constants;

/**
 * Classe pour les enemis de taille moyennes
 */
public class MediumEnemy extends Enemy {

    public static float WIDTH = 94;

    public static String [] TEXTURES_NAME = {
            "enemy_medium_0.png",
            "enemy_medium_1.png",
            "enemy_medium_2.png",
            "enemy_medium_3.png",
            "enemy_medium_4.png",
            "enemy_medium_5.png",
            "enemy_medium_6.png"};

    private float direction;

    /**
     * Construction de l'objet de base
     *
     * @param x Position initiale de l'enemi sur l'axe des X
     */
    public MediumEnemy(float x) {
        super(x);
    }

    /**
     * Définition des propriétés pour l'enemy
     */
    protected void setProperties(){
        direction = 1;
        setAnimatedTexture(100,TEXTURES_NAME);
        setHeight(95);
    }

    /**
     * Gestion du déplacement de l'enemy
     */
    protected void setEnemyPositionInLoop(){
        this.setY(this.getY() - (200 * Gdx.graphics.getDeltaTime()));

        //Gestion du ZigZag
        if(this.getX() > (Constants.GAME_WIDTH - WIDTH)){
            direction = -1;
        }else if (this.getX() < 0) {
            direction = 1;
        }

        this.setX(this.getX() + (direction * 200 * Gdx.graphics.getDeltaTime()));

        //Rotation sur lui-même
        this.incRotation(5);
    }

}
