package quddaz.myblog.Comment.controller;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import quddaz.myblog.Comment.service.CommentService;

@Controller
@RequiredArgsConstructor
public class CommentController {
  private final CommentService commentService;

  @PostMapping("/board/{id}/comment")
  public String createComment(@PathVariable("id") Long id, @RequestParam("content") String commentContent) {
    commentService.createComment(id, commentContent);
    return "redirect:/board/" + id;
  }
}
