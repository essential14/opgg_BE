package com.site.opgg_be.board;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public void insertBoard(BoardFileDTO dto, MultipartFile[] uploadfiles) { // 글 쓰기
        try {
            int group_file = 0; // 초기 그룹 설정
            BoardEntity board = dto.toBoard();
            // 파일 업로드 처리
            if (uploadfiles != null && uploadfiles.length > 0) {
                group_file = createNewFileGroup(); // 새로운 그룹 생성
                for (MultipartFile file : uploadfiles) {
                    if (!file.isEmpty()) {
                        String originalFilename = file.getOriginalFilename();
                        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
                        String storedFilename = UUID.randomUUID().toString().replaceAll("-", "") + fileExtension; // UUID와 확장자 결합
                        File storedFile = new File(storedFilename);
                        file.transferTo(storedFile);

                        FileEntity files = dto.toFile();
                        files.setOrg_file(originalFilename); // 원본 파일 이름
                        files.setStored_file(storedFilename); // 저장된 파일 이름
                        files.setGroup_file(group_file); // 파일 그룹 설정
                        mapper.insertFile(files);
                    }
                }
            }
            // 게시글 입력
            board.setGroup_file(group_file);
            mapper.insertBoard(board);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    @Transactional
    public void updateBoard(BoardFileDTO dto, MultipartFile[] updatefiles) { // 글 수정
        try {
            int oldGroupFile = mapper.getGroupFileByBno(dto.getBno());
            int group_file = (dto.getGroup_file() == 0) ? createNewFileGroup() : oldGroupFile;
            BoardEntity board = dto.toBoard();

            // 파일 업로드 처리
            if (updatefiles != null && updatefiles.length > 0) {
                for (MultipartFile file : updatefiles) {
                    if (!file.isEmpty()) {
                        String originalFilename = file.getOriginalFilename();
                        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".")); // 파일 확장자 가져오기
                        String storedFilename = UUID.randomUUID().toString().replaceAll("-", "") + fileExtension; // UUID와 확장자 결합
                        File storedFile = new File(storedFilename);
                        file.transferTo(storedFile);

                        FileEntity files = dto.toFile();
                        files.setOrg_file(originalFilename); // 원본 파일 이름
                        files.setStored_file(storedFilename); // 저장된 파일 이름
                        files.setGroup_file(group_file); // 설정된 group_file 값
                        mapper.insertFile(files); // 파일 정보 삽입

                    }
                }
            }
            // 게시글 업데이트
            board.setGroup_file(group_file); // 설정된 group_file 값
            mapper.updateBoard(board);
            // 이전 파일 정보와 실제 파일 삭제
            if (oldGroupFile != 0 && oldGroupFile != group_file) { // 수정된 파일 그룹이 있을 경우
                List<FileEntity> fileList = mapper.getFilesByGroup(oldGroupFile);
                for (FileEntity oldFileData : fileList) {
                    File oldFile = new File(uploadDir + "\\" + oldFileData.getStored_file());
                    if (oldFile.exists()) {
                        oldFile.delete();
                    }
                }
                mapper.delFileGroup(oldGroupFile); // 이전 파일 정보 삭제
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int createNewFileGroup() { // 그룹 번호
            Integer maxGroupFile = mapper.getMaxGroupFile();
            if(maxGroupFile == null) {
                return 1;
            } else {
                return maxGroupFile + 1;
            }
    }

    public int deleteBoard(BoardFileDTO dto) { // 글 삭제
        try {
            // 모든 파일 정보 가져 오기
            List<FileEntity> fileList = mapper.getFilesByGroup(dto.getGroup_file());

            // 각 파일 정보에 따라 서버에서 파일 삭제
            for (FileEntity fileEntity : fileList) {
                File oldFile = new File(uploadDir + "\\" + fileEntity.getStored_file());
                if (oldFile.exists()) {
                    oldFile.delete();
                }
            }
            // DB에서 파일 삭제
            mapper.delFileGroup(dto.getGroup_file());

            // DB에서 게시글 삭제
            mapper.delBoard(dto.getBno());
            return 1; // 성공

        } catch(Exception e) {
            e.printStackTrace();
            return 0; // 실패
        }
    }

    @Override
    public List<BoardFileDTO> getBoardList() { // 글 목록 조회
        return mapper.getBoardList();
    }

    @Override
    public BoardFileDTO getBoardDetail(int bno) { // 글 상세
        // 게시글 정보 가져오기
        BoardEntity board = mapper.getBoard(bno);
        BoardFileDTO boardFile = new BoardFileDTO();
        boardFile.fromBoard(board);

        // 파일이 있는 경우 (group_file가 0이 아닌 경우)
        if (board.getGroup_file() != 0) {
            List<FileEntity> fileList = mapper.getFilesByGroup(board.getGroup_file());

            // 파일 리스트가 있는 경우에만 DTO에 추가
            if (!fileList.isEmpty()) {
                boardFile.setFileList(fileList);
            }
        }
        // 조회수 증가
        mapper.updateViewCount(bno);

        return boardFile;
    }
}