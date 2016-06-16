package ch.iglwars;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ch.iglwars.Score.Leaderboard;
import ch.iglwars.Utils.Preference.*;

public class IGLWars extends Game {

	private SpriteBatch batch;
	private BitmapFont font;

	private boolean music = true;

	/**
	Sert au preference
	*/
	private PreferenceManager preferenceManager;
	private MusicManager musicManager;

	/**
	 * Initialisation du jeu
	 */
	@Override
	public void create () {
		batch = new SpriteBatch();
		// Pour tester, on utilise la police LibGDX's par defaut : Arial font.
		font = new BitmapFont();
		this.setScreen(new ch.iglwars.Screen.MainMenuScreen(this));

		(new Leaderboard()).getScores();

		/**
		 * Sert au préférence
		 */
		// create the preference manager
		preferenceManager = new PreferenceManager();

		// create the music manager
		musicManager = new MusicManager();
		musicManager.setVolume(preferenceManager.getVolume() );
		musicManager.setEnabled(preferenceManager.isMusicEnabled() );
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

	// Services' getters
	public PreferenceManager getPreferenceManager(){
		return preferenceManager;
	}

	public MusicManager getMusicManager() {
		return musicManager;
	}
}
