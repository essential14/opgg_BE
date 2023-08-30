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


    @Override
    public int insertComment(CommentDTO dto) {
        CommentEntity comment = dto.toComment();

        if(comment.getCno() != 0) { // cno가 존재하면 대댓글.
            handleReplyComment(comment);
        } else { // cno가 없으면 원본 댓글.
            handleMainComment(comment);
        }
        return 1;
    }

    private void handleMainComment(CommentEntity comment) {
        comment.setDepth(0);
        comment.setParent_cno(0);
        mapper.insertComment(comment);
        comment.setGroup_cno(comment.getCno()); // group_cno를 생성된 cno로 설정
        mapper.updateGroupCno(comment);
    }

    private void handleReplyComment(CommentEntity comment) {
        comment.setParent_cno(comment.getCno());
        comment.setDepth(comment.getDepth() + 1);
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
