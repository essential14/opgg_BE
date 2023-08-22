package com.site.opgg_be.board;
import java.util.List;


public interface BoardService {

    public void insertBoard(BoardFileDTO dto);
    public List<BoardFileDTO> getBoardList();
    public BoardFileDTO getBoardDetail(int bno);

}
