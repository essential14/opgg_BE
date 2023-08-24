package com.site.opgg_be.board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.web.multipart.MultipartFile;
// CREATE TABLE files (
// `fno` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
// `stored_file`  VARChar(200) NOT NULL,
// `org_file` VARCHAR(200) NOT NULL,
// );


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileEntity {
    private int fno;
    private String stored_file;
    private String org_file;
}
