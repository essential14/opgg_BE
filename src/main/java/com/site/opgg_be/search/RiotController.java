package com.site.opgg_be.search;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class RiotController {
    private RiotService riotService;
    public RiotController(RiotService riotService) {
        this.riotService = riotService;
    }


    @GetMapping("/search/{summonerName}")
    public List<RiotDTO> getSummonerMatchData(@PathVariable String summonerName) {
        String puuid = riotService.getPuuid(summonerName);
        List<String> matchIds = riotService.getMatchIds(puuid);
        return riotService.getMatchDatas(matchIds);
    }
}
