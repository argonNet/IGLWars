package ch.iglwars.Level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

import ch.iglwars.Enemy.BigEnemy;
import ch.iglwars.Enemy.Enemy;
import ch.iglwars.Enemy.MediumEnemy;
import ch.iglwars.Enemy.SmallEnemy;

/**
 * Classe qui représente le 1er niveau de jeu
 */
public class Level1 extends Level {

    private static final  int FIRST_SALVE_COUNT = 5;
    private static final  int SECOND_SALVE_COUNT = 5;

    private SmallEnemy[] firstSalve;
    private MediumEnemy[] secondSalve;
    private BigEnemy[] thirdSalve;

    private int nextEnemyToGo;
    private long lastGoTime;

    /**
     * Constructeur du niveau 1
     */
    public Level1(){
        lastGoTime = 0;


        firstSalve = new SmallEnemy[FIRST_SALVE_COUNT];
        for(int i = 0; i <  FIRST_SALVE_COUNT; i++){
            firstSalve[i] = new SmallEnemy(100);
        }

        secondSalve = new MediumEnemy[SECOND_SALVE_COUNT];
        for(int i = 0; i <  FIRST_SALVE_COUNT; i++){
            secondSalve[i] = new MediumEnemy(0);
        }


        thirdSalve = new BigEnemy[SECOND_SALVE_COUNT];
        for(int i = 0; i <  FIRST_SALVE_COUNT; i++){
            thirdSalve[i] = new BigEnemy(300);
        }

        nextEnemyToGo = 0;
    }

    /**
     * Lancement du niveau 1
     */
    @Override
    public void Run(SpriteBatch batch) {

        if(nextEnemyToGo < FIRST_SALVE_COUNT && TimeUtils.millis() - lastGoTime > 500){
            firstSalve[nextEnemyToGo].Start();
            secondSalve[nextEnemyToGo].Start();
            thirdSalve[nextEnemyToGo].Start();
            lastGoTime = TimeUtils.millis();
            nextEnemyToGo++;
        }

        for (SmallEnemy enemy : firstSalve) {
            enemy.draw(batch);
        }

        for (MediumEnemy enemy : secondSalve) {
            enemy.draw(batch);
        }

        for (BigEnemy enemy : thirdSalve) {
            enemy.draw(batch);
        }

    }

}
