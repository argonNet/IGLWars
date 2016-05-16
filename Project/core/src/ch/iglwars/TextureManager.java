package ch.iglwars;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * Classe de gestion des textures (Singleton)
 */
public class TextureManager {

    private static TextureManager instance;
    private AssetManager assetManager;

    /**
     * Constructeur privée pour le SingleTon
     */
    private TextureManager(){
        assetManager = new AssetManager();
    }


    /**
     * Permet d'obtenir et de la charger si nécessaire (chargement à la volée)
     * @param texturePath Nom du fichier qui contient la texture
     * @return Référence sur la texture
     */
    public Texture getTexture(String texturePath){

        if(!assetManager.isLoaded(texturePath)) {
            assetManager.load(texturePath,Texture.class);
            assetManager.finishLoadingAsset(texturePath);
        }

        return assetManager.get(texturePath, Texture.class);
    }


    /**
     * Accesseur pour le singleton.
     */
    public static TextureManager getInstance(){
        if(instance == null){
            instance = new TextureManager();
        }
        return instance;
    }
}
