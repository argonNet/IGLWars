package ch.iglwars.Weapon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

import ch.iglwars.Weapon.Ammo.Ammo;

/**
 * Classe représenant un type d'arme lançant qu'un tir à la fois
 */
public class SingleShoot extends Weapon {
    //Temps depuis la dernière munition tirée
    private long lastAmmoTime;

    /**
     * Constructeur de base
     */
    public SingleShoot() {
        lastAmmoTime = 0;
    }

    /**
     * Dessine les tirs de l'arme
     *
     * @param batch Element de LibGDX qui gère le rendu
     */
    public void shoot(SpriteBatch batch) {
        if (TimeUtils.millis() - lastAmmoTime > getAmmoRate()) {
            if (getAmmosList().size < getAmmoMax()) {
                //Place le tir just au-dessus du sprite du vaisseau
                try {

                    Ammo ammo = (Ammo) getAmmoClass().newInstance();
                    ammo.setX(getOwner().getX() + getOwner().getWidth() / 2);
                    ammo.setY(getOwner().getY() + getOwner().getHeight() / 2);
                    ammo.setTextures(getTextures());
                    ammo.setDirection(getDirectionTir());
                    ammo.Start();
                    getAmmosList().add(ammo);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

                lastAmmoTime = TimeUtils.millis();
            }
        }

        Iterator<Ammo> iter = getAmmosList().iterator();
        while (iter.hasNext()) {
            Ammo ammo = iter.next();
            ammo.draw(batch);
            //Si le tir atteint le haut de l'écran, on l'enlève
            if (ammo.isDestroyed()) {
                ammo.Stop();
                iter.remove();
            }
        }

    }
}
