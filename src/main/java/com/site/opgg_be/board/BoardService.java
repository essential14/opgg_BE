package com.site.opgg_be.board;
import java.io.IOException;
import java.util.List;


public interface BoardService {

    public void insertBoard(BoardFileDTO dto) throws IOException;
    public List<BoardFileDTO> getBoardList();
    public BoardFileDTO getBoardDetail(int bno);

}
