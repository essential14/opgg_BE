package com.site.opgg_be.board;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Select("select * from board order by bno desc")
    public List<BoardFileDTO> getBoardList();
    @Insert("INSERT INTO board (bno,title, id, content, viewcount, board_date) VALUES (null, #{title}, #{id}, #{content}, #{viewcount}, NOW())")
    public void insertBoard(BoardEntity board);
    @Insert("INSERT INTO files (bno, org_file, stored_file) VALUES (#{bno}, #{orgFile}, #{storedFile})")
    public void insertFile(FileEntity file);

    @Select("select * from board where bno=#{bno}")
    BoardEntity getBoard(int bno);

    @Select("select * from files where bno=#{bno}")
    FileEntity getFile(int bno);

    @Update("Update * from viewcount where bno=#{bno}")
    void updateViewCount(int bno);
}