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
        comment.setOrder_cno(0); //
        mapper.insertComment(comment);
        comment.setGroup_cno(comment.getCno()); // group_cno를 새로 생성된 cno로 설정
        mapper.updateGroupCno(comment);
    }


    private void handleReply(CommentEntity comment) {
        comment.setDepth(comment.getDepth() + 1);

        // 부모 댓글의 바로 다음 order_cno 값을 찾기
        int nextOrderCno = mapper.getOrderCno(comment.getCno()) + 1;

        // nextOrderCno 값과 동일하거나 큰 order_cno를 가진 모든 댓글의 order_cno 값을 1씩 증가
        mapper.upOrderCno(comment.getBno(), nextOrderCno);

        comment.setOrder_cno(nextOrderCno);
        comment.setParent_cno(comment.getCno());
        comment.setGroup_cno(comment.getGroup_cno());
        mapper.insertComment(comment);
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
