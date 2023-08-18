package com.site.opgg_be.member;
//CREATE TABLE member (
//        id VARCHAR(50) NOT NULL PRIMARY KEY,
//        name VARCHAR(30) NOT NULL,
//        birthday DATE NOT NULL,
//        password VARCHAR(250) NOT NULL
//        );

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private String id;
    private String name;
    private String birthday;
    private String password;
}
