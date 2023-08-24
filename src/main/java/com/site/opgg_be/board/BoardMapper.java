package com.site.opgg_be.board;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Select("select * from board order by bno desc")
    public List<BoardFileDTO> getBoardList();

    @Options (useGeneratedKeys = true, keyProperty = "fno")
    @Insert("INSERT INTO files (org_file, stored_file, file_group) VALUES (#{org_file}, #{stored_file}, null)")
    public void insertFile(FileEntity files);

    @Insert("INSERT INTO board (fno, title, id, content, viewcount, created_date, updated_date) VALUES (#{fno}, #{title}, #{id}, #{content}, #{viewcount}, NOW(),NOW())")
    public void insertBoard(BoardEntity board);

    @Update("update board set title=#{title}, content=#{content}, updated_date=NOW() where bno=#{bno}")
    public void updateBoard(BoardEntity board);

    @Update("update files set org_file=#{org_file},stored_file=#{stored_file} where fno=#{fno}")
    public void updateFile(FileEntity files);

    @Select("select * from board where bno=#{bno}")
    BoardEntity getBoard(int bno);

    @Select("select * from files where fno=#{fno}")
    FileEntity getFile(int fno);

    @Update("update board set viewcount=viewcount+1 where bno=#{bno}")
    void updateViewCount(int bno);
}