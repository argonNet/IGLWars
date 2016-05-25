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
    
    private Salve firstSalve;
    private Salve secondSalve;
    private Salve thirdSalve;

    private int nextEnemyToGo;
    private long lastGoTime;

    /**
     * Constructeur du niveau 1
     */
    public Level1(){

        addSalve(SalveFactory.One4EnemiesLineSalve(SmallEnemy.class));
        addDelayBetweenSalve(1000);
        addSalve(SalveFactory.One4EnemiesLineSalve(SmallEnemy.class));
        addDelayBetweenSalve(1000);
        addSalve(SalveFactory.One4EnemiesLineSalve(SmallEnemy.class));
        addDelayBetweenSalve(1000);
        addSalve(SalveFactory.One4EnemiesLineSalve(SmallEnemy.class));
        addDelayBetweenSalve(5000);
        addSalve(SalveFactory.One4EnemiesLineSalve(MediumEnemy.class));
        addDelayBetweenSalve(2000);
        addSalve(SalveFactory.One4EnemiesLineSalve(MediumEnemy.class));
        addDelayBetweenSalve(2000);
        addSalve(SalveFactory.OneColumnSalve(SmallEnemy.class,100,6,1000));
        addDelayBetweenSalve(2000);
        addSalve(SalveFactory.OneColumnSalve(SmallEnemy.class,300,6,1000));
    }

}
