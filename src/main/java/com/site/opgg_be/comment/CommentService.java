package com.site.opgg_be.comment;

import java.util.List;

public interface CommentService {

    public int insertComment(CommentDTO dto);

    public int updateComment(CommentDTO dto);

    public int deleteComment(CommentDTO dto);

    public List<CommentDTO> getCommenList(int bno);
}
