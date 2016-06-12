package ch.iglwars;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class IGLWars extends Game {

	private SpriteBatch batch;
	private BitmapFont font;

	private boolean music = true;

	/**
	 * Initialisation du jeu
	 */
	@Override
	public void create () {
		batch = new SpriteBatch();
		// Pour tester, on utilise la police LibGDX's par defaut : Arial font.
		font = new BitmapFont();
		this.setScreen(new ch.iglwars.Screen.MainMenuScreen(this));
	}

	public void render() {
		super.render(); // important!
	}

	public void dispose() {
        super.dispose();
		batch.dispose();
		font.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public BitmapFont getFont() {
		return font;
	}

	public boolean isMusic() {
		return music;
	}

	public void setMusic(boolean music) {
		this.music = music;
	}
}
