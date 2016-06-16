package ch.iglwars.Utils.Preference;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Esiskadi on 16.06.2016.
 */
public class PreferenceManager {
    // constants
    private static final String PREF_VOLUME = "volume";
    private static final String PREF_MUSIC_ENABLED = "music.enabled";
    private static final String PREFS_NAME = "iglwars";

    public PreferenceManager() {}

    protected Preferences getPrefs() {
        return Gdx.app.getPreferences(PREFS_NAME);
    }

    public boolean isMusicEnabled() {
        return getPrefs().getBoolean(PREF_MUSIC_ENABLED, true);
    }

    public void setMusicEnabled(
            boolean musicEnabled) {
        getPrefs().putBoolean(PREF_MUSIC_ENABLED, musicEnabled);
        getPrefs().flush();
    }

    public float getVolume()
    {
        return getPrefs().getFloat( PREF_VOLUME, 0.5f );
    }

    public void setVolume(float volume ) {
        getPrefs().putFloat( PREF_VOLUME, volume );
        getPrefs().flush();
        System.out.println(volume + " | " + getPrefs().getFloat(PREF_VOLUME, 0.5f));
    }
}