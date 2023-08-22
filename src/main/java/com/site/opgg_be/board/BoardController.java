package com.site.opgg_be.board;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @Value("${spring.servlet.multipart.location}") // 설정 파일 정보 읽어올 때 사용
    String uploadDir;


    @GetMapping("/board/list")
    public List<BoardFileDTO> getBoardList(){
        List<BoardFileDTO> boardlist = boardService.getBoardList();
        return boardlist;
    }

    @PostMapping("/board/write")
    public void insertBoard(BoardFileDTO dto) throws IOException {
        boardService.insertBoard(dto);
    }

    @GetMapping("/board/detail")
    public BoardFileDTO getBoardDetail(int bno) {
        BoardFileDTO boardFile = boardService.getBoardDetail(bno);
        return boardFile;
    }
}
