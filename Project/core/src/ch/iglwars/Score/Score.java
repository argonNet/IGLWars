package ch.iglwars.Score;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import java.text.SimpleDateFormat;
import java.util.Date;

import ch.iglwars.Utils.Constants;

/**
 * Représentation d'un score
 */
public class Score implements Json.Serializable{

    private String name;
    private Date date;
    private int score;

    /**
     * Constructeur de la classe
     * @param name Nom du joueur à qui appartient le score
     * @param date Date à laquelle a été effectuée le score
     * @param score Valeur du score (plus c'est haut plus c'est beau)
     */
    public Score(String name, Date date, int score){
        this.name  = name;
        this.date  = date;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Permet de transformer l'objet courant au format Json.
     * @param json Objet json qui représente l'objet courant
     */
    @Override
    public void write(Json json) {
        json.writeValue("name",name);
        json.writeValue("scoreDate",(new SimpleDateFormat(Constants.DB_DATE_FORMAT)).format(date));
        json.writeValue("score", score);
    }

    /**
     * Non implémenté pour le moment
     */
    @Override
    public void read(Json json, JsonValue jsonData) {
        throw new java.lang.UnsupportedOperationException();
    }
}
