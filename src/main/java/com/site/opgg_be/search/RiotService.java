package com.site.opgg_be.search;

import java.util.List;

public interface RiotService {
    String getPuuid(String summonerName);
    List<String> getMatchIds(String puuid);
    List<RiotDTO> getMatchDatas(List<String> matchIds, String summonerName);
    List<RiotDTO> getSummonerMatchData(String summonerName);
}