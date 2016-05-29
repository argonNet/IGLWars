package ch.iglwars.Level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import ch.iglwars.Enemy.Enemy;
import ch.iglwars.Player.Player;
import ch.iglwars.TextureManager;

/**
 * Classe qui représente une salve d'enemi. Elle utilisée pour la construction des niveau
 */
public class Salve {

    // Enemis contenus dans la salve
    private List<Enemy> enemies;

    private long lastEnemyStartTime;
    private int enemyToStartIndex;

    // Indication si la salve est déjà complètement lancée
    private boolean gone = false;

    // Indique si la salve est terminée (tous les enemy = not IsRunning)
    private boolean ended = false;

    private Texture testTexture;

    // Temps entre le lancement des ennemis au sein de la salve.
    private int delayBetweenEnemyInSalve;

    /**
     * Constructeur de base pour les salves
     * @param delayBetweenEnemyInSalve Temps entre les ennemis au sein de la salve.
     */
    public Salve(int delayBetweenEnemyInSalve) {
        this.enemies = new ArrayList<Enemy>();
        this.setDelayBetweenEnemyInSalve(delayBetweenEnemyInSalve);
        this.lastEnemyStartTime = 0;
        this.enemyToStartIndex = 0;

        testTexture = TextureManager.getInstance().getTexture("test.png");
    }

    public void addEnemy(Enemy enemy) {
        this.enemies.add(enemy);
    }


    /**
     * Affichage de la salve
     * @param batch Element de LibGDX qui gère le rendu
     */
    public void Run(SpriteBatch batch){

        //Gestion des envois des enemis de la salve
        if(enemyToStartIndex < enemies.size() &&
                TimeUtils.millis() - lastEnemyStartTime > delayBetweenEnemyInSalve){

            enemies.get(enemyToStartIndex).Start();
            enemyToStartIndex++;
            lastEnemyStartTime = TimeUtils.millis();
        }

        //Affichage des enemis en train de progresser
        for (Enemy enemy : enemies) {
            enemy.draw(batch);
        }

        //Test si l'entier de la salve a été lancé
        if(!this.isGone() && enemyToStartIndex == enemies.size() -1){
            this.setGone(true);
        }
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

    public boolean isEnded() {
        return ended;
    }

    private void setEnded(boolean ended) {
        this.ended = ended;
    }

    public int getDelayBetweenEnemyInSalve() {
        return delayBetweenEnemyInSalve;
    }

    public void setDelayBetweenEnemyInSalve(int delayBetweenEnemyInSalve) {
        this.delayBetweenEnemyInSalve = delayBetweenEnemyInSalve;
    }

    public boolean collides(Player player) {
        boolean collides = false;
        float playerMinX = player.getX();
        float playerMaxX = playerMinX + player.getWidth();
        float playerMinY = player.getY();
        float playerMaxY = playerMinY + player.getHeight();

        for (Enemy enemy : enemies) {
            if (enemy.getX() < playerMaxX && enemy.getY() < playerMaxY &&
                    (enemy.getX() + enemy.getWidth()) > playerMinX && (enemy.getY() + enemy.getHeight()) > playerMinY) {
                collides = true;
            }
        }

        return collides;
    }

}
