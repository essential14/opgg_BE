package com.site.opgg_be.comment;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("INSERT INTO comments(bno, Group_cno, parent_cno, content, depth, id, comment_date) VALUES(#{bno}, #{Group_cno}, #{parent_cno}, #{content}, #{depth}, #{id}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "cno")
    public int insertComment(CommentEntity commen);

    @Update("")
    public void updateComment(CommentDTO dto);

    @Delete("")
    public int deleteCommnet(CommentDTO dto);


    @Select("select * form comments")
    public List<CommentDTO> getCommenList();
}
