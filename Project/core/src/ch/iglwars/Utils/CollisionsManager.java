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

    public static int controlColliding(Salve salve) {
        int score = 0;
        for (Enemy enemy : salve.getEnemies()) {
            CollisionsManager.controlEnemyCollidingWithPlayerAndAmmo(enemy, salve);

            if (Player.getInstance().isDestroyed()) {
                return score;
            }
        }
        return score;
    }

    /**
     * Contrôle si deux éléments graphiques entrent en collision
     * @param element premier element
     * @param element2 deuxieme element
     * @return vrai s'il y a collision
     */
    private static boolean isColliding(GraphicElement element, GraphicElement element2) {
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
    private static boolean isCollidingWithPlayer(GraphicElement element) {
        return isColliding(Player.getInstance(), element);
    }

    /**
     * Contrôle si un vaisseau et ses tirs entre en collision avec le joueur ou ses tirs
     * @param enemy le vaisseau
     * @param salve la salve qui contient le vaisseau
     */
    public static void controlEnemyCollidingWithPlayerAndAmmo(Enemy enemy, Salve salve) {
        Player player = Player.getInstance();

        // On vérifie la collision
        if (isColliding(player, enemy)) {
            player.damage(1);
            enemy.damage(1);
        }
        // On vérifie les tirs du vaisseau
        controlCollidingWithAmmo(enemy, player);

        // On vérifie les tirs du joueur
        controlCollidingWithAmmo(player, enemy);
    }

    /**
     * Contrôle si un vaisseau touche un autre avec ses tirs
     * @param shooter le tireur
     * @param target la cible
     */
    private static void controlCollidingWithAmmo(Ship shooter, Ship target) {
        if (shooter != null && target != null && shooter.getWeapon() != null) {

                for (Ammo ammo : shooter.getWeapon().getAmmosList()) {
                    if (isColliding(target, ammo)) {
                        target.damage(ammo.getDamage());
                        ammo.destroy();
                    }
                }

        }
    }
}
