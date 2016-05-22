package ch.iglwars.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ch.iglwars.Constants;
import ch.iglwars.IGLWars;
import ch.iglwars.Level.Level1;
import ch.iglwars.TextureManager;

/**
 * Created by vitel on 19.05.16.
 */
public class GameScreen implements Screen {

    final IGLWars game;
    private Viewport viewport;
    private OrthographicCamera camera;

    private Level1 level1;

    public GameScreen(final IGLWars game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        viewport = new FitViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT, camera);

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
        level1.Run(game.getBatch());
        game.getBatch().end();
    }

    @Override
    public void show() {
        // Est appelée quand l'écran s'affiche
    }

    @Override
    public void hide() {
        // Est appelée quand on quitte l'écran
    }

    @Override
    public void pause() {
        // Est appelée quand l'app est mise en pause (éteindre l'écran du tel, press home menu)
    }

    @Override
    public void resume() {
        // Est appelée quand l'app est reprise après une pause
    }

    @Override
    public void dispose() {
        // TODO : dispose tous les objets necessaires
    }
}
