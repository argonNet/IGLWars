package ch.iglwars.Enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Polygon;

import ch.iglwars.TexturesMode.StaticTexture;
import ch.iglwars.TexturesMode.TextureMode;


/**
 * Created by Argon on 14.05.16.
 */
public class SmallEnemy extends Enemy{

    public static float WIDTH = 31;
    public static float HEIGHT = 48;
    private int direction = 1;

    /**
     * Constructeur sans paramètre (pour la SalveFactory)
     */
    public SmallEnemy(){
        super();
    }

    /**
     * Construction de l'objet de base
     *
     * @param x Position initiale de l'enemi sur l'axe des X
     * @param y Position initiale de l'enemi sur l'axe des Y
     */
    public SmallEnemy(float x, float y) {
        super(x, y);
    }

    /**
     * Définition des propriétés pour les petits enemis
     */
    protected void setProperties(){
        setHeight(HEIGHT);
    }

    @Override
    protected TextureMode createTextureMode() {
        return new StaticTexture("enemy_small.png");
    }

    /**
     * Gestion du déplacement de l'enemy de base
     */
    protected void setPositionInLoop(){
        super.setPositionInLoop();
        this.setY(this.getY() - (60 * Gdx.graphics.getDeltaTime()));
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
