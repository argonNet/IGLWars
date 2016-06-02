package ch.iglwars.Enemy;

import ch.iglwars.Ship;

/**
 * Classe de base pour tous les ennemis
 */
public abstract class Enemy extends Ship {

    protected static int GAUCHE = -1;
    protected static int DROITE = 1;

    private int points;

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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public abstract float getHeight();

    public abstract float getWidth();

}
