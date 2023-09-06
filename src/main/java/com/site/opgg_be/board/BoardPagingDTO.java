package com.site.opgg_be.board;

import lombok.Data;

import java.util.List;

@Data
public class BoardPagingDTO {
    private List<BoardFileDTO> lists;
    private Pagination pagination;
}