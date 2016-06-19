package ch.iglwars.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import ch.iglwars.IGLWars;
import ch.iglwars.Utils.Preference.MusicManager;
/**
 * Created by Esiskadi on 16.06.2016.
 *
 * Base pour tous les menus
 */
public abstract class BaseScreen implements Screen {

    protected final IGLWars game;
    protected final Stage stage;

    private BitmapFont font;
    private SpriteBatch batch;
    private Skin skin;
    private TextureAtlas atlas;
    private Table table;
    private Texture backgroundTexture;
    private TextureRegion backgroundTextureRegion;

    public BaseScreen(IGLWars game ){
        this.game = game;
        this.stage = new Stage();
    }

    protected String getName()
    {
        return getClass().getSimpleName();
    }

    public SpriteBatch getBatch() {
        if( batch == null ) {
            batch = new SpriteBatch();
        }
        return batch;
    }

    protected Skin getSkin() {
        if( skin == null ) {
            FileHandle skinFile = Gdx.files.internal( "skin/defaultskin.json" );
            skin = new Skin( skinFile );

            //Aggrandissement de la taille de la font
            skin.getFont("default-font").getData().setScale(2);
        }

        return skin;
    }

    protected Table getTable() {
        if( table == null ) {
            table = new Table( getSkin() );
            table.setFillParent( true );

            stage.addActor( table );
        }
        return table;
    }

    // Screen implementation
    @Override
    public void show() {

        // load the texture with our image
        backgroundTexture = new Texture("backgroundMenu.png");

        // set the linear texture filter to improve the image stretching
        backgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        // Here we create a region of our texture whose size is 512x301
        backgroundTextureRegion = new TextureRegion(backgroundTexture, 0, 0, 220, 400);

        // set the stage as the input processor
        Gdx.input.setInputProcessor( stage );
        game.getMusicManager().play(MusicManager.IGLWarsMusic.MENU );
    }

    @Override
    public void resize(int width, int height ){}

    @Override
    public void render(float delta ) {
        // update des actors
        stage.act( delta );

        // clear the screen (black)
        Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        getBatch().begin();
            getBatch().draw( backgroundTextureRegion, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
        getBatch().end();

        // draw the actors
        stage.draw();

        // utile pour gérer l'affichage pour debug
        //stage.setDebugAll(true);
    }

    @Override
    public void hide(){
        dispose();
    }

    @Override
    public void pause(){
    }

    @Override
    public void resume(){
    }

    @Override
    public void dispose(){
        // as the collaborators are lazily loaded, they may be null
        if( font != null ) font.dispose();
        if( batch != null ) batch.dispose();
        if( skin != null ) skin.dispose();
        if( atlas != null ) atlas.dispose();
    }
}

