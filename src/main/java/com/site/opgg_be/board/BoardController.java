package com.site.opgg_be.board;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class BoardController {
    private BoardService boardService;
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @PostMapping("/board/write")
    public void insertBoard(BoardFileDTO dto, @RequestPart(name = "uploadfiles", required = false) MultipartFile[] uploadfiles ) throws IOException {
        boardService.insertBoard(dto, uploadfiles);
    }

    @PostMapping("/board/update")
    public void updateBoard(BoardFileDTO dto, @RequestPart(name = "updatefiles", required = false) MultipartFile[] updatefiles ) {
        boardService.updateBoard(dto, updatefiles);
    }

    @PostMapping("/board/delete")
    public int deleteBoard(@RequestBody BoardFileDTO dto) {
        return boardService.deleteBoard((dto));
    }

    @GetMapping("/board/list")
    public BoardPagingDTO getBoardList(Pagination pagination){
        return boardService.getBoardList(pagination);
    }


    @GetMapping("/board/{bno}")
    public BoardFileDTO getBoardDetail(@PathVariable int bno) {
        BoardFileDTO boardFile = boardService.getBoardDetail(bno);
        return boardFile;
    }
}