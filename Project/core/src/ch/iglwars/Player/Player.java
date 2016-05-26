package ch.iglwars.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

import java.io.Console;

import ch.iglwars.Constants;
import ch.iglwars.GraphicElement;
import ch.iglwars.TextureManager;
import ch.iglwars.TexturesMode.TextureMode;

/**
 * TO-DO: Utiliser les variables et méthodes de Graphic Element une fois ce dernier mis à jour
 * Created by Aoi on 17/05/2016.
 */
public class Player extends GraphicElement implements GestureDetector.GestureListener {

    private Texture texture;

    public static String [] TEXTURES_NAME = {
            "player_standing.png"};

public Player()
{
    //Enregistre les événements de la détection des mouvements
    Gdx.input.setInputProcessor(new GestureDetector(this));
    setTexture();
}

    @Override
    protected TextureMode createTextureMode() {
        return null;
    }

    @Override
    protected void setPositionInLoop() {

    }

    @Override
    protected void setProperties() {

    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(texture,
                this.getX(), this.getY(),
                texture.getWidth() / 2,texture.getHeight() / 2,
                texture.getWidth(),texture.getHeight(),
                1,1,
                0,
                0,0,
                texture.getWidth(),texture.getHeight(),
                false,false);
    }



    protected void setTexture(){
        texture =  TextureManager.getInstance().getTexture(TEXTURES_NAME[0]);
    }

    /**
     * Méthode appelée quand on touche l'écran
     * @param x coordonnées x du doigt
     * @param y coordonnées y du doigt
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
     * @param x coordonnées x du doigt
     * @param y coordonnées y du doigt
     * @param count  Nombre de tapotement
     * @param button
     */
    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    /***
     * Méthode appelée quand on maintient le doigt pendant longtemps sur l'écran
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
     *Méthode appelée quand le joueur glisse un doigt sur l'écran
     *Calcule des nouvelles coordonnées de la bucket en se basant uniquement
     *sur la distance parcourue par le doigt entre la position précédente et celle actuelle
     * @param x coordonnées x du doigt
     * @param y coordonnées y du doigt
     * @param deltaX Différence x entre la position actuelle et l'ancienne
     * @param deltaY Différence y entre la position actuelle et l'ancienne
     */
    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {

       float rapportTailleX = (float)Constants.GAME_WIDTH  / Gdx.graphics.getWidth();
        float newX = this.getX() + (deltaX * rapportTailleX);
        if (newX < 0 ) newX = 0;
        else if (newX > (Constants.GAME_WIDTH - texture.getWidth())) newX = Constants.GAME_WIDTH - texture.getWidth();

        float rapportTailleY = (float)Constants.GAME_HEIGHT  / Gdx.graphics.getHeight();
        float newY = this.getY() - (deltaY * rapportTailleY);
        if (newY < 0 ) newY = 0;
        else if (newY > (Constants.GAME_HEIGHT -64)) newY = Constants.GAME_HEIGHT -64;

        this.setX(newX);
        this.setY(newY);
        return false;
    }

    /**
     * Méthode appelée dès que le joueur enlève son doigt sur l'écran
     * @param x Coordonnées x de l'endroit où le doigt s'est arrété
     * @param y Coordonnées y de l'endroit où le doigt s'est arrété
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
     * @param pointer1 Vecteur n°1 à 2 coordonnées de la position actuelle
     * @param pointer2 Vecteur n°1 à 2 coordonnées de la position actuelle
     */
    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    public float getHeight() {
        return texture.getHeight();
    }

    public float getWidth() {
        return texture.getWidth();
    }
}
