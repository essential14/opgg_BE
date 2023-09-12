package com.site.opgg_be.search;


import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;


@Service("riotService")
public class RiotServiceImpl implements RiotService {
    @Value("${riot.api-key}")
    private String API_KEY;

    private WebClient webClient;

    @Override
    public List<RiotDTO> getSummonerMatchData(String summonerName) {
        String puuid = getPuuid(summonerName);
        List<String> matchIds = getMatchIds(puuid);
        return getMatchDatas(matchIds, summonerName);
    }

    public RiotServiceImpl() {
        this.webClient = WebClient.builder()
                .baseUrl("https://kr.api.riotgames.com")
                .defaultHeader("X-Riot-Token", API_KEY)
                .build();
    }

    @Override
    public String getPuuid(String summonerName) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/lol/summoner/v4/summoners/by-name/{summonerName}?")
                .queryParam("api_key", API_KEY);

        JsonNode node = webClient.get()
                .uri(uriBuilder -> uriBuilder.path(((UriComponentsBuilder) builder).toUriString())
                        .build(summonerName))
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        System.out.println(node.toString());

        return node.path("puuid").asText();
    }

    public List<String> getMatchIds(String puuid) {
        WebClient webClient = WebClient.builder().baseUrl("https://asia.api.riotgames.com/").build();
        String path = "/lol/match/v5/matches/by-puuid/" + puuid + "/ids?start=0&count=10&api_key="+ API_KEY;
        List<String> matchIds = webClient.get()
                .uri(path)
                .retrieve()
                .bodyToMono(List.class)
                .block();

        return matchIds;
    }

    public List<RiotDTO> getMatchDatas(List<String> matchIds, String targetSummonerName) {
        List<RiotDTO> matchDataList = new ArrayList<>();
        WebClient webClient = WebClient.builder().baseUrl("https://asia.api.riotgames.com/").build();

        for (String matchId : matchIds) {
            String dataUrl = "/lol/match/v5/matches/" + matchId + "?api_key=" + API_KEY;
            JsonNode matchRoot = webClient.get()
                    .uri(dataUrl)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();

            JsonNode participants = matchRoot.path("info").path("participants");

            for (JsonNode playerNode : participants) { // 이름으로 나누기
                String summonerName = playerNode.path("summonerName").asText();

                if (targetSummonerName.trim().equals(summonerName.trim())) {

                    RiotDTO matchData = new RiotDTO();

                    matchData.setChampionName(playerNode.path("championName").asText());
                    matchData.setWin(playerNode.path("win").asBoolean());
                    matchData.setKill(playerNode.path("kills").asInt());
                    matchData.setDeath(playerNode.path("deaths").asInt());
                    matchData.setAssist(playerNode.path("assists").asInt());

                    matchDataList.add(matchData);
                    break;
                }
            }
        }

        return matchDataList;
    }

}
