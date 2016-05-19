package ch.iglwars;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

import ch.iglwars.TexturesMode.TextureMode;

/**
 * TO-DO: Mettre des éléments de la classe Enemy ici pour que la classe Player puisse en bénéficier !
 * Classe de base pour tous les éléments graphiques du jeu
 */
public abstract class GraphicElement {

    private float x;
    private float y;

    private float rotation;
    private float height; //utilisée notamment pour calculer la hauteur de départ
    private boolean isRunning; //Définit si un élément est en cours de déplacement

    private TextureMode textureMode;

    /**
     * Création des éléments graphiques
     */
    public GraphicElement(){
        isRunning = false;
        textureMode = createTextureMode();
        setProperties();
    }

    /**
     * Crée l'objet qui permet de gérer le mode de texture
     * @return Objet descendant de TextureMode
     */
    protected abstract TextureMode createTextureMode();

    /**
     * Permet au enfant de définir leur position dans la loop graphique avant de dessiner l'enemi.
     * Il s'agit de définir "l'intelligence" de déplacement automatique des ennemis.
     */
    protected abstract void setPositionInLoop();

    /**
     * Méthode qui permet de définir les propriétés des enfants
     */
    protected abstract void setProperties();

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
     * Methode qui dessine l'objet
     */
    public void draw(SpriteBatch batch) {

        if(isRunning) {
            setPositionInLoop();

            Texture texture = textureMode.getTextureToDraw(this);
            batch.draw(texture,
                    this.getX(), this.getY(),
                    texture.getWidth() / 2,texture.getHeight() / 2,
                    texture.getWidth(),texture.getHeight(),
                    1,1,
                    this.getRotation(),
                    0,0,
                    texture.getWidth(),texture.getHeight(),
                    false,false);
        }
    }

    /**
     * Démarre le *déplacement* de l'objet (calcul de position, dessin, etc)
     */
    public void Start(){
        this.isRunning = true;
    }

    /**
     * Stop le *déplacement* de l'objet (calcul de position, dessin, etc)
     */
    public void Stop(){
        this.isRunning = false;
    }


    protected float getRotation(){return this.rotation;}
    protected void setRotation(float rotationAngle){this.rotation = rotationAngle;}

    protected void setHeight(float height){this.height = height;}
    protected float getHeight(){ return this.height;}

    public float getX() {return x;}
    public void setX(float x) {this.x = x;}

    public float getY() {return y;}
    public void setY(float y) {this.y = y;}
}
