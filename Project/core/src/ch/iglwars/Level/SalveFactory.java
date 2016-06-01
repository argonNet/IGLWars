package ch.iglwars.Level;


import ch.iglwars.Utils.Constants;
import ch.iglwars.Enemy.Enemy;

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
    public static Salve OneColumnSalve(Class enemyClass, float columnPositionX, int quantity, int delayBetweenEnemy){

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
     * @return La salve générée
     */
    public static Salve One4EnemiesLineSalve(Class enemyClass){
        Salve salve = new Salve(0);

        float firstPos = Constants.GAME_WIDTH / 5;
        try {


            for(int i = 0; i < 4;i++){
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

}
