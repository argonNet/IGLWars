package ch.iglwars.Screen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.text.SimpleDateFormat;

import ch.iglwars.*;
import ch.iglwars.Score.Leaderboard;
import ch.iglwars.Score.Score;
import ch.iglwars.Score.ScoreLoadingListener;

/**
 * Created by Esiskadi on 26.05.16
 */
public class ScoreScreen extends BaseScreen {
    private Leaderboard board;
    public ScoreScreen(IGLWars game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        // retrieve the default table actor
        final Table table = super.getTable();
       // table.getMin // (Constants.GAME_WIDTH);

        table.add("HighScore").spaceBottom( 50 ).colspan(3);
        table.row();


        board = new Leaderboard();
        board.addListener(new ScoreLoadingListener() {
            @Override
            public void scoreLoaded() {
                displayBoard(table);
            }
        });
        board.loadScores();

    }

    private void displayBoard(Table table){

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        for (Score score : board.getScores()) {

            table.add(new Label(score.getName(),getSkin())).padRight(50);
            table.add(new Label(format.format(score.getDate()),getSkin()));
            table.add(new Label(String.valueOf(score.getScore()),getSkin())).padLeft(50).right();

            table.row();
        }

        //Ajout du bouton Back à la fin du tableau
        TextButton scoresButton = new TextButton( "Back", getSkin() );
        scoresButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        table.add(scoresButton).uniform().fill().colspan(3).center().padTop(150);
    }
}
