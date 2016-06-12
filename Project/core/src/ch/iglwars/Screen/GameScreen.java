package ch.iglwars.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import ch.iglwars.Parallax.ParallaxBackground;
import ch.iglwars.Parallax.ParallaxLayer;
import ch.iglwars.Parallax.TextureRegionParallaxLayer;
import ch.iglwars.Parallax.Utils;

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
    private OrthographicCamera backgroundCamera;
    private Music music;

    private int score = 0;

    private Level1 level1;
    //Permet de faire un mouvement automatique pour le fond avec optimisation
    private ParallaxBackground parallaxBackground;


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

    /**
     * Créer l'image de fond qui défilera tout au long du jeu
     * Utilisation de la librairie créée par Surahul:
     * https://github.com/surahul/ParallaxBackground-libgdx
     */
    private void createBackgroundLayers() {

        Texture landTexture = new Texture(Gdx.files.internal("backgroundLand.png"));
        TextureRegion land = new TextureRegion(landTexture);
        TextureRegionParallaxLayer landLayer = new TextureRegionParallaxLayer(land, Constants.GAME_HEIGHT, new Vector2(.3f,.3f), Utils.WH.height);
        landLayer.setTileModeY(ParallaxLayer.TileMode.repeat);

        Texture cloudTexture = new Texture(Gdx.files.internal("backgroundCloud.png"));
        TextureRegion cloud = new TextureRegion(cloudTexture);
        TextureRegionParallaxLayer cloudLayer = new TextureRegionParallaxLayer(cloud, Constants.GAME_HEIGHT * .6f, new Vector2(.9f,.9f), Utils.WH.height);
        cloudLayer.setTileModeY(ParallaxLayer.TileMode.repeat);
        cloudLayer.setPadBottom(Constants.GAME_HEIGHT*.1f);

        parallaxBackground = new ParallaxBackground();
        parallaxBackground.addLayers(landLayer,cloudLayer);


    }

    public GameScreen(final IGLWars game) {

        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        backgroundCamera = new OrthographicCamera();
        backgroundCamera.setToOrtho(false, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);

        viewport = new FitViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT, camera);
        createBackgroundLayers();
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
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        //Définition de la caméra (en 2D)
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        //début du mode de jeu
        game.getBatch().begin();

        //Définition de la caméra du background
        backgroundCamera.position.add(0, 1f, 0);
        backgroundCamera.update();
        parallaxBackground.draw(backgroundCamera, game.getBatch());

        //Définition de la caméra (en 2D)
        game.getBatch().setProjectionMatrix(camera.combined);
        camera.update();

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
