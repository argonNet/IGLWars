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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ch.iglwars.*;

/**
 * Created by Esiskadi on 26.05.16.
 */
public class OptionScreen implements Screen {

    final IGLWars game;
    private Viewport viewport;
    private OrthographicCamera camera;

    //Bouton
    Skin skin;
    Stage stage;
    SpriteBatch batch;
    private TextButton.TextButtonStyle textButtonStyle;

    public OptionScreen(final IGLWars game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        viewport = new FitViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT, camera);

        //Bouton
        createSkinButton();
        createReturnButton();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();
        //titre
        game.getFont().draw(game.getBatch(), "Option",
                Gdx.graphics.getHeight() / 6, Gdx.graphics.getHeight() / 8);

        //bouton
        stage.act(Math.min(Gdx.graphics.getDeltaTime(),1/30f));
        stage.draw();
        stage.setDebugAll(true);

        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
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
        skin.dispose();
    }

    public void createSkinButton() {
        batch = new SpriteBatch();
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        //Skin
        skin = new Skin();

        Pixmap pixmap = new Pixmap(100, 100, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLUE);
        pixmap.fill();

        skin.add("white", new Texture(pixmap));

        // Store the default libgdx font under the name "default".
        BitmapFont bfont = new BitmapFont();
        bfont.getData().setScale(6, 6);

        skin.add("default", bfont);

        // Style pour TextButton, mis dans default.
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
    }

    public void createReturnButton(){
        final TextButton returnButton=new TextButton("Retour",textButtonStyle);
        returnButton.setPosition(Gdx.graphics.getWidth()/2 - (returnButton.getWidth()/2),
                Gdx.graphics.getHeight()/2 - Gdx.graphics.getHeight()/4);
        stage.addActor(returnButton);

        returnButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
    }
}