package ch.iglwars.Screen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.Date;

import ch.iglwars.*;
import ch.iglwars.Score.Leaderboard;
import ch.iglwars.Score.Score;

/**
 * Ecran qui va informer l'utilisateur de sa défaite.
 */
public class GameOverScreen extends BaseScreen {
    private int score;

    public GameOverScreen(IGLWars game, int score) {
        super(game);
        this.score = score;
    }

    @Override
    public void show() {
        super.show();

        // retrieve the default table actor
        Table table = super.getTable();
        table.add( "!! Game Over !!").spaceBottom( 50 );
        table.row();

        //affichage du score
        table.add("Score : " + String.valueOf(score) + "points").spaceBottom( 50 );
        table.row();

        final TextField nameTextField = new TextField("", getSkin());
        table.add(nameTextField).uniform().fill();
        table.row();
        table.add("").spaceBottom( 50 );
        table.row();

        // Ajout du bouton Back (to main menu)
        TextButton scoresButton = new TextButton( "Save", getSkin() );
        scoresButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                (new Leaderboard()).addNewScore(new Score(nameTextField.getText(),new Date(), score));
                game.setScreen(new MainMenuScreen(game));
            }
        });
        table.add(scoresButton ).uniform().fill();
    }
}
