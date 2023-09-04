package com.site.opgg_be.comment;


import org.springframework.stereotype.Service;

import java.util.List;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

    private final CommentMapper mapper;

    public CommentServiceImpl(CommentMapper mapper) {
        this.mapper = mapper;
    }


    public int insertComment(CommentDTO dto) {
        CommentEntity comment = dto.toComment();

        if (comment.getCno() != 0) { // 대댓글
            handleReply(comment);
        } else { // 댓글
            handleComment(comment);
        }
        return 1;
    }

    private void handleComment(CommentEntity comment) {
        comment.setDepth(0);
        comment.setParent_cno(0);
        comment.setOrder_cno(0); //
        mapper.insertComment(comment);
        comment.setGroup_cno(comment.getCno()); // group_cno를 새로 생성된 cno로 설정
        mapper.updateGroupCno(comment);
    }


    private void handleReply(CommentEntity comment) {
        comment.setDepth(comment.getDepth() + 1);
        comment.setOrder_cno(comment.getOrder_cno()+1);
        comment.setParent_cno(comment.getCno());
        comment.setGroup_cno(comment.getGroup_cno());
        mapper.insertComment(comment);
    }



    @Override
    public int updateComment(CommentDTO dto) {
        CommentEntity comment = dto.toComment();
        comment.setCno(comment.getCno());
        comment.setContent(comment.getContent());
       return mapper.updateComment(comment);
    }

    @Override
    public int deleteComment(CommentDTO dto) {
        CommentEntity comment = dto.toComment();
        comment.setCno(comment.getCno());
        return mapper.deleteComment(comment);

    }

    @Override
    public List<CommentDTO> getCommenList(int bno) {
        return mapper.getCommenList(bno);
    }
}
