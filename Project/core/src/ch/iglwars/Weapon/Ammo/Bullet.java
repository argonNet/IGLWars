package ch.iglwars.Weapon.Ammo;

import ch.iglwars.Weapon.*;

/**
 * Classe en réprésantant une munition sous forme d'une balle
 */
public class Bullet extends ch.iglwars.Weapon.Ammo.Ammo {
    //Largeur de la balle
    public static float WIDTH = 15;
    //Longueur de la balle
    public static float HEIGHT = 15;
    //Dommage causé par la balle
    public static int DAMAGE = 1;
    //Vitesse de la trajectoire
    public static int SPEED = 300;

    /**
     * Méthode qui permet de définir les propriétés des enfants
     */
    @Override
    protected void setProperties() {
        setWidth(WIDTH);
        setHeight(HEIGHT);
        setDamage(DAMAGE);
        setSpeed(SPEED);
    }
}
