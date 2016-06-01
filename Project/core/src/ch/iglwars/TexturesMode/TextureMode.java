package ch.iglwars.TexturesMode;

import com.badlogic.gdx.graphics.Texture;

import ch.iglwars.Utils.GraphicElement;


/**
 * Classe de base pour la gestion des modes de textures
 */
public abstract class TextureMode {

    /**
     * Methode qui retourne la texture à dessiner en fonction de l'élément qu'elle représente
     * @param element Element représenté par la texture
     * @return Texture à dessiner
     */
    public abstract Texture getTextureToDraw(GraphicElement element);
}
