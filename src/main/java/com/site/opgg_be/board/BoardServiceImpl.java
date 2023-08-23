package com.site.opgg_be.board;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

    @Value("${spring.servlet.multipart.location}") // 설정 파일 정보 읽어올 때 사용
    String uploadDir;
    private final BoardMapper mapper;

    public BoardServiceImpl(BoardMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<BoardFileDTO> getBoardList() {
        return mapper.getBoardList();
    }

    @Override
    public void insertBoard(BoardFileDTO dto) throws IOException {
        BoardEntity board = dto.toBoard();
        mapper.insertBoard(board);

        MultipartFile[] org_file = dto.getOrg_file();
        if (org_file.length != 0) {
            for (MultipartFile file : org_file) {
                if (!file.isEmpty()) {
                    File stored_file = new File(UUID.randomUUID().toString().replaceAll("-", "") + ".jpg");
                    file.transferTo(stored_file); // 업로드 시켜줌


                }
            }
        }
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