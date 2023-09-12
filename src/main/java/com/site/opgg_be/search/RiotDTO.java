package com.site.opgg_be.search;

import lombok.Data;

@Data
public class RiotDTO {
    private boolean win; // 승/패 정보
    private int kill; // KDA 정보
    private int death; // KDA 정보
    private int assist; // KDA 정보
    private String championName; //챔피언 이름
}
