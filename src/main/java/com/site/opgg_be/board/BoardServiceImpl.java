package com.site.opgg_be.board;

import org.springframework.stereotype.Service;
import java.util.List;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
    private BoardMapper mapper;

    public BoardServiceImpl(BoardMapper mapper) {
        this.mapper = mapper;
    }
    @Override
    public List<BoardDTO> getBoardList() {
        List<BoardDTO> boardList = mapper.getBoardList();
        return boardList;
    }

}
