package com.site.opgg_be.board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//CREATE TABLE files (
//        fno NUMBER NOT NULL PRIMARY KEY,  -- AUTO_INCREMENT 대신 시퀀스와 트리거를 사용합니다.
//        bno NUMBER,
//        stored_file VARCHAR2(200) NOT NULL,
//        org_file VARCHAR2(200) NOT NULL,
//        FOREIGN KEY (bno) REFERENCES board(bno) ON DELETE CASCADE
//        );
//
//        -- fno의 시퀀스와 트리거 생성
//        CREATE SEQUENCE files_fno_seq;
//
//        CREATE OR REPLACE TRIGGER files_fno_trigger
//        BEFORE INSERT ON files
//        FOR EACH ROW
//        BEGIN
//        SELECT files_fno_seq.NEXTVAL INTO :new.fno FROM dual;
//        END;
//        /


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileEntity {
    private int fno;
    private String stored_file;
    private String org_file;
    private int group_file;

}
