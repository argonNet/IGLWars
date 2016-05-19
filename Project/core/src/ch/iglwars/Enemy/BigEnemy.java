package ch.iglwars.Enemy;

import com.badlogic.gdx.Gdx;

import ch.iglwars.Constants;
import ch.iglwars.TexturesMode.PassiveAnimatedTexture;
import ch.iglwars.TexturesMode.TextureMode;

/**
 * Created by Argon on 14.05.16.
 */
public class BigEnemy extends Enemy{

    public static float WIDTH = 80;

    public static String [] TEXTURES_NAME = {
            "enemy_big_0.png",
            "enemy_big_1.png",
            "enemy_big_2.png",
            "enemy_big_3.png"};

    private float direction;

    /**
     * Construction de l'objet de base
     *
     * @param x Position initiale de l'enemi sur l'axe des X
     */
    public BigEnemy(float x) {
        super(x);
    }

    /**
     * Définition des propriétés pour l'enemy
     */
    protected void setProperties(){
        direction = 1;
        setHeight(80);
    }

    @Override
    protected TextureMode createTextureMode() {
        return new PassiveAnimatedTexture(5,TEXTURES_NAME);
    }

    /**
     * Gestion du déplacement de l'enemy
     */
    protected void setPositionInLoop(){
        this.setY(this.getY() - (200 * Gdx.graphics.getDeltaTime()));

    }
}
