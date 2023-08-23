package com.site.opgg_be.board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.web.multipart.MultipartFile;
// CREATE TABLE files (
// `fno` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
// `bno` INT,
// `stored_file`  VARChar(200) NOT NULL,
// `org_file` VARCHAR(200) NOT NULL,
// FOREIGN KEY (bno) REFERENCES board(bno) ON DELETE CASCADE 종속 지양 보드가 파일을 참조하는 구조가 지향
// );


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileEntity {
    private int fno;
    private int bno;
    private String stored_file;
    private String org_file;
}
