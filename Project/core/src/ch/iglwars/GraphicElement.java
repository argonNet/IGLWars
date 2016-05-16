package ch.iglwars;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Classe de base pour tous les éléments graphiques du jeu
 */
public abstract class GraphicElement {

    private float x;
    private float y;

    /**
     * Methode abstraite qui dessine l'objet
     */
    public abstract void draw(SpriteBatch batch);


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;

    }
}
