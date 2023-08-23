package com.site.opgg_be.board;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;


public interface BoardService {


public void insertBoard(BoardFileDTO dto, MultipartFile[] uploadfiles) throws IOException;
    public List<BoardFileDTO> getBoardList();
    public BoardFileDTO getBoardDetail(int bno);

}
