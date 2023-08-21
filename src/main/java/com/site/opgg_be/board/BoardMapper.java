package com.site.opgg_be.board;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardMapper {
//    @Select("select * from board where bno=#{bno}")
//    public BoardDTO getBoardDetail(BoardDTO dto);

    @Select("select * from board")
    public List<BoardDTO> getBoardList();

//    @Insert("INSERT INTO board(title, writer, content, viewcount, board_date) values (#{title},#{writer},#{content},#{viewcount}, NOW())")
//    public int insertBoard(BoardDTO dto);
}