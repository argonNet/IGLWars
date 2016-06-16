package ch.iglwars.Enemy;

import com.badlogic.gdx.Gdx;

import ch.iglwars.Utils.Constants;
import ch.iglwars.TexturesMode.StaticTexture;
import ch.iglwars.TexturesMode.ITextureMode;
import ch.iglwars.Weapon.Ammo.Bullet;
import ch.iglwars.Weapon.SingleShoot;


/**
 * Created by Argon on 14.05.16.
 */
public class SmallEnemy extends Enemy{

    public static float WIDTH = 31;
    public static float HEIGHT = 48;
    private static int LIVES = 1;
    private static int POINTS = 5;

    //Textures par défaut pour les tirs du joueur
    private static String[] TEXTURES_BULLET =
            {
                    "bullet_enemy_0.png", "bullet_enemy_1.png"
            };
    //Fréquence de tir par défaut
    private static int BULLET_RATE = 2000;
    //Nombre max de tirs à l'écran par  défaut
    private static int BULLET_MAX = 1;

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
        setWidth(WIDTH);
        setLives(LIVES);
        setPoints(POINTS);

        //Arme de base du vaisseau joueur
        setWeapon(SingleShoot.class,TEXTURES_BULLET, BULLET_RATE, BULLET_MAX, Constants.BAS, Bullet.class);
    }

    @Override
    protected ITextureMode createTextureMode() {
        return new StaticTexture("enemy_small.png");
    }

    /**
     * Gestion du déplacement de l'enemy de base
     */
    @Override
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
