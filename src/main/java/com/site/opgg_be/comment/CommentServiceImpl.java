package com.site.opgg_be.comment;


import org.apache.ibatis.annotations.Mapper;
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
        comment.setOrder_cno(mapper.getMaxOrder(comment.getBno()) + 1); // 최대 order_cno 값을 가져와서 +1
        mapper.insertComment(comment);
        comment.setGroup_cno(comment.getCno());
        mapper.updateGroupCno(comment);
    }

    private void handleReply(CommentEntity comment) {
        comment.setDepth(comment.getDepth() + 1);
        comment.setParent_cno(comment.getCno());
        comment.setGroup_cno(comment.getGroup_cno());
        // 댓글 먼저 삽입
        mapper.insertComment(comment);
        // 삽입 후 cno 값을 기반으로 order_cno 값을 설정
        comment.setOrder_cno(mapper.getOrder_cno(comment.getCno()) + 1);
        mapper.upOrder_cno(comment); // 새로 추가될 대댓글보다 order_cno 값이 큰 모든 댓글의 order 값을 +1
    }


    @Override
    public void updateComment(CommentDTO dto) {
    }

//    @Override
//    public int deleteCommnet(CommentDTO dto) {
//        int res = mapper.deleteCommnet(dto.getGroup_cno());
//        return res;
//    }

    @Override
    public List<CommentDTO> getCommenList(int bno) {
        return mapper.getCommenList(bno);
    }
}
