package ch.iglwars.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import ch.iglwars.IGLWars;
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

    //Police de taille 16
    private static boolean fontLoaded;
    protected static BitmapFont font16;

    public BaseScreen(IGLWars game ){
        this.game = game;
        this.stage = new Stage();
    }

    /**
     * Chargement de la pokice par défaut pour l'application
     */
    private static void loadFont(){
        if(!fontLoaded) {
            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("skin/Ayuthaya.ttf"));
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = 25;

            font16 = generator.generateFont(parameter);
            generator.dispose();


            fontLoaded = true;
        }
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

//            //Chargement des polices
//            loadFont();
//
//            //Ajout des polices générée
//            skin.remove("default-font",BitmapFont.class);
//            skin.add("default-font", font16, BitmapFont.class);
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
        // set the stage as the input processor
        Gdx.input.setInputProcessor( stage );
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

