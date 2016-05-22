package ch.iglwars.Weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;

import ch.iglwars.GraphicElement;
import ch.iglwars.Player.Player;
import ch.iglwars.Ship;
import ch.iglwars.TextureManager;
import ch.iglwars.TexturesMode.TextureMode;

/**
 * Created by Aoi on 19/05/2016.
 * Réprésente les balles par défaut tirées par le joueur et la majorité des ennemis
 */
public class Weapon extends GraphicElement {

    //Représente le nombre de dégât que va faire l'arme
    //Par défaut, il fait 1hp
    private int damage;
    //Vitesse du tir
    private int speed;

    public Weapon(float x, float y)
    {
        damage = 1;
        speed = 600;
        this.setX(x);
        this.setY(y);
        setTexture();
    }

private Texture texture;

    public static String [] TEXTURES_NAME = {
            "player_bullet.png"};

    protected void setTexture(){
        texture =  TextureManager.getInstance().getTexture(TEXTURES_NAME[0]);
    }

    /**
     * Crée l'objet qui permet de gérer le mode de texture
     *
     * @return Objet descendant de TextureMode
     */
    @Override
    protected TextureMode createTextureMode() {
        return null;
    }

    /**
     * Définit la trajectoire de l'arme
     */
    @Override
    protected void setPositionInLoop() {
        this.setY(this.getY() + (speed * Gdx.graphics.getDeltaTime()));
    }

    /**
     * Méthode qui permet de définir les propriétés des enfants
     */
    @Override
    protected void setProperties() {

    }

    public void draw(SpriteBatch batch) {
        setPositionInLoop();
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

    //Vérifie s'il a touché un objet
    public boolean isOverlaps()
    {
        return false;
    }



}
