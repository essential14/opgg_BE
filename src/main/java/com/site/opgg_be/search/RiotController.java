package com.site.opgg_be.search;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/page/search")
    public List<RiotDTO> getSummonerMatchData(@RequestBody RiotReqDTO dto) {
        String summonerName = dto.getSummonerName();
        String puuid = riotService.getPuuid(summonerName);
        List<String> matchIds = riotService.getMatchIds(puuid);
        return riotService.getMatchDatas(matchIds, summonerName);
    }
}
