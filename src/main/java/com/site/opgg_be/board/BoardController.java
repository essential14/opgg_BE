package com.site.opgg_be.board;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")

public class BoardController {
    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board/list") //게시글 리스트 조회
    public List<BoardDTO> getBoardList(){
        List<BoardDTO> boardlist = boardService.getBoardList();
        return boardlist;
    }
}
