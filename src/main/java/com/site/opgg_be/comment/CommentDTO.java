package com.site.opgg_be.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {

    private int cno;
    private int bno;
    private int parent_cno;
    private String content;
    private String id;
    private int depth;
    private String comment_date;
    private int group_cno;
    private int order_cno;

    public CommentEntity toComment() { // CommentEntity 에 Data 넣기
        CommentEntity comment = new CommentEntity();
        comment.setCno(this.getCno());
        comment.setBno(this.getBno());
        comment.setGroup_cno(this.getGroup_cno());
        comment.setParent_cno(this.getParent_cno());
        comment.setContent(this.getContent());
        comment.setId(this.getId());
        comment.setDepth(this.getDepth());
        comment.setComment_date(this.getComment_date());
        comment.setOrder_cno(this.getOrder_cno());
        return comment;
    }
    public void fromComment(CommentEntity comment) { // CommentEntity 에서 Data 꺼내기
        this.setCno(comment.getCno());
        this.setBno(comment.getBno());
        this.setGroup_cno(comment.getGroup_cno());
        this.setParent_cno(comment.getParent_cno());
        this.setContent(comment.getContent());
        this.setId(comment.getId());
        this.setDepth(comment.getDepth());
        this.setComment_date(comment.getComment_date());
        this.setOrder_cno(comment.getOrder_cno());
    }
}
