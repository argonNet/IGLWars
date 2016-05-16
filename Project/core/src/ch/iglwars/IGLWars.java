package ch.iglwars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ch.iglwars.Enemy.Enemy;
import ch.iglwars.Enemy.SmallEnemy;
import ch.iglwars.Level.Level1;

public class IGLWars extends ApplicationAdapter {

	private SpriteBatch batch;
    private Viewport viewport;
	private OrthographicCamera camera;

    private Level1 level1;

	/**
	 * Initialisation du jeu
	 */
	@Override
	public void create () {
		camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        viewport = new FitViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT,camera);

		batch = new SpriteBatch();

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
	public void render () {
		//Fond noir par défaut
        Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        //Définition de la caméra (en 2D)
		camera.update();
		batch.setProjectionMatrix(camera.combined);

        //début du mode de jeu
		batch.begin();
        level1.Run(batch);
		batch.end();
	}
}
