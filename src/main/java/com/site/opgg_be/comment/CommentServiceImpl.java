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
        CommentEntity commen = dto.toComment(); // DTO에서 Entity로
        return mapper.insertComment(commen);
    }

    @Override
    public void updateComment(CommentDTO dto) {

    }
//    @Override
//    public int deleteCommnet(CommentDTO dto) {
////        int res = mapper.deleteCommnet(dto.getGroup_cno());
////        return res;
//    }

    @Override
    public List<CommentDTO> getCommenList() {
        return mapper.getCommenList();
    }
}
