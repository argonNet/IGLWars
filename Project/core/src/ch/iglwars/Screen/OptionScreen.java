package ch.iglwars.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ch.iglwars.*;

/**
 * Created by Esiskadi on 26.05.16.
 */

public class OptionScreen implements Screen {

    Skin skin;
    Stage stage;

    IGLWars game;
    private Viewport viewport;
    private OrthographicCamera camera;

    public OptionScreen(IGLWars pgame){
        this.game = pgame;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        viewport = new FitViewport(ch.iglwars.Utils.Constants.GAME_WIDTH,
                ch.iglwars.Utils.Constants.GAME_HEIGHT, camera);

        stage=new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin( Gdx.files.internal("ui/defaultskin.json"));

        Table table=new Table();
        table.setSize(800,480);

        //Pour ligne invisible (séparation)
        final Button.ButtonStyle bStyle = new Button.ButtonStyle();
        final Button l = new Button(bStyle);

        CheckBox boxMusic=new CheckBox("Music",skin);
        table.add(boxMusic).width(100);
        table.row();
        boxMusic.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setMusic(((CheckBox)actor).isChecked());
            }
        });

        table.add(l).size(100,20);
        table.row();

        CheckBox boxSoundEffect=new CheckBox("SoundEffect",skin);
        table.add(boxSoundEffect).width(100);
        table.row();

        table.add(l).size(100,20);
        table.row();

        final TextButton delScoreButton=new TextButton("Reset score",skin);
        table.add(delScoreButton).width(200).height(50);
        table.row();

        table.add(l).size(100,20);
        table.row();

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