package com.Urlol;

import com.AllException.Response429Exception;

import java.util.List;

public class LolService {
    TargetVo targetVo;
    RankVO rankVO;
    UrlolDao urlolDao = new UrlolDao();

    public TargetVo Info(TargetReq targetReq) {

        targetVo = urlolDao.SummonerInfo(targetReq);

        if (targetVo.getProfileIconId() == 429) {
            System.out.println("1");
            throw new Response429Exception();
        }
        return new TargetVo(targetVo.getName(), targetVo.getSummonerId(), targetVo.getAccountId(), targetVo.getProfileIconId(), targetVo.getSummonerLevel());

    }
    public RankVO rank (TargetVo targetVo){
       RankVO rankVO= urlolDao.League(targetVo.getAccountId());

       if(rankVO.getWin()==429){
           System.out.println("2");
           throw new Response429Exception();
       }
       return new RankVO(rankVO.getTier(),rankVO.getRank(),rankVO.getWin(),rankVO.getLose());

    }
    public List<DetailMatchVo> MatchId(TargetVo targetVo) throws Response429Exception{
        List<MatchVo> match = urlolDao.matchId(targetVo.getSummonerId());
        List<DetailMatchVo> matches = urlolDao.DetailMatch(targetVo.getName(), match);
        return matches;
    }
}
