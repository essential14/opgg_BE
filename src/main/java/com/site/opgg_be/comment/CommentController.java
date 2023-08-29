package com.site.opgg_be.comment;

import com.site.opgg_be.board.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    private CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment/write")
    public int insertComment(@RequestBody CommentDTO dto) {
        return commentService.insertComment(dto);
    }

    public void updateComment(CommentDTO dto) {
    }

    public int deleteCommnet(CommentDTO dto) {
        return 0;
    }

    @GetMapping("/comment/list")
    public List<CommentDTO> getCommentList() {
      List<CommentDTO> commen = commentService.getCommenList();
        return commen;
    }
}
