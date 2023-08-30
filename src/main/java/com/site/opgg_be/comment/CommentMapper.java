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

    @Update("UPDATE comments SET group_cno = #{group_cno} WHERE cno = #{cno}")
    void updateGroupCno(CommentEntity comment);
    
    @Delete("")
    public int deleteCommnet(CommentDTO dto);


    @Select("SELECT * FROM comments WHERE bno = #{bno} ORDER BY group_cno ASC, depth ASC, comment_date DESC;")
    public List<CommentDTO> getCommenList(int bno);

}
