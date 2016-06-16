package ch.iglwars.Screen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import ch.iglwars.*;

/**
 * Created by Esiskadi on 26.05.16
 */
public class ScoreScreen extends AbstractScreen {
    public ScoreScreen(IGLWars game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        // retrieve the default table actor
        Table table = super.getTable();
        table.add( "HighScore").spaceBottom( 50 );
        table.row();

        // Ajout du bouton Delete score (pour supprimer les scores à l'écran et raffraichir
        TextButton startGameButton = new TextButton( "Delete score", getSkin() );
        startGameButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                //TODO Del score and refresh
            }
        });
        table.add( startGameButton ).size( 300, 60 ).uniform().spaceBottom( 10 );
        table.row();

        // Ajout du bouton Back (to main menu)
        TextButton scoresButton = new TextButton( "Back", getSkin() );
        scoresButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
        table.add(scoresButton ).uniform().fill();
    }
}
