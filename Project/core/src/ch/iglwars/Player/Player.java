package ch.iglwars.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

import ch.iglwars.Utils.Constants;
import ch.iglwars.Ship;
import ch.iglwars.TexturesMode.StaticTexture;
import ch.iglwars.TexturesMode.ITextureMode;
import ch.iglwars.Weapon.Ammo.Bullet;
import ch.iglwars.Weapon.SingleShoot;

/**
 * Classe représentant le vaisseau du joueur
 */
public class Player extends Ship implements GestureDetector.GestureListener {

    //Textures du vaisseau
    private static String[] TEXTURES_NAME = {
            "player_standing.png"};
    //Largeur du vaisseau
    private static float WIDTH = 42;
    //Hauteur du vaisseau
    private static float HEIGHT = 48;
    // Nombre de vies du vaisseau
    private static int LIVES = 1;

    //Textures par défaut pour les tirs du joueur
    private static String[] TEXTURES_BULLET =
            {
                    "bullet_player_0.png", "bullet_player_1.png"
            };
    //Fréquence de tir par défaut
    private static int BULLET_RATE = 300;
    //Nombre max de tirs à l'écran par  défaur
    private static int BULLET_MAX = 10;

    //Instance unique de la classe
    private static Player instance;


    /**
     * Constructeur de base
     */
    public Player() {

        //Enregistre les événements de la détection des mouvements
        Gdx.input.setInputProcessor(new GestureDetector(this));
    }

    /**
     * Crée l'objet qui permet de gérer le mode de texture
     * TODO: Faire une texture passive animation
     *
     * @return Objet descendant de TextureMode
     */
    @Override
    protected ITextureMode createTextureMode() {
        return new StaticTexture("player_standing.png");
    }

    /**
     * Permet au enfant de définir leur position dans la loop graphique avant de dessiner l'élément.
     * Inutile au niveau du joueur car le déplacement n'est pas automatique !
     */
    @Override
    protected void setPositionInLoop() {

    }

    /**
     * Méthode qui permet de définir les propriétés des enfants
     */
    @Override
    protected void setProperties() {
        setWidth(WIDTH);
        setHeight(HEIGHT);
        setLives(LIVES);
        //Arme de base du vaisseau joueur
        setWeapon(SingleShoot.class, TEXTURES_BULLET, BULLET_RATE, BULLET_MAX, Constants.HAUT, Bullet.class);
    }


    /**
     * Méthode appelée quand on touche l'écran
     *
     * @param x       coordonnées x du doigt
     * @param y       coordonnées y du doigt
     * @param pointer
     * @param button
     */
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    /**
     * Méthode appelée dès qu'on tapote l'écran (presser et relâcher très rapidement à un endroit)
     * count: Nombre de fois où l'écran a été tapoté à ces coordonnées
     * (On peut définir une intervalle de temps entre les tapotements dans le 2ème constructeur de GestureDetector)
     *
     * @param x      coordonnées x du doigt
     * @param y      coordonnées y du doigt
     * @param count  Nombre de tapotement
     * @param button
     */
    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    /***
     * Méthode appelée quand on maintient le doigt pendant longtemps sur l'écran
     *
     * @param x coordonnées x du doigt
     * @param y coordonnées y du doigt
     * @return
     */
    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    /**
     * Glisse le doigt sur l'écran
     * La méthode s'active une fois le doigt levé.
     * Retient la dernière vélocité du doigt en pixels par seconde
     *
     * @param velocityX Vélocité sur l'axe des x en secondes
     * @param velocityY Vélocité sur l'axe des y en secondes
     * @param button
     */
    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    /**
     * pan = Faire un panoramique
     * Méthode appelée quand le joueur glisse un doigt sur l'écran
     * Calcule des nouvelles coordonnées de la bucket en se basant uniquement
     * sur la distance parcourue par le doigt entre la position précédente et celle actuelle
     *
     * @param x      coordonnées x du doigt
     * @param y      coordonnées y du doigt
     * @param deltaX Différence x entre la position actuelle et l'ancienne
     * @param deltaY Différence y entre la position actuelle et l'ancienne
     */
    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if (isRunning()) {
            float rapportTailleX = (float) Constants.GAME_WIDTH / Gdx.graphics.getWidth();
            float newX = this.getX() + (deltaX * rapportTailleX);
            if (newX < 0) newX = 0;
            else if (newX > (Constants.GAME_WIDTH - getWidth()))
                newX = Constants.GAME_WIDTH - getWidth();

            float rapportTailleY = (float) Constants.GAME_HEIGHT / Gdx.graphics.getHeight();
            float newY = this.getY() - (deltaY * rapportTailleY);
            if (newY < 0) newY = 0;
            else if (newY > (Constants.GAME_HEIGHT - getHeight()))
                newY = Constants.GAME_HEIGHT - getHeight();

            this.setX(newX);
            this.setY(newY);
        }
        return false;
    }

    /**
     * Méthode appelée dès que le joueur enlève son doigt sur l'écran
     *
     * @param x       Coordonnées x de l'endroit où le doigt s'est arrété
     * @param y       Coordonnées y de l'endroit où le doigt s'est arrété
     * @param pointer
     * @param button
     */
    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    /**
     * Méthode appelée quand il y a 2 doigts posés sur l'écran.
     * Retient la distance entre les 2 doigts
     *
     * @param initialDistance Distance entre les doigts depuis le début du mouvement.
     * @param distance        Distance actuelle entre les doigts.
     */
    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    /**
     * Pareil que la méthode zoom sauf qu'elle retient cette fois-ci les positions précédente et actuelle des 2 doigts
     *
     * @param initialPointer1 Vecteur n°1 à 2 coordonnées de la position précédente
     * @param initialPointer2 Vecteur n°2 à 2 coordonnées de la position précédente
     * @param pointer1        Vecteur n°1 à 2 coordonnées de la position actuelle
     * @param pointer2        Vecteur n°1 à 2 coordonnées de la position actuelle
     */
    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    /**
     * Accesseur pour le Singleton
     *
     * @return Instance unique de la classe joueur
     */
    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
            instance.setX(Constants.GAME_WIDTH / 2 - (instance.getWidth() /2));
            instance.Start();
        }
        return instance;
    }

}
