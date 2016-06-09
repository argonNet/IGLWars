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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ch.iglwars.*;
import ch.iglwars.Utils.Constants;

/**
 * Ecran qui va informer l'utilisateur de sa défaite.
 */
public class GameOverScreen implements Screen {

    final IGLWars game;
    private Viewport viewport;
    private OrthographicCamera camera;

    //Bouton
    Skin skin;
    Stage stage;
    SpriteBatch batch;
    private Label.LabelStyle textLabelStyle;
    private TextButton.TextButtonStyle textButtonStyle;
    private TextField.TextFieldStyle textFieldStyle;


    private void addGameOverLabel(){

        Label lblGameOver = new Label("!! Game OVER !!",textLabelStyle);
        lblGameOver.setPosition(
                Gdx.graphics.getWidth()/2 - (lblGameOver.getWidth()/2),
                3*Gdx.graphics.getHeight()/4);

        stage.addActor(lblGameOver);
    }

    private void addUserNameTextFieldToStage(){
        TextField txtUsername = new TextField("",textFieldStyle);
        txtUsername.setWidth(Gdx.graphics.getWidth()/2);
        txtUsername.setPosition(
                Gdx.graphics.getWidth()/2 - (txtUsername.getWidth()/2),
                Gdx.graphics.getHeight()/2);

        stage.addActor(txtUsername);
    }

    public GameOverScreen(final IGLWars game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.GAME_HEIGHT, Constants.GAME_WIDTH);
        viewport = new FitViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT, camera);

        //Création des éléments graphiques
        createSkinButton();
        createReturnButton();
        addGameOverLabel();
        addUserNameTextFieldToStage();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();
        //titre
        game.getFont().draw(game.getBatch(), "Score",
                Gdx.graphics.getHeight() / 6, Gdx.graphics.getHeight() / 8);


        game.getFont().draw(game.getBatch(), "Game Over !",
                Constants.GAME_WIDTH/2, Constants.GAME_HEIGHT/2);

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
        bfont.getData().setScale(3, 3);

        skin.add("default", bfont);

        // Style pour TextButton, mis dans default.
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);


        textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.fontColor = Color.WHITE;
        textFieldStyle.font = skin.getFont("default");
        skin.add("default", textFieldStyle);

        textLabelStyle = new Label.LabelStyle();
        textLabelStyle.fontColor = Color.WHITE;
        textLabelStyle.font = skin.getFont("default");
        skin.add("default", textLabelStyle);
    }

    public void createReturnButton(){
        final TextButton returnButton=new TextButton("SAVE",textButtonStyle);
        returnButton.setPosition(Gdx.graphics.getWidth()/2 - (returnButton.getWidth()/2),
                Gdx.graphics.getHeight()/2 - Gdx.graphics.getHeight()/4);
        stage.addActor(returnButton);

        returnButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                //ICI FAIRE QUELQUE CHOSE POUR LA SAUVEGARDE
                game.setScreen(new MainMenuScreen(game));
            }
        });
    }
}