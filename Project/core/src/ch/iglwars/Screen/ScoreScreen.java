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

/**
 * Created by Esiskadi on 26.05.16.
 */

public class ScoreScreen implements Screen {

    Skin skin;
    Stage stage;

    IGLWars game;
    private Viewport viewport;
    private OrthographicCamera camera;

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
        Table table=new Table();
        table.setSize(800,480);
        table.setPosition(0, 400);

        TextField textField = new TextArea("Nom : Toto  Score : 2230", skin);
        table.add(textField).width(200).height(100);
        table.row();

        TextField textField2 = new TextArea("Nom : Toto  Score : 2120", skin);
        table.add(textField2).width(200).height(100);
        table.row();

        TextField textField3 = new TextArea("Nom : Toto  Score : 2000", skin);
        table.add(textField3).width(200).height(100);
        table.row();

        TextField textField4 = new TextArea("Nom : Jason  Score : 1600", skin);
        table.add(textField4).width(200).height(100);
        table.row();

        TextField textField5 = new TextArea("Nom : Momo  Score : 1400", skin);
        table.add(textField5).width(200).height(100);
        table.row();

        TextField textField6 = new TextArea("Nom : To  Score : 0", skin);
        table.add(textField6).width(200).height(100);
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