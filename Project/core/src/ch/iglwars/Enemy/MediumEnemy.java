package ch.iglwars.Enemy;

import com.badlogic.gdx.Gdx;

import ch.iglwars.Constants;

/**
 * Classe pour les enemis de taille moyennes
 */
public class MediumEnemy extends Enemy {

    public static float WIDTH = 48;
    public static float HEIGHT = 80;

    public static String [] TEXTURES_NAME = {
            "enemy_medium_0.png",
            "enemy_medium_1.png",
            "enemy_medium_2.png",
            "enemy_medium_3.png"};

    private float direction;

    /**
     * Construction de l'objet de base
     *
     * @param x Position initiale de l'enemi sur l'axe des X
     */
    public MediumEnemy(float x, float y) {
        super(x, y);
    }

    /**
     * Définition des propriétés pour l'enemy
     */
    protected void setProperties(){
        direction = 1;
        setAnimatedTexture(200,TEXTURES_NAME);
        setHeight(HEIGHT);
    }

    /**
     * Gestion du déplacement de l'enemy
     */
    protected void setEnemyPositionInLoop(){
        // Zigtag descendant sur toute la longueur
        this.setY(this.getY() - (40 * Gdx.graphics.getDeltaTime()));

        //Gestion du ZigZag
        if(this.getX() > (Constants.GAME_WIDTH - WIDTH)){
            direction = -1;
        }else if (this.getX() < WIDTH) {
            direction = 1;
        }

        this.setX(this.getX() + (direction * 200 * Gdx.graphics.getDeltaTime()));

        /*// Deplacement lateral avec descente à chaque bord
        if(this.getX() > (Constants.GAME_WIDTH - WIDTH)){
            direction = -1;
            this.setY(this.getY() - HEIGHT); // TODO : a améliorer pour éviter le déplacement instantané
        } else if (this.getX() < 0) {
            direction = 1;
            this.setY(this.getY() - HEIGHT);
        }
        this.setX(this.getX() + (direction * 200 * Gdx.graphics.getDeltaTime()));*/


    }

}
