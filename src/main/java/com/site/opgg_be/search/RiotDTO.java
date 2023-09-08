package com.site.opgg_be.search;

import lombok.Data;

@Data
public class RiotDTO {
    private String winLose; // 승/패 정보
    private String kill; // KDA 정보
    private String death; // KDA 정보
    private String assist; // KDA 정보
    private String championName; //챔피언 이름
}
