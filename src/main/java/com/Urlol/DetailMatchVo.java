package com.Urlol;

public class DetailMatchVo {
    private int que;
    private String lane;
    private int champlv, sp1, sp2, kill, death, assistant, item0, item1,item2,item3,item4,item5,item6;
    private  String purple1, purple2, purple3,purple4,purple5,blue1, blue2, blue3, blue4, blue5,Result;
    private String myCham,TeamChamp0,TeamChamp1,TeamChamp2,TeamChamp3,TeamChamp4,TeamChamp5,TeamChamp6,TeamChamp7,TeamChamp8,TeamChamp9;


    public DetailMatchVo(int item0, int item1, int item2, int item3, int item4, int item5, int item6, int que , String lane, int champlv, int sp1, int sp2, String purple1, String purple2, String purple3, String purple4, String purple5, String blue1, String blue2, String blue3, String blue4, String blue5, String result, String myCham, String teamChamp0, String teamChamp1, String teamChamp2, String teamChamp3, String teamChamp4, String teamChamp5, String teamChamp6, String teamChamp7, String teamChamp8, String teamChamp9, int kill, int death, int assistant) {
        this.item0=item0;
        this.item1=item1;
        this.item2=item2;
        this.item3=item3;
        this.item4=item4;
        this.item5=item5;
        this.item6=item6;
        this.kill=kill;
        this.que=que;
        this.lane=lane;
        this.death=death;
        this.assistant=assistant;
        this.champlv = champlv;
        this.sp1 = sp1;
        this.sp2 = sp2;
        this.purple1 = purple1;
        this.purple2 = purple2;
        this.purple3 = purple3;
        this.purple4 = purple4;
        this.purple5 = purple5;
        this.blue1 = blue1;
        this.blue2 = blue2;
        this.blue3 = blue3;
        this.blue4 = blue4;
        this.blue5 = blue5;
        Result = result;
        this.myCham = myCham;
        TeamChamp0 = teamChamp0;
        TeamChamp1 = teamChamp1;
        TeamChamp2 = teamChamp2;
        TeamChamp3 = teamChamp3;
        TeamChamp4 = teamChamp4;
        TeamChamp5 = teamChamp5;
        TeamChamp6 = teamChamp6;
        TeamChamp7 = teamChamp7;
        TeamChamp8 = teamChamp8;
        TeamChamp9 = teamChamp9;
    }

    public int getItem0() {
        return item0;
    }

    public int getItem1() {
        return item1;
    }

    public int getItem2() {
        return item2;
    }

    public int getItem3() {
        return item3;
    }

    public int getItem4() {
        return item4;
    }

    public int getItem5() {
        return item5;
    }

    public int getItem6() {
        return item6;
    }

    public int getQue() {
        return que;
    }

    public String getLane() {
        return lane;
    }

    public int getKill() {
        return kill;
    }

    public int getDeath() {
        return death;
    }

    public int getAssistant() {
        return assistant;
    }
    public int getChamplv() {
        return champlv;
    }

    public int getSp1() {
        return sp1;
    }

    public int getSp2() {
        return sp2;
    }

    public String getPurple1() {
        return purple1;
    }

    public String getPurple2() {
        return purple2;
    }

    public String getPurple3() {
        return purple3;
    }

    public String getPurple4() {
        return purple4;
    }

    public String getPurple5() {
        return purple5;
    }

    public String getBlue1() {
        return blue1;
    }

    public String getBlue2() {
        return blue2;
    }

    public String getBlue3() {
        return blue3;
    }

    public String getBlue4() {
        return blue4;
    }

    public String getBlue5() {
        return blue5;
    }

    public String getResult() {
        return Result;
    }

    public String getMyCham() {
        return myCham;
    }

    public String getTeamChamp0() {
        return TeamChamp0;
    }

    public String getTeamChamp1() {
        return TeamChamp1;
    }

    public String getTeamChamp2() {
        return TeamChamp2;
    }

    public String getTeamChamp3() {
        return TeamChamp3;
    }

    public String getTeamChamp4() {
        return TeamChamp4;
    }

    public String getTeamChamp5() {
        return TeamChamp5;
    }

    public String getTeamChamp6() {
        return TeamChamp6;
    }

    public String getTeamChamp7() {
        return TeamChamp7;
    }

    public String getTeamChamp8() {
        return TeamChamp8;
    }

    public String getTeamChamp9() {
        return TeamChamp9;
    }
}
