package ch.iglwars.Level;


import ch.iglwars.Utils.Constants;
import java.util.Random;
import ch.iglwars.Enemy.BigEnemy;
import ch.iglwars.Enemy.Enemy;
import ch.iglwars.Player.Player;

/**
 * Cette classe permet de généré des Salve. On peut la voir comme une définition des salves
 * existantes
 */
public class SalveFactory {

    /**
     * Crée une salve d'enemi en colonne
     * @param enemyClass type d'enemis a utiliser pour la salve
     * @param columnPositionX position X de la colonne a envoyer
     * @param quantity nombre d'enemis a envoyer dans la salve
     * @param delayBetweenEnemy Délai entre les différents enemis de la salve
     * @return La salve générée
     */
    public static Salve oneColumnSalve(Class enemyClass, float columnPositionX, int quantity, int delayBetweenEnemy){

        Salve salve = new Salve(delayBetweenEnemy);

        try {


            for(int i = 0; i < quantity;i++){
                Enemy enemy = (Enemy)enemyClass.newInstance();
                enemy.setX(columnPositionX);
                enemy.setY(Constants.GAME_HEIGHT);
                salve.addEnemy(enemy);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return salve;

    }

    /**
     * Crée une ligne d'ennemi.
     * @param enemyClass type d'enemis a utiliser pour la salve
     * @param quantity nombre d'enemis a envoyer dans la salve
     * @param delayBetweenEnemy Délai entre les différents ennemis de la salve
     * @return La salve générée
     */
    public static Salve oneLineSalve(Class enemyClass, int quantity, int delayBetweenEnemy){
        Salve salve = new Salve(delayBetweenEnemy);

        float firstPos = Constants.GAME_WIDTH / (quantity + 1);
        try {


            for(int i = 0; i < quantity;i++){
                Enemy enemy = (Enemy)enemyClass.newInstance();
                enemy.setX(firstPos * (i+1));
                enemy.setY(Constants.GAME_HEIGHT);
                salve.addEnemy(enemy);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return salve;
    }

    /**
     * Création d'une salve de gros énemis positionnié de manière aléatoire sur l'ecran
     * @param quantity nombre d'ennemis a positionner.
     * @return Salve générée
     */
    public static Salve randomBigEnemySalve(int quantity){
        Salve salve = new Salve(0);
        Random random = new Random(System.currentTimeMillis());

        float forbidden = Constants.GAME_HEIGHT / 2;
        try {

            for(int i = 0; i < quantity;i++){
                Enemy enemy = new BigEnemy();
                enemy.setX(0);
                enemy.setY(forbidden + random.nextInt(Constants.GAME_HEIGHT / 2 - (int)enemy.getHeight()));
                salve.addEnemy(enemy);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return salve;
    }

    /**
     * Création d'une salve d'un enemi.
     * @param enemyClass Type d'enemi à mettre dans la salve
     * @param posX Position X de l'enemi
     * @return Salve générée
     */
    public static Salve oneEnemiSalve(Class enemyClass, float posX){
        Salve salve = new Salve(0);

        float forbidden = Player.getInstance().getHeight() * 2;

        try {

            Enemy enemy = (Enemy)enemyClass.newInstance();
            enemy.setX(posX);
            enemy.setY(Constants.GAME_HEIGHT);
            salve.addEnemy(enemy);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return salve;
    }




}
