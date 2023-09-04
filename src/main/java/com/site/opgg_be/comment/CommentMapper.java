package com.site.opgg_be.comment;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("INSERT INTO comments (bno, group_cno, parent_cno, content, depth, id, created_date, order_cno) VALUES(#{bno}, #{group_cno}, #{parent_cno}, #{content}, #{depth}, #{id}, now(), #{order_cno})")
    @Options(useGeneratedKeys = true, keyProperty = "cno", keyColumn = "cno")
    public int insertComment(CommentEntity comment);

    @Update("UPDATE comments SET group_cno = #{group_cno} WHERE cno = #{cno}")
    void updateGroupCno(CommentEntity comment);

    @Select("SELECT * FROM comments WHERE bno=#{bno} START WITH parent_cno = 0 CONNECT BY PRIOR cno = parent_cno ORDER SIBLINGS BY order_cno")
    public List<CommentDTO> getCommenList(int bno);

    @Update("UPDATE COMMENTS set CONTENT = #{content} where cno = #{cno}")
    public int updateComment(CommentEntity comment);

    @Delete("DELETE from comments where cno=#{cno}")
    public int deleteComment(CommentEntity comment);


//    @Select("SELECT order_cno FROM comments WHERE cno = #{cno}")
//    public int getOrderCno(int cno);
//
//    @Update("UPDATE comments SET order_cno = order_cno + 1 WHERE bno = #{bno} AND order_cno >= #{startOrderCno}")
//    public void upOrderCno(int bno, int startOrderCno);
//
}
