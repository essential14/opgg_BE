package com.site.opgg_be.comment;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("INSERT INTO comments(bno, Group_cno, parent_cno, content, depth, id, comment_date, order_cno) VALUES(#{bno}, #{group_cno}, #{parent_cno}, #{content}, #{depth}, #{id}, NOW(), #{order_cno})")
    @Options(useGeneratedKeys = true, keyProperty = "cno")
    public int insertComment(CommentEntity commen);

    @Select("SELECT IFNULL(MAX(order_cno), 0) FROM comments WHERE bno = #{bno}")
    public int getMaxOrder(int bno);

    @Select("SELECT order_cno FROM comments WHERE cno = #{cno}")
    public int getOrder_cno(int cno);

    @Update("UPDATE comments SET order_cno = order_cno + 1 WHERE bno = #{bno} AND order_cno >= #{order_cno}")
    public int upOrder_cno(CommentEntity comment);

    @Update("")
    public void updateComment(CommentDTO dto);

    @Update("UPDATE comments SET group_cno = #{group_cno} WHERE parent_cno = #{parent_cno}")
    void updateGroupCno(CommentEntity comment);

    @Delete("")
    public int deleteCommnet(CommentDTO dto);


    @Select("SELECT * FROM comments WHERE bno = #{bno} ORDER BY group_cno ASC, order_cno ASC, comment_date ASC;")
    public List<CommentDTO> getCommenList(int bno);

}
