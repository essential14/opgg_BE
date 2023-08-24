package com.site.opgg_be.board;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class BoardFileDTO {
    private int bno;
    private int fno;
    private String title;
    private String id;
    private String content;
    private int viewcount;
    private String created_date;
    private String updated_date;
    private String org_file;
    private String stored_file;

    public BoardEntity toBoard() { // BoardEntity 에 Data 넣기
        BoardEntity board = new BoardEntity();
        board.setBno(this.getBno());
        board.setFno(this.getFno());
        board.setTitle(this.getTitle());
        board.setId(this.getId());
        board.setContent(this.getContent());
        board.setViewcount(this.getViewcount());
        board.setCreated_date(this.getCreated_date());
        board.setUpdated_date(this.getUpdated_date());
        return board;
    }

    public FileEntity toFile() { // FileEntity 에 Data 넣기
        FileEntity files = new FileEntity();
        files.setFno(this.getFno());
        files.setOrg_file(this.getOrg_file());
        files.setStored_file(this.getStored_file());
        return files;
    }

    public void fromBoard(BoardEntity board) { // BoardEntity 에서 Data 꺼내기
        this.setBno(board.getBno());
        this.setBno(board.getFno());
        this.setTitle(board.getTitle());
        this.setId(board.getId());
        this.setContent(board.getContent());
        this.setViewcount(board.getViewcount());
        this.setCreated_date(board.getCreated_date());
        this.setUpdated_date(board.getUpdated_date());

    }

    public void fromFile(FileEntity files) { // FileEntity 에서 Data 꺼내기
        this.setFno(files.getFno());
        this.setOrg_file(files.getOrg_file());
        this.setStored_file(files.getStored_file());

    }

}
