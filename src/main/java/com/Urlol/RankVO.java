package com.Urlol;

public class RankVO {
    private String tier, rank;
    private int win, lose;

    public RankVO(String tier, String rank, int win, int lose) {
        this.tier = tier;
        this.rank = rank;
        this.win = win;
        this.lose = lose;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public String getTier() {
        return tier;
    }

    public String getRank() {
        return rank;
    }

    public int getWin() {
        return win;
    }

    public int getLose() {
        return lose;
    }
}
