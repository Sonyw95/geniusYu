package com.Urlol;

import com.AllException.Response429Exception;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UrlolDao {
    private final String api_key = "RGAPI-c4fa04aa-c391-4716-a383-b0329d043d7a";

    public TargetVo SummonerInfo(TargetReq targetReq) {
        StringBuffer sb = new StringBuffer("https://kr.api.riotgames.com/lol/");
        sb.append("summoner/v4/summoners/by-name/");
        sb.append(targetReq.getName());
        sb.append("?api_key=");
        sb.append(api_key);
        TargetVo targetVo = null;
        try {
            URL url = new URL(sb.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            JsonParser jp = new JsonParser();
            JsonObject jo = (JsonObject) jp.parse(result);
            String name = jo.get("name").getAsString();
            String summonerId = jo.get("id").getAsString();
            String accountId = jo.get("accountId").getAsString();
            int profileIconId = jo.get("profileIconId").getAsInt();
            long summonerLevel = jo.get("summonerLevel").getAsLong();


            targetVo = (new TargetVo(name, accountId, summonerId, profileIconId, summonerLevel));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sb.delete(2, sb.length());
        return targetVo;
    }

    public RankVO League(String str) {
        StringBuffer sb = new StringBuffer("https://kr.api.riotgames.com/lol/");
        RankVO rankVO = null;
        sb.append("league/v4/entries/by-summoner/");
        sb.append(str);
        sb.append("?api_key=" + api_key);

        String tier = "";
        String rank = "";
        int win = 0;
        int lose = 0;

        try {
            URL url = new URL(sb.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 429) {
                rankVO.setWin(429);
                return rankVO;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            JsonParser jp = new JsonParser();
            JsonArray arr = (JsonArray) jp.parse(result);

            if (arr.size() == 0) {
                tier = "UnRanked";
                rank = "0";
            } else {
                JsonObject jo = (JsonObject) arr.get(0);
                tier = jo.get("tier").getAsString();
                rank = jo.get("rank").getAsString();
                lose = jo.get("losses").getAsInt();
                win = jo.get("wins").getAsInt();
            }

            rankVO = (new RankVO(rank, tier, win, lose));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return rankVO;
    }

    public List<MatchVo> matchId(String id) {
        List<MatchVo> match = new ArrayList<>();
        StringBuffer sb = new StringBuffer("https://kr.api.riotgames.com/lol/");
        sb.append("match/v4/matchlists/by-account/");
        sb.append(id);
        sb.append("?api_key=" + api_key);

        try {
            URL url = new URL(sb.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            System.out.println("id: " + conn.getResponseCode());
            if (conn.getResponseCode() == 429) {
                throw new Response429Exception();
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            String result = "";


            while ((line = br.readLine()) != null) {
                result += line;
            }

            JsonParser jp = new JsonParser();
            JsonObject jo = (JsonObject) jp.parse(result);
            JsonArray arr = (JsonArray) jo.get("matches");
            for (int i = 0; i < 10; i++) {
                JsonObject joMatch = (JsonObject) arr.get(i);
                long gameId = joMatch.get("gameId").getAsLong();
                int queue = joMatch.get("queue").getAsInt();
                String lane = joMatch.get("lane").getAsString();
                match.add(new MatchVo(gameId, queue, lane));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return match;
    }

    public List<DetailMatchVo> DetailMatch(String str, List<MatchVo> list) {

        List<DetailMatchVo> detail_list = new ArrayList<>();
        String TeamChamp[] = new String[10];

        for (MatchVo a : list) {
            long lo = a.getGameId();
            try {
                StringBuffer sb = new StringBuffer("https://kr.api.riotgames.com/lol/");
                sb.append("match/v4/matches/");
                sb.append(lo);
                sb.append("?api_key=" + api_key);
                URL url = new URL(sb.toString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                if (conn.getResponseCode() == 429) {
                    Thread.sleep(2000);
                    sb = new StringBuffer("https://kr.api.riotgames.com/lol/");
                    sb.append("match/v4/matches/");
                    sb.append(lo);
                    sb.append("?api_key=" + api_key);
                    url = new URL(sb.toString());
                    conn = (HttpURLConnection) url.openConnection();
                }
                System.out.println("list" + conn.getResponseCode());
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String line;
                String result = "";

                while ((line = br.readLine()) != null) {
                    result += line;
                }

                JsonParser jp = new JsonParser();
                JsonObject jo = (JsonObject) jp.parse(result);
                JsonArray TeamArr = (JsonArray) jo.get("participantIdentities");
                JsonArray WinArr = (JsonArray) jo.get("participants");
                String myChamp = "";
                String team[] = new String[10];
                int[] item = new int[7];
                int kill = 0;
                int champLv = 0;
                int spell1Id = 0;
                int spell2Id = 0;
                int death = 0;
                int assistant = 0;
                String WinResult = "";

                for (int j = 0; j < 10; j++) {
                    JsonObject tmp = (JsonObject) TeamArr.get(j);
                    JsonObject jo2 = (JsonObject) tmp.get("player");
                    JsonObject WinR = (JsonObject) WinArr.get(j);
                    JsonObject Wins = (JsonObject) WinR.get("stats");
                    if (jo2.get("summonerName").getAsString().equals(str)) {
                        myChamp = ChageChampionNumber(WinR.get("championId").getAsInt());
                        if (Wins.get("participantId").getAsInt() == tmp.get("participantId").getAsInt()) {
                            WinResult = Wins.get("win").getAsString();
                            kill = Wins.get("kills").getAsInt();
                            champLv = Wins.get("champLevel").getAsInt();
                            spell1Id = WinR.get("spell1Id").getAsInt();
                            spell2Id = WinR.get("spell2Id").getAsInt();
                            death = Wins.get("deaths").getAsInt();
                            assistant = Wins.get("assists").getAsInt();
                            for (int i = 0; i < 7; i++) {
                                item[i] = Wins.get("item" + i).getAsInt();
                            }
                        }
                    }
                    if (j < 5) {
                        team[j] = jo2.get("summonerName").getAsString();
                        TeamChamp[j] = ChageChampionNumber(WinR.get("championId").getAsInt());
                    } else {
                        team[j] = jo2.get("summonerName").getAsString();
                        TeamChamp[j] = ChageChampionNumber(WinR.get("championId").getAsInt());
                    }
                }
                detail_list.add(new DetailMatchVo(item[0], item[1], item[2], item[3], item[4], item[5], item[6], a.getQue(), a.getLane(), champLv, spell1Id, spell2Id, team[0], team[1], team[2], team[3], team[4], team[5], team[6], team[7], team[8], team[9], WinResult, myChamp, TeamChamp[0], TeamChamp[1], TeamChamp[2], TeamChamp[3], TeamChamp[4], TeamChamp[5], TeamChamp[6], TeamChamp[7], TeamChamp[8], TeamChamp[9], kill, death, assistant));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return detail_list;
    }

    public String ChageChampionNumber(Object num) {
        String ChampionName = "";
        String HOST = "http://ddragon.leagueoflegends.com/cdn/10.12.1/data/ko_KR/champion.json";
        String all_champ[] = {"Aatrox", "Ahri", "Akali", "Alistar", "Amumu", "Anivia", "Annie", "Aphelios", "Ashe",
                "AurelionSol", "Azir", "Bard", "Blitzcrank", "Brand", "Braum", "Caitlyn", "Camille", "Cassiopeia",
                "Chogath", "Corki", "Darius", "Diana", "Draven", "DrMundo", "Ekko", "Elise", "Evelynn", "Ezreal",
                "Fiddlesticks", "Fiora", "Fizz", "Galio", "Gangplank", "Garen", "Gnar", "Gragas", "Graves",
                "Hecarim", "Illaoi", "Irelia", "Ivern", "Janna", "JarvanIV", "Jax", "Jayce", "Jhin", "Jinx",
                "Kaisa", "Kalista", "Karma", "Karthus", "Kassadin", "Katarina", "Kayle", "Kayn", "Kennen", "Khazix",
                "Kindred", "Kled", "KogMaw", "Leblanc", "LeeSin", "Leona", "Lissandra", "Lucian", "Lulu", "Lux",
                "Malphite", "Malzahar", "Maokai", "MasterYi", "MissFortune", "MonkeyKing", "Mordekaiser", "Morgana",
                "Nami", "Nasus", "Nautilus", "Neeko", "Nidalee", "Nocturne", "Nunu", "Olaf", "Orianna", "Ornn",
                "Pantheon", "Poppy", "Pyke", "Qiyana", "Quinn", "Rakan", "Rammus", "RekSai", "Renekton", "Rengar",
                "Riven", "Rumble", "Ryze", "Sejuani", "Senna", "Sett", "Shaco", "Shen", "Shyvana", "Singed", "Sion",
                "Sivir", "Skarner", "Sona", "Soraka", "Swain", "Sylas", "Syndra", "TahmKench", "Taliyah", "Talon",
                "Taric", "Teemo", "Thresh", "Tristana", "Trundle", "Tryndamere", "TwistedFate", "Twitch", "Udyr",
                "Urgot", "Varus", "Vayne", "Veigar", "Velkoz", "Vi", "Viktor", "Vladimir", "Volibear", "Warwick",
                "Xayah", "Xerath", "XinZhao", "Yasuo", "Yuumi", "Yorick", "Zac", "Zed", "Ziggs", "Zilean", "Zoe",
                "Zyra"};
        try {
            URL url = new URL(HOST);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            JsonParser jp = new JsonParser();
            JsonObject jo = (JsonObject) jp.parse(result);
            int size = all_champ.length;
            jo = jo.get("data").getAsJsonObject();
            for (int i = 0; i < size; i++) {
                JsonObject ChampionObj = jo.get(all_champ[i]).getAsJsonObject();
                if (Integer.parseInt(num.toString()) == ChampionObj.get("key").getAsInt()) {
                    ChampionName = all_champ[i];
                } else {
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ChampionName;
    }

}