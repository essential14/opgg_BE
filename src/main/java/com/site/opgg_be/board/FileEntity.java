package com.site.opgg_be.board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// CREATE TABLE files (
// `fno` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
// `bno` INT,
// `stored_file`  VARChar(200) NOT NULL,
// `org_file` VARCHAR(200) NOT NULL,
// FOREIGN KEY (bno) REFERENCES board(bno) ON DELETE CASCADE
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
