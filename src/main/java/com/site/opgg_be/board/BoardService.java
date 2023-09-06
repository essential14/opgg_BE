package com.site.opgg_be.board;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface BoardService {


    public void insertBoard(BoardFileDTO dto, MultipartFile[] uploadfiles) throws IOException;
    public void updateBoard(BoardFileDTO dto, MultipartFile[] updatefiles);
    public int deleteBoard(BoardFileDTO dto);
    public BoardPagingDTO getBoardList(Pagination pagination);
    public BoardFileDTO getBoardDetail(int bno);

}
