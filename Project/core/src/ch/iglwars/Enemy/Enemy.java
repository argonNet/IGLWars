package ch.iglwars.Enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import ch.iglwars.Constants;
import ch.iglwars.GraphicElement;
import ch.iglwars.TextureManager;

/**
 * Classe de base pour tous les ennemis
 */
public abstract class Enemy extends GraphicElement {

    private Texture texture;

    private Texture[] textures;
    private int currentTexture;
    private long delayBetweenTexture;
    private long lastTextureSwitch;
    private boolean incOrDec; //Si vrai alors il faut incrémenter

    private float height; //utilisée notamment pour calculer la hauteur de départ

    private float rotation;

    private boolean isRunning; //Définit si un ennemi est en cours de déplacement

    /**
     * Construction de l'objet de base
     * @param x Position initiale de l'ennemi sur l'axe des X
     */
    public Enemy(float x, Float y){
        currentTexture = 0;
        lastTextureSwitch = 0;
        incOrDec = false;
        isRunning = false;

        this.setX(x);
        this.setY(y);

        setProperties();
    }

    /**
     * Méthode qui permet de définir les propriétés des enfants
     * /!\ les méthodes setTexture et setHeight doivent être appelé dans celle-ci
     */
    protected abstract void setProperties();


    /**
     * Permet au enfant de définir la texture  (doit être appelé dans le constructeur)
     */
    protected void setTexture(String textureName){
        texture =  TextureManager.getInstance().getTexture(textureName);
    }

    /**
     * Définit une animation entre les textures
     * @param delayBetweenTexture Définit le temps de pause entre les images
     * @param textureNames Tableau contenant les noms de toutes les textures à afficher
     */
    protected void setAnimatedTexture(long delayBetweenTexture, String textureNames[]){
        this.delayBetweenTexture = delayBetweenTexture;
        this.textures = new Texture[textureNames.length];

        for (int i = 0; i<textureNames.length;i++){
            this.textures[i] = TextureManager.getInstance().getTexture(textureNames[i]);
        }
    }

    /**
     * Permet au enfant de définir la hauteur de l'élément
     * @param height
     */
    protected void setHeight(float height){
        this.height = height;
    }

    /**
     * Définit l'angle de rotation de l'objet dessiné
     * @param rotationAngle Valeur du nouvelle angle
     */
    protected void setRotation(float rotationAngle){
        this.rotation = rotationAngle;
    }


    /**
     * Incrémente l'angle de rotation actuel
     * @param valueToAdd Valeur a ajouter à l'angle actuel
     */
    protected void incRotation(float valueToAdd){
        this.rotation += valueToAdd;

        if(this.rotation > 360){
            this.rotation -= 360;
        }else if(this.rotation < 0){
            this.rotation += 360;
        }
    }

    /**
     * Accesseur pour l'angle de rotation en cours
     * @return valeur de l'angle
     */
    protected float getRotation(){
        return this.rotation;
    }

    /**
     * Permet au enfant de définir la position de l'ennemi. Cette méthode sera appelé avant de
     * dessiner l'ennemi.
     */
    protected  abstract void setEnemyPositionInLoop();

    /**
     * Démarre le déplacement de l'ennemi.
     */
    public void Start(){
        this.isRunning = true;
    }

    /**
     * Dessin global de l'ennemi
     */
    @Override
    public void draw(SpriteBatch batch) {

        if(isRunning) {
            setEnemyPositionInLoop();

            //Gestion du switch entre les textures
            if(textures != null) {
                if (TimeUtils.millis() - lastTextureSwitch > delayBetweenTexture) {
                    texture = textures[currentTexture];

                    if (currentTexture == textures.length - 1 ||  currentTexture == 0) {
                        incOrDec = !incOrDec;
                    }

                    if(incOrDec){
                        currentTexture++;
                    }else{
                        currentTexture--;
                    }

                    lastTextureSwitch = TimeUtils.millis();
                }
            }

            batch.draw(texture,
                    this.getX(), this.getY(),
                    texture.getWidth() / 2,texture.getHeight() / 2,
                    texture.getWidth(),texture.getHeight(),
                    1,1,
                    this.getRotation(),
                    0,0,
                    texture.getWidth(),texture.getHeight(),
                    false,false);

            //Stop l'ennemi si il atteint la fin de l'écran
            if(this.getY() < -1 * height ){
                isRunning = false;
            }
        }

    }
}
