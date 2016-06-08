package ch.iglwars.Enemy;

import com.badlogic.gdx.Gdx;

import ch.iglwars.Utils.Constants;
import ch.iglwars.Player.Player;
import ch.iglwars.TexturesMode.PassiveAnimatedTexture;
import ch.iglwars.TexturesMode.TextureMode;
import ch.iglwars.Weapon.Ammo.Bullet;
import ch.iglwars.Weapon.SingleShoot;

/**
 * Bosse du leve 1.
 */
public class BossLevel1 extends Enemy {

    public static final float WIDTH = 66;
    public static final float HEIGHT = 80;
    private static int LIVES = 70;
    private static int POINTS = 1000;

    private static final float SPEED = 100;

    //Textures par défaut pour les tirs du joueur
    private static String[] TEXTURES_BULLET =
            {
                    "bullet_enemy_0.png", "bullet_enemy_1.png"
            };
    //Fréquence de tir par défaut
    private static int BULLET_RATE = 400;
    //Nombre max de tirs à l'écran par défaut
    private static int BULLET_MAX = 10;

    public static String [] TEXTURES_NAME = {
            "level1_boss_0.png",
            "level1_boss_1.png",
            "level1_boss_2.png",
            "level1_boss_3.png"};

    /**
     * Définition de la texture de l'ennemi
     * @return Texture à créer
     */
    @Override
    protected TextureMode createTextureMode() {
        return new PassiveAnimatedTexture(50,TEXTURES_NAME);
    }

    /**
     * Définition des propriétés pour l'enemy
     */
    @Override
    protected void setProperties() {
        setHeight(HEIGHT);
        setWidth(WIDTH);
        setLives(LIVES);
        setPoints(POINTS);

        //Arme de base du vaisseau joueur
        setWeapon(SingleShoot.class,TEXTURES_BULLET, BULLET_RATE, BULLET_MAX, Constants.BAS , Bullet.class);
    }


    /**
     * Gestion du déplacement de l'enemy de base
     */
    @Override
    protected void setPositionInLoop(){
        super.setPositionInLoop();

        //Déplacement du bosse depuis le haut
        if(this.getY() >= Constants.GAME_HEIGHT - 2 * this.HEIGHT ){
            this.setY(this.getY() - (SPEED * Gdx.graphics.getDeltaTime()));
        } else { //Le bosse suit le joueur
            if(Player.getInstance().getX() - this.getX() > 0){ //Déplacement à droite
                this.setX(this.getX() + (SPEED * Gdx.graphics.getDeltaTime()));
            }else{ //Déplacement à gauche
                this.setX(this.getX() - (SPEED * Gdx.graphics.getDeltaTime()));
            }
        }
    }

    @Override
    public float getHeight() {
        return HEIGHT;
    }

    @Override
    public float getWidth() {
        return WIDTH;
    }
}
