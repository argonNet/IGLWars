package ch.iglwars.Utils.Preference;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by Esiskadi on 16.06.2016.
 *
 * Sert à gérer la music
 */
public class MusicManager implements Disposable {
    public enum IGLWarsMusic {
        MENU( "defense-line.mp3" );
        /** pour ajouter une seconde musique
         * LEVEL( "defense-line.mp3" );
         */
        private String fileName;
        private Music musicResource;

        private IGLWarsMusic(String fileName ) {
            this.fileName = fileName;
        }

        public String getFileName() {
            return fileName;
        }

        public Music getMusicResource() {
            return musicResource;
        }

        public void setMusicResource(Music musicBeingPlayed ){
            this.musicResource = musicBeingPlayed;
        }
    }

    /**
     Music en train d'être joué
     */
    private IGLWarsMusic musicBeingPlayed;

    /**
     * Volume
     */
    private float volume = 1f;

    /**
     * Musique activée ou non
     */
    private boolean enabled = true;

    public MusicManager()
    {
    }

    /**
     * Plays joue la musique
     * Si déjà jouée, ne coupe pas
     */
    public void play(IGLWarsMusic music ) {
        // Si musique activée ou non
        if( ! enabled ) return;

        // Si musique déjà en cours
        if( musicBeingPlayed == music ) return;

        stop();

        FileHandle musicFile = Gdx.files.internal( music.getFileName() );
        Music musicResource = Gdx.audio.newMusic( musicFile );
        musicResource.setVolume( volume );
        musicResource.setLooping( true );
        musicResource.play();

        musicBeingPlayed = music;
        musicBeingPlayed.setMusicResource( musicResource );
    }

    /**
     * Stops et disposes de la musique.
     */
    public void stop()
    {
        if( musicBeingPlayed != null ) {
            Music musicResource = musicBeingPlayed.getMusicResource();
            musicResource.stop();
            musicResource.dispose();
            musicBeingPlayed = null;
        }
    }

    /**
     * Définition du volume
     */
    public void setVolume(float volume ){
        // check and set the new volume
        if( volume < 0 || volume > 1f ) {
            throw new IllegalArgumentException( "The volume must be inside the range: [0,1]" );
        }
        this.volume = volume;

        // if there is a music being played, change its volume
        if( musicBeingPlayed != null ) {
            musicBeingPlayed.getMusicResource().setVolume( volume );
        }
    }

    /**
     * Active ou désactive la musique
     */
    public void setEnabled(boolean enabled ){
        this.enabled = enabled;

        if( ! enabled ) {
            stop();
        }
    }

    /**
     * Disposes du music manager.
     */
    public void dispose(){
        stop();
    }
}

