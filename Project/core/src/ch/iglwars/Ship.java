package ch.iglwars;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ch.iglwars.TexturesMode.TextureMode;
import ch.iglwars.Weapon.Weapon;

/**
 * Classe de base représantant les vaisseaux du jeu
 * Un vaisseau est un élément graphique ayant la possibilité d'utiliser une arme
 */
public abstract class Ship extends ch.iglwars.Utils.GraphicElement {

    //Arme que possède le vaisseau
    private Weapon weapon;

    // Nombre de vies que possède le vaisseau
    private int lives;


    /**
     * D'intialiser une arme au vaisseau (va remplacer celle existante)
     *
     * @param textures   Textures à donner aux tir de l'arme
     * @param rateBullet La fréquence de tir
     * @param maxBullet  Le nombre maximal de tir à l'écran
     * @param direction  La direction du tir (soit HAUT ou BAS)
     * @param ammoClass  Le type (Class) des munitions
     */
    protected void setWeapon(Class weaponClass, String[] textures, int rateBullet, int maxBullet, int direction, Class ammoClass) {
        try {

            weapon = (Weapon) weaponClass.newInstance();
            getWeapon().setOwner(this);
            getWeapon().setAmmoRate(rateBullet);
            getWeapon().setAmmoMax(maxBullet);
            getWeapon().setTextures(textures);
            getWeapon().setDirectionTir(direction);
            getWeapon().setAmmoClass(ammoClass);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
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
     * Permet au enfant de définir leur position dans la loop graphique avant de dessiner l'enemi.
     * Il s'agit de définir "l'intelligence" de déplacement automatique des ennemis.
     */
    @Override
    protected void setPositionInLoop() {

    }

    /**
     * Méthode qui permet de définir les propriétés des enfants
     */
    @Override
    protected void setProperties() {

    }

    /**
     * Methode qui dessine l'objet
     * Dessine aussi le tir de l'arme
     *
     * @param batch Element de LibGDX qui gère le rendu
     */
    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if (getWeapon() != null) {
            getWeapon().shoot(batch);
        }
    }

    public void damage(int ammoPower) {
        lives -= ammoPower;
    }

    public boolean isDestroyed() {
        boolean destroyed = lives < 1;

        if (destroyed) {
            this.Stop();
        }

        return destroyed;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
