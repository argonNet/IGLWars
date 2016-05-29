package ch.iglwars.Enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import ch.iglwars.Constants;
import ch.iglwars.GraphicElement;
import ch.iglwars.Ship;
import ch.iglwars.TextureManager;

/**
 * Classe de base pour tous les ennemis
 */
public abstract class Enemy extends Ship {


    /**
     * Constructeur par défaut, utilisé notament dans le cadre de la salve factory, se constructeur
     * ne doit pas avoir de paramètre.
     */
    public Enemy(){
        setProperties();
    }

    /**
     * Construction de l'objet de base
     * @param x Position initiale de l'ennemi sur l'axe des X
     */
    public Enemy(float x, float y){
        this.setX(x);
        this.setY(y);

        setProperties();
    }

    /**
     * Permet de stopper tous les enemis à la fin de l'ecran
     */
    protected void setPositionInLoop(){
        //Stop l'ennemi a atteint la fin de l'écran
        if(this.getY() < -1 * getHeight() ){
            this.Stop();
        }

    }

    public abstract float getHeight();

    public abstract float getWidth();

}
