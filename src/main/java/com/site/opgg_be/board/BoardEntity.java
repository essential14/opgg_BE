package com.site.opgg_be.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//  CREATE TABLE board (
//  `bno` INT NOT NULL AUTO_INCREMENT, // 게시글 번호
//  `title` VARCHAR(30) NOT NULL, // 제목
//  `id` VARCHAR(30) NOT NULL, // 글쓴이
//  `content` VARCHAR(5000) NOT NULL, // 내용
//  `viewcount` INT NOT NULL, // 조회수
//  `board_date` TIMESTAMP NOT NULL, // 작성일
//   PRIMARY KEY (`bno`));
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardEntity {
    private int bno;
    private String title;
    private String id;
    private String content;
    private int viewcount;
    private String board_date;


}

