package ch.iglwars.TexturesMode;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;

import ch.iglwars.Utils.GraphicElement;
import ch.iglwars.Utils.TextureManager;

/**
 * Définit une animation cyclic de texture. Chaque texture est afficher tous les X millisecondes
 */
public class PassiveAnimatedTexture implements TextureMode {

    private Texture[] textures;
    private int currentTexture;
    private long delayBetweenTexture;
    private long lastTextureSwitch;
    private boolean incOrDec; //Si vrai alors il faut incrémenter

    /**
     * Constructeur de la classe
     * @param delayBetweenTexture Définit le temps de pause entre les images
     * @param textureNames Tableau contenant les noms de toutes les textures à afficher
     */
    public PassiveAnimatedTexture(long delayBetweenTexture, String textureNames[]){

        currentTexture = 0;
        lastTextureSwitch = 0;
        incOrDec = false;

        this.delayBetweenTexture = delayBetweenTexture;
        this.textures = new Texture[textureNames.length];

        //Récupération de toutes les textures nécessaire
        for (int i = 0; i < textureNames.length; i++) {
            this.textures[i] = TextureManager.getInstance().getTexture(textureNames[i]);
        }
    }

    /**
     * Methode qui retourne toujours la même textures
     * @param element non nécessaire dans ce cas.
     * @return Texture passée au constructeur
     */
    @Override
    public Texture getTextureToDraw(GraphicElement element){

        if (TimeUtils.millis() - lastTextureSwitch > delayBetweenTexture) {

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
        return textures[currentTexture];
    }
}
