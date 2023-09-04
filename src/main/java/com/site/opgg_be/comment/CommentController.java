package com.site.opgg_be.comment;

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

    @GetMapping("/comment/{bno}")
    public List<CommentDTO> getCommentList(@PathVariable int bno) {
        List<CommentDTO> commen = commentService.getCommenList(bno);
        return commen;
    }

    @PostMapping("/comment/update")
    public int updateComment(@RequestBody CommentDTO dto) {
        return commentService.updateComment(dto);
    }

    @PostMapping("/comment/delete")
    public int deleteComment(@RequestBody CommentDTO dto) {
        return commentService.deleteComment(dto);
    }
}
