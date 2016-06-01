package ch.iglwars.Enemy;

import ch.iglwars.TexturesMode.PassiveAnimatedTexture;
import ch.iglwars.TexturesMode.TextureMode;

/**
 * Bosse du leve 1.
 */
public class BossLevel1 extends Enemy {

    public static float WIDTH = 48;
    public static float HEIGHT = 58;

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
        return new PassiveAnimatedTexture(5,TEXTURES_NAME);
    }

    /**
     * Définition des propriétés pour l'enemy
     */
    @Override
    protected void setProperties() {
        setHeight(HEIGHT);
    }


    @Override
    public float getHeight() {
        return 0;
    }

    @Override
    public float getWidth() {
        return 0;
    }
}
