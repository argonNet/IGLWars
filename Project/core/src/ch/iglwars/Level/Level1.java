package ch.iglwars.Level;

import ch.iglwars.Constants;
import ch.iglwars.Enemy.BossLevel1;
import ch.iglwars.Enemy.MediumEnemy;
import ch.iglwars.Enemy.SmallEnemy;

/**
 * Classe qui représente le 1er niveau de jeu
 */
public class Level1 extends Level {

    /**
     * Constructeur du niveau 1
     */
    public Level1(){
        addDelayBetweenSalve(2000);
        addSalve(SalveFactory.randomBigEnemySalve(4));
        addDelayBetweenSalve(2000);
        addSalve(SalveFactory.one4EnemiesLineSalve(SmallEnemy.class));
        addDelayBetweenSalve(1000);
        addSalve(SalveFactory.one4EnemiesLineSalve(SmallEnemy.class));
        addDelayBetweenSalve(1000);
        addSalve(SalveFactory.one4EnemiesLineSalve(SmallEnemy.class));
        addDelayBetweenSalve(1000);
        addSalve(SalveFactory.one4EnemiesLineSalve(SmallEnemy.class));
        addDelayBetweenSalve(5000);
        addSalve(SalveFactory.one4EnemiesLineSalve(MediumEnemy.class));
        addDelayBetweenSalve(2000);
        addSalve(SalveFactory.one4EnemiesLineSalve(MediumEnemy.class));
        addDelayBetweenSalve(2000);
        addSalve(SalveFactory.oneColumnSalve(SmallEnemy.class,100,6,1000));
        addDelayBetweenSalve(2000);
        addSalve(SalveFactory.oneColumnSalve(SmallEnemy.class,300,6,1000));
        addDelayBetweenSalve(5000);
        addSalve(SalveFactory.oneEnemiSalve(BossLevel1.class, Constants.GAME_WIDTH / 2));
    }

}
