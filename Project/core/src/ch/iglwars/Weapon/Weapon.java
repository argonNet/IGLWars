package ch.iglwars.Weapon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import ch.iglwars.Ship;
import ch.iglwars.Weapon.Ammo.Ammo;

/**
 * Classe de base représentant une arme que peut avoir un vaisseau
 */
public abstract class Weapon {
    //La fréquence à chaque  tir lancée
    private int ammoRate;
    //Le nombre maximum qu'il puisse avoir de tirs de ce vaisseau
    //Evite la surcharge de mémoire et d'avoir un écran brouillon
    private int ammoMax;
    //Direction du tir  (Vers le haut ou vers le bas)
    private int directionTir;
    //Textures des munitions
    private String[] textures;
    //Vaisseau qui possède cette arme
    private Ship owner;
    //Liste des tirs lancés et actif du vaisseau
    private Array<Ammo> ammosList;

    //Temps depuis la dernière munition tirée
    private long lastAmmoTime;
    //Le type (Classe) de la munition
    private Class ammoClass;

    /**
     * Constructeur de base
     */
    public Weapon() {
        ammosList = new Array<Ammo>();
    }

    /**
     * Dessine les tirs de l'arme
     *
     * @param batch Element de LibGDX qui gère le rendu
     */
    public abstract void shoot(SpriteBatch batch);


    protected int getAmmoRate() {
        return ammoRate;
    }

    protected int getAmmoMax() {
        return ammoMax;
    }

    protected int getDirectionTir() {
        return directionTir;
    }

    protected String[] getTextures() {
        return textures;
    }

    protected Ship getOwner() {
        return owner;
    }

    protected Array<Ammo> getAmmosList() {
        return ammosList;
    }

    protected long getLastAmmoTime() {
        return lastAmmoTime;
    }

    protected Class getAmmoClass() {
        return ammoClass;
    }

    public void setAmmoRate(int ammoRate) {
        this.ammoRate = ammoRate;
    }

    public void setAmmoMax(int ammoMax) {
        this.ammoMax = ammoMax;
    }

    public void setDirectionTir(int directionTir) {
        this.directionTir = directionTir;
    }

    public void setTextures(String[] textures) {
        this.textures = textures;
    }

    public void setOwner(Ship owner) {
        this.owner = owner;
    }

    public void setAmmosList(Array<Ammo> ammosList) {
        this.ammosList = ammosList;
    }

    public void setLastAmmoTime(long lastAmmoTime) {
        this.lastAmmoTime = lastAmmoTime;
    }

    public void setAmmoClass(Class ammoClass) {
        this.ammoClass = ammoClass;
    }
}
