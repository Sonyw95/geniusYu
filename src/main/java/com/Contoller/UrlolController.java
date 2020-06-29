package com.Contoller;

import com.AllException.Response429Exception;
import com.Urlol.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UrlolController {
    private LolService lolService = new LolService();
    List<DetailMatchVo> MatchList = new ArrayList<>();
    private TargetVo targetVo;
    private RankVO rankVO;

    @RequestMapping("/Urlol/UrlolForm")
    public String UrlolForm(TargetReq targetReq) {
        return "Urlol/UrlolForm";
    }

    @RequestMapping("/UrlolForm")
    public String redirectForm(TargetReq targetReq) {
        return "Urlol/UrlolForm";
    }

    @RequestMapping("/Urlol/MatchHistory")
    public String MatchHistory(@Valid TargetReq targetReq,Errors errors,Model model) {

        if (errors.hasErrors()) {
            return "Urlol/UrlolForm";
        }
        try {
            targetVo = lolService.Info(targetReq);
            model.addAttribute("Info", targetVo);

            rankVO = lolService.rank(targetVo);
            model.addAttribute("Rank", rankVO);

            MatchList = lolService.MatchId(targetVo);
            model.addAttribute("List", MatchList);



            return "Urlol/MatchHistory";
        } catch (Response429Exception e) {
            errors.rejectValue("name", "429");
            return "Urlol/UrlolForm";
        }
    }

}
