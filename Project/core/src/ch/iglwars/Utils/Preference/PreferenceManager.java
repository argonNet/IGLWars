package ch.iglwars.Utils.Preference;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Esiskadi on 16.06.2016.
 *
 * Sert à gérer les préférences (Volume et musique activée ou non)
 */
public class PreferenceManager {
    // constants
    private static final String PREF_VOLUME = "volume";
    private static final String PREF_MUSIC_ENABLED = "music.enabled";
    private static final String PREFS_NAME = "iglwars";
    private Preferences preferences;

    public PreferenceManager(){
    }

    protected Preferences getPrefs(){
        if(preferences==null){
            preferences = Gdx.app.getPreferences(PREFS_NAME);
        }
        return preferences;
    }

    public boolean isMusicEnabled() {
        //Si pas de valeur trouvée, true par défaut
        return getPrefs().getBoolean( PREF_MUSIC_ENABLED, true );
    }

    public void setMusicEnabled(boolean musicEnabled ){
        getPrefs().putBoolean( PREF_MUSIC_ENABLED, musicEnabled );
        getPrefs().flush();
    }

    public float getVolume(){
        // Si pas de valeur trouvée, 0.5f par défaut
        return getPrefs().getFloat( PREF_VOLUME, 0.5f );
    }

    public void setVolume(float volume ){
        getPrefs().putFloat( PREF_VOLUME, volume );
        getPrefs().flush();
    }
}