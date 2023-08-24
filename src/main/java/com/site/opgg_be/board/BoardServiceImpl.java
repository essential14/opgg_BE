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
    public void insertBoard(BoardFileDTO dto, MultipartFile[] uploadfiles) throws IOException {
        if (uploadfiles != null && uploadfiles.length > 0) {
            for (MultipartFile file : uploadfiles) {
                if (!file.isEmpty()) {
                    String originalFilename = file.getOriginalFilename();
                    String storedFilename = (UUID.randomUUID().toString().replaceAll("-", "") + ".jpg");
                    File storedFile = new File(storedFilename);
                    file.transferTo(storedFile);

                    FileEntity files = dto.toFile();
                    files.setOrg_file(originalFilename); // 원본 파일 이름
                    files.setStored_file(storedFilename); // 저장된 파일 이름
                    mapper.insertFile(files); // 여기서 fno 값이 자동으로 설정

                    int fno = files.getFno(); // 새롭게 생성된 fno 값을 가져오기
                    BoardEntity board = dto.toBoard();
                    board.setFno(fno);
                    mapper.insertBoard(board);
                    return;
                }
            }
        }
        BoardEntity board = dto.toBoard();
        board.setFno(0);
        mapper.insertBoard(board);
    }

    @Override
    public void updateBoard(BoardFileDTO dto, MultipartFile[] updatefiles) throws IOException {
        if (updatefiles != null && updatefiles.length > 0) {
            for (MultipartFile file : updatefiles) {
                if (!file.isEmpty()) {
                    String originalFilename = file.getOriginalFilename();
                    String storedFilename = (UUID.randomUUID().toString().replaceAll("-", "") + ".jpg");
                    File storedFile = new File(storedFilename);
                    file.transferTo(storedFile);

                    FileEntity files = dto.toFile();
                    files.setOrg_file(originalFilename); // 원본 파일 이름
                    files.setStored_file(storedFilename); // 저장된 파일 이름
                    mapper.insertFile(files); // 여기서 fno 값이 자동으로 설정

                    int fno = files.getFno(); // 새롭게 생성된 fno 값을 가져오기
                    BoardEntity board = dto.toBoard();
                    board.setFno(fno);
                    mapper.insertBoard(board);
                    return;
                }
            }
        }
        int fno = 0;
        BoardEntity board = dto.toBoard();
        board.setFno(fno);
        mapper.insertBoard(board);
    }


    @Override
    public BoardFileDTO getBoardDetail(int bno) {
        BoardEntity board = mapper.getBoard(bno);
        BoardFileDTO boardFile = new BoardFileDTO();
        boardFile.fromBoard(board);

        // 파일이 있는 경우 (fno 가 0이 아닌 경우)
        if (board.getFno() != 0) {
            FileEntity files = mapper.getFile(bno);
            boardFile.fromFile(files);
        }

        mapper.updateViewCount(bno);
        return boardFile;
    }
}