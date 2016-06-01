package ch.iglwars.Enemy;

import com.badlogic.gdx.Gdx;

import ch.iglwars.Utils.Constants;
import ch.iglwars.TexturesMode.PassiveAnimatedTexture;
import ch.iglwars.TexturesMode.TextureMode;

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
     * Constructeur sans paramètre (pour la SalveFactory)
     */
    public MediumEnemy(){
        super();
    }

    /**
     * Construction de l'objet de base
     *
     * @param x Position initiale de l'enemi sur l'axe des X
     * @param y Position initiale de l'enemi sur l'axe des Y
     */
    public MediumEnemy(float x, float y) {
        super(x, y);
    }

    /**
     * Définition des propriétés pour l'enemy
     */
    protected void setProperties(){
        direction = DROITE;
        setHeight(HEIGHT);
    }

    @Override
    protected TextureMode createTextureMode() {
        return new PassiveAnimatedTexture(100,TEXTURES_NAME);
    }

    /**
     * Gestion du déplacement de l'enemy
     */
    protected void setPositionInLoop(){

        // Zigtag descendant sur toute la longueur
        this.setY(this.getY() - (40 * Gdx.graphics.getDeltaTime()));

        //Gestion du ZigZag
        if(this.getX() > (Constants.GAME_WIDTH - WIDTH)){
            direction = GAUCHE;
        }else if (this.getX() < WIDTH) {
            direction = DROITE;
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

    @Override
    public float getWidth() {
        return WIDTH;
    }

    @Override
    public float getHeight() {
        return HEIGHT;
    }

}
