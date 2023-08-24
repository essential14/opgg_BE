package com.site.opgg_be.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//  CREATE TABLE board (
//  `bno` INT NOT NULL AUTO_INCREMENT, 
// 	`fno` INT NOT NULL,
//  `title` VARCHAR(30) NOT NULL, 
//  `id` VARCHAR(30) NOT NULL, 
//  `content` VARCHAR(5000) NOT NULL, 
//  `viewcount` INT NOT NULL,
//  `created_date` TIMESTAMP NOT NULL, 
//  `updated_date` TIMESTAMP
//   PRIMARY KEY (`bno`));
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



}

