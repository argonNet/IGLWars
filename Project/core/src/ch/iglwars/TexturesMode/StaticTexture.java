package ch.iglwars.TexturesMode;

import com.badlogic.gdx.graphics.Texture;

import ch.iglwars.Utils.GraphicElement;
import ch.iglwars.Utils.TextureManager;

/**
 * Représente une simple texture static
 */
public class StaticTexture implements ITextureMode {

    private Texture texture;

    /**
     * Constructeur de la classe
     * @param textureName Nom de la texture à utiliser
     */
    public StaticTexture(String textureName){
        texture =  TextureManager.getInstance().getTexture(textureName);
    }

    /**
     * Methode qui retourne toujours la même textures
     * @param element non nécessaire dans ce cas.
     * @return Texture passée au constructeur
     */
    @Override
    public Texture getTextureToDraw(GraphicElement element){
        return texture;
    }
}
