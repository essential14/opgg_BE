package com.site.opgg_be.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardEntity {

    private int bno;
    private int fno;
    private String title;
    private String id;
    private String content;
    private int viewcount;
    private String created_date;
    private String updated_date;
    private int group_file;



}



