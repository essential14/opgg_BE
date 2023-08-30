package com.site.opgg_be.comment;

import java.util.List;

public interface CommentService {

    public int insertComment(CommentDTO dto);

    public void updateComment(CommentDTO dto);

//    public int deleteCommnet(CommentDTO dto);

    public List<CommentDTO> getCommenList(int bno);
}
