package com.site.opgg_be.board;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardController {
    private BoardService boardService;
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @PostMapping("/board/write")
    public void insertBoard(@ModelAttribute BoardFileDTO dto) throws IOException { // 한 객체로 formData 처리 하려면 Model 써야 함
        boardService.insertBoard(dto);
    }

    @GetMapping("/board/list")
    public List<BoardFileDTO> getBoardList(){
        List<BoardFileDTO> boardlist = boardService.getBoardList();
        return boardlist;
    }

    @GetMapping("/board/{bno}")
    public BoardFileDTO getBoardDetail(@PathVariable int bno) {
        BoardFileDTO boardFile = boardService.getBoardDetail(bno);
        return boardFile;
    }
}
