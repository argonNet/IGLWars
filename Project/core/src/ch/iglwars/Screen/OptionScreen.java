package ch.iglwars.Screen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.Locale;

import ch.iglwars.*;
import ch.iglwars.Utils.Preference.MusicManager;

/**
 * Created by Esiskadi on 26.05.16
 *
 * Sert à afficher les options (son et bouton pour supprimer les scores)
 */
public class OptionScreen extends BaseScreen {
    private Label volumeValue;

    public OptionScreen(IGLWars game){
        super( game );
    }

    @Override
    public void show() {
        super.show();

        // Table pour ajouter les boutons, etc
        Table table = super.getTable();
        table.defaults().spaceBottom( 30 );
        table.columnDefaults( 0 ).padRight( 20 );
        table.add( "Options" ).colspan( 3 );

        final CheckBox musicCheckbox = new CheckBox( "", getSkin() );
        musicCheckbox.setChecked( game.getPreferenceManager().isMusicEnabled() );
        musicCheckbox.addListener( new ChangeListener() {
            @Override
            public void changed(
                    ChangeEvent event,
                    Actor actor )
            {
                boolean enabled = musicCheckbox.isChecked();
                game.getPreferenceManager().setMusicEnabled( enabled );
                game.getMusicManager().setEnabled( enabled );

                // Si on active la musique, la joue.
                if( enabled ) game.getMusicManager().play(MusicManager.IGLWarsMusic.MENU );
            }
        } );
        table.row();
        table.add( "Music" );
        table.add( musicCheckbox ).colspan( 2 ).left();
        musicCheckbox.getCells().get(0).size(25, 25); //Augmentation de la taille

        Slider volumeSlider = new Slider( 0f, 1f, 0.1f, false, getSkin());
        volumeSlider.setValue( game.getPreferenceManager().getVolume() );
        volumeSlider.addListener( new ChangeListener() {
            @Override
            public void changed(
                    ChangeEvent event,
                    Actor actor )
            {
                float value = ( (Slider) actor ).getValue();
                game.getPreferenceManager().setVolume( value );
                game.getMusicManager().setVolume( value );
                updateVolumeLabel();
            }
        } );

        // create the volume label
        volumeValue = new Label( "", getSkin() );
        updateVolumeLabel();

        // add the volume row
        table.row();
        table.add( "Volume" );
        table.add( volumeSlider );
        table.add( volumeValue ).width( 40 );

        // register the back button
        TextButton backButton = new TextButton( "Back", getSkin() );
        backButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
        table.row();
        table.add( backButton ).size( 250, 60 ).colspan( 3 );
    }

    /**
     * Updates the volume label next to the slider.
     */
    private void updateVolumeLabel()
    {
        float volume = ( game.getPreferenceManager().getVolume() * 100 );
        volumeValue.setText( String.format( Locale.US, "%1.0f%%", volume ) );
    }
}