package com.Urlol;

public class MatchVo {
    private long gameId;
    private int que;
    private String lane;

    public MatchVo(long gameId, int que, String lane) {
        this.gameId = gameId;
        this.que = que;
        this.lane = lane;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public int getQue() {
        return que;
    }

    public void setQue(int que) {
        this.que = que;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }
}
