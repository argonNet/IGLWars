package ch.iglwars.Level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Random;

import ch.iglwars.Constants;
import ch.iglwars.Enemy.BigEnemy;
import ch.iglwars.Enemy.Enemy;
import ch.iglwars.Enemy.MediumEnemy;
import ch.iglwars.Enemy.SmallEnemy;
import ch.iglwars.Player.Player;

/**
 * Classe qui représente le 1er niveau de jeu
 */
public class Level1 extends Level {

    private static final  int FIRST_SALVE_COUNT = 3;
    private static final  int SECOND_SALVE_COUNT = 6;
    private static final  int THIRD_SALVE_COUNT = 1;

    private Player player;
    private Salve firstSalve;
    private Salve secondSalve;
    private Salve thirdSalve;

    private int nextEnemyToGo;
    private long lastGoTime;

    /**
     * Constructeur du niveau 1
     */
    public Level1(){
        lastGoTime = 0;

        Random rand = new Random();

        firstSalve = new Salve();
        for(int i = 0; i <  FIRST_SALVE_COUNT; i++){
            firstSalve.addEnemy(new SmallEnemy(rand.nextInt(Constants.GAME_WIDTH - ((int) BigEnemy.WIDTH)), Constants.GAME_HEIGHT));
        }

        secondSalve = new Salve();
        for(int i = 0; i <  SECOND_SALVE_COUNT; i++){
            secondSalve.addEnemy(new MediumEnemy(0, Constants.GAME_HEIGHT));
        }


        thirdSalve = new Salve();
        for(int i = 0; i <  THIRD_SALVE_COUNT; i++){
            thirdSalve.addEnemy(new BigEnemy(rand.nextInt(Constants.GAME_WIDTH - ((int) BigEnemy.WIDTH)),
                    rand.nextInt(Constants.GAME_HEIGHT - ((int) BigEnemy.HEIGHT) * 3) + BigEnemy.HEIGHT * 2));
        }

        nextEnemyToGo = 0;

        player = new Player();
    }

    /**
     * Lancement du niveau 1
     */
    @Override
    public void Run(SpriteBatch batch) {

        if(TimeUtils.millis() - lastGoTime > 500){
            if(!firstSalve.isGone() && nextEnemyToGo < FIRST_SALVE_COUNT) {
                firstSalve.getEnemy(nextEnemyToGo).Start();

                lastGoTime = TimeUtils.millis();
                nextEnemyToGo++;
            }
            else if(!secondSalve.isGone() && nextEnemyToGo < SECOND_SALVE_COUNT) {
                secondSalve.getEnemy(nextEnemyToGo).Start();

                lastGoTime = TimeUtils.millis();
                nextEnemyToGo++;
            }
            else if(!thirdSalve.isGone() && nextEnemyToGo < THIRD_SALVE_COUNT) {
                thirdSalve.getEnemy(nextEnemyToGo).Start();

                lastGoTime = TimeUtils.millis();
                nextEnemyToGo++;
            }
            else {
                if(!firstSalve.isGone()) {
                    firstSalve.setGone(true);
                }
                else if(!secondSalve.isGone()) {
                    secondSalve.setGone(true);
                }
                else if(!thirdSalve.isGone()) {
                    thirdSalve.setGone(true);
                }
                nextEnemyToGo = 0;
            }
        }

        for (Enemy enemy : firstSalve.getEnemies()) {
            enemy.draw(batch);
        }

        for (Enemy enemy : secondSalve.getEnemies()) {
            enemy.draw(batch);
        }

        for (Enemy enemy : thirdSalve.getEnemies()) {
            enemy.draw(batch);
        }

        player.draw(batch);

    }

}
