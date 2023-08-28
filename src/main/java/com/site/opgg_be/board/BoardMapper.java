package com.site.opgg_be.board;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Options (useGeneratedKeys = true, keyProperty = "fno")
    @Insert("INSERT INTO files (org_file, stored_file, group_file) VALUES (#{org_file}, #{stored_file}, #{group_file})")
    public void insertFile(FileEntity files);

    @Insert("INSERT INTO board (fno, title, id, content, viewcount, created_date, updated_date, group_file) VALUES (#{fno}, #{title}, #{id}, #{content}, #{viewcount}, NOW(),NOW(), #{group_file})")
    public void insertBoard(BoardEntity board);

    @Update("update board set title=#{title}, content=#{content}, fno=#{fno}, group_file=#{group_file}, updated_date=NOW() where bno=#{bno}")
    public void updateBoard(BoardEntity board);

    @Select("select * from board order by bno desc")
    public List<BoardFileDTO> getBoardList();

    @Select("select * from files where group_file=#{group_file}") // group 으로 조회
    List<FileEntity> getFilesByGroup(int group_file);

    @Select("SELECT COALESCE(MAX(group_file), 0) FROM files")
    public int getMaxGroupFile();

    @Delete("delete from files where group_file=#{group_file}")
    public int delFileGroup(int group_file);

    @Delete("delete from board where bno=#{bno}")
    public int delBoard(int bno);

    @Select("select * from board where bno=#{bno}")
    public BoardEntity getBoard(int bno);

    @Select("SELECT group_file FROM board WHERE bno = #{bno}")
    int getGroupFileByBno(int bno);

    @Update("update board set viewcount=viewcount+1 where bno=#{bno}")
    void updateViewCount(int bno);
}