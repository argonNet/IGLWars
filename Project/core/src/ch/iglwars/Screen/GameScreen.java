package ch.iglwars.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ch.iglwars.Utils.Constants;
import ch.iglwars.IGLWars;
import ch.iglwars.Level.Level1;

/**
 * Created by vitel on 19.05.16.
 */
public class GameScreen implements Screen {

    final IGLWars game;
    private Viewport viewport;
    private OrthographicCamera camera;
    private Music music;

    private int score = 0;

    private Level1 level1;


    /**
     * Demande le nom du joueur à l'écran
     **/
    private String getPlayerName(){
        Skin skin = new Skin();
        Stage stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        Pixmap pixmap = new Pixmap(100, 100, Pixmap.Format.RGBA8888);
        skin.add("white", new Texture(pixmap));

        // Store the default libgdx font under the name "default".
        BitmapFont bfont = new BitmapFont();
        bfont.getData().setScale(6, 6);

        skin.add("default", bfont);

        TextField txtUsername = new TextField("",skin);
        txtUsername.setMessageText("test");
        txtUsername.setPosition(30, 30);


        return   txtUsername.getText();
    }

    public GameScreen(final IGLWars game) {

        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        viewport = new FitViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT, camera);
        music = Gdx.audio.newMusic(Gdx.files.internal("defense-line.mp3"));
        music.setLooping(true);

        level1 = new Level1();
    }

    /**
     * Gestion de resize du viewport (pour une meilleure gestion des différentes tailles d'écran)
     */
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    /**
     * Fonction de rendu des niveau de jeu.
     */
    @Override
    public void render (float delta) {
        //Fond noir par défaut
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        //Définition de la caméra (en 2D)
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        //début du mode de jeu
        game.getBatch().begin();

        if (level1.isEnded()) {
            game.setScreen(new GameOverScreen(game));

        }
        else {
            level1.Run(game.getBatch());
            score += level1.getScore();
            game.getFont().draw(game.getBatch(), "score = " + score, 0, Constants.GAME_HEIGHT - 20);
        }
        game.getBatch().end();
    }

    @Override
    public void show() {
        // Est appelée quand l'écran s'affiche
        music.play();
    }

    @Override
    public void hide() {
        // Est appelée quand on quitte l'écran
        music.stop();
    }

    @Override
    public void pause() {
        // Est appelée quand l'app est mise en pause (éteindre l'écran du tel, press home menu)
        music.pause();
    }

    @Override
    public void resume() {
        // Est appelée quand l'app est reprise après une pause
        music.play();
    }

    @Override
    public void dispose() {
        // TODO : dispose tous les objets necessaires
        music.dispose();
    }
}
