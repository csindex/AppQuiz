package com.example.appquiz.classes;

public class Score {
    String email,date,score;

    public Score(){

    }

    public Score(String email, String date, String score) {
        this.email = email;
        this.date = date;
        this.score = score;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
