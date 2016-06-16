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
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;


/**
 * Accès au leaderboard
 */
public class Leaderboard implements HttpResponseListener {

    private final static String URL = "http://www.softeo.ch/api.php/";
    private final static String SCORE_TABLE = "Scores";

    private final static String RECORDS = "records";

    private List<ScoreLoadingListener> scoreLoadingListeners = new ArrayList<ScoreLoadingListener>();

    private ArrayList<Score> scores;

    private URL url = null;
    private URLConnection conn = null;
    private String app_id;
    private String app_key;

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

    /**
     *
     * @param listener
     */
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
        Gdx.net.sendHttpRequest(httpGet,Leaderboard.this);
    }

    public void addNewScore(Score score){
        HttpRequest httpPost = new HttpRequest(HttpMethods.POST);
        httpPost.setUrl(URL + SCORE_TABLE);
        httpPost.setHeader("Content-Type", "application/json");
        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        httpPost.setContent(json.toJson(score));
        Gdx.net.sendHttpRequest(httpPost,Leaderboard.this);
    }


//
//    public void add_net_score(){
//        // LibGDX NET CLASS
//        HttpRequest httpPost = new HttpRequest(HttpMethods.POST);
//        httpPost.setUrl("https://api.parse.com/1/classes/score/");
//        httpPost.setHeader("Content-Type", "application/json");
//        httpPost.setHeader("X-Parse-Application-Id", app_id);
//        httpPost.setHeader("X-Parse-REST-API-Key", app_key);
//        httpPost.setContent("{\"score\": 1337, \"user\": \"CarelessLabs Java\"}");
//        Gdx.net.sendHttpRequest(httpPost,Parse.this);
//    }
//
//    public boolean add_score(){
//        // USING JAVA IO AND NET CLASS
//        try {
//            conn = url.openConnection();
//            conn.setDoOutput(true);
//            conn.setRequestProperty("X-Parse-Application-Id", app_id);
//            conn.setRequestProperty("X-Parse-REST-API-Key", app_key);
//            conn.setRequestProperty("Content-type", "application/json");
//            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
//            out.write("{\"score\": 1337, \"user\": \"CarelessLabs GDX\"}");
//
//            out.close();
//            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            String decodedString;
//            while ((decodedString = in.readLine()) != null) {
//                System.out.println(decodedString);
//            }
//            in.close();
//            return true;
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//    }



    @Override
    public void handleHttpResponse(HttpResponse httpResponse) {
        //final int statusCode = httpResponse.getStatus().getStatusCode();
        String textResponse = httpResponse.getResultAsString();
        fromJSONtoScores(textResponse);

        for (ScoreLoadingListener event : scoreLoadingListeners){
            event.scoreLoaded();
        }
    }



    @Override
    public void failed(Throwable t) {
        System.out.println(t.getMessage());
    }

    @Override
    public void cancelled() {
        //--
    }

    public ArrayList<Score> getScores(){
        return this.scores;
    }
}