package ch.iglwars.Level;

import java.util.ArrayList;
import java.util.List;

import ch.iglwars.Enemy.Enemy;

/**
 * Classe qui représente une salve d'enemi. Elle utilisée pour la construction des niveau
 */
public class Salve {

    // Enemis contenus dans la salve
    private List<Enemy> enemies;

    // Indication si la salve est déjà complètement lancée
    private boolean gone = false;

    public Salve() {
        this.enemies = new ArrayList<Enemy>();
    }

    public void addEnemy(Enemy enemy) {
        this.enemies.add(enemy);
    }

    public Enemy getEnemy(int index) {
        return this.enemies.get(index);
    }

    public List<Enemy> getEnemies() {
        return this.enemies;
    }

    public boolean isGone() {
        return gone;
    }

    public void setGone(boolean gone) {
        this.gone = gone;
    }
}
