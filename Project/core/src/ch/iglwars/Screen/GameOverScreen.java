package ch.iglwars.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Date;

import ch.iglwars.*;
import ch.iglwars.Player.Player;
import ch.iglwars.Score.Leaderboard;
import ch.iglwars.Score.Score;
import ch.iglwars.Utils.Constants;

/**
 * Ecran qui va informer l'utilisateur de sa défaite.
 */
public class GameOverScreen extends AbstractScreen {
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

        TextField nameTextField = new TextField("", getSkin());
        table.add(nameTextField).uniform().fill();
        table.row();
        table.add("").spaceBottom( 50 );
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
