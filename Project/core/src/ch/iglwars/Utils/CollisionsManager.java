package ch.iglwars.Utils;

import ch.iglwars.Enemy.Enemy;
import ch.iglwars.Level.Salve;
import ch.iglwars.Player.Player;
import ch.iglwars.Ship;
import ch.iglwars.Weapon.Ammo.Ammo;
import ch.iglwars.Weapon.Weapon;

/**
 * Created by Loïc Viret on 01.06.16.
 *
 * Utilitaire offrant des méthodes pour les vérifications de collisions
 */
public class CollisionsManager {

    /**
     * Contrôle si deux éléments graphiques entrent en collision
     * @param element premier element
     * @param element2 deuxieme element
     * @return vrai s'il y a collision
     */
    public static boolean isColliding(GraphicElement element, GraphicElement element2) {
        if (element == null || element2 == null) {
            return false;
        }
        else {
            boolean colliding = false;

            float elementMinX = element.getX();
            float elementMaxX = elementMinX + element.getWidth();
            float elementMinY = element.getY();
            float elementMaxY = elementMinY + element.getHeight();

            if (element2.getX() < elementMaxX && element2.getY() < elementMaxY &&
                    (element2.getX() + element2.getWidth()) > elementMinX &&
                    (element2.getY() + element2.getHeight()) > elementMinY) {
                colliding = true;
            }

            return colliding;
        }
    }

    /**
     * Contrôle si un élément graphique entre en collision avec le joueur
     * @param element l'élément graphique
     * @return vrai s'il y a collision
     */
    public static boolean isCollidingWithPlayer(GraphicElement element) {
        return isColliding(Player.getInstance(), element);
    }

    /**
     * Contrôle si un vaisseau et ses tirs entre en collision avec le joueur ou ses tirs
     * @param enemy le vaisseau
     * @return vrai s'il y a une collision
     */
    public static boolean isEnemyCollidingWithPlayerAndAmmo(Enemy enemy, Salve salve) {
        Player player = Player.getInstance();
        // On vérifie en premier lieu la collision des vaisseaux
        boolean colliding = false;

        // On vérifie la collision
        if (isColliding(player, enemy)) {
            colliding = true;
            player.Stop();
            salve.destroyEnemy(enemy);
        }
        // On vérifie les tirs du vaisseau
        else if (isCollidingWithAmmo(enemy, player)) {
            colliding = true;
            player.Stop();
        }
        // On vérifie les tirs du joueur
        else if (isCollidingWithAmmo(player, enemy)) {
            colliding = true;
            salve.destroyEnemy(enemy);
        }

        return colliding;
    }

    /**
     * Contrôle si un vaisseau touche un autre avec ses tirs
     * @param shooter le tireur
     * @param target la cible
     * @return vrai si touché au moins une fois
     */
    public static boolean isCollidingWithAmmo(Ship shooter, Ship target) {
        if (shooter != null && target != null && shooter.getWeaponsList() != null) {
            for (Weapon weapon : shooter.getWeaponsList()) {
                for (Ammo ammo : weapon.getAmmosList()) {
                    if (isColliding(target, ammo)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
