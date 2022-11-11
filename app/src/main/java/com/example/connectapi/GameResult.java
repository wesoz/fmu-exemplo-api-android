package com.example.connectapi;

public class GameResult {
    private double draw;
    private int games;
    private double loose;
    private double win;

    public double getDraw() {
        return draw;
    }

    public void setDraw(double draw) {
        this.draw = draw;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public double getLoose() {
        return loose;
    }

    public void setLoose(double loose) {
        this.loose = loose;
    }

    public double getWin() {
        return win;
    }

    public void setWin(double win) {
        this.win = win;
    }

    public String toString() {
        return "Draw: " + String.valueOf(this.draw) + ", Games: " + String.valueOf(this.games) + ", Loose: " + String.valueOf(this.loose) + ", Win: " + String.valueOf(this.win);
    }
}
