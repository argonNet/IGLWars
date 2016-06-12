package ch.iglwars.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ch.iglwars.*;
import ch.iglwars.Score.Leaderboard;
import ch.iglwars.Score.Score;
import ch.iglwars.Score.ScoreLoadingListener;

/**
 * Created by Esiskadi on 26.05.16.
 */

public class ScoreScreen implements Screen {

    private Skin skin;
    private Stage stage;
    private Table table;

    private IGLWars game;
    private Viewport viewport;
    private OrthographicCamera camera;

    private Leaderboard board;

    public ScoreScreen(IGLWars pgame){
        this.game = pgame;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        viewport = new FitViewport(ch.iglwars.Utils.Constants.GAME_WIDTH,
                ch.iglwars.Utils.Constants.GAME_HEIGHT, camera);

        stage=new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin( Gdx.files.internal("ui/defaultskin.json"));

        //TO-DO : Une fois accès à la bd, faire une boucle
        table =new Table();
        table.setSize(800,480);
        table.setPosition(0, 400);

        board = new Leaderboard();
        board.addListener(new ScoreLoadingListener() {
            @Override
            public void scoreLoaded() {
                displayBoard();
            }
        });
        board.loadScores();


        final TextButton backButton=new TextButton("Back",skin);
        table.add(backButton).width(200).height(50);
        table.row();

        stage.addActor(table);

        backButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
    }

    private void displayBoard(){
        for (Score score : board.getScores()) {
            TextField textField = new TextArea(score.getName() + " " + score.getScore() + " " + score.getDate().toString(), skin);
            table.add(textField).width(200).height(100);
            table.row();
        }
    }


    @Override
    public void render(float delta) {
        // clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // let the stage act and draw
        stage.act(delta);
        stage.draw();
        viewport = new FitViewport(ch.iglwars.Utils.Constants.GAME_WIDTH, ch.iglwars.Utils.Constants.GAME_HEIGHT, camera);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
        stage.dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}