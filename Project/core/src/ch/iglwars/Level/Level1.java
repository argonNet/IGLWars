package ch.iglwars.Level;

import ch.iglwars.Utils.Constants;
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
        addDelayBetweenSalve(500);
        addSalve(SalveFactory.oneLineSalve(SmallEnemy.class,5,1000));
        addDelayBetweenSalve(1000);
        addSalve(SalveFactory.oneLineSalve(SmallEnemy.class,10,500));
        addDelayBetweenSalve(500);
        addSalve(SalveFactory.oneLineSalve(MediumEnemy.class,5,1000));
        addSalve(SalveFactory.oneLineSalve(SmallEnemy.class,5,2000));
        addDelayBetweenSalve(500);
        addSalve(SalveFactory.oneLineSalve(SmallEnemy.class,5,100));
        addDelayBetweenSalve(500);
        addSalve(SalveFactory.oneLineSalve(MediumEnemy.class,5,1000));
        addDelayBetweenSalve(1000);
        addSalve(SalveFactory.oneLineSalve(SmallEnemy.class,7,1000));
        addDelayBetweenSalve(1000);
        addSalve(SalveFactory.oneLineSalve(MediumEnemy.class,10,2000));
        addSalve(SalveFactory.oneLineSalve(SmallEnemy.class,5,2000));
        addDelayBetweenSalve(1000);
        addSalve(SalveFactory.randomBigEnemySalve(3));
        addDelayBetweenSalve(1000);
        addSalve(SalveFactory.oneLineSalve(MediumEnemy.class,5,1500));
        addSalve(SalveFactory.oneColumnSalve(SmallEnemy.class,100,6,1000));
        addDelayBetweenSalve(1000);
        addSalve(SalveFactory.oneLineSalve(MediumEnemy.class,5,1500));
        addSalve(SalveFactory.oneColumnSalve(SmallEnemy.class,200,6,1000));
        addDelayBetweenSalve(1000);
        addSalve(SalveFactory.oneLineSalve(MediumEnemy.class,5,1500));
        addSalve(SalveFactory.oneColumnSalve(SmallEnemy.class,300,6,1000));
        addDelayBetweenSalve(1000);
        addSalve(SalveFactory.oneLineSalve(MediumEnemy.class,5,1500));
        addSalve(SalveFactory.oneColumnSalve(SmallEnemy.class,350,6,1000));
        addDelayBetweenSalve(7000);
        addSalve(SalveFactory.oneEnemiSalve(BossLevel1.class, Constants.GAME_WIDTH / 2));
    }

}
