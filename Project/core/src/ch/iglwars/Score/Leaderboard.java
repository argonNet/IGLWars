package ch.iglwars.Score;


import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.sun.media.jfxmedia.logging.Logger;

import ch.iglwars.Exception.LoadScoreException;
import ch.iglwars.Exception.SaveScoreException;

/**
 * Accès au leaderboard sur le web.
 */
public class Leaderboard implements HttpResponseListener {

    private final static int SUCCESSFUL_REQUEST = 200;

    private final static int REQUEST_LOAD_SCORES = 1000;
    private final static int REQUEST_ADD_SCORE = 2000;

    private final static String URL = "http://www.softeo.ch/api.php/";
    private final static String SCORE_TABLE = "Scores";

    private final static String RECORDS = "records";

    private List<ScoreLoadingListener> scoreLoadingListeners = new ArrayList<ScoreLoadingListener>();

    private ArrayList<Score> scores;

    private int currentRequest = 0;

    /**
     * Chargement des données au formats Json (texte) à des données objets
     * @param json Json d'entrée
     */
    private void fromJSONtoScores(String json){
        JsonValue root = (new JsonReader()).parse(json);
        JsonValue records =  root.get(SCORE_TABLE).get(RECORDS);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        scores = new ArrayList<Score>();

        for (JsonValue val: records) {
            String[] strligne = val.asStringArray();
            Date date;
            try{
                date = df.parse(strligne[2]);
            }catch(ParseException e){
                date = new Date();
                date.setTime(0);
            }
            scores.add(new Score(strligne[1],date, Integer.parseInt(strligne[3])));
        }
    }

    public void addListener(ScoreLoadingListener listener) {
        scoreLoadingListeners.add(listener);
    }

    /**
     * Récupère les scores du tableau
     */
    public void loadScores(){
        HttpRequest httpGet = new HttpRequest(HttpMethods.GET);
        httpGet.setUrl(URL + SCORE_TABLE);
        httpGet.setHeader("Content-Type", "application/json");

        currentRequest = REQUEST_LOAD_SCORES;
        Gdx.net.sendHttpRequest(httpGet,Leaderboard.this);
    }

    /**
     * Ajout d'un nouveau score
     * @param score Score à ajouter sur le serveur.
     */
    public void addNewScore(Score score){
        HttpRequest httpPost = new HttpRequest(HttpMethods.POST);
        httpPost.setUrl(URL + SCORE_TABLE);
        httpPost.setHeader("Content-Type", "application/json");
        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        httpPost.setContent(json.toJson(score));

        currentRequest = REQUEST_ADD_SCORE;
        Gdx.net.sendHttpRequest(httpPost,Leaderboard.this);
    }


    @Override
    public void handleHttpResponse(HttpResponse httpResponse){

        try {
            final int statusCode = httpResponse.getStatus().getStatusCode();

            switch (currentRequest) {
                case REQUEST_ADD_SCORE:
                    if (statusCode != SUCCESSFUL_REQUEST) {
                        throw new SaveScoreException();
                    }
                    break;

                case REQUEST_LOAD_SCORES:

                    if (statusCode != SUCCESSFUL_REQUEST) {
                        throw new LoadScoreException();
                    }

                    String textResponse = httpResponse.getResultAsString();
                    fromJSONtoScores(textResponse);

                    for (ScoreLoadingListener event : scoreLoadingListeners) {
                        event.scoreLoaded();
                    }
                    break;
            }
        }catch (Exception e) {
            Logger.logMsg(Logger.ERROR,e.getMessage());
        }
    }

    @Override
    public void failed(Throwable t) {
        Logger.logMsg(Logger.ERROR,t.getMessage());
    }

    @Override
    public void cancelled() {}

    public ArrayList<Score> getScores(){
        return this.scores;
    }
}