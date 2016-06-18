package ch.iglwars.Screen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import ch.iglwars.*;

/**
 * Created by Esiskadi on 26.05.16
 */
public class MainMenuScreen extends BaseScreen {
    public MainMenuScreen(IGLWars game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        // retrieve the default table actor
        Table table = super.getTable();
        table.add( "Welcome to IGLWars").spaceBottom( 50 );
        table.row();

        // Ajout du bouton Start
        TextButton startGameButton = new TextButton( "Start game", getSkin() );
        startGameButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
            }
        });
        table.add( startGameButton ).size( 300, 60 ).uniform().spaceBottom( 10 );
        table.row();

        // Ajout du bouton Option
        TextButton optionsButton = new TextButton( "Options", getSkin() );
        optionsButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                game.setScreen(new OptionScreen(game));
            }
        });
        table.add( optionsButton ).uniform().fill().spaceBottom( 10 );
        table.row();

        // Ajout du bouton Score
        TextButton scoresButton = new TextButton( "High Scores", getSkin() );
        scoresButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                game.setScreen(new ScoreScreen(game));
            }
        });
        table.add(scoresButton ).uniform().fill();
    }
}
