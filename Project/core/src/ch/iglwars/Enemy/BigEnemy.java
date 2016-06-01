package ch.iglwars.Enemy;

import com.badlogic.gdx.Gdx;

import ch.iglwars.Utils.Constants;
import ch.iglwars.TexturesMode.PassiveAnimatedTexture;
import ch.iglwars.TexturesMode.TextureMode;

/**
 * Created by Argon on 14.05.16.
 */
public class BigEnemy extends Enemy{

    public static float WIDTH = 94;
    public static float HEIGHT = 94;

    public static String [] TEXTURES_NAME = {
            "enemy_big_0.png",
            "enemy_big_1.png",
            "enemy_big_2.png",
            "enemy_big_3.png",
            "enemy_big_4.png",
            "enemy_big_5.png",
            "enemy_big_6.png"};

    private float direction;

    /**
     * Constructeur sans paramètre (pour la SalveFactory)
     */
    public BigEnemy(){
        super();
    }

    /**
     * Construction de l'objet de base
     *
     * @param x Position initiale de l'enemi sur l'axe des X
     * @param y Position initiale de l'enemi sur l'axe des Y
     */
    public BigEnemy(float x, float y) {
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
        return new PassiveAnimatedTexture(5,TEXTURES_NAME);
    }

    /**
     * Gestion du déplacement de l'enemy
     */
    protected void setPositionInLoop(){
        //this.setY(this.getY() - (200 * Gdx.graphics.getDeltaTime()));


        // Deplacement lateral gauche - droite
        if(this.getX() > (Constants.GAME_WIDTH - WIDTH)){
            direction = GAUCHE;
        } else if (this.getX() < 0) {
            direction = DROITE;
        }
        this.setX(this.getX() + (direction * 200 * Gdx.graphics.getDeltaTime()));

        //Rotation sur lui-même
        this.incRotation(2);
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
