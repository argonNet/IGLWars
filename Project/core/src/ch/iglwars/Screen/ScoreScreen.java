package ch.iglwars.Screen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import ch.iglwars.*;
import ch.iglwars.Score.Leaderboard;
import ch.iglwars.Score.Score;
import ch.iglwars.Score.ScoreLoadingListener;

/**
 * Created by Esiskadi on 26.05.16
 */
public class ScoreScreen extends AbstractScreen {
    private Leaderboard board;
    public ScoreScreen(IGLWars game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        // retrieve the default table actor
        final Table table = super.getTable();
        table.add( "HighScore").spaceBottom( 50 );
        table.row();

        board = new Leaderboard();
        board.addListener(new ScoreLoadingListener() {
            @Override
            public void scoreLoaded() {
                displayBoard(table);
            }
        });
        board.loadScores();


        // Ajout du bouton Back (to main menu)
        TextButton scoresButton = new TextButton( "Back", getSkin() );
        scoresButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
        table.add(scoresButton ).uniform().fill();
    }

    private void displayBoard(Table table){
        for (Score score : board.getScores()) {
            TextField textField = new TextArea(score.getName()
                    + " " + score.getScore() + " " + score.getDate().toString(), getSkin());
            table.add(textField).width(200).height(100);
            table.row();
        }
    }
}
