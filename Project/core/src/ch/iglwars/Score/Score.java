package ch.iglwars.Score;

import java.util.Date;

/**
 * Représentation d'un score
 */
public class Score {

    private String name;
    private Date date;


    private int score;

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

}
