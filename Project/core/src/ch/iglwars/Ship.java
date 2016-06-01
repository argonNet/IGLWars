package ch.iglwars;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

import ch.iglwars.TexturesMode.TextureMode;
import ch.iglwars.Weapon.Weapon;

/**
 * Classe de base représantant les vaisseaux du jeu
 * Un vaisseau est un élément graphique ayant la possibilité d'utiliser une arme
 */
public abstract class Ship extends ch.iglwars.Utils.GraphicElement {

    //Liste d'arme que possède le vaisseau
    //TODO: En faire une liste ou un seul objet ?
    private List<Weapon> weaponsList;


    /**
     * Permet d'ajouter une nouvelle arme au vaisseau
     *
     * @param textures   Textures à donner aux tir de l'arme
     * @param rateBullet La fréquence de tir
     * @param maxBullet  Le nombre maximal de tir à l'écran
     * @param direction  La direction du tir (soit HAUT ou BAS)
     * @param ammoClass  Le type (Class) des munitions
     */
    protected void addWeapon(Class weaponClass, String[] textures, int rateBullet, int maxBullet, int direction, Class ammoClass) {
        if (weaponsList == null)
        {
            weaponsList = new ArrayList<Weapon>();
        }
        try {

            Weapon weapon = (Weapon)weaponClass.newInstance();
            weapon.setOwner(this);
            weapon.setAmmoRate(rateBullet);
            weapon.setAmmoMax(maxBullet);
            weapon.setTextures(textures);
            weapon.setDirectionTir(direction);
            weapon.setAmmoClass(ammoClass);
            weaponsList.add(weapon);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        //weaponsList.add(new Weapon(this, rateBullet, maxBullet, textures, direction, ammoClass));
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
     * Dessine aussi le tir des armes
     *
     * @param batch Element de LibGDX qui gère le rendu
     */
    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if (weaponsList != null && !weaponsList.isEmpty()) {
            for (Weapon weapon : weaponsList) {
                weapon.shoot(batch);
            }
        }
    }

    public List<Weapon> getWeaponsList() {
        return weaponsList;
    }

    public void setWeaponsList(List<Weapon> weaponsList) {
        this.weaponsList = weaponsList;
    }
}
