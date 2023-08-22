package com.site.opgg_be.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardFileDTO {
    private int bno;
    private String title;
    private String id;
    private String content;
    private int viewcount;
    private String board_date;
    private int fno;
    private String org_file;
    private String stored_file;
    public BoardEntity toBoard() { // BoardEntity 에 Data 넣기
        BoardEntity board = new BoardEntity();
        board.setBno(this.getBno());
        board.setTitle(this.getTitle());
        board.setId(this.getId());
        board.setViewcount(this.getViewcount());
        board.setBoard_date(this.getBoard_date());
        return board;
    }
    public FileEntity toFile() { // FileEntity 에 Data 넣기
        FileEntity file = new FileEntity();
        file.setFno(this.getFno());
        file.setBno(this.getBno());
        file.setOrg_file(this.getOrg_file());
        file.setStored_file(this.getStored_file());
        return file;
    }
    public void fromBoard(BoardEntity board) { // BoardEntity 에서 Data 꺼내기
        this.setBno(board.getBno());
        this.setTitle(board.getTitle());
        this.setId(board.getId());
        this.setViewcount(board.getViewcount());
        this.setBoard_date(board.getBoard_date());

    }

    public void fromFile(FileEntity file) { // FileEntity 에서 Data 꺼내기
        this.setFno(file.getFno());
        this.setBno(file.getBno());
        this.setOrg_file(file.getOrg_file());
        this.setStored_file(file.getStored_file());

    }

}
