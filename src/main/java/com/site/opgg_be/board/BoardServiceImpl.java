package com.site.opgg_be.board;

import org.springframework.stereotype.Service;
import java.util.List;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
    private final BoardMapper mapper;

    public BoardServiceImpl(BoardMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<BoardFileDTO> getBoardList() {
        return mapper.getBoardList();
    }

    @Override
    public void insertBoard(BoardFileDTO dto) {
        BoardEntity board = dto.toBoard();
        mapper.insertBoard(board);

        FileEntity file = dto.toFile();
        mapper.insertFile(file);
    }

    @Override
    public BoardFileDTO getBoardDetail(int bno) {
        BoardEntity board = mapper.getBoard(bno);
        FileEntity file = mapper.getFile(bno);
        mapper.updateViewCount(bno);

        BoardFileDTO boardFile = new BoardFileDTO();
        boardFile.fromBoard(board);
        boardFile.fromFile(file);

        return boardFile;
    }
}