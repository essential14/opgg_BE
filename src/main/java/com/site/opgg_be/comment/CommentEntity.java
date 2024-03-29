package com.site.opgg_be.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//    CREATE TABLE comments (
//    cno INT AUTO_INCREMENT PRIMARY KEY,
//    bno INT NOT NULL,
//    Group_cno INT,
//    parent_cno INT NULL,
//    content VARCHAR(4000) NOT NULL,
//    id VARCHAR(30),
//    comment_date TIMESTAMP NOT NULL DEFAULT NOW(),
//    depth INT NOT NULL
//);
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {
    private int cno;
    private int bno;
    private int parent_cno;
    private String content;
    private String id;
    private int depth;
    private String created_date;
    private int order_cno;
    private int group_cno;

}
