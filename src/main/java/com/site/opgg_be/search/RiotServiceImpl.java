package com.site.opgg_be.search;


import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service("riotService")
public class RiotServiceImpl implements RiotService {

    @Value("${riot.api-key}")
    private String API_KEY;
    private WebClient webClient;

    public RiotServiceImpl() {
        this.webClient = WebClient.builder()
                .baseUrl("https://kr.api.riotgames.com")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Riot " + API_KEY)
                .build();
    }

    @Override
    public String getPuuid(String summonerName) {
        String formattedName = summonerName.replaceAll(" ", "%20");
        String path = "/lol/summoner/v4/summoners/by-name/" + formattedName;

        JsonNode node = webClient.get()
                .uri(path)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        return node.path("puuid").asText();
    }

    public List<String> getMatchIds(String puuid) {
        String path = "/lol/match/v5/matches/by-puuid/" + puuid + "/ids?start=0&count=10";
        List<String> matchIds = webClient.get()
                .uri(path)
                .retrieve()
                .bodyToMono(List.class)
                .block();

        return matchIds;
    }

    public List<RiotDTO> getMatchDatas(List<String> matchIds) {
        List<RiotDTO> matchDataList = new ArrayList<>();
        WebClient webClient = WebClient.builder().baseUrl("https://asia.api.riotgames.com/").build();

        for (String matchId : matchIds) {
            String dataUrl = "/lol/match/v5/matches/" + matchId + "?api_key=" + API_KEY;
            JsonNode root = webClient.get()
                    .uri(dataUrl)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();

            RiotDTO matchData = new RiotDTO();
            matchData.setWinLose(root.path("winLoseField").asText());
            matchData.setKill(root.path("killField").asText());
            matchData.setDeath(root.path("deathField").asText());
            matchData.setAssist(root.path("assistField").asText());
            matchData.setChampionName(root.path("championNameField").asText());

            matchDataList.add(matchData);
        }

        return matchDataList;
    }

    @Override
    public List<RiotDTO> getSummonerMatchData(String summonerName) {
        String puuid = getPuuid(summonerName);
        List<String> matchIds = getMatchIds(puuid);
        return getMatchDatas(matchIds);
    }

}
